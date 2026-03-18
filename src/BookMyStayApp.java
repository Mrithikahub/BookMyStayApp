abstract class Room {
    String type;
    int price;

    Room(String type, int price) {
        this.type = type;
        this.price = price;
    }

    abstract void display();
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1000);
    }

    void display() {
        System.out.println(type + " - Price: ₹" + price);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2000);
    }

    void display() {
        System.out.println(type + " - Price: ₹" + price);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 5000);
    }

    void display() {
        System.out.println(type + " - Price: ₹" + price);
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay App");

        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        r1.display();
        System.out.println("Available: " + singleAvailable);

        r2.display();
        System.out.println("Available: " + doubleAvailable);

        r3.display();
        System.out.println("Available: " + suiteAvailable);
    }
}