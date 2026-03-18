import java.util.*;

// Service class
class Service {
    String name;
    int cost;

    Service(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
}

// Add-On Manager
class AddOnServiceManager {

    HashMap<String, List<Service>> serviceMap = new HashMap<>();

    void addService(String reservationId, Service service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println("Added service " + service.name + " to " + reservationId);
    }

    void displayServices(String reservationId) {

        List<Service> services = serviceMap.get(reservationId);

        if(services == null) {
            System.out.println("No services for " + reservationId);
            return;
        }

        int total = 0;

        System.out.println("Services for " + reservationId + ":");

        for(Service s : services) {
            System.out.println(s.name + " - ₹" + s.cost);
            total += s.cost;
        }

        System.out.println("Total Add-On Cost: ₹" + total);
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES123";

        manager.addService(reservationId, new Service("Breakfast", 200));
        manager.addService(reservationId, new Service("Spa", 500));
        manager.addService(reservationId, new Service("Airport Pickup", 300));

        manager.displayServices(reservationId);
    }
}