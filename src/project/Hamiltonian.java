package project;

import java.util.Stack;

public class Hamiltonian 
{
	
	public void testHamiltonian(String filePath)
	{
		int[][] graph = ReadGraph.readGraph(filePath);
		if(graph != null)
			hamiltonian(new Stack<Integer>(), graph);
	}
	
	
	/**
	 * Uses backtracking to find whether a hamiltonian 
	 * circuit exists in the given graph
	 * 
	 * @param matrix adjacency matrix of graph, 
	 * <br>matrix[i][j] = 0 means no edge from i to j
	 * <br>matrix[i][j] = 1 means there is an edge from i to j
	 */
	public boolean hamiltonian(Stack<Integer> stack, int[][] matrix)
	{
		if(stack.isEmpty())
			stack.push(0);
		
		//base case: instance solved
		if(stack.size() == matrix.length + 1)
		{
				printSolution(stack);
				return true;
		}
		
		//check all neighbors
		for(int i = 0; i < matrix.length; i++)
		{
			//if there is an edge
			if(matrix[stack.peek()][i] == 1)
			{
				stack.push(i);
				if(checkFeasible(stack, matrix.length) && hamiltonian(stack, matrix))
					return true;
				stack.pop();
			}
		}
		
		//all neighbors checked and no solution found
		if(stack.size() == 1)
			System.out.println("No Hamiltonian circuit found");
		
		return false;

	}
	
	/**
	 * 
	 * 
	 * @param stack
	 */
	private void printSolution(Stack<Integer> stack) 
	{
		System.out.println("Hamiltonian circuit found:");
		for(int node : stack)
			System.out.print(node + 1 + " ");
		System.out.println();
	}

	/**
	 * 
	 * @param stack
	 * @param maxSize
	 * @return
	 */
	private boolean checkFeasible(Stack<Integer> stack, int maxSize) 
	{
		boolean[] visited = new boolean[maxSize];
				
		for(int i = 0; i < stack.size(); i++)
		{
			int vertex = stack.get(i);
			if(!visited[vertex])
				visited[vertex] = true;
			else if(visited[vertex] 
					&& (i != maxSize 
					|| vertex != stack.get(0)))
				return false;
		}
		
		return true;
	}
	
}
