package rooms;

import static rooms.Properties.JACUZZI;
import static rooms.Properties.MINIBAR;
import static rooms.Properties.REFRIGERATOR;
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
public class Penthouse extends Room {

  static Logger logger = Logger.getLogger(Penthouse.class);
  private boolean withTV;
  private boolean withRefrigerator;
  private boolean withMiniBar;
  private boolean withJacuzzi;

  /**
   * Constructor that accepts room number and floor number and sets
   * the room status as not booked. It sets the price and the total size
   * of this kind of room. It also adds room properties
   * 'TV', 'Refrigerator', 'MiniBar' and 'Jacuzzi' to the list of properties.
   *
   * @param number sets a number of the room. {@link hotel.HotelBuilder}
   *               methods automatically assign a consecutive value on given floor.
   * @param floor  sets a number of the floor on which the room is being set.
   */
  public Penthouse(int number, int floor) {
    super(number, floor);
    this.price = 550;
    this.totalSize = 50;
    this.withTV = true;
    this.withRefrigerator = true;
    this.withMiniBar = true;
    this.withJacuzzi = true;
    roomProperties.add(TV);
    roomProperties.add(REFRIGERATOR);
    roomProperties.add(MINIBAR);
    roomProperties.add(JACUZZI);
  }

  @Override
  public String toString() {
    return "Penthouse {"
        + "number: " + number
        + ", floor: " + floor
        + ", price: " + price + " PLN"
        + ", size: " + totalSize + " sqm"
        + ", is it booked? " + isBooked
        + ", has a balcony? " + withBalcony
        + ", has a TV? " + withTV
        + ", has a refrigerator? " + withRefrigerator
        + ", has a minibar? " + withMiniBar
        + ", has a jacuzzi? " + withJacuzzi
        + '}';
  }
}

