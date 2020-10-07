package com.snowflake.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * https://leetcode.com/problems/subdomain-visit-count/
 * 
 * @author arun
 */

public class SubdomainVisitCount {

	private static final String SEPARATOR = "\\s+";
	private static final String DOT_REGEX = "\\.";
	private static final String OUTPUT_SEPARATOR = " ";


	public static void main(String[] args) {
		System.out.println(subdomainVisits(new String[] { "9001 discuss.leetcode.com" }));
		System.out.println(subdomainVisits(
				new String[] { "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org" }));

		Solution solution = new Solution();
		System.out.println(solution.subdomainVisits(
				new String[] { "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org" }));

	}

	private static List<String> subdomainVisits(String[] cpdomains) {
		Map<String, Integer> counts = new HashMap<>();
		for (String domain : cpdomains) {
			String[] cpinfo = domain.split(SEPARATOR);
			int count = Integer.valueOf(cpinfo[0]);

			counts.put(cpinfo[1], counts.getOrDefault(cpinfo[1], 0) + count);
			String[] frags = cpinfo[1].split(DOT_REGEX, 2);
			while (frags.length > 1) {
				counts.put(frags[1], counts.getOrDefault(frags[1], 0) + count);
				frags = frags[1].split(DOT_REGEX, 2);
			}
		}

		List<String> ans = new ArrayList<>();
		for (Entry<String, Integer> entry : counts.entrySet())
			ans.add(String.join(" ", String.valueOf(entry.getValue()), entry.getKey()));
		return ans;
	}
	
	/**
	 * Trienode implementation
	 * 
	 * @author Arun MS
	 *
	 */
	
	private static class Solution {

		private TrieNode root = new TrieNode();

		public List<String> subdomainVisits(String[] cpdomains) {
			// Build trie
			for (String domain : cpdomains) {
				String[] cpinfo = domain.split(SEPARATOR);
				int count = Integer.valueOf(cpinfo[0]);
				addDomain(root, cpinfo[1], count);
			}

			// Prepare answer
			List<String> answer = new ArrayList<>();
			buildOutput(root, answer);

			return answer;
		}
		
		private TrieNode addDomain(TrieNode currentNode, String domain, int visitCount) {
			String[] frags = domain.split(DOT_REGEX, 2);
			if(frags.length > 1) {
				currentNode = addDomain(currentNode, frags[1], visitCount);
			}
			return addTrieNode(currentNode, frags[0], domain, visitCount);
		}

		private void buildOutput(TrieNode cursor, List<String> collector) {
			
			for (Entry<String, TrieNode> childEntry : cursor.children.entrySet()) {
				buildOutput(childEntry.getValue(), collector);

				StringBuilder entrySb = new StringBuilder(Integer.toString(childEntry.getValue().visitCount));
				entrySb.append(OUTPUT_SEPARATOR);
				entrySb.append(childEntry.getValue().domainName);
				collector.add(entrySb.toString());
			}
		}

		// Builds the trie
		private TrieNode addTrieNode(TrieNode cursor, String trieNodeName, String domainName, int visitCount) {
			cursor.children.putIfAbsent(trieNodeName, new TrieNode(domainName));
			cursor = cursor.children.get(trieNodeName);
			cursor.visitCount += visitCount;
			return cursor;
		}
	}

	private static class TrieNode {
		private Map<String, TrieNode> children = new HashMap<>();
		private int visitCount = 0;
		private String domainName;

		public TrieNode() {
		}

		public TrieNode(String domainName) {
			this.domainName = domainName;
		}
	}
}
