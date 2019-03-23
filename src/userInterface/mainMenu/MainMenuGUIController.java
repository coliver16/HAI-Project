package userInterface.mainMenu;

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

public class MainMenuGUIController {
    Boolean loggedIn = false;

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



}
