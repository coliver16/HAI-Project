package items;
import users.User;

public class Item {
    public int itemNo;
    public User userno;
    public Room room;
    public Category category;
    public Type type;
    public String make;
    public String model;
    public String serial;
    public String receipt;
    public String photo;
    public float value;
    public String comments;

    //Constructor to create an Item
    public Item(int item, User user, Room room, Category category, Type type, String make, String model, String serial, String receipt, String photo, float value, String comments) {
        this.itemNo = item;
        this.userno = user;
        this.room = room;
        this.category = category;
        this.type = type;
        this.make = make;
        this.model = model;
        this.serial = serial;
        this.receipt = receipt;
        this.photo = photo;
        this.value = value;
        this.comments = comments;
    }

    //Constructor for update an Item
    public void itemUpdate(Item oldItem, User user, Room room, Category category, Type type, String make, String model, String serial, String receipt, String photo, float value, String comments) {
        // If any field is not provided, the Item's old inputs will be used.
        if (user == null) {
            this.userno = oldItem.getUser();
        }
        else {
            this.userno = user;
        }

        if (room == null) {
            this.room = oldItem.getRoom();
        }
        else {
            this.room = room;
        }

        if (category == null) {
            this.category = oldItem.getCategory();
        }
        else {
            this.category = category;
        }

        if (type == null) {
            this.type = oldItem.getType();
        }
        else {
            this.type = type;
        }

        if (make.equals("")) {
            this.make = oldItem.getMake();
        }
        else {
            this.make = make;
        }

        if (model.equals("")) {
            this.model = oldItem.getModel();
        }
        else {
            this.model = model;
        }

        if (serial.equals("")) {
            this.serial = oldItem.getSerial();
        }
        else {
            this.serial = serial;
        }

        if (receipt.equals("")) {
            this.receipt = oldItem.getReceipt();
        }
        else {
            this.receipt = receipt;
        }

        if (photo.equals("")) {
            this.photo = oldItem.getPhoto();
        }
        else {
            this.photo = photo;
        }

        //A User should update the value of an Item every time they update their Item, therefore they must enter a value when updating
        this.value = value;

        if (comments.equals("")) {
            this.comments = oldItem.getComments();
        }
        else {
            this.comments = comments;
        }
    }





    //Delete an Item
    public void itemDelete(Item item) {
        item = null;
    }

    //Getters
    public int getItemNo() {
        return itemNo;
    }

    public User getUser() {
        return userno;
    }

    public Room getRoom() {
    return room;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getSerial() {
        return serial;
    }

    public String getReceipt() {
        return receipt;
    }

    public String getPhoto() {
        return photo;
    }

    public float getValue() {
        return value;
    }

    public String getComments() {
        return comments;
    }
}
