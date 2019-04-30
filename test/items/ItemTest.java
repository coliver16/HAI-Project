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
    public void itemSetGet() {

        Room _room = new Room(Room.rooms.Kitchen);
        Category _category = new Category(Category.categories.Antiques);
        Type type = new Type("1");
        String _make = "2";
        String _model = "3";
        String _serial = "4";
        String _receipt = "5";
        String _photo = "5";
        Float _value = Float.valueOf(12345);
        String _comments = "1234";
    }


    @Test
    public void itemDelete() {
    }

    @Test
    public void getItemNo() {
    }

    @Test
    public void setItemNo() {
    }

    @Test
    public void getRoom() {
    }

    @Test
    public void getCategory() {
    }

    @Test
    public void getType() {
    }

    @Test
    public void getMake() {
    }

    @Test
    public void getModel() {
    }

    @Test
    public void getSerial() {
    }

    @Test
    public void getReceipt() {
    }

    @Test
    public void setReceipt() {
    }

    @Test
    public void getPhoto() {
    }

    @Test
    public void setPhoto() {
    }

    @Test
    public void getKey() {
    }

    @Test
    public void setKey() {
    }

    @Test
    public void getValue() {
    }

    @Test
    public void getComments() {
    }

    @Test
    public void isDeleted() {
    }

    @Test
    public void compare() {
    }
}