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

// Inventory
class RoomInventory {

    HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    void reduceRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }

    void increaseRoom(String type) {
        inventory.put(type, inventory.get(type) + 1);
    }

    void display() {
        System.out.println("Inventory: " + inventory);
    }
}

// Booking + Cancellation Service
class BookingService {

    RoomInventory inventory = new RoomInventory();

    // store allocated room IDs
    Stack<String> rollbackStack = new Stack<>();

    HashMap<String, String> bookingMap = new HashMap<>();

    void bookRoom(Reservation r) {

        if(inventory.inventory.get(r.roomType) > 0) {

            String roomId = r.roomType + "-" + UUID.randomUUID();

            bookingMap.put(r.customerName, roomId);
            rollbackStack.push(roomId);

            inventory.reduceRoom(r.roomType);

            System.out.println("Booked: " + r.customerName + " -> " + roomId);

        } else {
            System.out.println("No rooms available for " + r.customerName);
        }
    }

    void cancelBooking(String customerName) {

        if(!bookingMap.containsKey(customerName)) {
            System.out.println("No booking found for " + customerName);
            return;
        }

        String roomId = bookingMap.get(customerName);

        // rollback using stack
        if(!rollbackStack.isEmpty()) {
            rollbackStack.pop();
        }

        String roomType = roomId.split("-")[0];

        inventory.increaseRoom(roomType);

        bookingMap.remove(customerName);

        System.out.println("Cancelled booking for " + customerName);
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {

        BookingService service = new BookingService();

        service.bookRoom(new Reservation("John", "Single Room"));
        service.bookRoom(new Reservation("Alice", "Double Room"));

        service.cancelBooking("John");

        service.inventory.display();
    }
}