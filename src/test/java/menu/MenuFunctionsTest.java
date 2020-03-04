package menu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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
import org.testng.annotations.AfterMethod;
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
  public void createHotel() throws InvalidBuilderInputException {
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

  @DataProvider(name = "filter")
  public Object[][] filter() {
    List<Properties> propsToFilter = MenuFunctions.getAllProperties();
    inputForFilter = String.valueOf(new Random().nextInt(allProps.size()) + 1);
    userInput = new Scanner(inputForFilter);
    return new Object[][]{{userInput, propsToFilter, hotel}};
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
    assertThat(allProps,
        equalTo(Arrays.asList(MenuFunctions.propsList)));
  }

  @Test(dataProvider = "filter")
  public void checkIfPropertiesAreFiltered(Scanner userInput,
                                           List<Properties> propsToFilter, Hotel hotel) {
    //given
    Collection<Room> allRooms = hotel.getAllRooms();
    //when
    MenuFunctions.filterByProperty(userInput, propsToFilter, allRooms);
    //then
    assertThat(propsToFilter.size(), equalTo(allProps.size() - inputForFilter.length()));
    assertThat(allProps.get(Integer.parseInt(inputForFilter) - 1), is(not(in(propsToFilter))));
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
    assertThat(roomsByNumber.get(Integer.parseInt(inputForBook)).isBooked(), equalTo(true));
    assertThat(roomsByNumber.get(Integer.parseInt(inputForBook)), is(in(user.getBookedRooms())));
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
    assertThat(user.getBookedRooms().size(), equalTo(2));
  }
}
