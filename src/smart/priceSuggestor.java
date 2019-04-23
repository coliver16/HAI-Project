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

    public float Suggest(String serial) {
        float estimate = 0;
        int medindex;
        int size = 0;
        dev = deviates();
        Float[] priceArray = new Float[size];
        ArrayList<Float> priceList = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select item_price from items_454 where item_serial_num = ?");
            pstmt.setString(1, serial);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                priceList.add(rs.getFloat("item_price"));
                size += 1;
            }
            priceArray = priceList.toArray(priceArray);
            Collections.sort(priceList);
            if (size/2 != 0) {
                medindex = (size+1)/2;
                estimate = priceArray[medindex];
            }
            else {
                medindex = size/2;
                estimate = (priceArray[medindex]+priceArray[medindex+1])/2;
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
        return estimate;
    }

    public float Suggest(String make, String model) {
        float estimate = 0;
        int medindex;
        int size = 0;
        dev = deviates();
        Float[] priceArray = new Float[size];
        ArrayList<Float> priceList = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("select item_price from items_454 where item_make = ? and item_model = ?");
            pstmt.setString(1, make);
            pstmt.setString(1, model);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                priceList.add(rs.getFloat("item_price"));
                size += 1;
            }
            priceArray = priceList.toArray(priceArray);
            Collections.sort(priceList);
            if (size/2 != 0) {
                medindex = (size+1)/2;
                estimate = priceArray[medindex];
            }
            else {
            medindex = size/2;
            estimate = (priceArray[medindex]+priceArray[medindex+1])/2;
            }
        }
        catch (SQLException e) {
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
        return estimate;
    }

    public float deviates() {
        //Store suggested prices and deviating prices until we get up to 10 devaiting prices
        //Calculate the deviation from the suggested for each using the equation (Deviating Price - Suggested Price)/Suggested Price
        //Find the mean of all those deviations excluding those means that are higher than .8 or lower than -.8 (in order to weed out bad input)
        //Return that mean (variable dev)
        //Every 10 added deviation prices, we repeat this process automatically
        return dev;
    }
}

