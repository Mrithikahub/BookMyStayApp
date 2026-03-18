import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

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
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    void validateRoom(String type) throws InvalidBookingException {
        if(!inventory.containsKey(type)) {
            throw new InvalidBookingException("Invalid Room Type: " + type);
        }

        if(inventory.get(type) <= 0) {
            throw new InvalidBookingException("No rooms available for: " + type);
        }
    }

    void reduceRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

// Booking Service
class BookingService {

    RoomInventory inventory = new RoomInventory();

    void bookRoom(Reservation r) {

        try {
            inventory.validateRoom(r.roomType);

            inventory.reduceRoom(r.roomType);

            System.out.println("Booking successful for " + r.customerName);

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {

        BookingService service = new BookingService();

        service.bookRoom(new Reservation("John", "Single Room"));
        service.bookRoom(new Reservation("Alice", "Luxury Room")); // invalid
        service.bookRoom(new Reservation("Bob", "Suite Room"));
    }
}