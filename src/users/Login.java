package users;
import database.database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
    database inventory = new database();
    Connection conn;
    {
        try {
            conn = inventory.Connect();//establish database connection
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
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
        String query = "select * from Profile_454 where profile_password = logPass and profile_email = logEmail";
        Statement stmt = null;
        boolean loggedIn = false;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            currentProfile.setFirstName(rs.getString("profile_firstname"));
            currentProfile.setLastName(rs.getString("profile_lastname"));
            currentProfile.setEmail(rs.getString("profile_email"));
            currentProfile.setPw(rs.getString("profile_password"));
            currentProfile.setPhoneNumber(rs.getString("profile_phone_number"));
            currentProfile.setInsuranceCompanyEmail(rs.getString("policy_claims_email"));
            currentProfile.setInsuranceCompanyFax(rs.getString("policy_fax"));
            currentProfile.setInsuranceCompanyName(rs.getString("policy_company"));
            //Check that currentProfile login was successful
            loggedIn = (currentProfile.getEmail().equals(logEmail) && currentProfile.getPw().equals(logPass));
            /*if (currentProfile.getEmail().equals(logEmail) && currentProfile.getPw().equals(logPass)) {
                loggedIn = true;
            } else {
                loggedIn = false;
            }*/
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    //
                }
            }
        }
        return loggedIn;
    }
}

