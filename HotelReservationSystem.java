import java.util.*;

class Room {
    int number;
    String type;
    boolean isBooked;

    public Room(int number, String type) {
        this.number = number;
        this.type = type;
        this.isBooked = false;
    }
}

class Hotel {
    private final List<Room> rooms = new ArrayList<>();
    private final Map<Integer, String> bookings = new HashMap<>();

    public Hotel() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "Suite"));
    }

    public void searchRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room " + room.number + " - " + room.type);
            }
        }
    }

    public void bookRoom(int roomNumber, String customerName) {
        for (Room room : rooms) {
            if (room.number == roomNumber && !room.isBooked) {
                room.isBooked = true;
                bookings.put(roomNumber, customerName);
                System.out.println("Room " + roomNumber + " booked successfully for " + customerName);
                return;
            }
        }
        System.out.println("Room not available or does not exist!");
    }

    public void viewBookings() {
        System.out.println("Current Bookings:");
        for (Map.Entry<Integer, String> entry : bookings.entrySet()) {
            System.out.println("Room " + entry.getKey() + " - " + entry.getValue());
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        
        while (true) {
            System.out.println("1. Search Rooms\n2. Book Room\n3. View Bookings\n4. Exit");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    hotel.searchRooms();
                    break;
                case 2:
                    System.out.println("Enter room number and customer name:");
                    int roomNumber = scanner.nextInt();
                    String customerName = scanner.next();
                    hotel.bookRoom(roomNumber, customerName);
                    break;
                case 3:
                    hotel.viewBookings();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
