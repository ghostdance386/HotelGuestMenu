import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

@Getter
@Setter
@ToString(exclude = "userInput")
@NoArgsConstructor
public class Hotel {

    private Collection<OneBedroom> oneBedroomList = new ArrayList<>();
    private Collection<Standard> standardList = new ArrayList<>();
    private Collection<Penthouse> penthouseList = new ArrayList<>();
    private Scanner userInput = new Scanner(System.in);

    public Hotel(Collection<OneBedroom> oneBedroomList, Collection<Standard> standardList, Collection<Penthouse> penthouseList) {
        this.oneBedroomList = oneBedroomList;
        this.standardList = standardList;
        this.penthouseList = penthouseList;
    }

}

