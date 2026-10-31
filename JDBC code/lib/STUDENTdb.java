
import java.sql.*;
import java.util.Scanner;

public class StudentDBMS {

    static final String URL = "jdbc:mysql://localhost:3306/college_db";
    static final String USER = "root";
    static final String PASSWORD = "Saad@2006";

    static Scanner sc = new Scanner(System.in);

    // Execute INSERT, UPDATE and DELETE with transaction management
    static int execute(Connection con, String sql, Object... values)
            throws SQLException {
        boolean autoCommit = con.getAutoCommit();
        con.setAutoCommit(false);

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++)
                ps.setObject(i + 1, values[i]);

            int rows = ps.executeUpdate();
            con.commit();
            return rows;

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                e.addSuppressed(ex);
            }
            throw e;

        } finally {
            con.setAutoCommit(autoCommit);
        }
    }

    // CREATE
    static void add(Connection con) throws SQLException {
        int roll = readInt("Roll number: ");

        System.out.print("Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Branch: ");
        String branch = sc.nextLine().trim();

        double marks = readDouble("Marks (0-100): ");

        if (name.isEmpty() || branch.isEmpty()
                || marks < 0 || marks > 100) {
            System.out.println("Invalid details!");
            return;
        }

        int rows = execute(con,
                "INSERT INTO students VALUES (?, ?, ?, ?)",
                roll, name, branch, marks);

        System.out.println(rows > 0 ? "Student added!" : "Not added.");
    }

    // READ
    static void view(Connection con) throws SQLException {
        String sql = "SELECT * FROM students ORDER BY roll_no";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.printf("%-10s %-20s %-15s %-10s%n",
                    "Roll No", "Name", "Branch", "Marks");

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.printf("%-10d %-20s %-15s %-10.2f%n",
                        rs.getInt("roll_no"),
                        rs.getString("name"),
                        rs.getString("branch"),
                        rs.getDouble("marks"));
            }

            if (!found)
                System.out.println("No records found.");
        }
    }

    // UPDATE
    static void update(Connection con) throws SQLException {
        int roll = readInt("Roll number to update: ");

        System.out.print("New name: ");
        String name = sc.nextLine().trim();

        System.out.print("New branch: ");
        String branch = sc.nextLine().trim();

        double marks = readDouble("New marks (0-100): ");

        if (name.isEmpty() || branch.isEmpty()
                || marks < 0 || marks > 100) {
            System.out.println("Invalid details!");
            return;
        }

        int rows = execute(con,
                "UPDATE students SET name=?, branch=?, marks=? WHERE roll_no=?",
                name, branch, marks, roll);

        System.out.println(rows > 0
                ? "Student updated!"
                : "Student not found.");
    }

    // DELETE
    static void delete(Connection con) throws SQLException {
        int roll = readInt("Roll number to delete: ");

        int rows = execute(con,
                "DELETE FROM students WHERE roll_no=?", roll);

        System.out.println(rows > 0
                ? "Student deleted!"
                : "Student not found.");
    }

    static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    static double readDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        try (Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.println("MySQL connected successfully!");

            while (true) {
                System.out.println(
                    "\n1.Add  2.View  3.Update  4.Delete  5.Exit");

                int choice = readInt("Choice: ");

                try {
                    switch (choice) {
                        case 1:
                            add(con);
                            break;

                        case 2:
                            view(con);
                            break;

                        case 3:
                            update(con);
                            break;

                        case 4:
                            delete(con);
                            break;

                        case 5:
                            System.out.println("Application closed.");
                            return;

                        default:
                            System.out.println("Invalid choice!");
                    }
                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
