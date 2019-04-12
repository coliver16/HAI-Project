package items;

import items.Item;
public class ItemEvent {
    private final Item message;

    public ItemEvent(Item message) {
        this.message = message;
    }

    public Item getMessage() {
        return message;
    }
}
