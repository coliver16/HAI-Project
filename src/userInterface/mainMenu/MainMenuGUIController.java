package userInterface.mainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import local.CSVParser;
import userInterface.GuiNavigator;
import userInterface.manageItems.viewItemsGUIController;
import users.Profile;
import users.UserLoginEvent;
import users.UserProfile;

import javax.swing.*;
import java.io.IOException;

/**
 * Controller class for mainMenuGUI.fxml
 */
public class MainMenuGUIController {
    private String name = UserProfile.getUserProfile().getFirstName() + " " + UserProfile.getUserProfile().getLastName();
    Boolean loggedIn = true;

    /**
     * Set JavaFX objects
     */
    @FXML
    private Label whyHai = new Label();

    @FXML
    private Label custServPortal = new Label();

    @FXML
    private Label contactUs = new Label();

    @FXML
    private ImageView cloudLogo;

    @FXML
    ImageView userLogo;

    @FXML
    private Label message = new Label();

    @FXML
    private Button viewProfile;

    @FXML
    private Button viewItems;

    @FXML
    private Button addItems;

    @FXML
    private Button logOut;

    /**
     * Initialize controller, setting JavaFX object values
     */
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

        message.setText("HAI! Welcome back, " + name);
        message.setFont(Font.font("Tahoma",35));
        message.setTextFill(Color.rgb(255,255,255));
        message.setEffect(dropShadow);

        viewProfile.setText("View Profile");
        viewItems.setText("View Items");
        addItems.setText("Add Items");
        logOut.setText("LOG OUT");

        Rectangle clip = new Rectangle(cloudLogo.getFitWidth(), cloudLogo.getFitHeight());
        Rectangle clip1 = new Rectangle(userLogo.getFitWidth(), userLogo.getFitHeight());

        clip.setArcWidth(20);
        clip.setArcHeight(20);
        clip1.setArcWidth(20);
        clip1.setArcHeight(20);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage cloud = cloudLogo.snapshot(parameters, null);
        WritableImage user = userLogo.snapshot(parameters,null);

        cloudLogo.setClip(null);
        cloudLogo.setEffect((new DropShadow(20, Color.BLACK)));

        userLogo.setClip(null);
        userLogo.setEffect((new DropShadow(20,Color.BLACK)));
        cloudLogo.setImage(cloud);
        userLogo.setImage(user);
    }

    /**
     * logout selected
     * @param event mouse click
     */
    @FXML
    public void setLogOut(ActionEvent event) {
        UserProfile.clear();
        setLoggedIn(true);
        GuiNavigator.loadGui(GuiNavigator.LOGIN_GUI);
    }

    /**
     * view items selected
     * @param event mouse click
     * @throws InterruptedException
     */
    @FXML
    public void setViewItems(ActionEvent event) throws InterruptedException {

        GuiNavigator.loadGui(GuiNavigator.VIEW_ITEMS_GUI);
    }

    /**
     * user profile selected
     * @param event mouse click
     */
    @FXML
    public void setViewProfile(ActionEvent event) {
        GuiNavigator.loadGui(GuiNavigator.MODIFY_USER_PRO);
    }

    /**
     * select add items
     * @param event mouse click
     * @throws IOException in case loadGUI fails
     */
    @FXML
    public void setAddItems(ActionEvent event) throws IOException {
        GuiNavigator.loadGui(GuiNavigator.VIEW_ITEMS_GUI);
        viewItemsGUIController controller = new viewItemsGUIController();
        controller.setAddButton(new ActionEvent());
    }

    private void setLoggedIn(boolean b) {
        loggedIn = b;
    }

}
