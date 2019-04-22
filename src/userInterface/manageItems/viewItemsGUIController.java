package userInterface.manageItems;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import eventBus.EventBusFactory;
import items.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import local.CSVWriter;
import local.ParseEvent;
import userInterface.GuiNavigator;
import database.update;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import local.CSVParser;
import users.Profile;
import users.UserLoginEvent;

public class viewItemsGUIController {
    private EventBus eventBus = EventBusFactory.getEventBus();
    private CSVParser parser = new CSVParser();
    private CSVWriter csvWriter = new CSVWriter();
    private String name = Profile.getUserName();
    private List<Item> itemImports = ItemList.getItemList();
    private Boolean confirmDelete = false;

    Alert alertConfirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
    Alert alertConfirmAdd = new Alert(Alert.AlertType.CONFIRMATION);


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

    public class EventHandler {
        @Subscribe
        public void parseEvent(ParseEvent event) {
            System.out.println("Made it to event");
            itemImports = (List) event.getMessage();
            System.out.println("Event: " + event.toString());
            System.out.println(itemImports.get(0).getMake());
            for (Item i : itemImports) {
                Item item = new Item(i.getItemNo(), new Room(i.getRoom().getStatus()), i.getCategory(), i.getType(), i.getMake(), i.getModel(), i.getSerial(), i.getReceipt(), i.getPhoto(), i.getValue(), i.getComments());
                //itemList.getItems().add(i);

                itemList.getItems().add(item);
                //itemList.getItems().

            }
        }

        @Subscribe
        public void itemEvent(ItemEvent event) {
            System.out.println("Item Added");
            event.getMessage().setItemNo(999);
            itemImports.add(event.getMessage());
            itemList.getItems().add(event.getMessage());
            ItemList.itemList.add(event.getMessage());
        }
    }

    @Subscribe
    public void itemEvent(ItemEvent event) {
        itemImports.add(event.getMessage());
    }

    public void registerListener() {
        ItemListener listener = new ItemListener();
        eventBus.register(listener);
    }




    @FXML
    public void initialize() throws Exception{
        EventHandler handler = new EventHandler();
        eventBus.register(handler);
        registerListener();

        alertConfirmDelete.setTitle("Confirm Dialog");
        alertConfirmDelete.setHeaderText("You will delete this item from the cloud.");
        alertConfirmDelete.setContentText("Do you wish to confirm deletion?");

        alertConfirmAdd.setTitle("Confirm Dialog");
        alertConfirmAdd.setHeaderText("You will add this item to the cloud.");
        alertConfirmAdd.setContentText("Do you wish to confirm creation of new item?");

       // csvWriter.writeCSV((List) itemImports);
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

//        TableColumn<Item, Boolean> column1 = new TableColumn<>("Delete?");
//        column1.setCellValueFactory(new PropertyValueFactory<>("delete"));

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

       // column1.setMinWidth(50);
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


        for (Item i : itemImports) {
            //Item item = new Item(i.getItemNo(), new Room(i.getRoom().getStatus()), i.getCategory(), i.getType(), i.getMake(), i.getModel(), i.getSerial(), i.getReceipt(), i.getPhoto(), i.getValue(), i.getComments());
            if (!i.isDeleted()) {
                //itemList.getItems().add(new Item(i.getItemNo(), new Room(i.getRoom().getStatus().toString()), i.getCategory(), i.getType(), i.getMake(), i.getModel(), i.getSerial(), i.getReceipt(), i.getPhoto(), i.getValue(), i.getComments()));
                itemList.getItems().add(i);
            }
        }

        backButton.setText("Back");
        addButton.setText("Add Item");
        deleteButton.setText("Delete Item");
        updateButton.setText("Sync Items");

    }


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
                        j.itemDelete();
                        break;
                    }
                }
                itemList.getItems().remove(i);
            }
            alertConfirmDelete.close();

            Thread thread = new Thread() {
                public void run() {
                    try {
                        CSVWriter.writeCSV(ItemList.itemList);
                        System.out.println("wrote file");
                        return;
                    } catch (Exception e) {
                        System.out.println("Error");
                        e.printStackTrace();

                        System.out.println(e);
                    }
                    //return;
                }
            };
            thread.start();
        }
        else {
            alertConfirmDelete.close();
        }

    }

    @FXML
    public void setBackButton(ActionEvent event) {
        GuiNavigator.loadGui(GuiNavigator.MAIN_MENU_GUI);
    }

    @FXML
    public void setUpdateButton(ActionEvent event) {
        update.update();
    }

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

        //addController.setAppMainObservableList()


        /*Label addItemLabel = new Label("Add New Items");

        StackPane addItemsLayout = new StackPane();
        addItemsLayout.getChildren().add(addItemLabel);
        Scene addItemScene = new Scene(addItemsLayout, 500,300);

        // Setup new popout window
        Stage addItemWindow = new Stage();
        addItemWindow.setTitle("Add New");
        addItemWindow.setScene(addItemScene);

        addItemWindow.show();*/
        //GuiNavigator.loadGui(GuiNavigator.VIEW_ITEMS_GUI)
    }
}
