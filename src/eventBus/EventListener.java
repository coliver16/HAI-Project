package eventBus;

import com.google.common.eventbus.*;
import items.Item;
import items.ItemEvent;

public class EventListener {
    public int lastMessage = 0;
    public Item itemMessage;

    @Subscribe
    public void listen(ItemEvent event) {
        itemMessage = event.getMessage();
    }
}
