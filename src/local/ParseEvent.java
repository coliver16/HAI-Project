package local;

import items.Item;

import java.util.ArrayList;
import java.util.List;

public class ParseEvent {
    private final List<Item> message;

    public ParseEvent(List<Item> message) {
        this.message = message;
    }

    public List<Item> getMessage() {
        return message;
    }
}

