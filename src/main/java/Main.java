import hotel.Hotel;
import hotel.HotelBuilder;
import menu.UserInterface;
import org.apache.log4j.Logger;

public class Main {

  static Logger logger = Logger.getLogger(Main.class);

  /**
   * Main method that should be used to build a {@link hotel.Hotel}
   * and initialize {@link menu.UserInterface}.
   *
   * @param args application does not use arguments provided at start.
   */
  public static void main(String[] args) {

    final Hotel myHotel = new HotelBuilder()
        .withOneBedrooms(3, 1)
        .withStandardRooms(3, 1)
        .withPenthouses(3, 1)
        .build();

    UserInterface userInterface = new UserInterface(myHotel);
    userInterface.showWelcomeMenu();

  }
}
