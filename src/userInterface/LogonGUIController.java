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

public class LogonGUIController {
    Boolean loggedIn = false;

    @FXML
    private ImageView cloudLogo;

    @FXML
    private ImageView userLogo;

    @FXML
    private Button logonButton;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label message = new Label();

    @FXML
    public void initialize() {
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

    @FXML
    private void cancelButton(ActionEvent event) {
        username.clear();
        password.clear();
    };

    @FXML
    private void loginButton(ActionEvent event) {
        final  String user = username.getText();
        final String pass = password.getText();

        if (user.equals("John Doe") && pass.equals("password1234")) {
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
            message.setTextFill(Color.rgb(210,39,30));
        }

    }


    @FXML
    private void setLoggedIn(boolean b) {
        loggedIn = b;
    }

}
