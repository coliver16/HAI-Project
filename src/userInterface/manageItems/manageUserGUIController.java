package userInterface.manageItems;

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
import userInterface.GuiNavigator;
import users.Profile;
import users.UserProfile;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class manageUserGUIController {

    private Alert invalidInput = new Alert(Alert.AlertType.ERROR);
    private Alert passwordsNotMatch = new Alert(Alert.AlertType.ERROR);
    private Alert confirmProfileChange = new Alert(Alert.AlertType.CONFIRMATION);

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
    private Button updateProfile;

    @FXML
    private Button cancelProfile;

    @FXML
    public void initialize() {
        invalidInput.setTitle("Invalid Input!");
        invalidInput.setHeaderText("You have submitted invalid inputs.");
        invalidInput.setContentText("Please correct items highlighted in red. Resubmit when completed.");

        passwordsNotMatch.setTitle("Invalid Passwords!");
        passwordsNotMatch.setHeaderText("Your passwords do not match.");
        passwordsNotMatch.setContentText("Please ensure your password and confirmation password match.");

        confirmProfileChange.setTitle("Profile Change Detected!");
        confirmProfileChange.setHeaderText("You have changed the values of your profile.");
        confirmProfileChange.setContentText("These changes will be permanent. Press OK to confirm changes..");

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
        cancelProfile.setText("Exit User Profile");
        updateProfile.setText("Update User Profile");

        firstName.setText(UserProfile.getUserProfile().getFirstName());
        lastName.setText(UserProfile.getUserProfile().getLastName());
        email.setText(UserProfile.getUserProfile().getEmail());
        insuranceCo.setText(UserProfile.getUserProfile().getInsuranceCompanyName());
        insuranceEmail.setText(UserProfile.getUserProfile().getInsuranceCompanyEmail());
        insuranceFax.setText(UserProfile.getUserProfile().getInsuranceCompanyFax());

        firstName.setEditable(false);
        lastName.setEditable(false);
        email.setEditable(false);
        phoneNumber.setEditable(false);
        insuranceCo.setEditable(false);
        insuranceEmail.setEditable(false);
        insuranceFax.setEditable(false);
        password.setEditable(false);
        verifyPassword.setEditable(false);

        updateProfile.setDisable(false);

    }

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

    private boolean verifyChanges() {
        boolean valid = true;
        if (!firstName.getText().equals(UserProfile.getUserProfile().getFirstName())) {
            valid = false;
        }
        if (!lastName.getText().equals(UserProfile.getUserProfile().getLastName())) {
            valid = false;
        }
        if (email.getText().equals(UserProfile.getUserProfile().getEmail())) {
            valid = false;
        }
        if (phoneNumber.getText().equals(UserProfile.getUserProfile().getPhoneNumber())) {
            valid = false;
        }
        if (insuranceCo.getText().equals(UserProfile.getUserProfile().getInsuranceCompanyName())) {
            valid = false;
        }
        if (insuranceEmail.getText().equals(UserProfile.getUserProfile().getInsuranceCompanyEmail())) {
            valid = false;
        }
        if (insuranceFax.getText().equals(UserProfile.getUserProfile().getInsuranceCompanyFax())) {
            valid = false;
        }
        return valid;
    }

    public boolean validatePasswords() {
        return (password == verifyPassword);
    }

    @FXML
    public void setCancelProfile(ActionEvent event) {
        GuiNavigator.loadGui(GuiNavigator.MAIN_MENU_GUI);
    }

    @FXML
    public void setUpdateProfile(ActionEvent event) {
        if (checkInput()) {
            Optional<ButtonType> result = confirmProfileChange.showAndWait();
            if (result.get() == ButtonType.OK){

                //setUserProfile(new Profile(firstName.getText(), lastName.getText(), email.getText(), password.getText(), phoneNumber.getText(), insuranceCo.getText(),
                //        insuranceFax.getText(), insuranceEmail.getText()));
                // TODO: need method call to update user profile and password with server
                GuiNavigator.loadGui(GuiNavigator.MAIN_MENU_GUI);
            }

            // TODO: need method to create user with server
        }
        else {
            invalidInput.showAndWait();
        }
    }

    public void setUserProfile(Profile p) {
    }
}
