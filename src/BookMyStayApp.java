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

// Inventory (shared resource)
class RoomInventory {

    HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 1);
    }

    // synchronized method for thread safety
    synchronized boolean bookRoom(String type) {

        if(inventory.get(type) > 0) {

            inventory.put(type, inventory.get(type) - 1);

            System.out.println(Thread.currentThread().getName() +
                    " booked " + type);

            return true;
        }

        System.out.println(Thread.currentThread().getName() +
                " failed (No rooms)");

        return false;
    }
}

// Booking Task (Thread)
class BookingTask implements Runnable {

    RoomInventory inventory;
    Reservation reservation;

    BookingTask(RoomInventory inventory, Reservation reservation) {
        this.inventory = inventory;
        this.reservation = reservation;
    }

    public void run() {
        inventory.bookRoom(reservation.roomType);
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Thread t1 = new Thread(new BookingTask(inventory,
                new Reservation("John", "Single Room")));

        Thread t2 = new Thread(new BookingTask(inventory,
                new Reservation("Alice", "Single Room")));

        t1.start();
        t2.start();
    }
}