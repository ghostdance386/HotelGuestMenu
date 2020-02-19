import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

@Getter
@Setter
public class RoomOperations {

  static Logger logger = Logger.getLogger(RoomOperations.class);
  private ArrayList<String> allProperties = new ArrayList<>();
  private int bookedRoomCount;

  public RoomOperations() {
    allProperties.add("Balcony");
    allProperties.add("TV");
    allProperties.add("Refrigerator");
    allProperties.add("MiniBar");
    allProperties.add("Jacuzzi");
    this.bookedRoomCount = 0;
  }

  public void filter(Scanner scanner, List<String> properties, Collection<Room> rooms) {
    for (int i = 0; i < properties.size(); i++) {
      System.out.println(i + 1 + ". " + properties.get(i));
    }
    int userInput = scanner.nextInt();
    if (userInput > 0 && userInput <= getAllProperties().size()) {
      List<Room> roomsFiltered = rooms.stream()
          .filter(room -> room.getRoomProperties().contains(getAllProperties().get(userInput - 1)))
          .collect(Collectors.toList());
      for (Room room : roomsFiltered
      ) {
        System.out.println(room.toString());
      }
      properties.remove(userInput - 1);
      filter(scanner, properties, roomsFiltered);
    } else if (userInput < 0 || userInput > getAllProperties().size()) {
      System.out.println("Incorrect option. Please choose again or type '0':\n");
      filter(scanner, properties, rooms);
    }
  }

  public void book(Scanner scanner, Collection<Room> rooms) {
    int chosenRoom = scanner.nextInt();
    Map<Integer, Room> roomsByNumber = rooms.stream()
        .collect(Collectors.toMap(Room::getNumber, Function.identity()));
    if (bookedRoomCount < 2) {
      if (roomsByNumber.containsKey(chosenRoom)) {
        if (!roomsByNumber.get(chosenRoom).isBooked) {
          roomsByNumber.get(chosenRoom).setBooked(true);
          bookedRoomCount++;
          System.out.println("You have successfully booked room no." + chosenRoom);
        } else {
          System.out.println("Room no." + chosenRoom + " is already booked. Choose another.");
        }
      } else {
        System.out.println("We don't have room with chosen number. Choose again.");
      }
    } else {
      System.out.println("You cannot book more than two rooms at one session");
    }
  }

  public void check(Collection<Room> rooms) {
    for (Room room : rooms
    ) {
      if (!room.isBooked()) {
        System.out.println(room.toString());
      }
    }
  }

  public void show(Scanner scanner, Hotel hotel) {
    switch (scanner.nextInt()) {
      case 1:
        for (Room room : hotel.getAllRooms()
        ) {
          System.out.println(room.toString());
        }
        break;
      case 2:
        for (Room room : hotel.getOneBedroomList()
        ) {
          System.out.println(room.toString());
        }
        break;
      case 3:
        for (Room room : hotel.getStandardList()
        ) {
          System.out.println(room.toString());
        }
        break;
      case 4:
        for (Room room : hotel.getPenthouseList()
        ) {
          System.out.println(room.toString());
        }
        break;
      case 0:
        break;
      default:
        System.out.println("Incorrect option. Please choose again or type '0'");
        show(scanner, hotel);
    }
  }
}
