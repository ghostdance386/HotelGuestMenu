import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OneBedroom extends Room {

  private boolean withTV;

  public OneBedroom(int number, int floor) {
    super(number, floor);
    this.price = 180;
    this.totalSize = 12;
    this.withTV = true;
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

