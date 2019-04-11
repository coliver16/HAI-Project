package database;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class database{
    /*//Driver, Host + URL
    static final String databaseHost = "haiproject.database.windows.net";
    static final String databaseURL = "jdbc:sqlserver://haiproject.database.windows.net:1433;database=Inventory";
    static final String databaseDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //Database credentials*/
    //static final String user = "haiadmin";
    //static final String pass = "HAIpassw0rd";

    // Create datasource.
    SQLServerDataSource ds = new SQLServerDataSource();

    {ds.setUser("haiadmin");
    ds.setPassword("HAIpassw0rd");
    ds.setServerName("haiproject.database.windows.net");
    ds.setPortNumber(1433);
    ds.setDatabaseName("Inventory");
    ds.setTrustServerCertificate(true);}


    //Connection declaration
    Connection conn = null;
    Statement stmt = null;



    ///Register database
    public Connection Connect() throws Exception

    {
        /*try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("{call dbo.uspGetEmployeeManagers(?)}")) {
            // Execute a stored procedure that returns some data.
            cstmt.setInt(1, 50);
            ResultSet rs = cstmt.executeQuery();

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.println("EMPLOYEE: " + rs.getString("LastName") + ", " + rs.getString("FirstName"));
                System.out.println("MANAGER: " + rs.getString("ManagerLastName") + ", " + rs.getString("ManagerFirstName"));
                System.out.println();
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }*/

        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = ds.getConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ///Establish connection
        //conn = DriverManager.getConnection(databaseURL, user, pass);


        // Sample of how to execute query
        /*try {
            stmt = conn.createStatement();

            String sql = "INSERT INTO Profile_454 (profile_user, profile_firstname, profile_lastname, profile_email, profile_password, profile_phone_number, policy_company, policy_fax, policy_claims_email) VALUES (2, 'Peter', 'Nincompoop', 'pnincompoop@gmail.com', '12345', '1234567890', 'insureco', '1234567890', 'insuranceCompanyEmail')";
            stmt.executeUpdate(sql);
        } catch (Exception x) {
            x.printStackTrace();
        }*/
        String query = "select * " +
                "from " + "Profile_454";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String fname = rs.getString("profile_firstname");
                System.out.println(fname + "\t");
            }
        } catch (SQLException e ) {
            e.printStackTrace();
            //JDBCTutorialUtilities.printSQLException(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    return conn;

    }

}
