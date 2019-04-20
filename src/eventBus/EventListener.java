package eventBus;

import com.google.common.eventbus.*;
import items.Item;
import items.ItemEvent;
import local.ParseEvent;
import users.Profile;
import users.UserLoginEvent;

import java.util.List;

public class EventListener {
    private int lastMessage = 0;
    private Item itemMessage;
    private Profile userProfile;
    private List<Item> list;

    @Subscribe
    public void parseEvent(ParseEvent event) {
        System.out.println("Made it to event");
        //itemImports = (List) event.getMessage();
        System.out.println("Event: " + event.toString());
        list = event.getMessage();
        //for (Object i : itemImports) {
        //    itemList.getItems().add(i);
        //}
    }

    @Subscribe
    public void userLoginEvent(UserLoginEvent event) {
        userProfile = event.getMessage();
    }

    @Subscribe
    public void listen(ItemEvent event) {
        itemMessage = event.getMessage();
    }
}
