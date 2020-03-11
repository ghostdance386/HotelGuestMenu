package hotel;

import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.apache.log4j.Logger;
import rooms.OneBedroom;
import rooms.Penthouse;
import rooms.Room;
import rooms.Standard;

/**
 * Class that provides methods that create {@link hotel.Hotel} class
 * and populate the lists of rooms.
 */
@Getter
public class HotelBuilder {

  private static Logger logger = Logger.getLogger(HotelBuilder.class);
  private final List<OneBedroom> oneBedroomList = new ArrayList<>();
  private final List<Standard> standardList = new ArrayList<>();
  private final List<Penthouse> penthouseList = new ArrayList<>();
  private final Map<Integer, Integer> roomNumbersTaken = new HashMap<>();

  /**
   * Creates {@link rooms.OneBedroom} objects and adds it to the list
   * with the number of the room and the number of the floor.
   *
   * @param roomCount specifies how many rooms we want to create.
   * @param floor     specifies on which floor we want to create these rooms
   * @return HotelBuilder with a populated list of {@link rooms.OneBedroom} objects.
   */
  @Step("Creating {0} one bedrooms on {1} floor")
  public HotelBuilder withOneBedrooms(int roomCount, int floor)
      throws InvalidBuilderInputException {
    if (roomCount <= 0) {
      throw new InvalidBuilderInputException("You have to create at least one room");
    }
    roomNumbersTaken.putIfAbsent(floor, floor * 100);
    for (int i = 1; i <= roomCount; i++) {
      OneBedroom oneBedroom = new OneBedroom(roomNumbersTaken.get(floor) + 1, floor);
      oneBedroomList.add(oneBedroom);
      roomNumbersTaken.put(floor, oneBedroom.getNumber());
    }
    return this;
  }

  /**
   * Overloaded method that accepts a list of {@link rooms.OneBedroom} objects.
   *
   * @param rooms is an already created list of {@link rooms.OneBedroom} objects.
   * @return HotelBuilder with a populated list of {@link rooms.OneBedroom} objects.
   */
  @Step("Adding one bedrooms from {0}")
  public HotelBuilder withOneBedrooms(List<OneBedroom> rooms) throws InvalidBuilderInputException {
    if (rooms.isEmpty()) {
      throw new InvalidBuilderInputException("You cannot add empty list of rooms");
    }
    for (Room room : rooms) {
      if (room.getNumber() != roomNumbersTaken.get(room.getFloor()) + 1) {
        room.setNumber(roomNumbersTaken.get(room.getFloor()) + 1);
        roomNumbersTaken.put(room.getFloor(), room.getNumber());
      }
    }
    oneBedroomList.addAll(rooms);
    return this;
  }

  /**
   * Creates {@link rooms.Standard} objects and adds it to the list
   * with the number of the room and the number of the floor.
   *
   * @param roomCount specifies how many rooms we want to create.
   * @param floor     specifies on which floor we want to create these rooms
   * @return HotelBuilder with a populated list of {@link rooms.Standard} objects.
   */
  @Step("Creating {0} standard rooms on {1} floor")
  public HotelBuilder withStandardRooms(int roomCount, int floor)
      throws InvalidBuilderInputException {
    if (roomCount <= 0) {
      throw new InvalidBuilderInputException("You have to create at least one room");
    }
    roomNumbersTaken.putIfAbsent(floor, floor * 100);
    for (int i = 1; i <= roomCount; i++) {
      Standard standardRoom = new Standard(roomNumbersTaken.get(floor) + 1, floor);
      standardList.add(standardRoom);
      roomNumbersTaken.put(floor, standardRoom.getNumber());
    }
    return this;
  }

  /**
   * Overloaded method that accepts a list of {@link rooms.Standard} objects.
   *
   * @param rooms is an already created list of {@link rooms.Standard} objects.
   * @return HotelBuilder with a populated list of {@link rooms.Standard} objects.
   */
  @Step("Adding standard rooms from {0}")
  public HotelBuilder withStandardRooms(List<Standard> rooms) throws InvalidBuilderInputException {
    if (rooms.isEmpty()) {
      throw new InvalidBuilderInputException("You cannot add empty list of rooms");
    }
    for (Room room : rooms) {
      if (room.getNumber() != roomNumbersTaken.get(room.getFloor()) + 1) {
        room.setNumber(roomNumbersTaken.get(room.getFloor()) + 1);
        roomNumbersTaken.put(room.getFloor(), room.getNumber());
      }
    }
    standardList.addAll(rooms);
    return this;
  }

  /**
   * Creates {@link rooms.Penthouse} objects and adds it to the list
   * with the number of the room and the number of the floor.
   *
   * @param roomCount specifies how many rooms we want to create.
   * @param floor     specifies on which floor we want to create these rooms
   * @return HotelBuilder with a populated list of {@link rooms.Penthouse} objects.
   */
  @Step("Creating {0} penthouses on {1} floor")
  public HotelBuilder withPenthouses(int roomCount, int floor) throws InvalidBuilderInputException {
    if (roomCount <= 0) {
      throw new InvalidBuilderInputException("You have to create at least one room");
    }
    roomNumbersTaken.putIfAbsent(floor, floor * 100);
    for (int i = 1; i <= roomCount; i++) {
      Penthouse penthouse = new Penthouse(roomNumbersTaken.get(floor) + 1, floor);
      penthouseList.add(penthouse);
      roomNumbersTaken.put(floor, penthouse.getNumber());
    }
    return this;
  }

  /**
   * Overloaded method that accepts a list of {@link rooms.Penthouse} objects.
   *
   * @param rooms is an already created list of {@link rooms.Penthouse} objects.
   * @return HotelBuilder with a populated list of {@link rooms.Penthouse} objects.
   */
  @Step("Adding penthouses from {0}")
  public HotelBuilder withPenthouses(List<Penthouse> rooms) throws InvalidBuilderInputException {
    if (rooms.isEmpty()) {
      throw new InvalidBuilderInputException("You cannot add empty list of rooms");
    }
    for (Room room : rooms) {
      if (room.getNumber() != roomNumbersTaken.get(room.getFloor()) + 1) {
        room.setNumber(roomNumbersTaken.get(room.getFloor()) + 1);
        roomNumbersTaken.put(room.getFloor(), room.getNumber());
      }
    }
    penthouseList.addAll(rooms);
    return this;
  }

  /**
   * Build method that creates a ready to use {@link hotel.Hotel} object.
   *
   * @return {@link hotel.Hotel} object with populated lists of rooms of different type.
   */
  @Step("Building the hotel with different type of rooms list")
  public Hotel build() {
    return new Hotel(oneBedroomList, standardList, penthouseList);
  }
}