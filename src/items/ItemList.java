package items;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import eventBus.EventBusFactory;
import local.ParseEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Public class holding list of user items.
 */
public class ItemList {
    private EventBus eventBus = EventBusFactory.getEventBus();
    public void initialize() {
        EventHandler handler = new EventHandler();
        eventBus.register(handler);
    }

    public class EventHandler {
        @Subscribe
        public void parseEvent(ParseEvent event) {
            System.out.println("Made it to event");
            itemList = event.getMessage();
        }
    }

    public static List<Item> itemList = new ArrayList<Item>();

    public static void setItemList(List<Item> i) {
        itemList = i;
    }


    public static List<Item> getItemList() {
        return itemList;
    }

    public static List<Item> addItem(Item i) {
        itemList.add(i);
        return itemList;
    }

    public Item deleteItem(Item i){
        int j = itemList.indexOf(i);
        itemList.get(j).itemDelete();
        return itemList.get(j);
    }

}
