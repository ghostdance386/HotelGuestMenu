import java.util.Scanner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;

@Getter
@Setter
@NoArgsConstructor
public class Menu {

  static Logger logger = Logger.getLogger(Menu.class);
  private Hotel hotel;
  private RoomOperations operations = new RoomOperations();
  private Scanner userInput = new Scanner(System.in);
  private String userFirstName;
  private String userLastName;

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
        System.out.println("Book the apartment:\n");
        showBookApartmentMenu();
        break;
      case 4:
        System.out.println("Filter the apartments:\n");
        showFilterApartmentsMenu();
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
    System.out.println("Please select one of the options. Type '0' to go back:\n"
        + "\n"
        + "1. All apartments\n"
        + "2. One Bedroom apartments\n"
        + "3. Standard apartments\n"
        + "4. Penthouses\n");
    operations.show(userInput, hotel);
    showBackOrLogoutMenu();
  }

  public void showAvailableApartmentsMenu() {
    operations.check(hotel.getAllRooms());
    showBackOrLogoutMenu();
  }

  public void showBookApartmentMenu() {
    System.out.println("Type in the number of the chosen room:\n");
    operations.book(userInput, hotel.getAllRooms());
    showBackOrLogoutMenu();
  }

  private void showFilterApartmentsMenu() {
    System.out.println("Type properties number to select. Type '0' to go back:\n");
    operations.filter(userInput, operations.getAllProperties(), hotel.getAllRooms());
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
