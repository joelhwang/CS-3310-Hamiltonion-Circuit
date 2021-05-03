package project;

import java.util.Stack;

public class Hamiltonian 
{
	
	/**
	 * Checks whether a hamiltonian circuit exists
	 * in the graph within the given file
	 * 
	 * @param filePath : the path to the graph file
	 * @param displayProgress : whether to display algorithm progress
	 */
	public void testHamiltonian(String filePath, boolean displayProgress)
	{
		//load graph into adjacency matrix
		int[][] graph = ReadGraph.readGraph(filePath);
		
		//if the graph was loaded successfully, check for a hamiltonian circuit
		if(graph != null)
			hamiltonian(new Stack<Integer>(), graph, displayProgress);
	}
	
	
	/**
	 * Uses backtracking to determine whether a hamiltonian circuit 
	 * exists in the given graph represented by the adjacency matrix
	 * 
	 * @param stack : list of vertices in path
	 * @param matrix : adjacency matrix of the graph, 
	 * <br>matrix[i][j] = 0 means no edge from i to j
	 * <br>matrix[i][j] = 1 means there is an edge from i to j
	 * @param displayProgress : whether to display algorithm progress
	 */
	public boolean hamiltonian(Stack<Integer> stack, int[][] matrix, boolean displayProgress)
	{
		//first iteration: stack is empty so push a vertex
		if(stack.isEmpty())
			stack.push(0);
		
		//base case: instance solved
		if(stack.size() == matrix.length + 1)
		{
			System.out.println("Hamiltonian circuit found:");
			printStack(stack);
			return true;
		}
		
		//current vertex being processed
		int currentVertex = stack.peek();
		
		//process all possible neighbors
		for(int i = 0; i < matrix.length; i++)
		{
			//if there is an edge from currentVertex to vertex i
			if(matrix[currentVertex][i] == 1)
			{
				//add vertex i to path
				stack.push(i);
				
				//display algorithm progress
				if(displayProgress)
					printStack(stack);
				
				//check if the path is feasible, if it is, process vertex i
				if(checkFeasible(stack, matrix.length) && hamiltonian(stack, matrix, displayProgress))
					return true;
				
				//if no solution was found after adding vertex i, remove vertex i from path
				stack.pop();
			}
		}
		
		//all neighbors checked and no solution found
		if(stack.size() == 1)
			System.out.println("No Hamiltonian circuit found");
		
		return false;

	}
	
	/**
	 * Prints the vertices in the order in which they were traversed
	 * 
	 * @param stack : the list of vertices
	 */
	private void printStack(Stack<Integer> stack) 
	{
		//vertices are represented by index 0 to n - 1 but correspond to vertex 1 to n
		for(int vertex : stack)
			System.out.print(vertex + 1 + " ");
		System.out.println();
	}

	/**
	 * Checks if the path of the vertices is feasible. A feasible
	 * path is one where no vertex is visited twice, unless is it
	 * is the first and last vertex and all other vertices have been visited.
	 * 
	 * @param stack : the list of vertices in the path
	 * @param totalVertices : the number of total vertices
	 * @return true if the path is feasible
	 */
	private boolean checkFeasible(Stack<Integer> stack, int totalVertices) 
	{
		//visited[someVertex] = true means someVertex has been visited before
		//initially, all values are false since no vertices have been visited
		boolean[] visited = new boolean[totalVertices];
				
		//loop through all vertices in the path
		for(int i = 0; i < stack.size(); i++)
		{
			//get a specific vertex
			int vertex = stack.get(i);
			
			//if the vertex has not been visited yet, mark it as visited
			if(!visited[vertex])
				visited[vertex] = true;
			
			/*
			 * else, vertex has been visited before
			 * 
			 * if the repeated vertex is not the first and last vertex,
			 * or it is, but not all other vertices have been
			 * visited, this path is not feasible so return false
			 */
			else if(i != totalVertices || vertex != stack.get(0))
				return false;
		}
		
		return true;
	}
	
}
