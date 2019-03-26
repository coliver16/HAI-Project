package userInterface.manageItems;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import userInterface.GuiNavigator;

public class viewItemsGUIController {

    private String name = "John Doe";

    Boolean loggedIn = true;

    @FXML
    private Label whyHai = new Label();

    @FXML
    private Label custServPortal = new Label();

    @FXML
    private Label contactUs = new Label();

    @FXML
    private ImageView cloudLogo;

    @FXML
    private Label message = new Label();

    @FXML
    private TableView itemList = new TableView();

    @FXML
    public void initialize() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.BLACK);

        whyHai.setText("Why HAI");
        whyHai.setFont(Font.font("Tahoma",15));
        whyHai.setTextFill(Color.rgb(255,255,255));
        whyHai.setEffect(dropShadow);

        custServPortal.setText("Customer Service Portal");
        custServPortal.setFont(Font.font("Tahoma",15));
        custServPortal.setTextFill(Color.rgb(255,255,255));
        custServPortal.setEffect(dropShadow);

        contactUs.setText("Contact Us");
        contactUs.setFont(Font.font("Tahoma",15));
        contactUs.setTextFill(Color.rgb(255,255,255));
        contactUs.setEffect(dropShadow);

        message.setText(name + "'s household items:");
        message.setFont(Font.font("Tahoma",35));
        message.setTextFill(Color.rgb(255,255,255));
        message.setEffect(dropShadow);


        Rectangle clip = new Rectangle(cloudLogo.getFitWidth(), cloudLogo.getFitHeight());

        clip.setArcWidth(20);
        clip.setArcHeight(20);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage cloud = cloudLogo.snapshot(parameters, null);

        cloudLogo.setClip(null);
        cloudLogo.setEffect((new DropShadow(20, Color.BLACK)));

        cloudLogo.setImage(cloud);

        TableColumn<String, Item> column1 = new TableColumn<>("Item No.");
        column1.setCellValueFactory(new PropertyValueFactory<>("itemNo"));
        TableColumn<String, Item> column2 = new TableColumn<>("Room");
        column2.setCellValueFactory(new PropertyValueFactory<>("room"));
        TableColumn<String, Item> column3 = new TableColumn<>("Category");
        column3.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<String, Item> column4 = new TableColumn<>("Product Type");
        column4.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<String, Item> column5 = new TableColumn<>("Make");
        column5.setCellValueFactory(new PropertyValueFactory<>("make"));
        TableColumn<String, Item> column6 = new TableColumn<>("Model");
        column6.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn<String, Item> column7 = new TableColumn<>("Serial");
        column7.setCellValueFactory(new PropertyValueFactory<>("serial"));
        TableColumn<String, Item> column8 = new TableColumn<>("Receipt");
        column8.setCellValueFactory(new PropertyValueFactory<>("receipt"));
        TableColumn<String, Item> column9 = new TableColumn<>("Photo");
        column9.setCellValueFactory(new PropertyValueFactory<>("photo"));
        TableColumn<String, Item> column10 = new TableColumn<>("Value");
        column10.setCellValueFactory(new PropertyValueFactory<>("value"));
        TableColumn<String, Item> column11 = new TableColumn<>("Misc Comments");
        column11.setCellValueFactory(new PropertyValueFactory<>("comments"));

        column1.setMinWidth(50);
        column2.setMinWidth(100);
        column3.setMinWidth(100);
        column4.setMinWidth(100);
        column5.setMinWidth(100);
        column6.setMinWidth(125);
        column7.setMinWidth(100);
        column8.setMinWidth(100);
        column9.setMinWidth(100);
        column10.setMinWidth(100);
        column11.setMinWidth(225);




        itemList.getColumns().add(column1);
        itemList.getColumns().add(column2);
        itemList.getColumns().add(column3);
        itemList.getColumns().add(column4);
        itemList.getColumns().add(column5);
        itemList.getColumns().add(column6);
        itemList.getColumns().add(column7);
        itemList.getColumns().add(column8);
        itemList.getColumns().add(column9);
        itemList.getColumns().add(column10);
        itemList.getColumns().add(column11);

        itemList.getItems().add(new Item("00001", "Living Room", "Electronics", "Television", "Sony", "Bravia", "12345567", "receipt", "photo","$1200", "Netflix-n-chill"));
        itemList.getItems().add(new Item("00005", "Living Room", "Electronics", "BluRay Player", "Sony", "Something", "5498138", "receipt", "photo","$200", "For watching smut"));
        itemList.getItems().add(new Item("00027", "Living Room", "Furniture", "Couch", "Lazy Boy", "Black Panther", "NA", "receipt", "photo","$2000", "Unicorn Leather"));
        itemList.getItems().add(new Item("00055", "Game Room", "Electronics", "Computer", "Apple", "MacBook", "66758", "receipt", "photo","$99900", "Cuz I'm hip"));
        itemList.getItems().add(new Item("00068", "Game Room", "Electronics", "Speakers", "Dolby", "Atmos", "9875", "receipt", "photo","$400", "Eargasm"));
        itemList.getItems().add(new Item("00075", "Kitchen", "Appliance", "Refrigerator", "GE", "Frigerator 2000", "64879531", "receipt", "photo","$800", "Keeping my shit cold since I 2018"));
        itemList.getItems().add(new Item("00101", "Bed Room", "Electronics", "Television", "LG", "TVinator 21000", "16345", "receipt", "photo","$200", "Watching more smut"));
        itemList.getItems().add(new Item("00112", "Bed Room", "Jewelry", "Necklace", "Pandora", "Disney Collection", "3468975", "receipt", "photo","$1200", "Fancy stuff"));
        itemList.getItems().add(new Item("00162", "Garage", "Tools", "Tools", "Craftsman", "Carkit", "548768", "receipt", "photo","$400", "For fixing stuff"));

    }

    public class Item {
        private String itemNo;
        private String room;
        private String category;
        private String type;
        private String make;
        private String model;
        private String serial;
        private String receipt;
        private String photo;
        private String value;
        private String comments;

        public Item() {}

        public Item(String item, String room, String cat, String type, String make, String model, String serial, String receipt, String photo, String value, String comment) {
            this.itemNo = item;
            this.room = room;
            this.category = cat;
            this.type = type;
            this.make = make;
            this.model = model;
            this.serial = serial;
            this.receipt = receipt;
            this.photo = photo;
            this.value = value;
            this.comments = comment;
        }

        public String getItemNo() { return itemNo;}
        public String getRoom() { return room;}
        public String getCategory() { return category;}
        public String getType() { return type;}
        public String getMake() { return make;}
        public String getModel() { return model;}
        public String getSerial() { return serial;}
        public String getReceipt() { return receipt;}
        public String getPhoto() { return photo;}
        public String getValue() { return value;}
        public String getComments() { return comments;}

    }
}
