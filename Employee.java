import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    // Default constructor
    Employee() {
        id = 0;
        name = "Unknown";
        salary = 0.0;
    }

    // Constructor with name
    Employee(String name) {
        this.id = 0;
        this.name = name;
        this.salary = 0.0;
    }

    // Constructor with name and salary
    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Constructor with all details
    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Display employee details
    void display() {
        System.out.println("\nEmployee Details");
        System.out.println("ID     : " + id);
        System.out.println("Name   : " + name);
        System.out.println("Salary : " + salary);
        System.out.println("Annual Salary : " + (salary * 12));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee e;

        System.out.println("1. Default");
        System.out.println("2. Name");
        System.out.println("3. Name and Salary");
        System.out.println("4. All Details");
        System.out.print("Enter Choice: ");

        int ch = sc.nextInt();
        sc.nextLine();                      // Consume newline

        switch (ch) {
            case 1:
                e = new Employee();
                break;

            case 2:
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                e = new Employee(name);
                break;

            case 3:
                System.out.print("Enter Name: ");
                name = sc.nextLine();
                System.out.print("Enter Salary: ");
                double salary = sc.nextDouble();
                e = new Employee(name, salary);
                break;

            case 4:
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine(); // Consume newline

                System.out.print("Enter Name: ");
                name = sc.nextLine();

                System.out.print("Enter Salary: ");
                salary = sc.nextDouble();

                e = new Employee(id, name, salary);
                break;

            default:
                System.out.println("Invalid Choice!");
                sc.close();
                return;
        }

        e.display();
    sc.close();
    }
}
