package menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import hotel.Hotel;
import hotel.HotelBuilder;
import hotel.InvalidBuilderInputException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import jdk.jfr.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rooms.Properties;
import rooms.Room;
import users.User;
import users.Users;

public class MenuFunctionsTest {

  private Hotel hotel;
  private User user;
  private Scanner userInput;
  private List<Properties> allProps;

  /**
   * Sets up hotel object and builds it with different type of rooms
   * created by {@link HotelBuilder}.
   *
   * @throws InvalidBuilderInputException - is thrown when the number of rooms
   *                                      that are created or added is equal to 0
   */
  @BeforeClass(alwaysRun = true)
  @Description("Set up hotel object and fill it with rooms with hotel builder. Create a user")
  public void setupHotelRoomsAndUser() throws InvalidBuilderInputException {
    hotel = new HotelBuilder()
        .withOneBedrooms(3, 1)
        .withStandardRooms(3, 2)
        .withPenthouses(3, 3)
        .build();
    allProps = MenuFunctions.getAllProperties();
    user = Users.getUser("FirstName", "LastName");
  }

  @Test
  @Description("Check if properties list contains all enum Properties")
  public synchronized void checkIfGetAllPropertiesContainsAllProperties() {
    assertThat("List of all properties does not contain all properties", allProps,
        equalTo(Arrays.asList(MenuFunctions.propsList)));
  }

  /**
   * Provides input for filtering by property functionality.
   *
   * @return the two-dimensional array of objects that
   * contains user input, list of properties and list of rooms
   */
  @DataProvider(name = "propFilter")
  public synchronized Object[][] propFilter() {
    Collection<Room> allRooms = hotel.getAllRooms();
    Object[][] objects = new Object[allProps.size()][3];
    for (int i = 0; i < objects.length; i++) {
      List<Properties> propsToFilter = MenuFunctions.getAllProperties();
      objects[i] = new Object[]{i + 1, propsToFilter, allRooms};
    }
    return objects;
  }

  @Test(dataProvider = "propFilter")
  @Description("Check if filter option filters rooms by properties")
  public synchronized void checkIfPropertiesAreFiltered(int inputStream, List<Properties> propsToFilter,
                                                        Collection<Room> allRooms) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    //when
    MenuFunctions.filterByProperty(userInput, propsToFilter, allRooms);
    //then
    assertThat("Previously filtered property is still available to filter",
        allProps.get(inputStream - 1), is(not(in(propsToFilter))));
  }

  /**
   * Provides input for filter by room type functionality.
   *
   * @return the two-dimensional array of objects that
   * contains user input and hotel object
   */
  @DataProvider(name = "typeFilter")
  public synchronized Object[][] typeFilter() {
    Object[][] objects = new Object[5][2];
    for (int i = 0; i < objects.length; i++) {
      objects[i] = new Object[]{i, hotel};
    }
    return objects;
  }

  @Test(dataProvider = "typeFilter")
  @Description("Check if filter option filters rooms by type")
  public synchronized void checkIfRoomTypesAreFiltered(int inputStream, Hotel hotel) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    //when
    Collection<? extends Room> filteredRooms = MenuFunctions.filterByType(userInput, hotel);
    //then
    switch (inputStream) {
      case 1:
        assertThat("All rooms type filter does not show all rooms",
            filteredRooms, equalTo(hotel.getAllRooms()));
        break;
      case 2:
        assertThat("One bedroom type filter does not show one bedrooms",
            filteredRooms, equalTo(hotel.getOneBedroomList()));
        break;
      case 3:
        assertThat("Standard type filter does not show standard rooms",
            filteredRooms, equalTo(hotel.getStandardList()));
        break;
      case 4:
        assertThat("Penthouse type filter does not show penthouses",
            filteredRooms, equalTo(hotel.getPenthouseList()));
        break;
      case 0:
        assertThat("This option should return null", filteredRooms, is(nullValue()));
        break;
    }
  }

  /**
   * Provides input for booking functionality.
   *
   * @return the two-dimensional array of objects that
   * contains user, number of the room and list of rooms
   */
  @DataProvider(name = "book")
  public synchronized Object[][] book() {
    Collection<Room> allRooms = hotel.getAllRooms();
    Object[][] objects = new Object[allRooms.size()][3];
    int index = 0;
    Iterator<Room> it = allRooms.iterator();
    while (it.hasNext() && index < objects.length) {
      int roomNumber = it.next().getNumber();
      objects[index++] = new Object[]{user, roomNumber, allRooms};
    }
    return objects;
  }

  @Test(dataProvider = "book")
  @Description("Check if user can book a room")
  public synchronized void checkIfRoomGetsBooked(User user, int inputStream, Collection<Room> allRooms) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    user.getBookedRooms().clear();
    for (Room room : hotel.getAllRooms()
    ) {
      room.setBooked(false);
    }
    //when
    Room room = MenuFunctions.book(user, userInput, allRooms);
    //then
    assertThat(Objects.requireNonNull(room).isBooked()).isTrue();
  }

  @Test(dataProvider = "book")
  @Description("Check if no more than two rooms can be booked by user during one session")
  public synchronized void checkIfNoMoreThanTwoRoomsCanBeBooked(User user, int inputStream,
                                                                Collection<Room> allRooms) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    Iterator<Room> it = allRooms.iterator();
    int count = 0;
    user.getBookedRooms().clear();
    for (Room room : hotel.getAllRooms()
    ) {
      room.setBooked(false);
    }
    //when
    MenuFunctions.book(user, userInput, allRooms);
    while (it.hasNext() && count < 3) {
      int roomNumber = it.next().getNumber();
      scannerInput = String.valueOf(roomNumber);
      userInput = new Scanner(scannerInput);
      MenuFunctions.book(user, userInput, allRooms);
      count++;
    }
    //then
    assertThat("More than two books were booked by the user",
        user.getBookedRooms().size(), equalTo(2));
  }

  @Test(dataProvider = "book")
  @Description("Check if an already booked room can be booked again")
  public synchronized void checkIfBookedRoomCanBeBooked(User user, int inputStream,
                                                        Collection<Room> allRooms) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    Scanner userInput2 = new Scanner(scannerInput);
    user.getBookedRooms().clear();
    for (Room room : hotel.getAllRooms()
    ) {
      room.setBooked(false);
    }
    //when
    MenuFunctions.book(user, userInput, allRooms);
    int bookedRoomsCountBeforeSecondBooking = user.getBookedRooms().size();
    MenuFunctions.book(user, userInput2, allRooms);
    int bookedRoomsCountBeforeAfterSecondBooking = user.getBookedRooms().size();
    //then
    assertThat("Already booked room was booked by the user",
        bookedRoomsCountBeforeSecondBooking, equalTo(bookedRoomsCountBeforeAfterSecondBooking));
  }

  @Test(dataProvider = "book")
  @Description("Check if list of available rooms contains rooms that are booked")
  public synchronized void checkIfAvailableRoomsListContainsBookedRooms(User user, int inputStream,
                                                                        Collection<Room> allRooms) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    user.getBookedRooms().clear();
    for (Room room : hotel.getAllRooms()
    ) {
      room.setBooked(false);
    }
    //when
    Room bookedRoom = MenuFunctions.book(user, userInput, allRooms);
    Collection<Room> availableRoomsAfter = MenuFunctions.checkIfAvailable(allRooms);
    //then
    assertThat(availableRoomsAfter).doesNotContain(bookedRoom);
  }

  @Test(dataProvider = "book")
  @Description("Check if booked rooms are added to the list of rooms booked by user")
  public synchronized void checkIfRoomsBookedByUserAreAddedToTheList(User user, int inputStream,
                                                                     Collection<Room> allRooms) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    user.getBookedRooms().clear();
    for (Room room : hotel.getAllRooms()
    ) {
      room.setBooked(false);
    }
    //when
    Room bookedRoom = MenuFunctions.book(user, userInput, allRooms);
    Collection<Room> roomsBookedByUser = MenuFunctions.showBooked(user);
    //then
    assertThat("Booked room was not added to user's booked rooms list",
        bookedRoom, is(in(roomsBookedByUser)));
  }
}
