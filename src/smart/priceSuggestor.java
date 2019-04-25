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
    float dev = 0;

    static {
        try {
            currentProfile = CSVParser.readProfile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: Try changing to match comments instead as well (search for year in comments)
    public float Suggest(String serial) {
        float estimate = 0;
        int medindex;
        int size = 0;
        //TODO: Fix this line to add dev - dev = deviates();
        //Create an array to be able to select prices by index
        Float[] priceArray = new Float[size];
        //Create an array list to be able to add prices dynamically
        ArrayList<Float> priceList = new ArrayList<>();
        try {
            //Select all price that match with passed serial num
            pstmt = conn.prepareStatement("select item_price from items_454 where item_serial_num = ?");
            pstmt.setString(1, serial);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //Add these prices dynamically to the array list
                priceList.add(rs.getFloat("item_price"));
                size += 1;
            }
            //Convert the array list to an array to be able to select prices by index
            priceArray = priceList.toArray(priceArray);
            //Sort the array in asceding order
            Collections.sort(priceList);
            //if the size of the array is odd, the median is the middle number
            if (size / 2 != 0) {
                medindex = (size + 1) / 2;
                estimate = priceArray[medindex];
            }
            //if the size of the array is even, the median is the average of the two middle numbers
            else {
                medindex = size / 2;
                estimate = (priceArray[medindex] + priceArray[medindex + 1]) / 2;
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
        //TODO: Fix this line to add dev - estimate += estimate * dev;
        return estimate;
    }

    //TODO: Search for year in comments and add that to be passed
    public float Suggest(String make, String model) {
        float estimate = 0;
        int medindex;
        int size = 0;
        // TODO: FIX this line: dev = deviates();
        Float[] priceArray = new Float[size];
        ArrayList<Float> priceList = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select item_price from items_454 where item_make = ? and item_model = ?");
            pstmt.setString(1, make);
            pstmt.setString(2, model);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                priceList.add(rs.getFloat("item_price"));
                size += 1;
            }
            priceArray = priceList.toArray(priceArray);
            Collections.sort(priceList);
            if (size / 2 != 0) {
                medindex = (size + 1) / 2;
                estimate = priceArray[medindex];
            } else {
                medindex = size / 2;
                estimate = (priceArray[medindex] + priceArray[medindex + 1]) / 2;
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
        //TODO: Fix this line to add dev - estimate += estimate *;
        return estimate;
    }
}

    //Store suggested prices and deviating prices until we get up to 10 devaiting prices
    //Calculate the deviation from the suggested for each using the equation (Deviating Price - Suggested Price)/Suggested Price
    //Find the mean of all those deviations excluding those means that are higher than .8 or lower than -.8 (in order to weed out bad input)
    //Return that mean (variable dev)
    //Every 10 added deviation prices, we repeat this process automatically tp update dev
