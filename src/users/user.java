package users;

public class user {
    public int id;

    //Constructer to create a new user
    public user(int id) {
        this.id = id;
    }

    //Delete user
    public void userDelete(user user) {
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
