import java.util.Scanner;
public class Calculator{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char choice;

        do{
            System.out.println("Enter the number 1:");
            int num1 = sc.nextInt();
            System.out.println("Enter number 2:");
            int num2 = sc.nextInt();

            System.out.println("\n1.Addition");
            System.out.println("2.Substraction");
            System.out.println("3.Multiplication");
            System.out.println("4.Division");
            System.out.println("Enter your choice:");

            int ch = sc.nextInt();

            switch(ch){
                case 1:
                    System.out.println("Addition = " +(num1 + num2));
                    break;
                case 2:
                    System.out.println("Substraction = " +(num1 -num2)); 
                    break;
                case 3:
                    System.out.println("Multiplication = " +(num1 * num2));
                    break;
                case 4:
                    if(num2!= 0){
                        System.out.println("Division = " +((double)num1 / num2));
                    }
                    else{
                        System.out.println("cannot divide by zero");
                    }
                    break;
                    
                default:
                    System.out.println("Invalid Choice");    
            }

            System.out.println("\nDo you want to continue?(y/n):");
            choice = sc.next().charAt(0);
            
        }while(choice == 'y');
        System.out.println("Calculator Closed.");
        sc.close();
    }
}


