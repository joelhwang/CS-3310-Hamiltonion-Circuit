package project;

import java.util.Scanner;

public class Main 
{
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args)
	{
		testProgram();
	}
	
	/**
	 * Test solution
	 */
	private static void testProgram()
	{
		System.out.println("CS 3310 Group Project: Hamiltonian Circuit Problem\n");
		
		//read user choice
		promptUser();
		int input = sc.nextInt();
		
		//if quit is selected
		if(input > 5 || input < 1)
			return;
		
		//read algorithm progress choice
		System.out.print("Display algorithm progress (1 = yes): ");
		int displayProgress = sc.nextInt();		
		sc.nextLine();
		
		//execute selected choice
		handleInput(input, displayProgress);
	}
	
	/**
	 * Prompts user run a specific example
	 */
	private static void promptUser()
	{
		System.out.println("Enter 1, 2, 3, 4, or 5 to test an example:");
		System.out.println("[1] - Example1.txt");
		System.out.println("[2] - Example2.txt");
		System.out.println("[3] - Example3.txt");
		System.out.println("[4] - Example4.txt");		
		System.out.println("[5] - CustomExample.txt");
		System.out.println("[Other Number] - Quit");
		System.out.print("Option #: ");
	}
	
	/**
	 * Tests a specific task based on input
	 * 
	 * @param input : task number
	 * @param displayProgress : whether to display algorithm progress
	 * <br>0 = no, 
	 * <br>1 = yes
	 */
	private static void handleInput(int input, int displayProgress)
	{
		//if displayProgress = 1, algorithm progress should be displayed
		boolean dP = displayProgress == 1;
		
		Hamiltonian h = new Hamiltonian();
		
		switch(input)
		{
			//Choice 1,2,3 -> Example1/2/3.txt
			case 1:
			case 2:
			case 3:
			case 4:
				String filePath = String.format("./Example%s.txt", input);
				h.testHamiltonian(filePath, dP);
				break;
			case 5:
				h.testHamiltonian("./CustomExample.txt", dP);
		}
	}

}
