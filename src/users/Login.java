package users;
import database.database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
    database inventory = new database();
    Connection conn;
    Profile currentProfile = new Profile("", "", "", "", "", "", "", "");

    public boolean Login(String logEmail, String logPass) {
        String query = "select * " + "from " + "Profile_454 " + "where" + "PWDCOMPARE(logPass, profile_password) = 1 " + "and profile_email = logEmail";
        Statement stmt = null;
        boolean loggedIn = false;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String fname = rs.getString("profile_firstname");
            String lname = rs.getString("profile_lastname");
            String email = rs.getString("profile_email");
            String pw = rs.getString("profile_password");
            String phoneNumber = rs.getString("profile_phone_number");
            String insuranceCompanyName = rs.getString("policy_company");
            String insuranceCompanyFax = rs.getString("policy_fax");
            String insuranceCompanyEmail = rs.getString("policy_claims_email");
            currentProfile = new Profile(fname, lname, email, pw, phoneNumber, insuranceCompanyName, insuranceCompanyFax, insuranceCompanyEmail);
            //Check that currentProfile login was successful
            if (currentProfile.getEmail().equals("")) {
                loggedIn = false;
            }
            else {
                loggedIn = true;
            }
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
                }
            }
        }
        return loggedIn;
    }
}

