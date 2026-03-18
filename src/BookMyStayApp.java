import java.util.*;

// Reservation class
class Reservation {
    String customerName;
    String roomType;

    Reservation(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

// Booking History
class BookingHistory {

    List<Reservation> history = new ArrayList<>();

    void addBooking(Reservation r) {
        history.add(r);
        System.out.println("Booking stored for " + r.customerName);
    }

    void showHistory() {
        System.out.println("\nBooking History:");

        for(Reservation r : history) {
            System.out.println(r.customerName + " -> " + r.roomType);
        }
    }
}

// Report Service
class ReportService {

    void generateReport(List<Reservation> history) {

        System.out.println("\nBooking Report:");

        for(Reservation r : history) {
            System.out.println("Customer: " + r.customerName +
                    ", Room: " + r.roomType);
        }
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        ReportService report = new ReportService();

        history.addBooking(new Reservation("John", "Single Room"));
        history.addBooking(new Reservation("Alice", "Double Room"));
        history.addBooking(new Reservation("Bob", "Suite Room"));

        history.showHistory();

        report.generateReport(history.history);
    }
}