package items;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.*;

public class room {
    public enum rooms {
        Livingroom, Bedroom, Kitchen, Bathroom, Office, Garage, Gameroom;
    }

    EnumSet<rooms> allRooms = EnumSet.allOf(rooms.class);
    List<rooms> listOfRooms = new ArrayList<>(allRooms);
}




