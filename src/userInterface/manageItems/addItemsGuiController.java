package userInterface.manageItems;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class addItemsGuiController {
    @FXML
    private ComboBox room;

    @FXML
    private Label roomLabel;

    @FXML
    private ComboBox category;

    @FXML
    private Label categoryLabel;

    @FXML
    private TextField productType;

    @FXML
    private Label productLabel;

    @FXML
    private TextField make;

    @FXML
    private Label makeLabel;

    @FXML
    private TextField model;

    @FXML
    private Label modelLabel;

    @FXML
    private TextField serial;

    @FXML
    private Label serialLabel;

    @FXML
    private TextField value;

    @FXML
    private Label valueLabel;

    @FXML
    private TextArea comments;

    @FXML
    private Label commentslabel;

    @FXML
    private Button submit;

    @FXML
    private Button submitAndAdd;

    @FXML
    private Button cancel;

    @FXML
    private ImageView itemImage;

    @FXML
    private ImageView imageReceipt;

    ObservableList<String> roomOptions =
            FXCollections.observableArrayList(
                    "Living Room", "Dining Room", "Bed Room (Master)", "Bedroom (Child)", "Bedroom (Guest)", "Kitchen", "Garage", "Attic", "Basement", "Other"
            );
    private String rooms[] = {"Living Room", "Dining Room", "Bed Room (Master)", "Bedroom (Child)", "Bedroom (Guest)", "Kitchen", "Garage", "Attic", "Basement", "Other"};

    ObservableList<String> categoryOptions =
            FXCollections.observableArrayList(
                    "Antiques", "Appliances", "Automotive", "Clothing", "Collectibles", "Furniture", "Electronic", "Jewelry", "Musical Instruments", "tools", "Other"
            );
    private String categories[] = {"Antiques", "Appliances", "Automotive", "Clothing", "Collectibles", "Furniture", "Electronic", "Jewelry", "Musical Instruments", "tools", "Other"};

    @FXML
    public void initialize() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.BLACK);

        roomLabel.setText("Room");
        roomLabel.setFont(Font.font("Tahoma",15));
        roomLabel.setTextFill(Color.rgb(255,255,255));
        roomLabel.setEffect(dropShadow);


        //Room = new ComboBox(FXCollections
        //        .observableArrayList(rooms));
        //Room = new ComboBox(roomOptions);
        room.setItems(roomOptions);
        //Room.getItems().setAll(rooms);
        //Room.getItems().addAll("Living Room", "Dining Room", "Bed Room (Master)", "Bedroom (Child)", "Bedroom (Guest)", "Kitchen", "Garage", "Attic", "Basement", "Other");
        room.setEditable(true);

        categoryLabel.setText("Category");
        categoryLabel.setFont(Font.font("Tahoma",15));
        categoryLabel.setTextFill(Color.rgb(255,255,255));
        categoryLabel.setEffect(dropShadow);

        //Category = new ComboBox(FXCollections
        //        .observableArrayList(categories));
        //Category.getItems().addAll("Antiques", "Appliances", "Automotive", "Clothing", "Collectibles", "Furniture", "Electronic", "Jewelry", "Musical Instruments", "tools", "Other");
        //Category = new ComboBox(categoryOptions);
        category.setItems(categoryOptions);
        category.setEditable(true);

        productLabel.setText("Product");
        productLabel.setFont(Font.font("Tahoma",15));
        productLabel.setTextFill(Color.rgb(255,255,255));
        productLabel.setEffect(dropShadow);

        productType.setPromptText("Type of Item");

        makeLabel.setText("Make");
        makeLabel.setFont(Font.font("Tahoma",15));
        makeLabel.setTextFill(Color.rgb(255,255,255));
        makeLabel.setEffect(dropShadow);

        make.setPromptText("Manufacturer");

        modelLabel.setText("Model");
        modelLabel.setFont(Font.font("Tahoma",15));
        modelLabel.setTextFill(Color.rgb(255,255,255));
        modelLabel.setEffect(dropShadow);

        model.setPromptText("Model Type?");

        serialLabel.setText("Serial");
        serialLabel.setFont(Font.font("Tahoma",15));
        serialLabel.setTextFill(Color.rgb(255,255,255));
        serialLabel.setEffect(dropShadow);

        serial.setPromptText("Serial (if applicable)");

        valueLabel.setText("Value (USD)");
        valueLabel.setFont(Font.font("Tahoma",15));
        valueLabel.setTextFill(Color.rgb(255,255,255));
        valueLabel.setEffect(dropShadow);

        value.setPromptText("200.00");

        commentslabel.setText("Comments");
        commentslabel.setFont(Font.font("Tahoma",15));
        commentslabel.setTextFill(Color.rgb(255,255,255));
        commentslabel.setEffect(dropShadow);

        comments.setPromptText("Any special comments?");

        submit.setText("Add Item");
        submitAndAdd.setText("Add Item and Create New");
        submitAndAdd.wrapTextProperty().set(true);
        cancel.setText("Cancel Item");


        Rectangle clip = new Rectangle(itemImage.getFitWidth(), itemImage.getFitHeight());
        Rectangle clip1 = new Rectangle(imageReceipt.getFitWidth(), imageReceipt.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        clip1.setArcWidth(20);
        clip1.setArcHeight(20);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage cloud = itemImage.snapshot(parameters, null);
        WritableImage user = imageReceipt.snapshot(parameters,null);

        itemImage.setClip(null);
        imageReceipt.setEffect((new DropShadow(20, Color.BLACK)));

        itemImage.setClip(null);
        imageReceipt.setEffect((new DropShadow(20,Color.BLACK)));
        itemImage.setImage(cloud);
        imageReceipt.setImage(user);

    }
    ;

}
