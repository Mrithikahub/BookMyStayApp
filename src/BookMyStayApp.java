import java.io.*;
import java.util.*;

// Reservation class (Serializable)
class Reservation implements Serializable {
    String customerName;
    String roomType;

    Reservation(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

// Persistence Service
class PersistenceService {

    void saveData(List<Reservation> bookings) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("bookings.dat"));

            oos.writeObject(bookings);
            oos.close();

            System.out.println("Data saved successfully");

        } catch (Exception e) {
            System.out.println("Error saving data");
        }
    }

    List<Reservation> loadData() {

        List<Reservation> bookings = new ArrayList<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream("bookings.dat"));

            bookings = (List<Reservation>) ois.readObject();
            ois.close();

            System.out.println("Data loaded successfully");

        } catch (Exception e) {
            System.out.println("No previous data found");
        }

        return bookings;
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();

        List<Reservation> bookings = new ArrayList<>();

        bookings.add(new Reservation("John", "Single Room"));
        bookings.add(new Reservation("Alice", "Double Room"));

        // Save data
        service.saveData(bookings);

        // Load data
        List<Reservation> loaded = service.loadData();

        System.out.println("Recovered Bookings:");

        for(Reservation r : loaded) {
            System.out.println(r.customerName + " -> " + r.roomType);
        }
    }
}