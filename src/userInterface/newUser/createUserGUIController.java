package userInterface.newUser;

import database.update;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import local.CSVWriter;
import userInterface.GuiNavigator;
import users.Profile;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller class for createUserGUI.fxml
 */
public class createUserGUIController {
    private Alert invalidInput = new Alert(Alert.AlertType.ERROR);

    /**
     * Create all JavaFX objects for buttons, labels, text fields, etc...
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

    /**
     * Initialize controller, setting JavaFX object values
     */
    @FXML
    public void initialize() {
        invalidInput.setTitle("Invalid Input!");
        invalidInput.setHeaderText("You have submitted invalid inputs.");
        invalidInput.setContentText("Please correct items highlighted in red. Resubmit when completed.");

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

        insuranceFax.textProperty().addListener((observable, oldValue, newValue) -> {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(newValue);
            StringBuilder result = new StringBuilder();
            String finalString = "";
            try {
                while (m.find()) {
                    result.append(m.group());
                }
                int size = result.length();
                for (int i = 0; i < size; i++) {
                    if (i == 3 || i == 6) finalString += "-";
                    finalString += result.charAt(i);
                }
                for (int i = size; i < 10; i++) {
                    if (i == 3 || i == 6) finalString += "-";
                    finalString += "#";
                }
            } catch (Exception e) {
                finalString = "INVALID NUMBER";
            }
            insuranceFax.setText(finalString);
        });

        phoneNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(newValue);
            StringBuilder result = new StringBuilder();
            String finalString = "";
            try {
                while (m.find()) {
                    result.append(m.group());
                }
                int size = result.length();
                for (int i = 0; i < size; i++) {
                    if (i == 3 || i == 6) finalString += "-";
                    finalString += result.charAt(i);
                }
                for (int i = size; i < 10; i++) {
                    if (i == 3 || i == 6) finalString += "-";
                    finalString += "#";
                }
            } catch (Exception e) {
                finalString = "INVALID NUMBER";
            }
            phoneNumber.setText(finalString);
        });


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
        clip.setArcWidth(20);
        clip.setArcHeight(20);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage cloud = cloudLogo.snapshot(parameters, null);

        cloudLogo.setClip(null);
        cloudLogo.setEffect((new DropShadow(20, Color.BLACK)));

        cloudLogo.setImage(cloud);

        cancelProfile.setText("Cancel User");
        createProfile.setText("Create User");
    }

    /**
     * Check to ensure valid user input
     * @return valid boolean (good or bad input)
     */
    private boolean checkInput() {
        boolean valid = true;
        if (firstName.getText().isEmpty()) {
            valid = false;
            first.setTextFill(Color.rgb(255,0,0));
        }
        else {
            first.setTextFill(Color.rgb(255,255,255));
        }
        if (lastName.getText().isEmpty()) {
            valid = false;
            last.setTextFill(Color.rgb(255,0,0));
        }
        else {
            last.setTextFill(Color.rgb(255,255,255));
        }
        if (email.getText().isEmpty()) {
            valid = false;
            personalEmail.setTextFill(Color.rgb(255,0,0));
        }
        else {
            personalEmail.setTextFill(Color.rgb(255,255,255));
        }
        if (password.getText().isEmpty()) {
            valid = false;
            pass.setTextFill(Color.rgb(255,0,0));
        }
        else {
            pass.setTextFill(Color.rgb(255,255,255));
        }
        if (verifyPassword.getText().isEmpty() || !validatePasswords()) {
            valid = false;
            passVerify.setTextFill(Color.rgb(255,0,0));
        }
        else {
            passVerify.setTextFill(Color.rgb(255,255,255));
        }
        if (phoneNumber.getText().isEmpty() || phoneNumber.getLength() != 12 || phone.getText().contains("#")) {
            valid = false;
            phone.setTextFill(Color.rgb(255,0,0));
        }
        else {
            phone.setTextFill(Color.rgb(255,255,255));
        }
        if (insuranceCo.getText().isEmpty()) {
            valid = false;
            insurance.setTextFill(Color.rgb(255,0,0));
        }
        else {
            insurance.setTextFill(Color.rgb(255,255,255));
        }
        if (insuranceEmail.getText().isEmpty()) {
            valid = false;
            insureEmail.setTextFill(Color.rgb(255,0,0));
        }
        else {
            insureEmail.setTextFill(Color.rgb(255,255,255));
        }
        if (insuranceFax.getText().isEmpty() || insuranceFax.getLength() != 12 || insuranceFax.getText().contains("#")) {
            valid = false;
            fax.setTextFill(Color.rgb(255,0,0));
        }
        else {
            fax.setTextFill(Color.rgb(255,255,255));
        }
        return valid;
    }

    /**
     * Check to ensure passwords match
     * @return boolean
     */
    public boolean validatePasswords() {
        return (password.getText().equals(verifyPassword.getText()));
    }

    /**
     * Cancel user creation
     * @param event cancel button was clicked
     */
    @FXML
    public void setCancelProfile(ActionEvent event) {
        GuiNavigator.loadGui(GuiNavigator.LOGIN_GUI);
    }

    /**
     * Create and set user profile
     * @param event create user button was clicked
     * @throws IOException in case of CSVWriter IO
     */
    @FXML
    public void setCreateProfile(ActionEvent event) throws IOException {
        if (checkInput()) {
            Profile profile = new Profile(firstName.getText(), lastName.getText(), email.getText(), password.getText(), phoneNumber.getText(), insuranceCo.getText(),
                    insuranceFax.getText(), insuranceEmail.getText());
                    try {
                        CSVWriter.writeUserProfile(profile);
                        // write updates to server
                        update.profileUpdate();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            GuiNavigator.loadGui(GuiNavigator.CREATE_USER_SUCCESS_GUI);
        }
        else {
            invalidInput.showAndWait();
        }
    }

    @FXML
    public void setLoginMenu(ActionEvent event) {
        GuiNavigator.loadGui(GuiNavigator.LOGIN_GUI);
    }
}
