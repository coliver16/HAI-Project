package userInterface.newUser;

import com.sun.java.accessibility.util.GUIInitializedListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import sun.plugin2.message.Message;
import userInterface.GuiNavigator;

import java.net.PasswordAuthentication;
import java.security.Key;


public class createUserGUIController {

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
    private Label message;

    @FXML
    private Label first;

    @FXML
    private TextField firstName;

    @FXML
    private Label last;

    @FXML
    private TextField lastName;

    @FXML
    private Label personalEmail;

    @FXML
    private TextField email;

    @FXML
    private Label pass;

    @FXML
    private PasswordField password;

    @FXML
    private Label passVerify;

    @FXML
    private PasswordField verifyPassword;

    @FXML
    private Label phone;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Label insurance;

    @FXML
    private TextField insuranceCo;

    @FXML
    private Label fax;

    @FXML
    private TextField insuranceFax;

    @FXML
    private Label insureEmail;

    @FXML
    private TextField insuranceEmail;

    @FXML
    private Button createProfile;

    @FXML
    private Button cancelProfile;

    @FXML
    public void initialize() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.BLACK);

        message.setText("HAI! There new User. Please create your Profile below!");
        message.setFont(Font.font("Tahoma",35));
        message.setTextFill(Color.rgb(255,255,255));
        message.setEffect(dropShadow);

        first.setText("First Name");
        first.setFont(Font.font("Tahoma",15));
        first.setTextFill(Color.rgb(255,255,255));
        first.setEffect(dropShadow);

        last.setText("Last Name");
        last.setFont(Font.font("Tahoma",15));
        last.setTextFill(Color.rgb(255,255,255));
        last.setEffect(dropShadow);

        personalEmail.setText("Email");
        personalEmail.setFont(Font.font("Tahoma",15));
        personalEmail.setTextFill(Color.rgb(255,255,255));
        personalEmail.setEffect(dropShadow);

        pass.setText("Password");
        pass.setFont(Font.font("Tahoma",15));
        pass.setTextFill(Color.rgb(255,255,255));
        pass.setEffect(dropShadow);

        passVerify.setText("Verify Password");
        passVerify.setFont(Font.font("Tahoma",15));
        passVerify.setTextFill(Color.rgb(255,255,255));
        passVerify.setEffect(dropShadow);

        phone.setText("Phone Number");
        phone.setFont(Font.font("Tahoma",15));
        phone.setTextFill(Color.rgb(255,255,255));
        phone.setEffect(dropShadow);

        insurance.setText("Insurance Company Name");
        insurance.setFont(Font.font("Tahoma",15));
        insurance.setTextFill(Color.rgb(255,255,255));
        insurance.setEffect(dropShadow);

        fax.setText("Insurance Company FAX");
        fax.setFont(Font.font("Tahoma",15));
        fax.setTextFill(Color.rgb(255,255,255));
        fax.setEffect(dropShadow);

        insureEmail.setText("Insurance Company Email");
        insureEmail.setFont(Font.font("Tahoma",15));
        insureEmail.setTextFill(Color.rgb(255,255,255));
        insureEmail.setEffect(dropShadow);

        firstName.setPromptText("Name");
        lastName.setPromptText("Last");
        email.setPromptText("someone@somewhere.com");
        phoneNumber.setPromptText("(999) 999-9999");
        insuranceCo.setPromptText("HAI Insurance LTD");
        insuranceFax.setPromptText("(999) 999-9999");
        insuranceEmail.setPromptText("help@me.com");


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


        Rectangle clip = new Rectangle(cloudLogo.getFitWidth(), cloudLogo.getFitHeight());
//        Rectangle clip1 = new Rectangle(userLogo.getFitWidth(), userLogo.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
//        clip1.setArcWidth(20);
//        clip1.setArcHeight(20);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage cloud = cloudLogo.snapshot(parameters, null);
  //      WritableImage User = userLogo.snapshot(parameters,null);

        cloudLogo.setClip(null);
        cloudLogo.setEffect((new DropShadow(20, Color.BLACK)));

//        userLogo.setClip(null);
//        userLogo.setEffect((new DropShadow(20,Color.BLACK)));
        cloudLogo.setImage(cloud);
    //    userLogo.setImage(User);

        cancelProfile.setText("Cancel User");
        createProfile.setText("Create User");
    }

    @FXML
    public void setCancelProfile(ActionEvent event) {
        GuiNavigator.loadGui(GuiNavigator.LOGIN_GUI);
    }

    @FXML
    public void setCreateProfile(ActionEvent event) {
        GuiNavigator.loadGui(GuiNavigator.CREATE_USER_SUCCESS_GUI);
    }

    @FXML
    public void setLoginMenu(ActionEvent event) {
        GuiNavigator.loadGui(GuiNavigator.LOGIN_GUI);
    }

   /* @FXML
    public void takePhoneNumber(ActionEvent event) {
        StringConverter<String> formatter;
        formatter = new StringConverter<String>() {
            @Override
            public String toString(String string) {
                if (string.length() == 13) {
                    return string;
                }
                else if (string.length() == 10 && string.indexOf('(') == -1 && string.indexOf(')') == -1 && string.indexOf('-') == -1) {
                    return '(' + string.substring(0,3) + ')' + string.substring(3,6) + '-' + string.substring(6);
                }
                else return "(999)999-9999";
            }

            @Override
            public String fromString(String string) {
                if (string == null) {
                    return "(999)999-9999";
                }
                return string;
            }


        };

        phoneNumber.setTextFormatter(new TextFormatter<String>(formatter, phoneNumber.getText()));
    }*/

   @FXML
    public void takePhoneNumber(KeyEvent keyEvent) {
       if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
           System.out.println(keyEvent.getEventType().toString());
           System.out.println(keyEvent.getTarget().toString().indexOf("phoneNumber"));
           char ar[] = keyEvent.getCharacter().toCharArray();
           char ch = ar[keyEvent.getCharacter().toCharArray().length-1];
           if (!(ch>='0' && ch <= '9') || phoneNumber.getLength() > 9) {
               keyEvent.consume();
           }
       }
   }


}
