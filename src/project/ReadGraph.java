package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadGraph 
{
	
	/**
	 * Reads a graph and turns it into an adjacency matrix
	 * The graph should have a single integer on the first
	 * line representing the number of vertices = n, n should be
	 * greater than or equal to 3. There should be n subsequent lines, 
	 * with n values in each line, separated by a comma. A value of 0 
	 * represents no edge and a value of 1 represents an edge from vertex
	 * row index to vertex column index. A vertex should not have an edge 
	 * to itself (no loops).
	 * 
	 * @param filePath : the path to the graph file
	 * @return a 2d array representing the adjacency matrix of the graph
	 */
	public static int[][] readGraph(String filePath)
	{
		Scanner sc = null;
		try 
		{
			//attempt to open file and pass it to scanner
			sc = new Scanner(new File(filePath));
			
			//first line should have a single integer = n = # of vertices
			int numVertices = sc.nextInt();
			sc.nextLine();
			
			//create adjacency matrix
			int[][] adjMatrix = new int[numVertices][numVertices];
			
			//loop through each of the n subsequent lines
			for(int i = 0; i < numVertices; i++)
			{
				//read the entire line and split it around each comma
				String line = sc.nextLine();
				String[] values = line.split(",");
			
				//loop through each of the n values
				for(int j = 0; j < numVertices; j++)
				{
					//convert the value into an int
					int value = Integer.valueOf(values[j].trim());
					
					//only values of 0 or 1 are accepted
					//and there can be no loops
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
			System.out.println("Error: file not found!");
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
		
		//if there was an error reading the file, return null
		return null;
	}

}
