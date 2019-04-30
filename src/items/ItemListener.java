package items;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import eventBus.EventBusFactory;
import local.ParseEvent;

import java.util.List;

public class ItemListener {

    @Subscribe
    public void sendParseEvent(ParseEvent parseEvent) {
        System.out.println();
    }
}
