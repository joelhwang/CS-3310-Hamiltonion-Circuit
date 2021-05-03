package project;

public class GenerateGraph 
{
	public static void main(String[] args)
	{
		generate(5);
	}
	
	public static void generate(int size)
	{
        int[][] x = new int[size][size];
        for(int i = 0; i < size - 1; i++)
        {
            for(int j = i + 1; j < size; j++)
            {
                double r = Math.random();
                if(r < 0.6)
                {
                   x[i][j] = 1;
                   x[j][i] = 1;
                }
            }
        }
        
        System.out.println(size);
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
               System.out.print(x[i][j]);
               if(j < size - 1)
                   System.out.print(",");
            }
            System.out.println();
        }
	}

}
