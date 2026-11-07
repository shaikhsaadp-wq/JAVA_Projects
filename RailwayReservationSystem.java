import java.util.ArrayList;
import java.util.Scanner;

/* =====================================================================
   Railway Reservation System - Console based mini project (Java)
   Storage : ArrayList (in memory only, data is lost when program ends)
   ===================================================================== */

/* ---------- Custom (checked) exception ---------- */
class SeatNotAvailableException extends Exception {
    public SeatNotAvailableException(String message) {
        super(message);
    }
}

/* ---------- Abstraction + Inheritance : Person -> Passenger ---------- */
abstract class Person {
    private String name;
    private int age;
    private String gender;

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }

    public abstract void displayDetails();
}

class Passenger extends Person {
    private String mobile;

    public Passenger(String name, int age, String gender, String mobile) {
        super(name, age, gender);
        this.mobile = mobile;
    }

    public String getMobile() { return mobile; }

    @Override
    public void displayDetails() {
        System.out.println("Passenger    : " + getName() + ", " + getAge()
                + " yrs, " + getGender());
        System.out.println("Mobile No.   : " + mobile);
    }
}

/* ---------- Train (Encapsulation : private data, public methods) ---------- */
class Train {
    private int trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private int totalSleeper;
    private int totalAC;
    private int availableSleeper;
    private int availableAC;
    private double sleeperFare;
    private double acFare;

    public Train(int trainNumber, String trainName, String source,
                 String destination, int totalSleeper, int totalAC,
                 double sleeperFare, double acFare) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSleeper = totalSleeper;
        this.totalAC = totalAC;
        this.availableSleeper = totalSleeper;
        this.availableAC = totalAC;
        this.sleeperFare = sleeperFare;
        this.acFare = acFare;
    }

    public int getTrainNumber() { return trainNumber; }
    public String getTrainName() { return trainName; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getTotalSleeper() { return totalSleeper; }
    public int getTotalAC() { return totalAC; }
    public int getAvailableSleeper() { return availableSleeper; }
    public int getAvailableAC() { return availableAC; }
    public double getSleeperFare() { return sleeperFare; }
    public double getAcFare() { return acFare; }

    public void setTrainName(String trainName) { this.trainName = trainName; }
    public void setSource(String source) { this.source = source; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setSleeperFare(double sleeperFare) { this.sleeperFare = sleeperFare; }
    public void setAcFare(double acFare) { this.acFare = acFare; }
    public void setAvailableSleeper(int seats) { this.availableSleeper = seats; }
    public void setAvailableAC(int seats) { this.availableAC = seats; }

    public void displayRow() {
        System.out.printf("%-6d %-18s %-10s %-10s %-9s %-9.2f %-9s %-9.2f%n",
                trainNumber, trainName, source, destination,
                availableSleeper + "/" + totalSleeper, sleeperFare,
                availableAC + "/" + totalAC, acFare);
    }
}

/* ---------- Abstraction : Ticket is abstract, subclasses give the details ---------- */
abstract class Ticket {
    private String bookingId;
    private Passenger passenger;
    private Train train;
    private int seats;
    private double fare;
    private String status;

    public Ticket(String bookingId, Passenger passenger, Train train, int seats) {
        this.bookingId = bookingId;
        this.passenger = passenger;
        this.train = train;
        this.seats = seats;
        this.status = "CONFIRMED";
    }

    public String getBookingId() { return bookingId; }
    public Passenger getPassenger() { return passenger; }
    public Train getTrain() { return train; }
    public int getSeats() { return seats; }
    public double getFare() { return fare; }
    public String getStatus() { return status; }

    protected void setFare(double fare) { this.fare = fare; }

    public void markCancelled() {
        this.status = "CANCELLED";
        restoreSeats();
    }

    /* These three methods are overridden by SleeperTicket and ACTicket */
    public abstract double calculateFare();
    public abstract String getCategory();
    public abstract void restoreSeats();

    public void displayTicket() {
        System.out.println("-------------------- TICKET --------------------");
        System.out.println("Booking ID   : " + bookingId);
        System.out.println("Status       : " + status);
        System.out.println("Train        : " + train.getTrainNumber() + " - "
                + train.getTrainName());
        System.out.println("Route        : " + train.getSource() + " to "
                + train.getDestination());
        System.out.println("Class        : " + getCategory());
        System.out.println("Seats        : " + seats);
        System.out.printf("Total Fare   : Rs. %.2f%n", fare);
        passenger.displayDetails();
        System.out.println("------------------------------------------------");
    }

    public void displayRow() {
        System.out.printf("%-8s %-16s %-6d %-9s %-6d %-10.2f %-10s%n",
                bookingId, passenger.getName(), train.getTrainNumber(),
                getCategory(), seats, fare, status);
    }
}

class SleeperTicket extends Ticket {
    private static final double RESERVATION_CHARGE = 20.0;

    public SleeperTicket(String bookingId, Passenger passenger, Train train, int seats) {
        super(bookingId, passenger, train, seats);
        setFare(calculateFare());
    }

    @Override
    public double calculateFare() {
        return getSeats() * getTrain().getSleeperFare() + RESERVATION_CHARGE;
    }

    @Override
    public String getCategory() {
        return "Sleeper";
    }

    @Override
    public void restoreSeats() {
        Train t = getTrain();
        t.setAvailableSleeper(t.getAvailableSleeper() + getSeats());
    }
}

class ACTicket extends Ticket {
    private static final double RESERVATION_CHARGE = 40.0;

    public ACTicket(String bookingId, Passenger passenger, Train train, int seats) {
        super(bookingId, passenger, train, seats);
        setFare(calculateFare());
    }

    @Override
    public double calculateFare() {
        return getSeats() * getTrain().getAcFare() + RESERVATION_CHARGE;
    }

    @Override
    public String getCategory() {
        return "AC";
    }

    @Override
    public void restoreSeats() {
        Train t = getTrain();
        t.setAvailableAC(t.getAvailableAC() + getSeats());
    }
}

/* ---------- Input helper : all keyboard reading and validation ---------- */
class InputHelper {
    private static Scanner sc = new Scanner(System.in);

    /* Method overloading : readText with one and with two parameters */
    public static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (s.length() > 0) {
                return s;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public static String readText(String prompt, String current) {
        System.out.print(prompt + " [" + current + "]: ");
        String s = sc.nextLine().trim();
        if (s.length() == 0) {
            return current;
        }
        return s;
    }

    /* Method overloading : readInt without and with a range */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public static int readInt(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Please enter a number between " + min + " and "
                    + max + ".");
        }
    }

    public static double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                double value = Double.parseDouble(s);
                if (value > 0) {
                    return value;
                }
                System.out.println("Value must be greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static double readPositiveDouble(String prompt, double current) {
        while (true) {
            System.out.print(prompt + " [" + current + "]: ");
            String s = sc.nextLine().trim();
            if (s.length() == 0) {
                return current;
            }
            try {
                double value = Double.parseDouble(s);
                if (value > 0) {
                    return value;
                }
                System.out.println("Value must be greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static String readName(String prompt) {
        while (true) {
            String s = readText(prompt);
            if (s.matches("[A-Za-z ]+")) {
                return s;
            }
            System.out.println("Only letters and spaces are allowed.");
        }
    }

    public static String readName(String prompt, String current) {
        while (true) {
            String s = readText(prompt, current);
            if (s.matches("[A-Za-z ]+")) {
                return s;
            }
            System.out.println("Only letters and spaces are allowed.");
        }
    }

    public static String readMobile(String prompt) {
        while (true) {
            String s = readText(prompt);
            if (s.matches("[0-9]{10}")) {
                return s;
            }
            System.out.println("Mobile number must contain exactly 10 digits.");
        }
    }

    public static String readGender(String prompt) {
        while (true) {
            String s = readText(prompt);
            if (s.equalsIgnoreCase("M")) {
                return "Male";
            } else if (s.equalsIgnoreCase("F")) {
                return "Female";
            } else if (s.equalsIgnoreCase("O")) {
                return "Other";
            }
            System.out.println("Please enter M, F or O.");
        }
    }

    public static boolean readYesNo(String prompt) {
        while (true) {
            String s = readText(prompt);
            if (s.equalsIgnoreCase("Y")) {
                return true;
            } else if (s.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Please enter Y or N.");
        }
    }
}

/* ---------- Module 1 : Train Management (CRUD on ArrayList<Train>) ---------- */
class TrainManager {
    private ArrayList<Train> trains;

    public TrainManager() {
        trains = new ArrayList<>();
    }

    public boolean isEmpty() {
        return trains.isEmpty();
    }

    /* Create */
    public boolean addTrain(Train train) {
        if (searchTrain(train.getTrainNumber()) != null) {
            return false;
        }
        trains.add(train);
        return true;
    }

    /* Read */
    public void printHeader() {
        System.out.println("--------------------------------------------------"
                + "---------------------------------------");
        System.out.printf("%-6s %-18s %-10s %-10s %-9s %-9s %-9s %-9s%n",
                "No.", "Train Name", "From", "To", "SL Seats", "SL Fare",
                "AC Seats", "AC Fare");
        System.out.println("--------------------------------------------------"
                + "---------------------------------------");
    }

    public void displayAllTrains() {
        printHeader();
        for (Train t : trains) {
            t.displayRow();
        }
        System.out.println("--------------------------------------------------"
                + "---------------------------------------");
        System.out.println("Total trains : " + trains.size());
    }

    /* Method overloading : search by train number */
    public Train searchTrain(int trainNumber) {
        for (Train t : trains) {
            if (t.getTrainNumber() == trainNumber) {
                return t;
            }
        }
        return null;
    }

    /* Method overloading : search by source and destination */
    public ArrayList<Train> searchTrain(String source, String destination) {
        ArrayList<Train> result = new ArrayList<>();
        for (Train t : trains) {
            if (t.getSource().equalsIgnoreCase(source)
                    && t.getDestination().equalsIgnoreCase(destination)) {
                result.add(t);
            }
        }
        return result;
    }

    /* Update */
    public boolean updateTrain(int trainNumber, String name, String source,
                               String destination, double sleeperFare,
                               double acFare) {
        Train t = searchTrain(trainNumber);
        if (t == null) {
            return false;
        }
        t.setTrainName(name);
        t.setSource(source);
        t.setDestination(destination);
        t.setSleeperFare(sleeperFare);
        t.setAcFare(acFare);
        return true;
    }

    /* Delete */
    public boolean deleteTrain(int trainNumber) {
        Train t = searchTrain(trainNumber);
        if (t == null) {
            return false;
        }
        trains.remove(t);
        return true;
    }
}

/* ---------- Module 2 and 3 : Reservation and Cancellation ---------- */
class ReservationManager {
    private ArrayList<Ticket> tickets;
    private int nextBookingNumber;

    public ReservationManager() {
        tickets = new ArrayList<>();
        nextBookingNumber = 1001;
    }

    private String generateBookingId() {
        String id = "RR" + nextBookingNumber;
        nextBookingNumber++;
        return id;
    }

    public Ticket bookTicket(Passenger passenger, Train train, int category,
                             int seats) throws SeatNotAvailableException {
        Ticket ticket;
        if (category == 1) {
            if (seats > train.getAvailableSleeper()) {
                throw new SeatNotAvailableException("Only "
                        + train.getAvailableSleeper()
                        + " Sleeper seat(s) available.");
            }
            train.setAvailableSleeper(train.getAvailableSleeper() - seats);
            ticket = new SleeperTicket(generateBookingId(), passenger, train, seats);
        } else {
            if (seats > train.getAvailableAC()) {
                throw new SeatNotAvailableException("Only "
                        + train.getAvailableAC() + " AC seat(s) available.");
            }
            train.setAvailableAC(train.getAvailableAC() - seats);
            ticket = new ACTicket(generateBookingId(), passenger, train, seats);
        }
        tickets.add(ticket);
        return ticket;
    }

    public Ticket searchBooking(String bookingId) {
        for (Ticket t : tickets) {
            if (t.getBookingId().equalsIgnoreCase(bookingId)) {
                return t;
            }
        }
        return null;
    }

    public ArrayList<Ticket> searchBookingsByMobile(String mobile) {
        ArrayList<Ticket> result = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getPassenger().getMobile().equals(mobile)) {
                result.add(t);
            }
        }
        return result;
    }

    /* Returns 0 = not found, 1 = already cancelled, 2 = cancelled now */
    public int cancelTicket(String bookingId) {
        Ticket t = searchBooking(bookingId);
        if (t == null) {
            return 0;
        }
        if (t.getStatus().equals("CANCELLED")) {
            return 1;
        }
        t.markCancelled();
        return 2;
    }

    public boolean hasConfirmedBookings(int trainNumber) {
        for (Ticket t : tickets) {
            if (t.getTrain().getTrainNumber() == trainNumber
                    && t.getStatus().equals("CONFIRMED")) {
                return true;
            }
        }
        return false;
    }

    public void displayHistory() {
        int confirmed = 0;
        int cancelled = 0;
        System.out.println("-----------------------------------------------"
                + "--------------------------");
        System.out.printf("%-8s %-16s %-6s %-9s %-6s %-10s %-10s%n",
                "Book ID", "Passenger", "Train", "Class", "Seats", "Fare (Rs.)",
                "Status");
        System.out.println("-----------------------------------------------"
                + "--------------------------");
        for (Ticket t : tickets) {
            t.displayRow();
            if (t.getStatus().equals("CONFIRMED")) {
                confirmed++;
            } else {
                cancelled++;
            }
        }
        System.out.println("-----------------------------------------------"
                + "--------------------------");
        System.out.println("Total bookings: " + tickets.size()
                + "  (Confirmed: " + confirmed + ", Cancelled: " + cancelled + ")");
    }

    public boolean isEmpty() {
        return tickets.isEmpty();
    }
}

/* ---------- Main class : menu driven user interface ---------- */
public class RailwayReservationSystem {
    private static TrainManager trainManager = new TrainManager();
    private static ReservationManager reservationManager = new ReservationManager();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = InputHelper.readInt("Enter your choice: ", 0, 9);
            System.out.println();
            switch (choice) {
                case 1: addTrain(); break;
                case 2: viewTrains(); break;
                case 3: searchTrain(); break;
                case 4: updateTrain(); break;
                case 5: deleteTrain(); break;
                case 6: bookTicket(); break;
                case 7: cancelTicket(); break;
                case 8: viewReservation(); break;
                case 9: viewHistory(); break;
                case 0:
                    System.out.println("Thank you for using Railway Reservation System.");
                    break;
            }
            System.out.println();
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("========== RAILWAY RESERVATION SYSTEM ==========");
        System.out.println(" 1. Add Train");
        System.out.println(" 2. View All Trains");
        System.out.println(" 3. Search Train");
        System.out.println(" 4. Update Train");
        System.out.println(" 5. Delete Train");
        System.out.println(" 6. Book Ticket");
        System.out.println(" 7. Cancel Ticket");
        System.out.println(" 8. View Reservation Details");
        System.out.println(" 9. Booking History");
        System.out.println(" 0. Exit");
        System.out.println("================================================");
    }

    /* ---- Train Management ---- */
    private static void addTrain() {
        System.out.println("--- Add Train ---");
        int number = InputHelper.readInt("Train number (1-99999): ", 1, 99999);
        if (trainManager.searchTrain(number) != null) {
            System.out.println("A train with number " + number + " already exists.");
            return;
        }
        String name = InputHelper.readText("Train name: ");
        String source = InputHelper.readName("Source station: ");
        String destination = InputHelper.readName("Destination station: ");
        if (source.equalsIgnoreCase(destination)) {
            System.out.println("Source and destination cannot be the same.");
            return;
        }
        int sleeper = InputHelper.readInt("Number of Sleeper seats (0-200): ", 0, 200);
        int ac = InputHelper.readInt("Number of AC seats (0-200): ", 0, 200);
        double sleeperFare = InputHelper.readPositiveDouble(
                "Sleeper fare per seat (Rs.): ");
        double acFare = InputHelper.readPositiveDouble("AC fare per seat (Rs.): ");

        Train train = new Train(number, name, source, destination, sleeper, ac,
                sleeperFare, acFare);
        trainManager.addTrain(train);
        System.out.println("Train " + number + " added successfully.");
    }

    private static void viewTrains() {
        System.out.println("--- All Trains ---");
        if (trainManager.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }
        trainManager.displayAllTrains();
    }

    private static void searchTrain() {
        System.out.println("--- Search Train ---");
        System.out.println("1. Search by train number");
        System.out.println("2. Search by source and destination");
        int option = InputHelper.readInt("Enter option: ", 1, 2);
        if (option == 1) {
            int number = InputHelper.readInt("Train number: ");
            Train t = trainManager.searchTrain(number);
            if (t == null) {
                System.out.println("Train not found.");
            } else {
                System.out.println("Train found:");
                trainManager.printHeader();
                t.displayRow();
            }
        } else {
            String source = InputHelper.readName("Source station: ");
            String destination = InputHelper.readName("Destination station: ");
            ArrayList<Train> result = trainManager.searchTrain(source, destination);
            if (result.isEmpty()) {
                System.out.println("No trains found for this route.");
            } else {
                System.out.println(result.size() + " train(s) found:");
                trainManager.printHeader();
                for (Train t : result) {
                    t.displayRow();
                }
            }
        }
    }

    private static void updateTrain() {
        System.out.println("--- Update Train ---");
        int number = InputHelper.readInt("Train number to update: ");
        Train t = trainManager.searchTrain(number);
        if (t == null) {
            System.out.println("Train not found.");
            return;
        }
        System.out.println("Press Enter to keep the current value.");
        String name = InputHelper.readText("Train name", t.getTrainName());
        String source = InputHelper.readName("Source station", t.getSource());
        String destination = InputHelper.readName("Destination station",
                t.getDestination());
        if (source.equalsIgnoreCase(destination)) {
            System.out.println("Source and destination cannot be the same.");
            System.out.println("Update cancelled.");
            return;
        }
        double sleeperFare = InputHelper.readPositiveDouble("Sleeper fare per seat",
                t.getSleeperFare());
        double acFare = InputHelper.readPositiveDouble("AC fare per seat",
                t.getAcFare());
        trainManager.updateTrain(number, name, source, destination, sleeperFare, acFare);
        System.out.println("Train " + number + " updated successfully.");
    }

    private static void deleteTrain() {
        System.out.println("--- Delete Train ---");
        int number = InputHelper.readInt("Train number to delete: ");
        Train t = trainManager.searchTrain(number);
        if (t == null) {
            System.out.println("Train not found.");
            return;
        }
        if (reservationManager.hasConfirmedBookings(number)) {
            System.out.println("Cannot delete: this train has confirmed bookings.");
            return;
        }
        if (InputHelper.readYesNo("Delete train " + number + "? (Y/N): ")) {
            trainManager.deleteTrain(number);
            System.out.println("Train " + number + " deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    /* ---- Ticket Reservation ---- */
    private static void bookTicket() {
        System.out.println("--- Book Ticket ---");
        if (trainManager.isEmpty()) {
            System.out.println("No trains available. Add a train first.");
            return;
        }
        trainManager.displayAllTrains();
        int number = InputHelper.readInt("Enter train number to book: ");
        Train train = trainManager.searchTrain(number);
        if (train == null) {
            System.out.println("Train not found.");
            return;
        }

        System.out.println("Enter passenger details:");
        String name = InputHelper.readName("Name: ");
        int age = InputHelper.readInt("Age (1-120): ", 1, 120);
        String gender = InputHelper.readGender("Gender (M/F/O): ");
        String mobile = InputHelper.readMobile("Mobile number (10 digits): ");
        Passenger passenger = new Passenger(name, age, gender, mobile);

        System.out.println("Select class: 1. Sleeper  2. AC");
        int category = InputHelper.readInt("Enter class: ", 1, 2);
        if (category == 1) {
            System.out.println("Available Sleeper seats: " + train.getAvailableSleeper());
        } else {
            System.out.println("Available AC seats: " + train.getAvailableAC());
        }
        int seats = InputHelper.readInt("Number of seats (1-6): ", 1, 6);

        try {
            Ticket ticket = reservationManager.bookTicket(passenger, train,
                    category, seats);
            System.out.println("Ticket booked successfully!");
            ticket.displayTicket();
        } catch (SeatNotAvailableException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    /* ---- Ticket Cancellation ---- */
    private static void cancelTicket() {
        System.out.println("--- Cancel Ticket ---");
        String id = InputHelper.readText("Enter booking ID: ");
        Ticket ticket = reservationManager.searchBooking(id);
        if (ticket == null) {
            System.out.println("Booking not found.");
            return;
        }
        ticket.displayTicket();
        if (ticket.getStatus().equals("CANCELLED")) {
            System.out.println("This ticket is already cancelled.");
            return;
        }
        if (InputHelper.readYesNo("Cancel this ticket? (Y/N): ")) {
            reservationManager.cancelTicket(id);
            System.out.println("Ticket " + ticket.getBookingId()
                    + " cancelled. " + ticket.getSeats()
                    + " " + ticket.getCategory() + " seat(s) restored.");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }

    /* ---- Additional : reservation details and history ---- */
    private static void viewReservation() {
        System.out.println("--- Reservation Details ---");
        System.out.println("1. Search by booking ID");
        System.out.println("2. Search by mobile number");
        int option = InputHelper.readInt("Enter option: ", 1, 2);
        if (option == 1) {
            String id = InputHelper.readText("Enter booking ID: ");
            Ticket t = reservationManager.searchBooking(id);
            if (t == null) {
                System.out.println("Booking not found.");
            } else {
                t.displayTicket();
            }
        } else {
            String mobile = InputHelper.readMobile("Mobile number (10 digits): ");
            ArrayList<Ticket> result = reservationManager.searchBookingsByMobile(mobile);
            if (result.isEmpty()) {
                System.out.println("No bookings found for this mobile number.");
            } else {
                for (Ticket t : result) {
                    t.displayTicket();
                }
            }
        }
    }

    private static void viewHistory() {
        System.out.println("--- Booking History ---");
        if (reservationManager.isEmpty()) {
            System.out.println("No bookings made yet.");
            return;
        }
        reservationManager.displayHistory();
    }
}