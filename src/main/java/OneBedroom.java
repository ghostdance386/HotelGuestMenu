import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;

@Getter
@Setter
@NoArgsConstructor
public class OneBedroom extends Room {

  static Logger logger = Logger.getLogger(OneBedroom.class);
  private boolean withTV;

  public OneBedroom(int number, int floor) {
    super(number, floor);
    this.price = 180;
    this.totalSize = 12;
    this.withTV = true;
    roomProperties.add("TV");
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

