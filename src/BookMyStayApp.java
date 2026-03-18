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
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    boolean isAvailable(String type) {
        return inventory.getOrDefault(type, 0) > 0;
    }

    void reduceRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

// Booking Service
class BookingService {

    Queue<Reservation> queue = new LinkedList<>();
    Set<String> allocatedRooms = new HashSet<>();
    HashMap<String, Set<String>> roomAllocMap = new HashMap<>();

    RoomInventory inventory = new RoomInventory();

    void addRequest(Reservation r) {
        queue.add(r);
    }

    void processBookings() {

        while(!queue.isEmpty()) {

            Reservation r = queue.poll();

            if(inventory.isAvailable(r.roomType)) {

                String roomId = r.roomType + "-" + UUID.randomUUID();

                // ensure unique room id
                allocatedRooms.add(roomId);

                // map room type → allocated IDs
                roomAllocMap.putIfAbsent(r.roomType, new HashSet<>());
                roomAllocMap.get(r.roomType).add(roomId);

                inventory.reduceRoom(r.roomType);

                System.out.println("Booking confirmed for " + r.customerName +
                        " Room ID: " + roomId);

            } else {
                System.out.println("No rooms available for " + r.customerName);
            }
        }
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {

        BookingService service = new BookingService();

        service.addRequest(new Reservation("John", "Single Room"));
        service.addRequest(new Reservation("Alice", "Double Room"));
        service.addRequest(new Reservation("Bob", "Suite Room"));
        service.addRequest(new Reservation("Mike", "Suite Room"));

        service.processBookings();
    }
}