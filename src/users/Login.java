package users;
import com.google.common.eventbus.EventBus;
import database.database;
import eventBus.EventBusFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Login {
    database inventory = new database();
    Connection conn = null;



    String fname = "";
    String lname = "";
    String email = "";
    String pw = "";
    String phoneNumber = "";
    String insuranceCompanyName = "";
    String insuranceCompanyFax = "";
    String insuranceCompanyEmail = "";
    Profile currentProfile = new Profile("", "", "", "", "", "", "", "");

    public boolean Log(String logEmail, String logPass) {
        try {
            conn = inventory.Connect();//establish database connection
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        boolean loggedIn = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Profile_454 WHERE profile_email = ? AND profile_password = ? ");
            pstmt.setString(1,logEmail);
            pstmt.setString(2,logPass);
            ResultSet rs = pstmt.executeQuery();
            //System.out.println("sql query" + pstmt.toString());
            if(rs.next()) {
                //pstmt = conn.prepareStatement("SELECT * FROM Profile_454 Where profile_email = ? ");
                //pstmt.setString(1,logEmail);
                //rs = pstmt.executeQuery();

                currentProfile.setFirstName(rs.getString("profile_firstname"));
                currentProfile.setLastName(rs.getString("profile_lastname"));
                currentProfile.setEmail(rs.getString("profile_email"));
                currentProfile.setPw(rs.getString("profile_password"));
                currentProfile.setPhoneNumber(rs.getString("profile_phone_number"));
                currentProfile.setInsuranceCompanyEmail(rs.getString("policy_claims_email"));
                currentProfile.setInsuranceCompanyFax(rs.getString("policy_fax"));
                currentProfile.setInsuranceCompanyName(rs.getString("policy_company"));
                System.out.println(currentProfile.getFirstName());
                //Check that currentProfile login was successful
                loggedIn = true;
                /*if (currentProfile.getEmail().equals(logEmail) && currentProfile.getPw().equals(logPass)) {
                    loggedIn = true;
                } else {
                    loggedIn = false;
                }*/
            }


        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        if (loggedIn) {
            UserProfile.setUserProfile(currentProfile);
        }
        return loggedIn;
    }
}

