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

public class HotelBuilder {

  static Logger logger = Logger.getLogger(HotelBuilder.class);
  private List<OneBedroom> oneBedroomList = new ArrayList<>();
  private List<Standard> standardList = new ArrayList<>();
  private List<Penthouse> penthouseList = new ArrayList<>();
  private Map<Integer, Integer> roomNumbersTaken = new HashMap<>();

  public HotelBuilder withOneBedrooms(int roomCount, int floor) {
    roomNumbersTaken.putIfAbsent(floor, floor * 100);
    for (int i = 1; i <= roomCount; i++) {
      OneBedroom oneBedroom = new OneBedroom(roomNumbersTaken.get(floor) + 1, floor);
      oneBedroomList.add(oneBedroom);
      roomNumbersTaken.put(floor, oneBedroom.getNumber());
    }
    return this;
  }

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

  public HotelBuilder withStandardRooms(int roomCount, int floor) {
    roomNumbersTaken.putIfAbsent(floor, floor * 100);
    for (int i = 1; i <= roomCount; i++) {
      Standard standardRoom = new Standard(roomNumbersTaken.get(floor) + 1, floor);
      standardList.add(standardRoom);
      roomNumbersTaken.put(floor, standardRoom.getNumber());
    }
    return this;
  }

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

  public HotelBuilder withPenthouses(int roomCount, int floor) {
    roomNumbersTaken.putIfAbsent(floor, floor * 100);
    for (int i = 1; i <= roomCount; i++) {
      Penthouse penthouse = new Penthouse(roomNumbersTaken.get(floor) + 1, floor);
      penthouseList.add(penthouse);
      roomNumbersTaken.put(floor, penthouse.getNumber());
    }
    return this;
  }

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

  public Hotel build() {
    return new Hotel(oneBedroomList, standardList, penthouseList);
  }
}