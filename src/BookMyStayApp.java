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

// Booking Queue
class BookingQueue {

    Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.add(r);
        System.out.println("Added booking request for " + r.customerName);
    }

    void showQueue() {
        System.out.println("Booking Requests:");

        for(Reservation r : queue) {
            System.out.println(r.customerName + " -> " + r.roomType);
        }
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();

        queue.addRequest(new Reservation("John", "Single Room"));
        queue.addRequest(new Reservation("Alice", "Double Room"));
        queue.addRequest(new Reservation("Bob", "Suite Room"));

        queue.showQueue();
    }
}