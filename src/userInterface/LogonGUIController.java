/**
 * Controller for logonGUI.fxml
 */

package userInterface;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import eventBus.EventBusFactory;
import items.ItemList;
import items.ItemListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import local.CSVParser;
import local.CSVWriter;
import org.apache.commons.csv.CSVPrinter;
import users.Login;
import users.Profile;
import users.UserLoginEvent;
import users.UserProfile;

import java.io.IOException;

/**
 * Controller class for logonGUI.fxml
 */
public class LogonGUIController {
    private EventBus eventBus = EventBusFactory.getEventBus();
    private Boolean loggedIn = false;
    private Profile userProfile;

    /**
     * Set JavaFX GUI buttons, text boxes, labels, etc...
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
    private ImageView userLogo;

    @FXML
    private Button logonButton;

    @FXML
    private Button createUser;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label message = new Label();

    @FXML
    private CheckBox rememberMe = new CheckBox();

    /**
     * EventHandler to receieve login information from another thread.
     */
    public class EventHandler {
        @Subscribe
        public void userLoginEvent(UserLoginEvent event) {
            System.out.println("User has logged in");
            userProfile = event.getMessage();
            if (rememberMe.isSelected()) {
                try {
                    CSVWriter.writeUserProfile(userProfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * Register EventBus listener
     */
    public void registerListener() {
        ItemListener listener = new ItemListener();
        eventBus.register(listener);
    }

    /**
     * Initialize controller
     */
    @FXML
    public void initialize() {
        // Generate and register EventBus objects
        EventHandler handler = new EventHandler();
        eventBus.register(handler);
        registerListener();

        // set JavaFX styling
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.BLACK);

        /**
         * set object values
         */
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

        rememberMe.setText("Remember Login?");
        rememberMe.setFont(Font.font("Tahoma",10));
        rememberMe.setTextFill(Color.rgb(255,255,255));
        rememberMe.setEffect(dropShadow);
        rememberMe.setSelected(true);

        username.setEffect((new DropShadow(20, Color.BLACK)));
        password.setEffect((new DropShadow(20, Color.BLACK)));

        /**
         * clips are used for image positioning
         */
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

        createUser.setText("Create User");
        Profile tempProfile = UserProfile.getUserProfile();
        if (!tempProfile.getEmail().isEmpty()) {
            username.setText(tempProfile.getEmail());
            password.setText(tempProfile.getPw());
        }
    }

    /**
     * ActionEvent handlers for different buttons
     * @param event mouse click or keyboard "enter" or "space" selecting button.
     */
    @FXML
    private void createUser(ActionEvent event) {
        username.clear();
        password.clear();
        // Switch view
        GuiNavigator.loadGui(GuiNavigator.CREATE_USER_GUI);
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        username.clear();
        password.clear();
        message.setText("");
    };


    @FXML
    private void loginButton(ActionEvent event) throws Exception {
        final String user = username.getText();
        final String pass = password.getText();
        System.out.println("User: " + user + " Pass: " + pass);
        // Log user in
        Login log = new Login();

        if (log.Log(user,pass)) {
            message.setText("Your Password is confirmed!");
            message.setTextFill(Color.rgb(0,0,0));
            setLoggedIn(true);

            // Execute parser on another thread to keep GUI thread free (prevents UI lock)
            Thread thread = new Thread(){
                public void run() {
                    try {

                        ItemList.setItemList(CSVParser.readFile());
                        System.out.println("parsed file");
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

            // Switch view to mainMenuGUI.fxml
            GuiNavigator.loadGui(GuiNavigator.MAIN_MENU_GUI);

        }
        else {
            password.clear();
            message.setText("Your Password is Incorrect!");
            message.setFont(Font.font("Tahoma",15));
            message.setTextFill(Color.rgb(255,255,255));
            message.setEffect((new DropShadow(2,2,2,Color.BLACK)));
        }

    }


    @FXML
    private void setLoggedIn(boolean b) {
        loggedIn = b;
    }

}
