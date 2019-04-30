package userInterface.manageItems;

import com.google.common.eventbus.EventBus;
import eventBus.EventBusFactory;
import items.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import smart.priceSuggestor;

import javax.swing.event.ChangeEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class addItemsGuiController {
    EventBus eventBus = EventBusFactory.getEventBus();
    String imageName;
    String receiptName;
    private File defaultImage = new File("src\\userInterface\\manageItems\\noImage.png");
    private File defaultReceipt = new File("src\\userInterface\\manageItems\\noReceipt.png");
    String tempImage;
    String tempReceipt;
    final Tooltip tooltip = new Tooltip();

    private Alert invalidInput = new Alert(Alert.AlertType.ERROR);

    @FXML
    private Image imageBox = new Image(defaultImage.toURI().toString());

    @FXML
    private Image receiptBox = new Image(defaultReceipt.toURI().toString());

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
    private Button uploadImage;

    @FXML
    private ImageView itemReceipt;

    @FXML
    private Button uploadReceipt;

    private ObservableList<String> roomOptions =
            FXCollections.observableArrayList(
                    "Attic", "Basement", "Livingroom", "DiningRoom", "Bedroom", "Bathroom", "Kitchen", "Garage", "Gameroom", "Office", "Other"
            );
    private String rooms[] = {"Attic", "Basement", "Living room", "Dining room", "Bed room", "Bath room", "Kitchen", "Garage", "Game room", "Office", "Other"};

    private ObservableList<String> categoryOptions =
            FXCollections.observableArrayList(
                    "Antiques", "Appliances", "Art", "Automotive", "Clothing", "Collectibles", "Electronic", "Furniture", "Jewelry", "MusicalInstruments", "Tools", "Other"
            );
    private String categories[] = {"Antiques", "Appliances", "Art", "Automotive", "Clothing", "Collectibles", "Electronic", "Furniture", "Jewelry", "Musical Instruments", "Tools", "Other"};

    @FXML
    public void initialize() {
        itemImage.setImage(imageBox);
        itemReceipt.setImage(receiptBox);

        invalidInput.setTitle("Invalid Input!");
        invalidInput.setHeaderText("You have submitted invalid inputs.");
        invalidInput.setContentText("Please correct items highlighted in red. Resubmit when completed.");

        imageName = defaultImage.toPath().toString();
        receiptName = defaultReceipt.toPath().toString();

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.BLACK);

        roomLabel.setText("Room");
        roomLabel.setFont(Font.font("Tahoma",15));
        roomLabel.setTextFill(Color.rgb(255,255,255));
        roomLabel.setEffect(dropShadow);

        room.setItems(roomOptions);
        room.setEditable(true);

        categoryLabel.setText("Category");
        categoryLabel.setFont(Font.font("Tahoma",15));
        categoryLabel.setTextFill(Color.rgb(255,255,255));
        categoryLabel.setEffect(dropShadow);

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

        /*tooltip.setText(
                "\nHAI! We found you a suggested value.\n" +
                        "Feel free to adjust as desired!"
        );*/

       // value.setTooltip(tooltip);

        commentslabel.setText("Comments");
        commentslabel.setFont(Font.font("Tahoma",15));
        commentslabel.setTextFill(Color.rgb(255,255,255));
        commentslabel.setEffect(dropShadow);

        comments.setPromptText("Any special comments?");

        submit.setText("Add Item");
        submitAndAdd.setText("Add Item and Create New");
        submitAndAdd.wrapTextProperty().set(true);
        cancel.setText("Cancel Item");


        make.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue && !model.getText().isEmpty()) {
                    priceSuggestor p = new priceSuggestor();
                    value.setText(String.valueOf(p.Suggest(make.getText(), model.getText(), "")));
                    tooltip.setText("HAI! We found you a suggested value.\n" +
                            "Feel free to adjust as desired!");
                    tooltip.setFont(new Font("Arial", 14));
                    value.setTooltip(tooltip);
                }
            }
        });



        model.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue && !make.getText().isEmpty()) {
                    priceSuggestor p = new priceSuggestor();
                    value.setText(String.valueOf(p.Suggest(make.getText(), model.getText(), "")));
                    tooltip.setText("HAI! We found you a suggested value.\n" +
                            "Feel free to adjust as desired!");
                    tooltip.setFont(new Font("Arial", 14));
                    value.setTooltip(tooltip);
                }
            }
        });


    }

    private boolean checkInput() {
        boolean valid = true;
        if (room.getEditor().getText().isEmpty()) {
            valid = false;
            roomLabel.setTextFill(Color.rgb(255,0,0));
        }
        else {
            roomLabel.setTextFill(Color.rgb(255,255,255));
        }
        if (category.getEditor().getText().isEmpty()) {
            valid = false;
            categoryLabel.setTextFill(Color.rgb(255,0,0));
        }
        else {
            categoryLabel.setTextFill(Color.rgb(255,255,255));
        }
        if  (productType.getText().isEmpty()) {
            valid = false;
            productLabel.setTextFill(Color.rgb(255,0,0));
        }
        else {
            productLabel.setTextFill(Color.rgb(255,255,255));
        }
        if (make.getText().isEmpty()) {
            valid = false;
            makeLabel.setTextFill(Color.rgb(255,0,0));
        }
        else {
            makeLabel.setTextFill(Color.rgb(255,255,255));
        }
        if (model.getText().isEmpty()) {
            valid = false;
            modelLabel.setTextFill(Color.rgb(255,0,0));
        }
        else {
            modelLabel.setTextFill(Color.rgb(255,255,255));
        }
        if (serial.getText().isEmpty()) {
            valid = false;
            serialLabel.setTextFill(Color.rgb(255,0,0));
        }
        else {
            serialLabel.setTextFill(Color.rgb(255,255,255));
        }
        if (value.getText().isEmpty()) {
            valid = false;
            valueLabel.setTextFill(Color.rgb(255,0,0));
        }
        else {
            valueLabel.setTextFill(Color.rgb(255,255,255));
        }
        return valid;
    }

    @FXML
    private void setSubmit(ActionEvent event) throws Exception {
        if (checkInput()) {
            Room r = new Room(roomOptions.get(room.getSelectionModel().getSelectedIndex()));
            Category c = new Category(categoryOptions.get(category.getSelectionModel().getSelectedIndex()));
            Type t = new Type(productType.getText());

            Item item = new Item(0, r, c, t, make.getText(), model.getText(), serial.getText(), tempReceipt, tempImage, Float.parseFloat(value.getText()), comments.getText());

            ItemEvent itemEvent = new ItemEvent(item);
            eventBus.register(itemEvent);
            eventBus.post(itemEvent);
            Stage stage = (Stage) submit.getScene().getWindow();
            stage.close();
        }
        else {
            invalidInput.showAndWait();
        }
    }

    @FXML
    private void setCancel(ActionEvent event) {
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void setSubmitAndAdd(ActionEvent event) throws Exception {
        if (checkInput()) {
            Room r = new Room(roomOptions.get(room.getSelectionModel().getSelectedIndex()));
            Category c = new Category(categoryOptions.get(category.getSelectionModel().getSelectedIndex()));
            Type t = new Type(productType.getText());

            Item item = new Item(0, r, c, t, make.getText(), model.getText(), serial.getText(), tempReceipt, tempImage, Float.parseFloat(value.getText()), comments.getText());

            ItemEvent itemEvent = new ItemEvent(item);
            eventBus.register(itemEvent);
            eventBus.post(itemEvent);
            addReset();
        }
        else {
            invalidInput.showAndWait();
        }
    }

    private void addReset() {
        roomOptions.clear();
        categoryOptions.clear();
        productType.clear();
        make.clear();
        model.clear();
        serial.clear();
        value.clear();
        comments.clear();
    }

    @FXML
    private void setUploadImage(ActionEvent event) throws Exception {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select Item Image");
        File selectedImage = fc.showOpenDialog(null);
        //String tempName;
        if (selectedImage != null) {
            tempImage = selectedImage.toPath().toString();
            imageBox = new Image(selectedImage.toURI().toString());
            itemImage.setImage(imageBox);

        }
    }

    @FXML
    private void setUploadReceipt(ActionEvent event) throws Exception {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select Item Receipt");
        File selectedImage = fc.showOpenDialog(null);
        //String tempName;
        if (selectedImage != null) {
            tempReceipt = selectedImage.getName();
            receiptBox = new Image(selectedImage.toURI().toString());
            itemReceipt.setImage(receiptBox);

        }
    }
}
