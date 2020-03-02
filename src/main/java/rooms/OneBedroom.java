package rooms;

import static rooms.Properties.TV;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;

/**
 * Class that extends {@link rooms.Room} abstract class and adds fields
 * that are specific to this type of room.
 */
@Getter
@Setter
@NoArgsConstructor
public class OneBedroom extends Room {

  static Logger logger = Logger.getLogger(OneBedroom.class);
  private boolean withTV;

  /**
   * Constructor that accepts room number and floor number and sets
   * the room status as not booked. It sets the price and the total size
   * of this kind of room. It also adds room property
   * 'TV' to the list of properties.
   *
   * @param number sets a number of the room. {@link hotel.HotelBuilder}
   *               methods automatically assign a consecutive value on given floor.
   * @param floor  sets a number of the floor on which the room is being set.
   */
  public OneBedroom(int number, int floor) {
    super(number, floor);
    this.price = 180;
    this.totalSize = 12;
    this.withTV = true;
    roomProperties.add(TV);
  }

  @SuppressWarnings("checkstyle:OperatorWrap")
  @Override
  public String toString() {
    return "OneBedroom {"
        + "number: " + number
        + ", floor: " + floor
        + ", price: " + price + " PLN"
        + ", size: " + totalSize + " sqm"
        + ", is it booked? " + isBooked
        + ", has a balcony? " + withBalcony
        + ", has a TV? " + withTV
        + '}';
  }
}

