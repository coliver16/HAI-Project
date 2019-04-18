package items;
import java.lang.*;
//import users.User;

public class Item {
    private int itemNo;
    //public User userno;
    private Room room;
    private Category category;
    private Type type;
    private String make;
    private String model;
    private String serial;
    private String receipt;
    private String photo;
    private float value;
    private String comments;
    private Boolean deleted = false;

    //Constructor to create an Item
    public Item(int item, /*User user,*/ Room room, Category category, Type type, String make, String model, String serial, String receipt, String photo, float value, String comments) {
        this.itemNo = item;
        //this.userno = user;
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
    public void itemUpdate(Item oldItem, /*User user,*/ Room room, Category category, Type type, String make, String model, String serial, String receipt, String photo, float value, String comments) {
        // If any field is not provided, the Item's old inputs will be used.
        /*if (user == null) {
            this.userno = oldItem.getUser();
        }
        else {
            this.userno = user;
        }*/

        if (room == null) {
            this.room = oldItem.getRoom();
        } else {
            this.room = room;
        }

        if (category == null) {
            this.category = oldItem.getCategory();
        } else {
            this.category = category;
        }

        if (type == null) {
            this.type = oldItem.getType();
        } else {
            this.type = type;
        }

        if (make.equals("")) {
            this.make = oldItem.getMake();
        } else {
            this.make = make;
        }

        if (model.equals("")) {
            this.model = oldItem.getModel();
        } else {
            this.model = model;
        }

        if (serial.equals("")) {
            this.serial = oldItem.getSerial();
        } else {
            this.serial = serial;
        }

        if (receipt.equals("")) {
            this.receipt = oldItem.getReceipt();
        } else {
            this.receipt = receipt;
        }

        if (photo.equals("")) {
            this.photo = oldItem.getPhoto();
        } else {
            this.photo = photo;
        }

        //A User should update the value of an Item every time they update their Item, therefore they must enter a value when updating
        this.value = value;

        if (comments.equals("")) {
            this.comments = oldItem.getComments();
        } else {
            this.comments = comments;
        }
        //

    }


    //Delete an Item
    public void itemDelete() {
        this.deleted = true;
    }

    //Getters
    public int getItemNo() {
        return itemNo;
    }

    /*public User getUser() {
        return userno;
    }*/

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


    public boolean Compare(Item two) {
        Float f1 = new Float(this.getValue());
        Float f2 = new Float(two.getValue());
        boolean equals = true;
        if (!this.getRoom().equals(two.getRoom())) {
            equals = false;
        }
        if (!this.getCategory().equals(two.getCategory())) {
            equals = false;
        }
        if (!this.getType().equals(two.getType())) {
            equals = false;
        }
        if (!this.getMake().equals(two.getMake())) {
            equals = false;
        }
        if (!this.getModel().equals(two.getModel())) {
            equals = false;
        }
        if (!this.getSerial().equals(two.getSerial())) {
            equals = false;
        }
        if (!this.getReceipt().equals(two.getReceipt())) {
            equals = false;
        }
        if (!this.getPhoto().equals(two.getPhoto())) {
            equals = false;
        }
        if (Float.compare(f1, f2) != 0) {
            equals = false;
        }
        if (!this.getComments().equals(two.getComments())) {
            equals = false;
        }
        return equals;
    }
}
