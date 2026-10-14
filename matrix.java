import java.util.Scanner;

public class matrix
{
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter row and column of matix A : ");
        int r1 = sc.nextInt(), c1 = sc.nextInt();
        int A[][] = new int[r1][c1];
        
        System.out.println("Enter matrix A : ");
        for(int i = 0; i < r1; i++)
        {
        for(int j = 0; j < c1; j++)
        {
           A[i][j] = sc.nextInt();
        }
    }
        System.out.println("Enter rows and column of matrix B : ");
        int r2 = sc.nextInt(), c2 = sc.nextInt();
        int B[][] = new int[r2][c2];

        System.out.println("Enter matrix B : ");
        for(int i = 0; i < r2; i++)
        {    
        for(int j = 0; j < c2; j++)
        {   
        B[i][j] = sc.nextInt();
        }
    }

        if(r1 == r2 && c1 == c2)
        {
            System.out.println("\n Substraction : ");
            for(int i = 0; i < r1; i++)
            {
                for(int j = 0; j < c2; j++){
                    System.out.print(A[i][j] - B[i][j] + " ");
                }
                System.out.println();
            }
        }

        if(r1 == r2 && c1 == c2)
        {
            System.out.println("\n Addition : ");
            for(int i = 0; i < r1; i++)
            {
                for(int j = 0; j < c2; j++){
                    System.out.print(A[i][j] + B[i][j] + " ");
                }
                System.out.println();
            }
        }


        if(c1 == r2){
            int c[][] = new int[r1][c2];
            System.out.println("\n Multiplication : ");
            for(int i = 0; i < r1; i++)
            {
                for(int j = 0; j < c2; j++)
                {
                    for(int k = 0; k < c1; k++)
                        c[i][j] = c[i][j] + A[i][k] * B[k][j];
                    System.out.print(c[i][j] + " ");
                }
                System.out.println();
            }
        }

        System.out.println("\n Transpose of A : ");
        for(int i = 0; i < c1; i++)
        {
            for(int j = 0; j < r1; j++)
                System.out.print(A[j][i] + " ");
            System.out.println();
            
        }

         System.out.println("\n Transpose of B : ");
        for(int i = 0; i < c2; i++)
        {
            for(int j = 0; j < r2; j++)
                System.out.print(B[j][i] + " ");
            System.out.println();
        }
        System.out.println("Row wise Sum : ");
        for(int i = 0; i < r1; i++)
        {
            int sum = 0;
            for(int j = 0; j < c1; j++){
                sum = sum + A[i][j]; 
            }
            System.out.println("Row " +(i+1)+ " = " +sum);
        }
        System.out.println("Column wise Sum : ");
        for(int j = 0; j < c1; j++)
        {
            int sum = 0;
            for(int i = 0; i < r1; i++){
                sum = sum + A[i][j]; 
            }
            System.out.println("Column " +(j+1)+ " = " +sum);
        }
        sc.close();    
    }
}
