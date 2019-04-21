package users;

import com.google.common.eventbus.Subscribe;

public class UserProfile {
    public class EventHandler {
        @Subscribe
        public void userLoginEvent(UserLoginEvent event) {
            userProfile = event.getMessage();
            }
    }
    private static Profile userProfile = new Profile("","","","","","","","");

    public static void setUserProfile(Profile p) {
        userProfile = p;
    }

    public static Profile getUserProfile() { return userProfile;}
}
