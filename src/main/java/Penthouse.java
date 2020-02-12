import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Penthouse extends Room {

    private int number;
    private int floor;
    private double totalSize;
    private boolean isBooked;
    private String type = "Exclusive Penthouse";
    private boolean hasBalcony = true;
    private boolean hasJacuzzi = true;
    private boolean hasMiniBar = true;
    private boolean hasTV = true;
    private boolean hasRefrigerator = true;
    private int price;

}

