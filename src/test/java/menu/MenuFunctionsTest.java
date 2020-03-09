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
import java.util.Objects;
import java.util.Scanner;
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
  private List<Properties> allProps;

  @BeforeClass(alwaysRun = true)
  public void setupHotelRoomsAndUser() throws InvalidBuilderInputException {
    hotel = new HotelBuilder()
        .withOneBedrooms(3, 1)
        .withStandardRooms(3, 2)
        .withPenthouses(3, 3)
        .build();
    allProps = MenuFunctions.getAllProperties();
    user = Users.getUser("FirstName", "LastName");
  }

  @BeforeMethod(alwaysRun = true)
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
    Collection<Room> allRooms = hotel.getAllRooms();
    Object[][] objects = new Object[allProps.size()][3];
    for (int i = 0; i < objects.length; i++) {
      List<Properties> propsToFilter = MenuFunctions.getAllProperties();
      objects[i] = new Object[]{i + 1, propsToFilter, allRooms};
    }
    return objects;
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

  @Test
  public void checkIfGetAllPropertiesContainsAllProperties() {
    assertThat("List of all properties does not contain all properties", allProps,
        equalTo(Arrays.asList(MenuFunctions.propsList)));
  }

  @Test(dataProvider = "propFilter")
  public void checkIfPropertiesAreFiltered(int inputStream, List<Properties> propsToFilter,
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

  @Test(dataProvider = "book")
  public void checkIfRoomGetsBooked(User user, int inputStream, Collection<Room> allRooms) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    //when
    MenuFunctions.book(user, userInput, allRooms);
    Room bookedRoom = allRooms.stream()
        .filter(room -> room.getNumber() == inputStream)
        .findAny()
        .orElse(null);
    //then
    assertThat("Status of the booked room has not changed to 'booked'",
        Objects.requireNonNull(bookedRoom).isBooked(), equalTo(true));
  }

  @Test(dataProvider = "book")
  public void checkIfNoMoreThanTwoRoomsCanBeBooked(User user, int inputStream,
                                                   Collection<Room> allRooms) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    Iterator<Room> it = allRooms.iterator();
    int count = 0;
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
  public void checkIfBookedRoomCanBeBooked(User user, int inputStream,
                                           Collection<Room> allRooms) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    Scanner userInput2 = new Scanner(scannerInput);
    //when
    MenuFunctions.book(user, userInput, allRooms);
    MenuFunctions.book(user, userInput2, allRooms);
    //then
    assertThat("Already booked room was booked by the user",
        user.getBookedRooms().size(), equalTo(1));
  }

  @Test(dataProvider = "book")
  public void checkIfAvailableRoomsListContainsBookedRooms(User user, int inputStream,
                                                           Collection<Room> allRooms) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    //when
    MenuFunctions.book(user, userInput, allRooms);
    Collection<Room> availableRooms = MenuFunctions.checkIfAvailable(allRooms);
    //then
    assertThat("Available rooms list contains booked room(s)",
        availableRooms.size(), equalTo(allRooms.size() - 1));
  }

  @Test(dataProvider = "book")
  public void checkIfRoomsBookedByUserAreAddedToTheList(User user, int inputStream,
                                                        Collection<Room> allRooms) {
    //given
    String scannerInput = String.valueOf(inputStream);
    userInput = new Scanner(scannerInput);
    //when
    MenuFunctions.book(user, userInput, allRooms);
    Collection<Room> roomsBookedByUser = MenuFunctions.showBooked(user);
    Room bookedRoom = allRooms.stream()
        .filter(room -> room.getNumber() == inputStream)
        .findAny()
        .orElse(null);
    //then
    assertThat("Booked room was not added to user's booked rooms list",
        bookedRoom, is(in(roomsBookedByUser)));
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
