package menu;

import hotel.Hotel;
import java.util.Scanner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import users.User;
import users.Users;

/**
 * Class used to display the hotel menu with different options
 * that user may choose regarding rooms.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserInterface {

  static Logger logger = Logger.getLogger(UserInterface.class);
  private Hotel hotel;
  private Scanner userInput = new Scanner(System.in);
  private User currentUser;

  public UserInterface(Hotel hotel) {
    this.hotel = hotel;
  }

  /**
   * Displays a welcome message and asks to provide first name and last name
   * that will be used to create {@link users.User} object. After the input
   * it invokes showMainMenu() method.
   */
  public void showWelcomeMenu() {
    System.out.println("Welcome to Hotel application, \n"
        + "please provide your name and surname and press Enter:");
    currentUser = Users.getUser(userInput.next(), userInput.next());
    showMainMenu();
  }

  /**
   * Invoked by showWelcomeMenu() method. Displays all the options
   * that user may choose to perform some operations on the rooms
   * at the hotel. Every option invokes some other method in this class.
   */
  public void showMainMenu() {
    System.out.println("\nYou are logged in as: "
        + currentUser.getFirstName() + " " + currentUser.getLastName()
        + "\n"
        + "Please select one of the options:\n"
        + "\n"
        + "1. Receive the list of hotel apartments\n"
        + "2. Get list of available apartments\n"
        + "3. Book the apartment\n"
        + "4. Filter apartments\n"
        + "\n"
        + "5. Show your booked apartments\n"
        + "\n"
        + "0. Logout");
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
      case 5:
        System.out.println("Booked apartments:\n");
        showBookedByUserMenu();
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

  /**
   * Displays the menu that allow user to choose the list of room to display.
   * Gives option to go back to showMainMenu() or showWelcomeMenu().
   */
  public void showAllApartmentsMenu() {
    System.out.println("Please select one of the options. Type '0' to go back:\n"
        + "\n"
        + "1. All apartments\n"
        + "2. One Bedroom apartments\n"
        + "3. Standard apartments\n"
        + "4. Penthouses\n");
    MenuFunctions.filterByType(userInput, hotel);
    showBackOrLogoutMenu();
  }

  /**
   * Displays the list of rooms that are not booked at the moment.
   * Gives option to go back to showMainMenu() or showWelcomeMenu().
   */
  public void showAvailableApartmentsMenu() {
    MenuFunctions.checkIfAvailable(hotel.getAllRooms());
    showBackOrLogoutMenu();
  }

  /**
   * Displays the room booking menu. Asks user to provide a number
   * of the room that he wants to book.
   * Gives option to go back to showMainMenu() or showWelcomeMenu().
   */
  public void showBookApartmentMenu() {
    System.out.println("Type in the number of the chosen room:\n");
    MenuFunctions.book(currentUser, userInput, hotel.getAllRooms());
    showBackOrLogoutMenu();
  }

  /**
   * Displays the room filtering by properties menu. Asks user to provide
   * a number of property that will be used as a filter.
   * Gives option to go back to showMainMenu() or showWelcomeMenu().
   */
  public void showFilterApartmentsMenu() {
    System.out.println("Type properties number to select. Type '0' to go back:\n");
    MenuFunctions.filterByProperty(userInput,
        MenuFunctions.getAllProperties(), hotel.getAllRooms());
    showBackOrLogoutMenu();
  }

  /**
   * Displays a list of rooms booked by user during current session.
   * Gives option to go back to showMainMenu() or showWelcomeMenu().
   */
  public void showBookedByUserMenu() {
    MenuFunctions.showBooked(currentUser);
    showBackOrLogoutMenu();
  }

  /**
   * Gives user option to go back to showMainMenu() or showWelcomeMenu()
   * by pressing respectively 0 or 9.
   */
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
