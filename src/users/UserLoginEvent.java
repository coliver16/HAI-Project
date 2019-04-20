package users;

public class UserLoginEvent {
    private Profile userProfile;

    public UserLoginEvent(Profile profile) { this.userProfile = profile;}

    public Profile getMessage() { return userProfile;}
}
