package users;

public class User {
    public int id;

    //Constructer to create a new User
    public User(int id) {
        this.id = id;
    }

    //Delete User
    public void userDelete(User user) {
        user = null;
    }

    //Getter
    public int getId() {
        return id;
    }

    /*
    //Add User
    public void addUser()
    {
        INSERT INTO dbo.User_454 DEFAULT VALUES
    }
    */
}
