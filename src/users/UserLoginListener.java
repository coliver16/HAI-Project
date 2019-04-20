package users;

import com.google.common.eventbus.Subscribe;

public class UserLoginListener {

    @Subscribe
    public void sendLoginEvent(UserLoginEvent userLoginEvent) { }
}
