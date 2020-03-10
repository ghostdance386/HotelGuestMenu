package menu;

import static rooms.Properties.BALCONY;
import static rooms.Properties.JACUZZI;
import static rooms.Properties.MINIBAR;
import static rooms.Properties.REFRIGERATOR;
import static rooms.Properties.TV;

import hotel.Hotel;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import rooms.Properties;
import rooms.Room;
import users.User;

/**
 * Utility class that provides methods to perform operations
 * on rooms in the hotel during user session.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MenuFunctions {

  private static Logger logger = Logger.getLogger(MenuFunctions.class);
  static final Properties[] propsList = {BALCONY, TV, REFRIGERATOR, MINIBAR, JACUZZI};

  static List<Properties> getAllProperties() {
    List<Properties> allProperties = new ArrayList<>();
    Collections.addAll(allProperties, propsList);
    return allProperties;
  }

  /**
   * Filters the room list by the properties chosen by the user.
   *
   * @param scanner    gives the number of the property that user chooses to filter with.
   * @param properties is the list of properties that user may filter rooms by.
   * @param rooms      is the list of all the rooms that user can filter.
   */

  @Step("Filtering the list of all rooms by property chosen by user")
  public static void filterByProperty(Scanner scanner,
                                      List<Properties> properties, Collection<Room> rooms) {
    for (int i = 0; i < properties.size(); i++) {
      System.out.println(i + 1 + ". " + properties.get(i));
    }
    int userInput = 0;
    if (scanner.hasNext()) {
      userInput = scanner.nextInt();
    }
    if (userInput > 0 && userInput <= properties.size()) {
      int finalUserInput = userInput;
      List<Room> roomsFiltered = rooms.stream()
          .filter(room -> room.getRoomProperties().contains(properties.get(finalUserInput - 1)))
          .collect(Collectors.toList());
      for (Room room : roomsFiltered
      ) {
        System.out.println(room.toString());
      }
      properties.remove(userInput - 1);
      filterByProperty(scanner, properties, roomsFiltered);
    } else if (userInput < 0 || userInput > getAllProperties().size()) {
      System.out.println("Incorrect option. Please choose again or type '0':\n");
      logger.log(Level.INFO, "User chose incorrect property filter menu option");
      filterByProperty(scanner, properties, rooms);
    }
  }

  /**
   * Books the room for the user.
   *
   * @param currentUser is the user that is currently working with {@link menu.UserInterface}
   *                    and is booking the room.
   * @param scanner     gives the number of the room that user wants to book.
   * @param rooms       is the list of all rooms in the hotel.
   */
  @Step("Booking the room by {0}")
  public static void book(User currentUser, Scanner scanner, Collection<Room> rooms) {
    int chosenRoom = scanner.nextInt();
    Map<Integer, Room> roomsByNumber = rooms.stream()
        .collect(Collectors.toMap(Room::getNumber, Function.identity()));
    if (currentUser.getBookedRooms().size() < 2) {
      if (roomsByNumber.containsKey(chosenRoom)) {
        if (!roomsByNumber.get(chosenRoom).isBooked()) {
          roomsByNumber.get(chosenRoom).setBooked(true);
          currentUser.getBookedRooms().add(roomsByNumber.get(chosenRoom));
          System.out.println("You have successfully booked room no." + chosenRoom);
          logger.log(Level.INFO, "User booked a room");
        } else {
          System.out.println("Room no." + chosenRoom + " is already booked. Choose another.");
          logger.log(Level.INFO, "User wanted to book a booked room");
        }
      } else {
        System.out.println("We don't have room with chosen number. Choose again.");
        logger.log(Level.INFO, "User wanted to book a room with incorrect number");
      }
    } else {
      System.out.println("You cannot book more than two rooms at one session");
      logger.log(Level.INFO, "User wanted to book more than two rooms");
    }
  }

  /**
   * Checks which rooms are available for booking.
   *
   * @param rooms is the list of all rooms in the hotel.
   */
  @Step("Displaying the list of available rooms")
  public static Collection<Room> checkIfAvailable(Collection<Room> rooms) {
    Collection<Room> availableRooms = new ArrayList<>();
    for (Room room : rooms) {
      if (!room.isBooked()) {
        System.out.println(room.toString());
        availableRooms.add(room);
      }
    }
    return availableRooms;
  }

  /**
   * Filters the rooms by the type selected by user.
   *
   * @param scanner gives the number of the type that user wants to filter rooms by.
   * @param hotel   is the instance of {@link Hotel} that contains all the lists of rooms.
   */
  @Step("Filtering the rooms by the type chosen by user")
  public static Collection<? extends Room> filterByType(Scanner scanner, Hotel hotel) {
    switch (scanner.nextInt()) {
      case 1:
        for (Room room : hotel.getAllRooms()) {
          System.out.println(room.toString());
        }
        return hotel.getAllRooms();
      case 2:
        for (Room room : hotel.getOneBedroomList()) {
          System.out.println(room.toString());
        }
        return hotel.getOneBedroomList();
      case 3:
        for (Room room : hotel.getStandardList()) {
          System.out.println(room.toString());
        }
        return hotel.getStandardList();
      case 4:
        for (Room room : hotel.getPenthouseList()) {
          System.out.println(room.toString());
        }
        return hotel.getPenthouseList();
      case 0:
        break;
      default:
        System.out.println("Incorrect option. Please choose again or type '0'");
        logger.log(Level.INFO, "User chose incorrect type filter menu option");
        filterByType(scanner, hotel);
    }
    return null;
  }

  /**
   * Shows the list of rooms booked by the user during current session.
   *
   * @param currentUser is the user that is currently working with {@link menu.UserInterface}
   */
  @Step("Displaying the list of rooms booked by {0}")
  public static Collection<Room> showBooked(User currentUser) {
    if (currentUser.getBookedRooms().isEmpty()) {
      System.out.println("You have not booked any room yet");
    } else {
      for (Room room : currentUser.getBookedRooms()
      ) {
        System.out.println(room.toString());
      }
    }
    return currentUser.getBookedRooms();
  }
}
