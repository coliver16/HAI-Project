package local;

import items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * ParseEvent to contain parsed event item for passing data between threads
 * Part of EventBus system
 */
public class ParseEvent {

    private List<Item> message;

    public ParseEvent(List<Item> message) {
        this.message = message;
    }

    public List<Item> getMessage() {
        return message;
    }
}

