import java.util.Stack;

public class Hamiltonian 
{
	public static void main(String[] args)
	{
		Hamiltonian h = new Hamiltonian();
		int[][] g = new int[][]{{0,1,1,1,0,0},
								{1,0,1,0,1,0},
								{1,1,0,1,1,0},
								{1,0,1,0,1,1},
								{0,1,1,1,0,1},
								{0,0,0,1,1,0}};
		
		Stack<Integer> stack = new Stack<>();
		h.hamiltonianR(stack, g);
	}
	
	
	/**
	 * Uses backtracking to attempt to find a hamiltonian circuit
	 * in the given graph recursively
	 * 
	 * @param matrix adjacency matrix of graph, 
	 * <br>matrix[i][j] = 0 means no edge from i to j
	 * <br>matrix[i][j] = 1 means there is an edge from i to j
	 */
	public boolean hamiltonianR(Stack<Integer> stack, int[][] matrix)
	{
		if(stack.isEmpty())
			stack.push(0);
		
		//base case solved
		if(stack.size() == matrix.length + 1)
		{
				printSolution(stack);
				return true;
		}
		
		//check all neighbors
		for(int i = 0; i < matrix.length; i++)
		{
			if(matrix[stack.peek()][i] == 1)
			{
				stack.push(i);
				if(checkFeasible(stack, matrix.length) && hamiltonianR(stack, matrix))
					return true;
				stack.pop();
			}
		}
		
		//all neighbors checked and no solution found
		if(stack.size() == 1)
			System.out.println("No Hamiltonian circuit found");
		
		return false;

	}

	private void printSolution(Stack<Integer> stack) 
	{
		System.out.println("Hamiltonian circuit found:");
		for(int node : stack)
		{
			switch(node)
			{
				case 0: 
					System.out.print("a ");
					break;
				case 1:
					System.out.print("b ");
					break;
				case 2:
					System.out.print("c ");
					break;
				case 3:
					System.out.print("d ");
					break;
				case 4:
					System.out.print("e ");
					break;
				case 5:
					System.out.print("f ");
					break;
			}
		}
		System.out.println();
	}

	private boolean checkFeasible(Stack<Integer> stack, int maxSize) 
	{
		int[] visited = new int[maxSize];
		
		Integer[] stackNodes = stack.toArray(new Integer[0]);
		
		for(int i = 0; i < stackNodes.length; i++)
		{
			int node = stackNodes[i];
			if(visited[node] == 0)
				visited[node]++;
			else if(visited[node] == 1 
					&& (i != maxSize 
					|| node != stackNodes[0]))
				return false;
		}
		
		return true;
	}
	
}
