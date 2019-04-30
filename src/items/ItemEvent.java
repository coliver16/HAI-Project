package items;

/**
 * ItemEvent to pass item object between threads.
 * Used with EventBus.
 */
public class ItemEvent {
    private Item message;

    public ItemEvent(Item message) {
        this.message = message;
    }

    public Item getMessage() {
        return message;
    }
}
