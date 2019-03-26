package userInterface;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class GuiNavigator {
    public static final String MAIN = "main.fxml";
    public static final String LOGIN_GUI = "logonGUI.fxml";
    public static final String MAIN_MENU_GUI = "mainMenu/mainMenuGUI.fxml";
    public static final String CREATE_USER_GUI = "newUser/createUserGUI.fxml";
    public static final String CREATE_USER_SUCCESS_GUI = "newUser/createUserSuccessGUI.fxml";
    public static final String VIEW_ITEMS_GUI = "manageItems/viewItemsGUI.fxml";

    /** Main application layout controller **/
    private static MainController mainController;

    /**
     * Store the main controller for later use in navigation
     */

    public static void setMainController(MainController mainController) {
        GuiNavigator.mainController = mainController;
    }

    /**
     * Loads the view specified by the fxml file into the pane
     * of the main application layout.
     */
    public static void loadGui(String fxml) {
        try {
            mainController.setGuiHolder(
                    FXMLLoader.load(GuiNavigator.class.getResource((fxml)))
            );
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
