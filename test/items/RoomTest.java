package items;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

    @Test void constructors()
    {
      Room r0 = new Room("Attic");
      Room r1 = new Room(Room.rooms.Attic);
      Room r2 = new Room(Room.rooms.Diningroom);
      assertEquals(r0, r1);
      assertNotEquals(r1, r2);
    }

    @Test
    public void getStatus()
    {
      assertEquals(r0.getStatus, Room.rooms.Attic);
      assertNotEquals(r0.getStatus, Room.rooms.Diningroom);
    }

    @Test
    public void getRoom()
    {
      assertEquals(r0.getRoom(), "Attic");
      assertNotEquals(r0.getRoom(), "Diningroom");
    }

    @Test
    public void setRoom()
    {
      r0.setRoom("DiningRoom")
      assertEquals(r0.getRoom(), "Diningroom");
      assertNotEquals(r0.getRoom(), "Attic");
    }

    @Test
    public void setStatus()
    {
      r0.setStatus(Room.rooms.Attic);
      assertEquals(r0.getStatus(), Room.rooms.Attic);
      assertNotEquals(r0.getStatus(), Room.rooms.Diningroom);
    }
}
