package items;

import com.google.common.eventbus.Subscribe;
import local.ParseEvent;

import java.util.ArrayList;
import java.util.List;

public class ItemList {

    public class EventHandler {
        @Subscribe
        public void parseEvent(ParseEvent event) {
            System.out.println("Made it to event");
            itemList = event.getMessage();
            //System.out.println("Event: " + event.toString());
            //System.out.println(itemImports.get(0).make);
            //for (Object i : itemImports) {
             //   itemList.getItems().add(i);
            //}
        }
    }

    public static List<Item> itemList = new ArrayList<Item>();

    public static void setItemList(List<Item> i) {
        itemList = i;
    }


    public static List<Item> getItemList() {
        return itemList;
    }

    public List<Item> addItem(Item i) {
        itemList.add(i);
        return itemList;
    }

    public Item deleteItem(Item i){
        int j = itemList.indexOf(i);
        itemList.get(j).itemDelete();
        return itemList.get(j);
    }

}
