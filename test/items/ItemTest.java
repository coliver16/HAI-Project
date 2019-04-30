package items;

import org.junit.Test;
import org.hamcrest.*;

import static org.junit.Assert.*;

public class ItemTest {
    int itemNo = 1;
    Room room = new Room(Room.rooms.Livingroom);
    Category category = new Category(Category.categories.Electronics);
    Type type = new Type("TV");
    String make = "Sony";
    String model = "Plaything";
    String serial = "1a2a3a4a";
    String receipt = "none";
    String photo = "no photo";
    Float value = Float.valueOf(1234);
    String comments = "No comments at this time";

    Item item = new Item(itemNo,room, category, type, make,model,serial, receipt, photo, value, comments);

    @Test
    public void itemSetGet() {

      Room _room = new Room(Room.rooms.Kitchen);
      Category _category = new Category(Category.categories.Antiques);
      Type type = new Type("type");
      String _make = "make";
      String _model = "model";
      String _serial = "serial";
      String _receipt = "receipt";
      String _photo = "photo";
      Float _value = Float.valueOf(1);
      String _comments = "comments";

      item.itemUpdate(item, room, category, type, make,model,serial, receipt, photo, value, comments);

      assertEquals(item.getRoom().getRoom(), Room.rooms.Attic);
      assertEquals(item.getMake(), "make");
      assertEquals(item.getModel(), "model");
      assertEquals(item.getSerial(), "serial");
      assertEquals(item.getReceipt(), "receipt");
      assertEquals(item.getPhoto(), "photo");
      assertEquals(item.getValue(), 1);
      assertEquals(item.getComments(), "comments");

      item.setPhoto("issa photo");
      item.setItemNo(2);
      item.setReceipt("issa receipt");
      assertEquals(item.getItemNo(), 2);
      assertEquals(item.getReceipt(), "issa receipt");
      assertEquals(item.getPhoto(), "issa photo");

      assertNotEquals(item.getRoom().getRoom(), _room.getRoom());
      assertNotEquals(item.getMake(), _make);
      assertNotEquals(item.getModel(), _model);
      assertNotEquals(item.getSerial(), _serial);
      assertNotEquals(item.getValue(), String.valueOf(_value));
      assertNotEquals(item.getComments(), _comments);
    }

    @Test
    public void itemUpdate() {
        Room _room = new Room(Room.rooms.Kitchen);
        Category _category = new Category(Category.categories.Antiques);
        Type type = new Type("Rocking Chair");
        String _make = "udpateMake";
        String _model = "updateModel";
        String _serial = "updatedSerial";
        String _receipt = "Some";
        String _photo = "no new photo";
        Float _value = Float.valueOf(1234564);
        String _comments = "No new comments at this time";

        item.itemUpdate(item, room, category, type, make,model,serial, receipt, photo, value, comments);

        assertEquals(item.getItemNo(), itemNo);
        assertEquals(item.getRoom().getRoom(), _room.getRoom());
        assertEquals(item.getMake(), _make);
        assertEquals(item.getModel(), _model);
        assertEquals(item.getSerial(), _serial);
        assertEquals(item.getReceipt(), _receipt);
        assertEquals(item.getPhoto(), _photo);
        assertEquals(item.getValue(), String.valueOf(_value));
        assertEquals(item.getComments(), _comments);

        assertNotEquals(item.getRoom().getRoom(), room.getRoom());
        assertNotEquals(item.getMake(), make);
        assertNotEquals(item.getModel(), model);
        assertNotEquals(item.getSerial(), serial);
        assertNotEquals(item.getReceipt(), receipt);
        assertNotEquals(item.getPhoto(), photo);
        assertNotEquals(item.getValue(), String.valueOf(value));
        assertNotEquals(item.getComments(), comments);
    }

    @Test
    public void compare()
    {
      Room _room = new Room(Room.rooms.Kitchen);
      Category _category = new Category(Category.categories.Antiques);
      Type type = new Type("type");
      String _make = "kake";
      String _model = "model";
      String _serial = "serial";
      String _receipt = "receipt";
      String _photo = "photo";
      Float _value = Float.valueOf(1);
      String _comments = "comments";

      item.itemUpdate(item, room, category, type, make,model,serial, receipt, photo, value, comments);

      Item item2 = new Item(itemNo,room, category, type, make,model,serial, receipt, photo, value, comments);

      Room _room = new Room(Room.rooms.Attic);
      Category _category = new Category(Category.categories.Appliances);
      Type type = new Type("type2");
      String _make = "kake2";
      String _model = "model2";
      String _serial = "serial2";
      String _receipt = "receipt2";
      String _photo = "photo2";
      Float _value = Float.valueOf(2);
      String _comments = "comment2";

      Item difItem = new Item(itemNo, room, category, type, make, model, serial, receipt, photo, value, comments);

      assertEquals(item.compare(item, item2), true);
      assertEquals(item.compare(item, item3), false);
    }

    @Test
    public void itemDelete()
    {
      item.itemDelete();

      assertEquals(item.isDeleted(), true);
    }
}
