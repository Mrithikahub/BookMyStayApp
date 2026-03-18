import java.util.HashMap;

// Inventory class
class RoomInventory {

    HashMap<String, Integer> inventory = new HashMap<>();

    // constructor to initialize data
    RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    void displayInventory() {
        System.out.println("Room Availability:");

        for(String key : inventory.keySet()) {
            System.out.println(key + " -> " + inventory.get(key));
        }
    }
}

// Abstract Room
abstract class Room {
    String type;
    int price;

    Room(String type, int price) {
        this.type = type;
        this.price = price;
    }

    abstract void display();
}

// Room types
class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1000);
    }

    void display() {
        System.out.println(type + " - ₹" + price);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2000);
    }

    void display() {
        System.out.println(type + " - ₹" + price);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 5000);
    }

    void display() {
        System.out.println(type + " - ₹" + price);
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay App");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();
    }
}