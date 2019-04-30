/*package items;

import org.junit.Test;
import org.hamcrest.*;

import static org.junit.Assert.*;

public class ItemTest {
    //Create dummy variables to create item
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

    //Initialize and instantiate item
    Item item = new Item(itemNo,room, category, type, make,model,serial, receipt, photo, value, comments);

    //Testing for itemUpdate
    @Test
    public void itemSetGet() {

      //Alter variables
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

      //Update item
      item.itemUpdate(item, room, category, type, make,model,serial, receipt, photo, value, comments);

      //Test if getters return proper values
      assertEquals(item.getRoom().getRoom(), Room.rooms.Attic);
      assertEquals(item.getMake(), "make");
      assertEquals(item.getModel(), "model");
      assertEquals(item.getSerial(), "serial");
      assertEquals(item.getReceipt(), "receipt");
      assertEquals(item.getPhoto(), "photo");
      assertEquals(item.getValue(), 1);
      assertEquals(item.getComments(), "comments");

      //Call setters
      item.setPhoto("issa photo");
      item.setItemNo(2);
      item.setReceipt("issa receipt");

      //Test if setters properly changed values
      assertEquals(item.getItemNo(), 2);
      assertEquals(item.getReceipt(), "issa receipt");
      assertEquals(item.getPhoto(), "issa photo");

      //More assertions
      assertNotEquals(item.getRoom().getRoom(), _room.getRoom());
      assertNotEquals(item.getMake(), _make);
      assertNotEquals(item.getModel(), _model);
      assertNotEquals(item.getSerial(), _serial);
      assertNotEquals(item.getValue(), String.valueOf(_value));
      assertNotEquals(item.getComments(), _comments);
    }

    //Testing for itemUpdate
    @Test
    public void itemUpdate() {

        //Alter variables
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

        //Call to update
        item.itemUpdate(item, room, category, type, make,model,serial, receipt, photo, value, comments);

        //Assertions to ensure item is updated
        assertEquals(item.getItemNo(), itemNo);
        assertEquals(item.getRoom().getRoom(), _room.getRoom());
        assertEquals(item.getMake(), _make);
        assertEquals(item.getModel(), _model);
        assertEquals(item.getSerial(), _serial);
        assertEquals(item.getReceipt(), _receipt);
        assertEquals(item.getPhoto(), _photo);
        assertEquals(item.getValue(), String.valueOf(_value));
        assertEquals(item.getComments(), _comments);

        //More assertions
        assertNotEquals(item.getRoom().getRoom(), room.getRoom());
        assertNotEquals(item.getMake(), make);
        assertNotEquals(item.getModel(), model);
        assertNotEquals(item.getSerial(), serial);
        assertNotEquals(item.getReceipt(), receipt);
        assertNotEquals(item.getPhoto(), photo);
        assertNotEquals(item.getValue(), String.valueOf(value));
        assertNotEquals(item.getComments(), comments);
    }

    //Compare testing
    @Test
    public void compare()
    {
      //Alter variables
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

      //Update item
      item.itemUpdate(item, room, category, type, make,model,serial, receipt, photo, value, comments);

      //Create a second identical item
      Item item2 = new Item(itemNo,room, category, type, make,model,serial, receipt, photo, value, comments);

      //Alter variables again
      Room _room = new Room(Room.rooms.Attic);
      Category _category = new Category(Category.categories.Appliances);
      Type type = new Type("type2");
      String _make = "make2";
      String _model = "model2";
      String _serial = "serial2";
      String _receipt = "receipt2";
      String _photo = "photo2";
      Float _value = Float.valueOf(2);
      String _comments = "comment2";

      //Create a different item
      Item difItem = new Item(itemNo, room, category, type, make, model, serial, receipt, photo, value, comments);

      //Assertions between identical and non-identical items
      assertEquals(item.compare(item, item2), true);
      assertEquals(item.compare(item, item3), false);
    }

    //Testing for itemDelete
    @Test
    public void itemDelete()
    {
      //Call function
      item.itemDelete();
      //Confirm functionality
      assertEquals(item.isDeleted(), true);
    }
}*/
