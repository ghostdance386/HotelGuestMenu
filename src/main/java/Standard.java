import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Standard extends Room {

  private boolean withTV;
  private boolean withRefrigerator;

  public Standard(int number, int floor) {
    super(number, floor);
    this.price = 280;
    this.totalSize = 20;
    this.withTV = true;
    this.withRefrigerator = true;
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

