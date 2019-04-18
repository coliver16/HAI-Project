package userInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import users.Login;

public class LogonGUIController {
    Boolean loggedIn = false;

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

        username.setEffect((new DropShadow(20, Color.BLACK)));
        password.setEffect((new DropShadow(20, Color.BLACK)));


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
    }

    @FXML
    private void createUser(ActionEvent event) {
        username.clear();
        password.clear();
        GuiNavigator.loadGui(GuiNavigator.CREATE_USER_GUI);
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        username.clear();
        password.clear();
        message.setText("");
    };

    @FXML
    private void loginButton(ActionEvent event) {
        final  String user = username.getText();
        final String pass = password.getText();
        Login log = new Login();

        if (log.Log(user,pass)) {
            message.setText("Your Password is confirmed!");
            message.setTextFill(Color.rgb(0,0,0));
            //username.clear();
            //password.clear();
            setLoggedIn(true);
            GuiNavigator.loadGui(GuiNavigator.MAIN_MENU_GUI);
        }
        else {
            username.clear();
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
