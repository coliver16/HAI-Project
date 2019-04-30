package items;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.*;

public class Room {
    private rooms room;

    public Room(String r) {
        //room = rooms.valueOf(r);
        room = Enum.valueOf(Room.rooms.class, r);

    }

    public Room(rooms r) {
        room = r;
    }
    public enum rooms {
        Attic, Basement, Livingroom, Diningroom, Bedroom, Kitchen, Bathroom, Office, Garage, Gameroom, Other
    }

    public rooms getStatus() {
        return room;
    }
    public String getRoom() {
        return room.toString();
    }

    public void setRoom(String r) {
        room = rooms.valueOf(r);
    }

    public void setStatus(rooms r) {
        room = r;
    }
    EnumSet<rooms> allRooms = EnumSet.allOf(rooms.class);
    List<rooms> listOfRooms = new ArrayList<>(allRooms);
}
