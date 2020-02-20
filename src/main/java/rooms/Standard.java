package rooms;

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
public class Standard extends Room {

  static Logger logger = Logger.getLogger(Standard.class);
  private boolean withTV;
  private boolean withRefrigerator;

  /**
   * Constructor that accepts room number and floor number and sets
   * the room status as not booked. It sets the price and the total size
   * of this kind of room. It also adds room properties
   * 'TV' and 'Refrigerator' to the list of properties.
   *
   * @param number sets a number of the room. {@link hotel.HotelBuilder}
   *               methods automatically assign a consecutive value on given floor.
   * @param floor  sets a number of the floor on which the room is being set.
   */
  public Standard(int number, int floor) {
    super(number, floor);
    this.price = 280;
    this.totalSize = 20;
    this.withTV = true;
    this.withRefrigerator = true;
    roomProperties.add("TV");
    roomProperties.add("Refrigerator");
  }

  @Override
  public String toString() {
    return "Standard {"
        + "number: " + number
        + ", floor: " + floor
        + ", price: " + price + " PLN"
        + ", size: " + totalSize + " sqm"
        + ", is it booked? " + isBooked
        + ", has a balcony? " + withBalcony
        + ", has a TV? " + withTV
        + ", has a refrigerator? " + withRefrigerator
        + '}';
  }
}

