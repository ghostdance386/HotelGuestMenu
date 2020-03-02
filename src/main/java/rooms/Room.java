package rooms;

import static rooms.Properties.BALCONY;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.log4j.Logger;

/**
 * Abstract class that contains the most relevant information
 * that all child classes use.
 * It is used as a type in {@link hotel.Hotel} class lists of rooms
 * and in {@link menu.MenuFunctions} methods.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class Room {

  static Logger logger = Logger.getLogger(Room.class);
  int number;
  int floor;
  int price;
  double totalSize;
  boolean isBooked;
  boolean withBalcony;
  List<Properties> roomProperties = new ArrayList<>();

  /**
   * Constructor that accepts room number and floor number and sets
   * the room status as not booked. It also adds a room property
   * 'Balcony' to the list of properties.
   *
   * @param number sets a number of the room. {@link hotel.HotelBuilder}
   *               methods automatically assign a consecutive value on given floor.
   * @param floor  sets a number of the floor on which the room is being set.
   */
  public Room(int number, int floor) {
    this.number = number;
    this.floor = floor;
    this.isBooked = false;
    this.withBalcony = true;
    roomProperties.add(BALCONY);
  }
}

