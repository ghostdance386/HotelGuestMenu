package menu;

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
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
  private String inputForFilter;
  private String inputForBook;
  private List<Properties> allProps;

  @BeforeClass
  public void setupHotelRoomsAndUser() throws InvalidBuilderInputException {
    hotel = new HotelBuilder()
        .withOneBedrooms(3, 1)
        .withStandardRooms(3, 2)
        .withPenthouses(3, 3)
        .build();
    allProps = MenuFunctions.getAllProperties();
    user = Users.getUser("K", "D");
  }

  @BeforeMethod(groups = "bookingTests")
  public void emptyUserBookedRoomsList() {
    if (!user.getBookedRooms().isEmpty()) {
      user.getBookedRooms().clear();
    }
    for (Room room : hotel.getAllRooms()
    ) {
      room.setBooked(false);
    }
  }

  @DataProvider(name = "propFilter")
  public Object[][] propFilter() {
    List<Properties> propsToFilter = MenuFunctions.getAllProperties();
    inputForFilter = String.valueOf(new Random().nextInt(allProps.size()) + 1);
    userInput = new Scanner(inputForFilter);
    return new Object[][]{{userInput, propsToFilter, hotel}};
  }

  @DataProvider(name = "typeFilter")
  public Object[][] typeFilter() {
    Object[][] objects = new Object[5][2];
    for (int i = 0; i < objects.length; i++) {
      objects[i] = new Object[]{i, hotel};
    }
    return objects;
  }

  @DataProvider(name = "book")
  public Object[][] book() {
    int roomNumber = hotel.getAllRooms().iterator().next().getNumber();
    inputForBook = String.valueOf(roomNumber);
    userInput = new Scanner(inputForBook);
    return new Object[][]{{user, userInput, hotel}};
  }

  @Test
  public void checkIfGetAllPropertiesContainsAllProperties() {
    assertThat("List of all properties does not contain all properties", allProps,
        equalTo(Arrays.asList(MenuFunctions.propsList)));
  }

  @Test(dataProvider = "propFilter")
  public void checkIfPropertiesAreFiltered(Scanner userInput,
                                           List<Properties> propsToFilter, Hotel hotel) {
    //given
    Collection<Room> allRooms = hotel.getAllRooms();
    //when
    MenuFunctions.filterByProperty(userInput, propsToFilter, allRooms);
    //then
    assertThat("Previously filtered property was not removed from properties to filter list",
        propsToFilter.size(), equalTo(allProps.size() - inputForFilter.length()));
    assertThat("Previously filtered property is still available to filter",
        allProps.get(Integer.parseInt(inputForFilter) - 1), is(not(in(propsToFilter))));
  }

  @Test(dataProvider = "book", groups = "bookingTests")
  public void checkIfRoomGetsBooked(User user, Scanner userInput, Hotel hotel) {
    //given
    Collection<Room> allRooms = hotel.getAllRooms();
    Map<Integer, Room> roomsByNumber = allRooms.stream()
        .collect(Collectors.toMap(Room::getNumber, Function.identity()));
    //when
    MenuFunctions.book(user, userInput, allRooms);
    //then
    assertThat("Status of the booked room has not changed to 'booked'",
        roomsByNumber.get(Integer.parseInt(inputForBook)).isBooked(), equalTo(true));
  }

  @Test(dataProvider = "book", groups = "bookingTests")
  public void checkIfNoMoreThanTwoRoomsCanBeBooked(User user, Scanner userInput, Hotel hotel) {
    //given
    Collection<Room> allRooms = hotel.getAllRooms();
    Iterator<Room> it = allRooms.iterator();
    int count = 0;
    //when
    while (it.hasNext() && count < 3) {
      int roomNumber = it.next().getNumber();
      inputForBook = String.valueOf(roomNumber);
      userInput = new Scanner(inputForBook);
      MenuFunctions.book(user, userInput, allRooms);
      count++;
    }
    //then
    assertThat("More than two books were booked by the user",
        user.getBookedRooms().size(), equalTo(2));
  }

  @Test(dataProvider = "book", groups = "bookingTests")
  public void checkIfBookedRoomCanBeBooked(User user, Scanner userInput, Hotel hotel) {
    //given
    Collection<Room> allRooms = hotel.getAllRooms();
    Room room = allRooms.iterator().next();
    room.setBooked(true);
    //when
    MenuFunctions.book(user, userInput, allRooms);
    //then
    assertThat("Already booked room was booked by the user",
        user.getBookedRooms().isEmpty(), equalTo(true));
  }

  @Test(dataProvider = "book", groups = "bookingTests")
  public void checkIfAvailableRoomsListContainsBookedRooms(User user,
                                                           Scanner userInput, Hotel hotel) {
    //given
    Collection<Room> allRooms = hotel.getAllRooms();
    //when
    MenuFunctions.book(user, userInput, allRooms);
    Collection<Room> availableRooms = MenuFunctions.checkIfAvailable(allRooms);
    //then
    assertThat("Available rooms list contains booked room(s)",
        availableRooms.size(), equalTo(allRooms.size() - 1));
  }

  @Test(dataProvider = "book", groups = "bookingTests")
  public void checkIfRoomsBookedByUserAreAddedToTheList(User user, Scanner userInput, Hotel hotel) {
    //given
    Collection<Room> allRooms = hotel.getAllRooms();
    Map<Integer, Room> roomsByNumber = allRooms.stream()
        .collect(Collectors.toMap(Room::getNumber, Function.identity()));
    //when
    MenuFunctions.book(user, userInput, allRooms);
    Collection<Room> roomsBookedByUser = MenuFunctions.showBooked(user);
    //then
    assertThat("Booked room was not added to user's booked rooms list",
        roomsByNumber.get(Integer.parseInt(inputForBook)), is(in(roomsBookedByUser)));
    assertThat("User's booked rooms list size is incorrect",
        roomsBookedByUser.size(), equalTo(1));
  }

  @Test(dataProvider = "typeFilter")
  public void checkIfRoomTypesAreFiltered(int inputStream, Hotel hotel) {
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
}
