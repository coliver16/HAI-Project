package items;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.*;

public class Room {
    private rooms room;

    public Room(String r) {
        room = rooms.valueOf(r);
    }

    public Room(rooms r) {
        room = r;
    }
    public enum rooms {
        Livingroom, Bedroom, Kitchen, Bathroom, Office, Garage, Gameroom, Other
    }

    public rooms getStatus() {
        return room;
    }

    public void setStatus(String r) {
        room = rooms.valueOf(r);
    }

    public void setStatus(rooms r) {
        room = r;
    }
    EnumSet<rooms> allRooms = EnumSet.allOf(rooms.class);
    List<rooms> listOfRooms = new ArrayList<>(allRooms);
}




