package userInterface.manageItems;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import eventBus.EventBusFactory;
import items.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import local.CSVParser;
import local.CSVWriter;
import local.ParseEvent;
import userInterface.GuiNavigator;
import database.update;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import users.Profile;
import users.UserProfile;
import items.S3;

/**
 * Controller class for viewItemsGUI.fxml
 */
public class viewItemsGUIController {
    private EventBus eventBus = EventBusFactory.getEventBus();
    //paths for local content
    private String USER_IMAGES = "src\\local\\images\\";
    private String USER_RECEIPTS = "src\\local\\receipts\\";
    private File defaultImage = new File("src\\userInterface\\manageItems\\noImage.png");
    private File defaultReceipt = new File("src\\userInterface\\manageItems\\noReceipt.png");

    private String name;
    private List<Item> itemImports = ItemList.getItemList();
    // increment is used to serialize new items.
    private int increment = 1;

    private boolean initialized = false;
    private Alert alertConfirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
    private Alert alertConfirmAdd = new Alert(Alert.AlertType.CONFIRMATION);

    /**
     * Create JavaFX Objects
     */
    @FXML
    private Image imageBox = new Image(defaultImage.toURI().toString());
    @FXML
    private Image receiptBox = new Image(defaultReceipt.toURI().toString());

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
    private Button backButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private ImageView itemReceipt;

    @FXML
    private ImageView itemImage;

    // Get user name to display to GUI
    public viewItemsGUIController() {
        name = UserProfile.getUserProfile().getFirstName() + " " + UserProfile.getUserProfile().getLastName();
    }

    /**
     * EventHandler for successful parse of cached user items and new items
     */
    public class EventHandler {
        /**
         * Successful Parse of data
         * @param event item list from another thread
         */
        @Subscribe
        public void parseEvent(ParseEvent event) {
            // Updated repeat bug. Updating global class object after parse.
            ItemList.setItemList(event.getMessage());
            itemList.getItems().clear();
            itemImports = (List) event.getMessage();
            itemList.getItems().clear();
            for (Item i : itemImports) {
                // filter deleted items from user view
                if (!i.isDeleted()) {
                    if (i.getItemNo() > increment) { increment = i.getItemNo() + 1; }
                    Item item = new Item(i.getItemNo(), new Room(i.getRoom().getStatus()), i.getCategory(), i.getType(), i.getMake(), i.getModel(), i.getSerial(), i.getReceipt(), i.getPhoto(), i.getValue(), i.getComments());
                    itemList.getItems().add(item);
                }

            }
        }

        /**
         * Successful add of item
         * @param event item passed form another thread
         */
        @Subscribe
        public void itemEvent(ItemEvent event) {
            System.out.println("Item Added");
            event.getMessage().setItemNo(increment++);
            Item i = event.getMessage();
            Thread thread = new Thread() {
                public void run() {
                    try {
                        // save local copy of item photo
                        if (i.getPhoto() != null) {
                            File original = new File(i.getPhoto());
                            S3 s3 = new S3();
                            String filename = original.getName();
                            String extension = filename.substring(filename.lastIndexOf(".") + 1, original.getName().length());
                            String newPath = USER_IMAGES+ i.getItemNo() + "_" + i.getMake() + "_" + i.getModel() + "." + extension;
                            File newFile = new File(newPath);
                            Files.copy(original.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            //newFile.renameTo(new File(USER_IMAGES + i.getItemNo()+"_"+i.getMake()+"_"+i.getModel()));
                            i.setPhoto(newFile.toPath().toString());
                            s3.putObject(UserProfile.getUserProfile().getEmail() + "/" + newFile.toPath(), newFile.getAbsolutePath());
                        }
                        else {
                            i.setPhoto(defaultImage.getPath().toString());
                        }

                        // save local copy of item receipt
                        if (i.getReceipt() != null) {
                            File original = new File(i.getReceipt());
                            S3 s3 = new S3();
                            String filename = original.getName();
                            String extension = filename.substring(filename.lastIndexOf(".") + 1, original.getName().length());
                            String newPath = USER_RECEIPTS + i.getItemNo() + "_" + i.getMake() + "_" + i.getModel() + "_RECEIPT" + "." + extension;
                            File newFile = new File(newPath);
                            Files.copy(original.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            //newFile.renameTo(new File(USER_IMAGES + i.getItemNo()+"_"+i.getMake()+"_"+i.getModel()));
                            i.setReceipt(newFile.toPath().toString());
                            s3.putObject(UserProfile.getUserProfile().getEmail() + "/" + newFile.toPath(), newFile.getAbsolutePath());
                        }
                        else {
                            i.setReceipt(defaultReceipt.getPath().toString());
                        }
                        //i.setItemNo(increment++);

                        // add item to display list and user item list
                        itemImports.add(i);
                        itemList.getItems().add(i);
                        ItemList.addItem(i);

                        List<Item> l = new ArrayList<>();
                        l.add(i);
                        // write new item to local CSV
                        CSVWriter.appendToCSV(l);
                    } catch (Exception e) {
                        System.out.println("Error");
                        e.printStackTrace();

                        System.out.println(e);
                    }
                }
            };
            thread.start();
        }
    }

    /**
     * register listener for new items
     */
    public void registerListener() {
        ItemListener listener = new ItemListener();
        eventBus.register(listener);
    }


    /**
     * initialize controller objects
     */
    @FXML
    public void initialize() {

        itemImage.setImage(imageBox);

        itemReceipt.setImage(receiptBox);

        EventHandler handler = new EventHandler();
        eventBus.register(handler);
        registerListener();

        alertConfirmDelete.setTitle("Confirm Dialog");
        alertConfirmDelete.setHeaderText("You will delete this item from the cloud.");
        alertConfirmDelete.setContentText("Do you wish to confirm deletion?");

        alertConfirmAdd.setTitle("Confirm Dialog");
        alertConfirmAdd.setHeaderText("You will add this item to the cloud.");
        alertConfirmAdd.setContentText("Do you wish to confirm creation of new item?");

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


        // create table columns

        TableColumn<Item, String> column1 = new TableColumn<>("Item No.");
        column1.setCellValueFactory(new PropertyValueFactory<>("itemNo"));
        TableColumn<Item, String> column2 = new TableColumn<>("Room");
        column2.setCellValueFactory(new PropertyValueFactory<>("Room"));
        TableColumn<Item, String> column3 = new TableColumn<>("Category");
        column3.setCellValueFactory(new PropertyValueFactory<>("Category"));
        TableColumn<Item, String> column4 = new TableColumn<>("Product Type");
        column4.setCellValueFactory(new PropertyValueFactory<>("Type"));
        TableColumn<Item, String> column5 = new TableColumn<>("Make");
        column5.setCellValueFactory(new PropertyValueFactory<>("make"));
        TableColumn<Item, String> column6 = new TableColumn<>("Model");
        column6.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn<Item, String> column7 = new TableColumn<>("Serial");
        column7.setCellValueFactory(new PropertyValueFactory<>("serial"));
        TableColumn<Item, String> column8 = new TableColumn<>("Receipt");
        column8.setCellValueFactory(new PropertyValueFactory<>("receipt"));
        TableColumn<Item, String> column9 = new TableColumn<>("Photo");
        column9.setCellValueFactory(new PropertyValueFactory<>("photo"));
        TableColumn<Item, String> column10 = new TableColumn<>("Value");
        column10.setCellValueFactory(new PropertyValueFactory<>("value"));
        TableColumn<Item, String> column11 = new TableColumn<>("Misc Comments");
        column11.setCellValueFactory(new PropertyValueFactory<>("comments"));

        // set column widths
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


        // setting cell factories for enumerated values
        column2.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getRoom().getStatus().toString());
        });

        column3.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCategory().getCategory().toString());
        });

        column4.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getType().productType.toString());
        });
        itemList.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        // add columns to table
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


        // add initial items to list
        if (!initialized) {
            for (Item i : itemImports) {
                if (!i.isDeleted()) {

                    itemList.getItems().add(i);
                }

            }
            initialized = true;
        }


        /**
         * Selection handler for table.
         * Used for deletion of selected items or viewing images
         */
        itemList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int i = itemList.getSelectionModel().getSelectedIndex();
                Item thing;
                thing = (Item) itemList.getItems().get((Integer) i);
                File newImage = new File(thing.getPhoto());
                File newReceipt = new File(thing.getReceipt());
                if (newImage.exists()) {
                    imageBox = new Image(newImage.toURI().toString());
                }
                else {
                    imageBox = new Image(defaultImage.toURI().toString());
                }
                if (newReceipt.exists()) {
                    receiptBox = new Image(newReceipt.toURI().toString());
                }
                else {
                    receiptBox = new Image(defaultReceipt.toURI().toString());
                }
                itemImage.setImage(imageBox);
                itemReceipt.setImage(receiptBox);
            }
        });

        // set button labels
        backButton.setText("Back");
        addButton.setText("Add Item");
        deleteButton.setText("Delete Item");
        updateButton.setText("Sync Items");

    }


    /**
     * Delete button handler
     * @param event delete item button was clicked
     * @throws InterruptedException
     */
    @FXML
    public void setDeleteButton(ActionEvent event) throws InterruptedException {
        Optional<ButtonType> result = alertConfirmDelete.showAndWait();
        if (result.get() == ButtonType.OK) {

            ObservableList selectedItem = itemList.getSelectionModel().getSelectedIndices();//getSelectedItem();
            ArrayList<Item> items = new ArrayList<Item>();
            for (Object i : selectedItem) {
                items.add((Item) itemList.getItems().get((Integer) i));
            }

            for (Item i : items) {
                for (Item j : ItemList.itemList) {
                    if (j.Compare(i)) {
                        i.itemDelete();
                        j.itemDelete();

                        this.itemList.getItems().remove(i);
                        //itemImports.remove(i);
                    }
                }
            }
            alertConfirmDelete.close();
            itemList.getSelectionModel().clearSelection();
            //ItemList.itemList.isEmpty();

            // update items on CSV with background thread
            Thread thread = new Thread() {
                public void run() {
                    try {
                        CSVWriter.writeCSV(ItemList.itemList);
                        System.out.println("wrote file");
                        //CSVParser.readFile();
                        return;
                    } catch (Exception e) {
                        System.out.println("Error");
                        e.printStackTrace();

                        System.out.println(e);
                    }
                }
            };
            thread.start();
        }
        else {
            alertConfirmDelete.close();
        }

    }

    /**
     * back button was selected, load mainMenuGUI.fxml
     * @param event mouse click
     */
    @FXML
    public void setBackButton(ActionEvent event) {
        GuiNavigator.loadGui(GuiNavigator.MAIN_MENU_GUI);
    }

    /**
     * update user profile was selected
     * @param event mouse click
     */
    @FXML
    public void setUpdateButton(ActionEvent event) {
        update.update();
    }

    /**
     * add item button was selected, change view to allow data entry
     * load add item window, load controller and open view items in the background.
     * Window modality allows the window to be moved independent of main window.
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    public void setAddButton(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addItemsGUI.fxml"));
        Parent parent = fxmlLoader.load();
        addItemsGuiController addController = fxmlLoader.<addItemsGuiController>getController();
        Scene scene = new Scene(parent, 520, 750);
        scene.getStylesheets().setAll(getClass().getResource("../gui.css").toExternalForm());
        Stage stage = new Stage();
        stage.setMinWidth(520);
        stage.setMaxWidth(520);
        stage.setMinHeight(750);
        stage.setMaxHeight(750);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setAlwaysOnTop(false);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
