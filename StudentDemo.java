import java.util.*;

interface Academic {
    void storeAcademicDetails();
    void displayAcademicDetails();
}

interface Sports {
    void storeSportsDetails();
    void displaySportsDetails();
}

class Student implements Academic, Sports {

    String name;
    int rollno;
    float percentage;
    String sportname;

    Scanner sc = new Scanner(System.in);

    public void storeAcademicDetails() {
        System.out.print("Enter Student name: ");
        name = sc.nextLine();

        System.out.print("Enter roll no: ");
        rollno = sc.nextInt();

        System.out.print("Enter percentage: ");
        percentage = sc.nextFloat();

        sc.nextLine();
    }

    public void displayAcademicDetails() {
        System.out.println("\n----- Academic Details ------");
        System.out.println("Name: " + name);
        System.out.println("Roll no: " + rollno);
        System.out.println("Percentage: " + percentage);
    }

    public void storeSportsDetails() {
        System.out.print("Enter Sport Name: ");
        sportname = sc.nextLine();
    }

    public void displaySportsDetails() {
        System.out.println("\n------ Sport Details ------");
        System.out.println("Sport Name: " + sportname);
    }
}

public class StudentDemo {

    public static void main(String[] args) {

        Student s = new Student();

        s.storeAcademicDetails();
        s.storeSportsDetails();

        System.out.println("\n----- Combined Student Details -----");

        s.displayAcademicDetails();
        s.displaySportsDetails();
    }
}