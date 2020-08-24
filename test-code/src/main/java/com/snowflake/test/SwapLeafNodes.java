package com.snowflake.test;

import java.util.ArrayList;
import java.util.List;

public class SwapLeafNodes {

	public static void main(String[] args) {

	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	class Solution {
		
		List<TreeNode> leafNodes = new ArrayList<>();
		
		
		public TreeNode findParentOfLeafNodes(TreeNode root) {

			if (root == null) {
				return root;
			}

			TreeNode temp = root.right;
			root.right = invertTree(root.left);
			root.left = invertTree(temp);
			return root;
		}
	}

}
