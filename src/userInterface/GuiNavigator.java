package userInterface;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class GuiNavigator {
    public static final String MAIN = "main.fxml";
    public static final String LOGIN_GUI = "logonGUI.fxml";
    public static final String MAIN_MENU_GUI = "mainMenu/mainMenuGUI.fxml";
    public static final String EMPLOYEE_PORTAL_GUI = "employeeInterface/employeePortalGUI.fxml";
    public static final String MEMBER_ADD_GUI = "memberInterface/memberAddGUI.fxml";
    public static final String MEMBER_GUI = "memberInterface/memberGUI.fxml";
    public static final String MANAGER_PORTAL_GUI = "managerInterface/managerPortalGUI.fxml";
    public static final String MANAGER_LOGIN_GUI = "managerInterface/managerLoginGUI.fxml";

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
