import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Penthouse extends Room {

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
    }

    @Override
    public String toString() {
        return "Penthouse{" +
                "number=" + number +
                ", floor=" + floor +
                ", price=" + price +
                ", totalSize=" + totalSize +
                ", isBooked=" + isBooked +
                ", withBalcony=" + withBalcony +
                ", withTV=" + withTV +
                ", withRefrigerator=" + withRefrigerator +
                ", withMiniBar=" + withMiniBar +
                ", withJacuzzi=" + withJacuzzi +
                '}';
    }
}

