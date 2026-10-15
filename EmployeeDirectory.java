
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.HashMap;

class Employee {
    int id;
    String name;
    String department;

    Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + department;
    }
    
}

public class EmployeeDirectory {
    public static void main(String[] args) {

        ArrayList<Employee> list = new ArrayList<>();

        list.add(new Employee(101, "Amit", "IT"));
        list.add(new Employee(102, "Siya", "HR"));
        list.add(new Employee(103, "Rahul", "Sales"));

        System.out.println("Employees Using ArrayList:");

        Iterator<Employee> it = list.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        HashSet<String> department = new HashSet<>();

        department.add("IT");
        department.add("HR");
        department.add("Sales");
        department.add("IT");

        System.out.println("\nDepartments Using HashSet:");

        for (String dept : department) {
            System.out.println(dept);
        }

        HashMap<Integer, Employee> map = new HashMap<>();

        for (Employee e : list) {
            map.put(e.id, e);
        }

        System.out.println("\nEmployee Retrieved Using HashMap:");
        System.out.println(map.get(102));

        Employee newEmp = new Employee(104, "Sneha", "Finance");

        list.add(newEmp);
        map.put(newEmp.id, newEmp);

        System.out.println("\nEmployee Count: " + list.size());
        System.out.println("Employee With ID 104: " + map.get(104));
    }
}
