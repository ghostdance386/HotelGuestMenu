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

    @Override
    public String toString() {
        return "OneBedroom{" +
                "number=" + number +
                ", floor=" + floor +
                ", price=" + price +
                ", totalSize=" + totalSize +
                ", isBooked=" + isBooked +
                ", withBalcony=" + withBalcony +
                ", withTV=" + withTV +
                '}';
    }
}

