package smart;

import database.database;
import local.CSVParser;
import users.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.ArrayList;

public class priceSuggestor {
    static database inventory = new database();
    static Connection conn = null;
    static Profile currentProfile;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    static float dev = deviate.devUpdate();
    static float devPointer = dev;
    public static int numDevUpdates;

    public priceSuggestor() {};

    static {
        try {
            conn = inventory.Connect();//establish database connection
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int Suggest(String make, String model, String year) {
        float estimate = 0;
        int medindex;
        int size = 0;
        Float[] priceArray = new Float[size];
        ArrayList<Float> priceList = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select item_price from Item_454 where item_make = ? and item_model = ? and item_comments like ? ");
            pstmt.setString(1, make);
            pstmt.setString(2, model);
            pstmt.setString(3, "%" + year + "%");
            rs = pstmt.executeQuery();
            //Check to make sure the result set is not empty
            if (!rs.isBeforeFirst()) {System.out.println("no data");}
            else {
                    //Add the prices generated by the query to an array list, while counting the number of prices added
                    while (rs.next()) {
                        priceList.add(rs.getFloat("item_price"));
                        size += 1;
                    }
                    //Turn the array list into an array to be able to use indexes
                    priceArray = priceList.toArray(priceArray);
                    //Sort the array
                    Collections.sort(priceList);
                    //If the # of elements in the array is odd, the median in the middle #
                    if (size / 2 != 0) {
                        medindex = (size + 1) / 2;
                        estimate = priceArray[medindex];
                    }
                    //If the # of elements in the array is even, the median is the average of the 2 middle #s
                    else {
                        medindex = size / 2;
                        if (size == 1) {
                            estimate = (priceArray[medindex])/2;
                        }
                        else if (size > 1) {
                            estimate = (priceArray[medindex] + priceArray[medindex + 1]) / 2;
                        }
                        else { estimate = 0;}
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Account for deviation
        estimate += estimate*dev;
        return (int) estimate;
    }
}
    //TODO: Add Suggest() and addDeviationDecimal() methods to GUI
    //TODO: To incorporate Suggest() method into GUI, it should be a button that users can select
    //TODO: To incorporate addDeviationDecimal() to GUI, the method should look like: if (userInput != suggested) {addDeviationDecimal(suggested, userInput}
    //TODO: Make sure that this process is happening every 10 deviations (I think I have accounted for this in devUpdate())
    //TODO: Prompt users to input the year of an item in the comments