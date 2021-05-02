package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadGraph 
{
	
	/**
	 * Reads a graph and turns it into an adjacency matrix
	 * The graph should have a single integer on the first
	 * line representing the number of vertices = n. There should
	 * be n subsequent lines, with n values in each line, separated
	 * by a comma. A value of 0 represents no edge and a value of
	 * 1 represents an edge from node row index to node column
	 * index. A node should not have an edge to itself (no self loops).
	 * 
	 * @param filePath : the path to the graph file
	 * @return a 2d array representing the adjacency matrix of the graph
	 */
	public static int[][] readGraph(String filePath)
	{
		Scanner sc = null;
		try 
		{
			sc = new Scanner(new File(filePath));
			int numVertices = sc.nextInt();
			sc.nextLine();
			
			int[][] adjMatrix = new int[numVertices][numVertices];
			
			for(int i = 0; i < numVertices; i++)
			{
				String line = sc.nextLine();
				String[] values = line.split(",");
			
				for(int j = 0; j < numVertices; j++)
				{
					int value = Integer.valueOf(values[j].trim());
					if(value == 0 || value == 1 && i != j)
						adjMatrix[i][j] = value;
					else
						throw new Exception();
				}	
			}
			return adjMatrix;
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Error: file not found");
		} catch (Exception e2) 
		{
			e2.printStackTrace();
			System.out.println("Error: invalid input in file!");
		}
		finally
		{
			if(sc != null)
				sc.close();
		}
		return null;
	}

}
