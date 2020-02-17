import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Hotel {

  private Collection<OneBedroom> oneBedroomList = new ArrayList<>();
  private Collection<Standard> standardList = new ArrayList<>();
  private Collection<Penthouse> penthouseList = new ArrayList<>();
  private Collection<Room> allRooms = new ArrayList<>();


  Hotel(Collection<OneBedroom> oneBedroomList, Collection<Standard> standardList,
        Collection<Penthouse> penthouseList) {
    this.oneBedroomList = oneBedroomList;
    this.standardList = standardList;
    this.penthouseList = penthouseList;
    allRooms.addAll(oneBedroomList);
    allRooms.addAll(standardList);
    allRooms.addAll(penthouseList);
  }

}

