package items;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemListTest {

    Room _room = new Room(Room.rooms.Kitchen);
    Category _category = new Category(Category.categories.Antiques);
    Type type = new Type("type");
    String _make = "make";
    String _model = "model";
    String _serial = "serial";
    String _receipt = "receipt";
    String _photo = "photo";
    Float _value = Float.valueOf(0);
    String _comments = "comments";

    Item it0 = new Item(itemNo,room, category, type, make,model,serial, receipt, photo, value, comments);

    Room _room = new Room(Room.rooms.Diningroom);
    Category _category = new Category(Category.categories.Appliances);
    Type type = new Type("type1");
    String _make = "make1";
    String _model = "model1";
    String _serial = "serial1";
    String _receipt = "receipt1";
    String _photo = "photo1";
    Float _value = Float.valueOf(1);
    String _comments = "comments1";

    Item it1 = new Item(itemNo,room, category, type, make,model,serial, receipt, photo, value, comments);

    ItemList il = new ItemList();
    ItemList emptyList = new ItemList();
    ItemList nonEmpty = new ItemList();

    nonEmpty.addItem(it1);

    @Test
    public void getItemList()
    {
      assertEquals(il.getItemList(), emptyList.getItemList());
      assertNotEquals(il.getItemList(), nonEmpty.getItemList())
    }

    @Test
    public void addDeleteItem()
    {
      il.addItem(it0);
      il.addItem(it1);

      assertEquals(il.getItemList.contains(t0), true);
      assertEquals(il.getItemList.contains(t1), true);
      assertNotEquals(il.getItemList.contains(t0), false);
      assertNotEquals(il.getItemList.contains(t1), false);

      il.deleteItem(it0);
      assertEquals(il.getItemList.contains(t0), false);
      assertNotEquals(il.getItemList.contains(t0), true);

      il.deleteItem(it1);
      assertEquals(il.getItemList.contains(t1), false);
      assertNotEquals(il.getItemList.contains(t1), true);
    }

    @Test
    public void setItemList()
    {
      ItemList.setItemList(emptyList);
      assertEquals(il.getItemList(), emptyList.getItemList());
      assertNotEquals(il.getItemList(), nonEmpty.getItemList());
    }
}
