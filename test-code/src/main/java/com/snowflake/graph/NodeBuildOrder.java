package com.snowflake.graph;

/**
 *	CICD => 4.7
 * 
 *	This solution takes O(P + D) time, where P is the number of projects and 0 is the number of dependency pairs.
 *
 *	This is a topological sort algorithm.
 * 
 *	@author Arun MS
 *
 */

public class NodeBuildOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/* Find a correct build order . */
	
	Project[] findBuildOrder(String[] projects, String[][] dependencies) {
		Graph graph = buildGraph(projects, dependencies) ;
		return orderprojects(graph.getNodes());
	}
	
/* Build the graph, adding the edge (a, b) if b is dependent on a. Assumes a pair
 * is listed in "build order». The pair (a, b) i n dependencies indicates that b
 * depends on a and a must be built before b. */

	Graph buildGraph(String[] projects, String[][] dependencies) {
		Graph graph = new Graph();
		for (String project : projects) {
			graph.createNode(project);
		}

		for (String[] dependency: dependencies) {
			String first = dependency[0];
			String second = dependency[l];
			graph.addEdge(first, second);
		}

	return graph;
	}

	/* Return a list of the projects a correct build order. */
	Project[] orderProjects(ArrayList<Project> projects) {
		Project[] order = new Project[projects . size()] ;

		/* Add "roots» to the build order first. */
		int endOfList = addNonDependent(order, projects, B);

		int toBeProcessed = B;
		while (toBeProcessed < order . length) {
			Project current = order[toBeProcessed];

			/* We have a circular dependency since there are no remaining projects with zero dependencies . */
			if (current == nUll) {
	return null;
	}
	/* Remove myself as a dependency. */
	ArrayList<Project> children = current.getChildren();
	for (Project child : children) {
		child.decrementDependencies();
	}

	/* Add children that have no one depending on them. */
	endOfList = addNonDependent(order, children, endOfList);
	toBeProcessed++;
	}

		return order;
	}

	/* A helper function to insert projects with zero dependencies into the order array, starting at index offset. */
	int addNonDependent(Project[] order, ArrayList<Project> projects, int offset) {
	for (Project project : projects) {
	if (project.getNumberDependencies() == 0) {
	order[offset] = projectj
	offset++;
	}
	}
	return offset;
	}

	public class Graph {
		private ArrayList<Project> nodes = new ArrayList<Project>();
		private HashMap<String, Project> map = new HashMap<String, Project>();

		public Project getOrCreateNode(String name) {
			if (!map.containsKey(name» {
				Project node = new Project(name);
				nodes.add(node);
				map.put(name, node);
			}

			return map.get(name);
		}

		public void addEdge(String startName, String endName) {
			Project start = getOrCreateNode(startName);
			Project end = getOrCreateNode(endName);
			start.addNeighbor(end)j
		}

	public ArrayList<Project> getNodes() { return nodes; }
	}

	public class Project {
	private ArrayList<Project> children = new ArrayList<project>();
	private HashMap<String, Project> map = new HashMap<String, Project>();
	private String name;
	private int dependencies 0;

	public Project(String n) {name n;}
(rackingThe(odinglnterview.com 16th Edition 253
Solutions to Chapter 4 I Trees and Graphs
98
99 public void addNeighbor(Project node) {
100 if (!map . containsKey(node.getName())) {
101 children . add(node) ;
102 map . put(node.getName() , node);
103 node.incrementDependencies();
104 }
105 }
106
107 public void incrementDependencies() { dependencies++; }
108 public void decrementDependencies() { dependencies --; }
109
110 public String getName() { return name; }
111 public ArrayList<Project> getChildren() { return childr en; }
112 public int getNumberDependencies() { return dependencies; }
113 }



	 * 
	 * 
	 */

}
