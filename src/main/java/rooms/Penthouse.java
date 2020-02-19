package rooms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;

@Getter
@Setter
@NoArgsConstructor
public class Penthouse extends Room {

  static Logger logger = Logger.getLogger(Penthouse.class);
  private boolean withTV;
  private boolean withRefrigerator;
  private boolean withMiniBar;
  private boolean withJacuzzi;

  public Penthouse(int number, int floor) {
    super(number, floor);
    this.price = 550;
    this.totalSize = 50;
    this.withTV = true;
    this.withRefrigerator = true;
    this.withMiniBar = true;
    this.withJacuzzi = true;
    roomProperties.add("TV");
    roomProperties.add("Refrigerator");
    roomProperties.add("MiniBar");
    roomProperties.add("Jacuzzi");
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

