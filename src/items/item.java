package items;
import users.user;

public class item {
    public int itemNo;
    public user user;
    public room room;
    public category category;
    public type type;
    public String make;
    public String model;
    public String serial;
    public String receipt;
    public String photo;
    public float value;
    public String comments;

    //Constructor to create an item
    public item(int item, user user, room room, category category, type type, String make, String model, String serial, String receipt, String photo, float value, String comments) {
        this.itemNo = item;
        this.user = user;
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

    //Constructor for update an item
    public void itemUpdate(item oldItem, user user, room room, category category, type type, String make, String model, String serial, String receipt, String photo, float value, String comments) {
        // If any field is not provided, the item's old inputs will be used.
        if (user == null) {
            this.user = oldItem.getUser();
        }
        else {
            this.user = user;
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

        //A user should update the value of an item every time they update their item, therefore they must enter a value when updating
        this.value = value;

        if (comments.equals("")) {
            this.comments = oldItem.getComments();
        }
        else {
            this.comments = comments;
        }
    }

    /*
    //Add an item
    public void addItem (item newItem, user user, room room, category category, type type, String make, String model, String serial, String receipt, String photo, float value, String comments)
    {
        INSERT INTO dbo.Item_454 (user_own, item_room, item_category, item_type, item_make, item_model, item_serial_num, item_receipt, item_image, item_price, item_comments)
            VALUES (user,
                    room,
                    category,
                    type,
                    make,
                    model,
                    serial,
                    receipt,
                    photo,
                    value,
                    comments)
    }
     */

    //Delete an item
    public void itemDelete(item item) {
        item = null;
    }

    //Getters
    public int getItemNo() {
        return itemNo;
    }

    public user getUser() {
        return user;
    }

    public room getRoom() {
    return room;
    }

    public category getCategory() {
        return category;
    }

    public type getType() {
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