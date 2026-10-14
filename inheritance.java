import java.util.Scanner;

class Product {
    int Id;
    String name;
    double price;

    void acceptProductDetails(Scanner sc) {
        System.out.print("Enter product ID: ");
        Id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter product name: ");
        name = sc.nextLine();

        System.out.print("Enter price: ");
        price = sc.nextDouble();
    }

    void displayProductDetails() {
        System.out.println("Product ID: " + Id);
        System.out.println("Product Name: " + name);
        System.out.println("Price: " + price);
    }
}

class Electronic extends Product {
    String brand;
    int warranty;

    void acceptElectronicDetails(Scanner sc) {
        acceptProductDetails(sc);

        sc.nextLine();
        System.out.print("Enter Brand: ");
        brand = sc.nextLine();

        System.out.print("Enter Warranty: ");
        warranty = sc.nextInt();
    }

    void displayElectronicDetails() {
        displayProductDetails();

        System.out.println("Brand: " + brand);
        System.out.println("Warranty: " + warranty + " years");
    }
}

class Clothing extends Product {
    String size;
    String material;

    void acceptClothingDetails(Scanner sc) {
        acceptProductDetails(sc);

        sc.nextLine();
        System.out.print("Enter Size: ");
        size = sc.nextLine();

        System.out.print("Enter Material: ");
        material = sc.nextLine();
    }

    void displayClothingDetails() {
        displayProductDetails();

        System.out.println("Size: " + size);
        System.out.println("Material: " + material);
    }
}

public class inheritance {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Electronic e = new Electronic();

        System.out.println("Enter Electronic Details:");
        e.acceptElectronicDetails(sc);

        System.out.println("\nElectronic Details:");
        e.displayElectronicDetails();

        Clothing c = new Clothing();

        System.out.println("\nEnter Clothing Details:");
        c.acceptClothingDetails(sc);

        System.out.println("\nClothing Details:");
        c.displayClothingDetails();

        sc.close();
    }
}
