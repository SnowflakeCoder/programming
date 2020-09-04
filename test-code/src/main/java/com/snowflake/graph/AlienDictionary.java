package com.snowflake.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AlienDictionary {

	/*
	
	// Class to represent a graph 
	private static class Graph 
	{ 

		// An array representing the graph as an adjacency list 
		private final LinkedList<Integer>[] adjacencyList; 

		Graph(int nVertices) 
		{ 
			adjacencyList = new LinkedList[nVertices]; 
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) 
			{ 
				adjacencyList[vertexIndex] = new LinkedList<>(); 
			} 
		} 

		// function to add an edge to graph 
		void addEdge(int startVertex, int endVertex) 
		{ 
			adjacencyList[startVertex].add(endVertex); 
		} 

		private int getNoOfVertices() 
		{ 
			return adjacencyList.length; 
		} 

		// A recursive function used by topologicalSort 
		private void topologicalSortUtil(int currentVertex, boolean[] visited, 
										Stack<Integer> stack) 
		{ 
			// Mark the current node as visited. 
			visited[currentVertex] = true; 

			// Recur for all the vertices adjacent to this vertex 
			for (int adjacentVertex : adjacencyList[currentVertex]) 
			{ 
				if (!visited[adjacentVertex]) 
				{ 
					topologicalSortUtil(adjacentVertex, visited, stack); 
				} 
			} 

			// Push current vertex to stack which stores result 
			stack.push(currentVertex); 
		} 

		// prints a Topological Sort of the complete graph 
		void topologicalSort() 
		{ 
			Stack<Integer> stack = new Stack<>(); 

			// Mark all the vertices as not visited 
			boolean[] visited = new boolean[getNoOfVertices()]; 
			for (int i = 0; i < getNoOfVertices(); i++) 
			{ 
				visited[i] = false; 
			} 

			// Call the recursive helper function to store Topological 
			// Sort starting from all vertices one by one 
			for (int i = 0; i < getNoOfVertices(); i++) 
			{ 
				if (!visited[i]) 
				{ 
					topologicalSortUtil(i, visited, stack); 
				} 
			} 

			// Print contents of stack 
			while (!stack.isEmpty()) 
			{ 
				System.out.print((char)('a' + stack.pop()) + " "); 
			} 
		} 
	} 


	
	
	 
	// This function fidns and prints order 
	// of characer from a sorted array of words. 
	// alpha is number of possible alphabets 
	// starting from 'a'. For simplicity, this 
	// function is written in a way that only 
	// first 'alpha' characters can be there 
	// in words array. For example if alpha 
	// is 7, then words[] should contain words 
	// having only 'a', 'b','c' 'd', 'e', 'f', 'g' 
	private static void printOrder(String[] words, int alpha) 
	{ 
		// Create a graph with 'aplha' edges 
		Graph graph = new Graph(alpha); 

		for (int i = 0; i < words.length - 1; i++) 
		{ 
			// Take the current two words and find the first mismatching 
			// character 
			String word1 = words[i]; 
			String word2 = words[i+1]; 
			for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) 
			{ 
				// If we find a mismatching character, then add an edge 
				// from character of word1 to that of word2 
				if (word1.charAt(j) != word2.charAt(j)) 
				{ 
					graph.addEdge(word1.charAt(j) - 'a', word2.charAt(j)- 'a'); 
					break; 
				} 
			} 
		} 

		// Print topological sort of the above created graph 
		graph.topologicalSort(); 
	} 

	// Driver program to test above functions 
	public static void main(String[] args) 
	{ 
		String[] words = {"caa", "aaa", "aab"}; 
		printOrder(words, 3); 
		
		printOrder(new String[]{ "caa", "baa", "aaa", "aab" }, 4);
		
	} 	

	*/
	
	
	
	private static class Graph
	{
	    List<List<Integer>> adj = new ArrayList<>();
	    
	    Graph(int n)
	    {
	        for(int i=0;i<n;i++)
	        {
	            adj.add(i,new ArrayList<Integer>());
	        }
	    }
	        
	    void addEdge(int from, int to) 
	    { 
	        adj.get(from).add(to); 
	    }
	    
	    int getNoOfVertices() 
	    { 
	        return adj.size(); 
	    } 
	    
	    void topologicalSortUtil(int curr, boolean[] visited, Stack<Integer> st) 
	    { 
	        visited[curr] = true; 
	  
	        for (int i= 0;i< adj.get(curr).size();i++) 
	        { 
	            int x = adj.get(curr).get(i);
	            if(!visited[x]) 
	            { 
	                topologicalSortUtil(x, visited, st); 
	            } 
	        } 
	  
	        st.push(curr); 
	    } 
	  
	    public String topologicalSort() 
	    { 
	        Stack<Integer> st = new Stack<>(); 
	        int n = getNoOfVertices();
	        boolean[] visited = new boolean[n]; 
	        for(int i = 0;i < n;i++) 
	            visited[i] = false; 
	  
	        for(int i = 0;i < n;i++)
	            if (!visited[i]) 
	               topologicalSortUtil(i, visited, st);
	        
	        String ans = "";
	        while (!st.isEmpty())
	            ans += (char)('a' + st.pop()); 
	        
	        adj.clear(); 
	        return ans;
	 
	    } 
	    
	}


	private static class Solution
	{
	    public static String findOrder(String[] words, int n, int k)
	    {
	        Graph g = new Graph(k);
	        // int n = words.length;
	        for (int i = 0; i < n - 1; i++) 
	        { 
	            int len = Math.min(words[i].length(), words[i+1].length());
	            for(int j = 0;j < len;j++) 
	            { 
	                if(words[i].charAt(j) != words[i+1].charAt(j)) 
	                { 
	                    g.addEdge(words[i].charAt(j) - 'a', words[i+1].charAt(j)- 'a'); 
	                    break; 
	                } 
	            } 
	        } 
	  
	        String ans = g.topologicalSort();
	        return ans;
	    }
	}
	
	
	public static void main(String[] args) {
		System.out.println(Solution.findOrder(new String[]{ "baa", "abcd", "abca", "cab", "cad" }, 5, 4));
		
//		System.out.println(Solution.findOrder(new String[]{ "caa", "aaa", "aab" }, 3, 3));
		
//		System.out.println(Solution.findOrder(new String[]{ "caa", "baa", "aaa", "aab" }, 4, 3));
		
	}
	
	
}



