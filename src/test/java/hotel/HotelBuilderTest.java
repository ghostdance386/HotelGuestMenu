package hotel;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HotelBuilderTest {

  private Hotel hotel;
  private HotelBuilder builder;

  @DataProvider(name = "roomsAndFloors")
  public static Object[][] roomsAndFloors() {
    return new Object[][]{{1, 0}, {1, 1}, {10, 5}, {5, 20},
        {99, 5}, {100, 10}, {101, 11}};
  }

  @Test
  public void checkIfCreatingZeroRoomsCausesException() {
    Assert.assertThrows(InvalidBuilderInputException.class, () -> new HotelBuilder()
        .withStandardRooms(0, 1));
    Assert.assertThrows(InvalidBuilderInputException.class, () -> new HotelBuilder()
        .withOneBedrooms(0, 1));
    Assert.assertThrows(InvalidBuilderInputException.class, () -> new HotelBuilder()
        .withPenthouses(0, 1));
  }

  @Test
  public void checkIfAddingZeroRoomsCausesException() {
    Assert.assertThrows(InvalidBuilderInputException.class, () -> new HotelBuilder()
        .withStandardRooms(new ArrayList<>()));
    Assert.assertThrows(InvalidBuilderInputException.class, () -> new HotelBuilder()
        .withOneBedrooms(new ArrayList<>()));
    Assert.assertThrows(InvalidBuilderInputException.class, () -> new HotelBuilder()
        .withPenthouses(new ArrayList<>()));
  }

  @Test(dataProvider = "roomsAndFloors")
  public void checkIfOneBedroomsAreCreated(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    //when
    builder.withOneBedrooms(room, floor);
    //then
    assertThat("The room's list size does not match the number of created rooms",
        builder.getOneBedroomList(), Matchers.hasSize(room));
  }

  @Test
  public void checkIfOneBedroomsAreAdded()
      throws InvalidBuilderInputException {
    //given
    int roomCount = 10;
    builder = new HotelBuilder();
    builder.withOneBedrooms(roomCount, 1);
    //when
    builder.withOneBedrooms(builder.getOneBedroomList());
    //then
    assertThat("The room's list size does not match the number of added rooms",
        builder.getOneBedroomList().size(), Matchers.equalTo(2 * roomCount));
  }

  @Test(dataProvider = "roomsAndFloors")
  public void checkIfCreatedOneBedroomsHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    int expectedRoomNumber = room + (floor * 100);
    //when
    builder.withOneBedrooms(room, floor);
    //then
    assertThat("The number(s) of the room(s) is not correct",
        builder.getOneBedroomList().get(room - 1).getNumber(),
        Matchers.equalTo(expectedRoomNumber));
  }

  @Test(dataProvider = "roomsAndFloors")
  public void checkIfAddedOneBedroomsHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    builder.withOneBedrooms(room, floor);
    int expectedRoomNumber = 2 * room + (floor * 100);
    //when
    builder.withOneBedrooms(builder.getOneBedroomList());
    //then
    assertThat("The number(s) of the room(s) is not correct",
        builder.getOneBedroomList().get(2 * room - 1).getNumber(),
        Matchers.equalTo(expectedRoomNumber));
  }

  @Test(dataProvider = "roomsAndFloors")
  public void checkIfStandardRoomsAreCreated(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    //when
    builder.withStandardRooms(room, floor);
    //then
    assertThat("The room's list size does not match the number of created rooms",
        builder.getStandardList(), Matchers.hasSize(room));
  }

  @Test
  public void checkIfStandardRoomsAreAdded()
      throws InvalidBuilderInputException {
    //given
    int roomCount = 10;
    builder = new HotelBuilder();
    builder.withStandardRooms(roomCount, 1);
    //when
    builder.withStandardRooms(builder.getStandardList());
    //then
    assertThat("The room's list size does not match the number of added rooms",
        builder.getStandardList().size(), Matchers.equalTo(2 * roomCount));
  }

  @Test(dataProvider = "roomsAndFloors")
  public void checkIfCreatedStandardRoomsHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    int expectedRoomNumber = room + (floor * 100);
    //when
    builder.withStandardRooms(room, floor);
    //then
    assertThat("The number(s) of the room(s) is not correct",
        builder.getStandardList().get(room - 1).getNumber(), Matchers.equalTo(expectedRoomNumber));
  }

  @Test(dataProvider = "roomsAndFloors")
  public void checkIfAddedStandardRoomsHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    builder.withStandardRooms(room, floor);
    int expectedRoomNumber = 2 * room + (floor * 100);
    //when
    builder.withStandardRooms(builder.getStandardList());
    //then
    assertThat("The number(s) of the room(s) is not correct",
        builder.getStandardList().get(2 * room - 1).getNumber(),
        Matchers.equalTo(expectedRoomNumber));
  }

  @Test(dataProvider = "roomsAndFloors")
  public void checkIfPenthousesAreCreated(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    //when
    builder.withPenthouses(room, floor);
    //then
    assertThat("The room's list size does not match the number of created rooms",
        builder.getPenthouseList(), Matchers.hasSize(room));
  }

  @Test
  public void checkIfPenthousesAreAdded()
      throws InvalidBuilderInputException {
    //given
    int roomCount = 10;
    builder = new HotelBuilder();
    builder.withPenthouses(roomCount, 1);
    //when
    builder.withPenthouses(builder.getPenthouseList());
    //then
    assertThat("The room's list size does not match the number of added rooms",
        builder.getPenthouseList().size(), Matchers.equalTo(2 * roomCount));
  }

  @Test(dataProvider = "roomsAndFloors")
  public void checkIfCreatedPenthousesHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    int expectedRoomNumber = room + (floor * 100);
    //when
    builder.withPenthouses(room, floor);
    //then
    assertThat("The number(s) of the room(s) is not correct",
        builder.getPenthouseList().get(room - 1).getNumber(), Matchers.equalTo(expectedRoomNumber));
  }

  @Test(dataProvider = "roomsAndFloors")
  public void checkIfAddedPenthousesHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    builder.withPenthouses(room, floor);
    int expectedRoomNumber = 2 * room + (floor * 100);
    //when
    builder.withPenthouses(builder.getPenthouseList());
    //then
    assertThat("The number(s) of the room(s) is not correct",
        builder.getPenthouseList().get(2 * room - 1).getNumber(),
        Matchers.equalTo(expectedRoomNumber));
  }

  @Test(dataProvider = "roomsAndFloors")
  public void checkIfHotelIsBuild(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    //when
    hotel = builder.withOneBedrooms(room, floor)
        .withStandardRooms(room, floor)
        .withPenthouses(room, floor)
        .build();
    //then
    assertThat("The hotel object was not build", hotel, Matchers.notNullValue());
  }

  @Test(dataProvider = "roomsAndFloors")
  public void checkIfHotelContainsAllRooms(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    //when
    hotel = builder.withOneBedrooms(room, floor)
        .withStandardRooms(room, floor)
        .withPenthouses(room, floor)
        .build();
    //then
    assertThat("The one bedroom rooms are missing in list of all rooms",
        hotel.getOneBedroomList(), Matchers.everyItem(Matchers.in(hotel.getAllRooms())));
    assertThat("The standard rooms are missing in list of all rooms",
        hotel.getStandardList(), Matchers.everyItem(Matchers.in(hotel.getAllRooms())));
    assertThat("The penthouses are missing in list of all rooms",
        hotel.getPenthouseList(), Matchers.everyItem(Matchers.in(hotel.getAllRooms())));
  }

}
