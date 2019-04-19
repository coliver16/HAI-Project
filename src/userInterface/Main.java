package userInterface;
import users.Login;

import eventBus.EventBusFactory;
import eventBus.EventListener;
import items.ItemList;
import items.ItemListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import database.*;
import local.CSVParser;
import local.ParseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;


/**
 * Main application
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HAI! Let Us Help You.");
        //primaryStage.setFullScreen(true);
        primaryStage.setMaximized(true);
        primaryStage.setScene(createScene(loadedMainPane()));
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1200);
        primaryStage.setMaxHeight(1080);
        primaryStage.setMaxWidth(1920);
        primaryStage.show();
    }


    /**
     * Load the main fxml layout.
     * Set up the menu navigation, and load the main fxml layout
     * @return the loaded pane
     * @throws IOException if the pane cannot be loaded
     */
    private Pane loadedMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(GuiNavigator.MAIN));


        MainController mainController = loader.getController();
        GuiNavigator.setMainController(mainController);
        GuiNavigator.loadGui(GuiNavigator.LOGIN_GUI);
        //GuiNavigator.loadGui(GuiNavigator.EMPLOYEE_GUI);
        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().setAll(getClass().getResource("gui.css").toExternalForm());

        return scene;
    }


    public static void main(String[] args) throws Exception {
        EventBusFactory.getEventBus().register(new EventListener());
        ItemList itemList = new ItemList();
        database inventory = new database();
        inventory.Connect();
        Thread thread = new Thread() {
            public void run() {
                try {
                    itemList.setItemList(CSVParser.readFile());
                    System.out.println("parsed file");
                    return;
                } catch (Exception e) {
                    System.out.println("Error");
                    e.printStackTrace();

                    System.out.println(e);
                }
                return;
            }
        };
        thread.start();
        launch(args);





    }
}
