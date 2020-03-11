package hotel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
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

  @Test(description = "Check if one bedrooms are created", dataProvider = "roomsAndFloors")
  public synchronized void checkIfOneBedroomsAreCreated(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    //when
    builder.withOneBedrooms(room, floor);
    //then
    assertThat(builder.getOneBedroomList())
        .as("check if list of one bedrooms has correct size")
        .hasSize(room);
  }

  @Test(description = "Check if one bedrooms created while building have correct numbers",
      dataProvider = "roomsAndFloors")
  public synchronized void checkIfCreatedOneBedroomsHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    int expectedRoomNumber = room + (floor * 100);
    //when
    builder.withOneBedrooms(room, floor);
    //then
    assertThat(builder.getOneBedroomList().get(room - 1).getNumber())
        .as("check if created room has correct number")
        .isEqualTo(expectedRoomNumber);
  }

  @Test(description = "Check if one bedrooms added from separate collection have correct numbers",
      dataProvider = "roomsAndFloors")
  public synchronized void checkIfAddedOneBedroomsHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    builder.withOneBedrooms(room, floor);
    int expectedRoomNumber = 2 * room + (floor * 100);
    //when
    builder.withOneBedrooms(builder.getOneBedroomList());
    //then
    assertThat(builder.getOneBedroomList().get(2 * room - 1).getNumber())
        .as("check if added room has correct number")
        .isEqualTo(expectedRoomNumber);
  }

  @Test(description = "Check if standard rooms are created",
      dataProvider = "roomsAndFloors")
  public synchronized void checkIfStandardRoomsAreCreated(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    //when
    builder.withStandardRooms(room, floor);
    //then
    assertThat(builder.getStandardList())
        .as("check if list of standard room has correct size")
        .hasSize(room);
  }

  @Test(description = "Check if standard rooms created while building have correct numbers",
      dataProvider = "roomsAndFloors")
  public synchronized void checkIfCreatedStandardRoomsHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    int expectedRoomNumber = room + (floor * 100);
    //when
    builder.withStandardRooms(room, floor);
    //then
    assertThat(builder.getStandardList().get(room - 1).getNumber())
        .as("check if created room has correct number")
        .isEqualTo(expectedRoomNumber);
  }

  @Test(description = "Check if standard rooms added from separate collection have correct numbers",
      dataProvider = "roomsAndFloors")
  public synchronized void checkIfAddedStandardRoomsHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    builder.withStandardRooms(room, floor);
    int expectedRoomNumber = 2 * room + (floor * 100);
    //when
    builder.withStandardRooms(builder.getStandardList());
    //then
    assertThat(builder.getStandardList().get(2 * room - 1).getNumber())
        .as("check if added room has correct number")
        .isEqualTo(expectedRoomNumber);
  }

  @Test(description = "Check if penthouses are created", dataProvider = "roomsAndFloors")
  public synchronized void checkIfPenthousesAreCreated(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    //when
    builder.withPenthouses(room, floor);
    //then
    assertThat(builder.getPenthouseList())
        .as("check if list of penthouses has correct size")
        .hasSize(room);
  }

  @Test(description = "Check if penthouses created while building have correct numbers",
      dataProvider = "roomsAndFloors")
  public synchronized void checkIfCreatedPenthousesHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    int expectedRoomNumber = room + (floor * 100);
    //when
    builder.withPenthouses(room, floor);
    //then
    assertThat(builder.getPenthouseList().get(room - 1).getNumber())
        .as("check if created room has correct number")
        .isEqualTo(expectedRoomNumber);
  }

  @Test(description = "Check if penthouses added from separate collection have correct numbers",
      dataProvider = "roomsAndFloors")
  public synchronized void checkIfAddedPenthousesHaveCorrectRoomNumbers(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    builder.withPenthouses(room, floor);
    int expectedRoomNumber = 2 * room + (floor * 100);
    //when
    builder.withPenthouses(builder.getPenthouseList());
    //then
    assertThat(builder.getPenthouseList().get(2 * room - 1).getNumber())
        .as("check if added room has correct number")
        .isEqualTo(expectedRoomNumber);
  }

  @Test(description = "Check if hotel is built by the hotel builder", dataProvider = "roomsAndFloors")
  public synchronized void checkIfHotelIsBuild(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    //when
    hotel = builder.withOneBedrooms(room, floor)
        .withStandardRooms(room, floor)
        .withPenthouses(room, floor)
        .build();
    //then
    assertThat(hotel).as("check if hotel was built").isNotNull();
  }

  @Test(description = "Check if hotel contains all rooms created or added by the hotel builder",
      dataProvider = "roomsAndFloors")
  public synchronized void checkIfHotelContainsAllRooms(int room, int floor)
      throws InvalidBuilderInputException {
    //given
    builder = new HotelBuilder();
    //when
    hotel = builder.withOneBedrooms(room, floor)
        .withStandardRooms(room, floor)
        .withPenthouses(room, floor)
        .build();
    //then
    assertThat(hotel.getAllRooms())
        .as("check if one bedrooms are on all rooms list")
        .containsAll(hotel.getOneBedroomList());
    assertThat(hotel.getAllRooms())
        .as("check if standard rooms are on all rooms list")
        .containsAll(hotel.getStandardList());
    assertThat(hotel.getAllRooms())
        .as("check if penthouses are on all rooms list")
        .containsAll(hotel.getPenthouseList());
  }

  @Test(description = "Check if creating zero rooms causes an exception")
  public void checkIfCreatingZeroRoomsCausesException() {
    assertThatExceptionOfType(InvalidBuilderInputException.class)
        .isThrownBy(() -> new HotelBuilder().withOneBedrooms(0, 1));
    assertThatExceptionOfType(InvalidBuilderInputException.class)
        .isThrownBy(() -> new HotelBuilder().withStandardRooms(0, 1));
    assertThatExceptionOfType(InvalidBuilderInputException.class)
        .isThrownBy(() -> new HotelBuilder().withPenthouses(0, 1));
  }

  @Test(description = "Check if adding zero rooms causes an exception")
  public void checkIfAddingZeroRoomsCausesException() {
    assertThatExceptionOfType(InvalidBuilderInputException.class)
        .isThrownBy(() -> new HotelBuilder().withOneBedrooms(new ArrayList<>()));
    assertThatExceptionOfType(InvalidBuilderInputException.class)
        .isThrownBy(() -> new HotelBuilder().withStandardRooms(new ArrayList<>()));
    assertThatExceptionOfType(InvalidBuilderInputException.class)
        .isThrownBy(() -> new HotelBuilder().withPenthouses(new ArrayList<>()));
  }

  @Test(description = "Check if one bedrooms are added correctly from separate collection")
  public synchronized void checkIfOneBedroomsAreAdded()
      throws InvalidBuilderInputException {
    //given
    int roomCount = 10;
    builder = new HotelBuilder();
    builder.withOneBedrooms(roomCount, 1);
    //when
    builder.withOneBedrooms(builder.getOneBedroomList());
    //then
    assertThat(builder.getOneBedroomList().size())
        .as("check if list of one bedrooms increases its size correctly")
        .isEqualTo(2 * roomCount);
  }

  @Test(description = "Check if standard rooms are added correctly from separate collection")
  public synchronized void checkIfStandardRoomsAreAdded()
      throws InvalidBuilderInputException {
    //given
    int roomCount = 10;
    builder = new HotelBuilder();
    builder.withStandardRooms(roomCount, 1);
    //when
    builder.withStandardRooms(builder.getStandardList());
    //then
    assertThat(builder.getStandardList().size())
        .as("check if list of standard room increases its size correctly")
        .isEqualTo(2 * roomCount);
  }

  @Test(description = "Check if penthouses are added correctly from separate collection")
  public synchronized void checkIfPenthousesAreAdded()
      throws InvalidBuilderInputException {
    //given
    int roomCount = 10;
    builder = new HotelBuilder();
    builder.withPenthouses(roomCount, 1);
    //when
    builder.withPenthouses(builder.getPenthouseList());
    //then
    assertThat(builder.getPenthouseList().size())
        .as("check if list of penthouses increases its size correctly")
        .isEqualTo(2 * roomCount);
  }
}
