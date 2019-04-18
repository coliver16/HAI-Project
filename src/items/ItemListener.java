package items;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import eventBus.EventBusFactory;
import local.ParseEvent;

import java.util.List;

public class ItemListener {

    //EventBus eventBus = EventBusFactory.getEventBus();

    @Subscribe
    public void sendParseEvent(ParseEvent parseEvent) {
        System.out.println();
    }
    /*public void sendParseEvent(List<Item> items) {
        ParseEvent parseEvent = new ParseEvent(items);
        eventBus.post(parseEvent);
    }*/

    /*@Subscribe
    public void sendItemEvent(Item item) {
        ItemEvent itemEvent = new ItemEvent(item);
        eventBus.post(itemEvent);
    }*/
}
