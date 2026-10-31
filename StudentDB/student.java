import java.sql.*;
import java.util.Scanner;

public class student {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/student_db",
            "root",
            "Saad@2006"
        );

        System.out.println("Database Connected!");

        while (true) {
            System.out.println("\n1.Add  2.View  3.Update  4.Delete  5.Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Roll No: ");
                int r = sc.nextInt();
                sc.nextLine();

                System.out.print("Name: ");
                String n = sc.nextLine();

                System.out.print("Branch: ");
                String b = sc.nextLine();

                System.out.print("Marks: ");
                double m = sc.nextDouble();

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO student_info VALUES (?, ?, ?, ?)");
                ps.setInt(1, r);
                ps.setString(2, n);
                ps.setString(3, b);
                ps.setDouble(4, m);

                ps.executeUpdate();
                System.out.println("Student Added!");
                ps.close();
            }

            else if (ch == 2) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM student_info");

                while (rs.next()) {
                    System.out.println(
                        rs.getInt(1) + " " +
                        rs.getString(2) + " " +
                        rs.getString(3) + " " +
                        rs.getDouble(4));
                }

                rs.close();
                st.close();
            }

            else if (ch == 3) {
                System.out.print("Roll No to update: ");
                int r = sc.nextInt();
                sc.nextLine();

                System.out.print("New Name: ");
                String n = sc.nextLine();

                System.out.print("New Branch: ");
                String b = sc.nextLine();

                System.out.print("New Marks: ");
                double m = sc.nextDouble();

                PreparedStatement ps = con.prepareStatement(
                    "UPDATE student_info SET name=?, branch=?, marks=? WHERE roll_no=?");
                ps.setString(1, n);
                ps.setString(2, b);
                ps.setDouble(3, m);
                ps.setInt(4, r);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Updated!" : "Student Not Found!");
                ps.close();
            }

            else if (ch == 4) {
                System.out.print("Roll No to delete: ");
                int r = sc.nextInt();

                PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM student_info WHERE roll_no=?");
                ps.setInt(1, r);

                int rows = ps.executeUpdate();
                System.out.println(rows > 0 ? "Deleted!" : "Student Not Found!");
                ps.close();
            }

            else if (ch == 5) {
                con.close();
                sc.close();
                System.out.println("Program Closed!");
                break;
            }

            else {
                System.out.println("Invalid Choice!");
            }
        }
    }
}