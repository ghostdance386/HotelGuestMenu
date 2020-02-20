package hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import rooms.OneBedroom;
import rooms.Penthouse;
import rooms.Room;
import rooms.Standard;

/**
 * Class that provides methods that create {@link hotel.Hotel} class
 * and populate the lists of rooms.
 */
public class HotelBuilder {

  static Logger logger = Logger.getLogger(HotelBuilder.class);
  private List<OneBedroom> oneBedroomList = new ArrayList<>();
  private List<Standard> standardList = new ArrayList<>();
  private List<Penthouse> penthouseList = new ArrayList<>();
  private Map<Integer, Integer> roomNumbersTaken = new HashMap<>();

  /**
   * Creates {@link rooms.OneBedroom} objects and adds it to the list
   * with the number of the room and the number of the floor.
   *
   * @param roomCount specifies how many rooms we want to create.
   * @param floor     specifies on which floor we want to create these rooms
   * @return HotelBuilder with a populated list of {@link rooms.OneBedroom} objects.
   */
  public HotelBuilder withOneBedrooms(int roomCount, int floor) {
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
  public HotelBuilder withOneBedrooms(List<OneBedroom> rooms) {
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
  public HotelBuilder withStandardRooms(int roomCount, int floor) {
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
  public HotelBuilder withStandardRooms(List<Standard> rooms) {
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
  public HotelBuilder withPenthouses(int roomCount, int floor) {
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
  public HotelBuilder withPenthouses(List<Penthouse> rooms) {
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
  public Hotel build() {
    return new Hotel(oneBedroomList, standardList, penthouseList);
  }
}