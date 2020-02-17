import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Menu {
  private Hotel hotel;
  private Scanner userInput = new Scanner(System.in);
  private String userFirstName;
  private String userLastName;
  private int bookedRoomCount = 0;

  public Menu(Hotel hotel) {
    this.hotel = hotel;
  }

  public void showWelcomeMenu() {
    System.out.println("Welcome to Hotel application, \n"
        + "please provide your name and surname and press Enter:");
    setUserFirstName(userInput.next());
    setUserLastName(userInput.next());
    showMainMenu();
  }

  public void showMainMenu() {
    System.out.println("\nYou are logged in as: "
        + getUserFirstName() + " " + getUserLastName()
        + "\n"
        + "Please select one of the options:\n"
        + "\n"
        + "1. Receive the list of apartments\n"
        + "2. Get list of available apartments\n"
        + "3. Book the apartment\n"
        + "4. Filter apartments\n"
        + "\n"
        + "0. Back");
    switch (userInput.nextInt()) {
      case 1:
        System.out.println("List of apartments:\n");
        showAllApartmentsMenu();
        break;
      case 2:
        System.out.println("Available apartments:\n");
        showAvailableApartmentsMenu();
        break;
      case 3:
        System.out.println("Book the apartment");
        showBookApartmentMenu();
        break;
      case 4:
        System.out.println("Filter the apartments");
        break;
      case 0:
        showWelcomeMenu();
        break;
      default:
        System.out.println("Incorrect option. Please choose again");
        showMainMenu();
        break;
    }
  }

  public void showAllApartmentsMenu() {
    System.out.println("Please select one of the options:\n"
        + "\n"
        + "1. All apartments\n"
        + "2. One Bedroom apartments\n"
        + "3. Standard apartments\n"
        + "4. Penthouses\n");

    switch (userInput.nextInt()) {
      case 1:
        for (Room room : getHotel().getAllRooms()
        ) {
          System.out.println(room.toString());
        }
        break;
      case 2:
        for (Room room : getHotel().getOneBedroomList()
        ) {
          System.out.println(room.toString());
        }
        break;
      case 3:
        for (Room room : getHotel().getStandardList()
        ) {
          System.out.println(room.toString());
        }
        break;
      case 4:
        for (Room room : getHotel().getPenthouseList()
        ) {
          System.out.println(room.toString());
        }
        break;
      default:
        System.out.println("Incorrect option. Please choose again");
        showAllApartmentsMenu();
    }
    showBackOrLogoutMenu();
  }

  public void showAvailableApartmentsMenu() {
    for (Room room : getHotel().getAllRooms()
    ) {
      if (!room.isBooked()) {
        System.out.println(room.toString());
      }
    }
    showBackOrLogoutMenu();
  }

  public void showBookApartmentMenu() {
    System.out.println("Type in the number of the chosen room:\n");
    int chosenRoom = userInput.nextInt();
    Map<Integer, Room> roomsByNumber = getHotel().getAllRooms().stream()
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
    showBackOrLogoutMenu();
  }

  public void showBackOrLogoutMenu() {
    System.out.println("\n9. Logout\n" + "0. Back");
    switch (userInput.nextInt()) {
      case 0:
        showMainMenu();
        break;
      case 9:
        showWelcomeMenu();
        break;
      default:
        System.out.println("Incorrect option. Please choose again");
        showBackOrLogoutMenu();
    }
  }
}
