import java.util.HashMap;

// Inventory class
class RoomInventory {

    HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0); // unavailable
    }

    HashMap<String, Integer> getInventory() {
        return inventory;
    }
}

// Room class
class Room {
    String type;
    int price;

    Room(String type, int price) {
        this.type = type;
        this.price = price;
    }

    void display() {
        System.out.println(type + " - ₹" + price);
    }
}

// Search service
class SearchService {

    void searchAvailableRooms(HashMap<String, Integer> inventory) {

        System.out.println("Available Rooms:");

        for(String key : inventory.keySet()) {

            int available = inventory.get(key);

            if(available > 0) {
                System.out.println(key + " -> Available: " + available);
            }
        }
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        SearchService search = new SearchService();

        search.searchAvailableRooms(inventory.getInventory());
    }
}