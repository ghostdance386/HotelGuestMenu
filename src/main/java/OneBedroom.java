import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class OneBedroom extends Room {

    private int number;
    private int floor;
    private double totalSize;
    private boolean isBooked;
    private String type = "One Bedroom";
    private boolean hasBalcony = false;
    private boolean hasJacuzzi = false;
    private boolean hasMiniBar = false;
    private boolean hasTV = true;
    private boolean hasRefrigerator = false;
    private int price;

}

