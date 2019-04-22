package database;
import local.*;
import userInterface.Main;
import items.*;
import users.Profile;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class update{
    static database inventory = new database();
    static Connection conn;
    static Profile currentProfile;

    static {
        try {
            currentProfile = CSVParser.readProfile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            conn = inventory.Connect();//establish database connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(){
        List<Item> remote = Download("Item_454");
        try {
            List<Item> local = CSVParser.readFile();
            List<Item> up = compare(local,remote);
            Upload(up);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restore(){
        List<Item> remote = Download("DeletedItems_454");

    }

    public static void Upload(List<Item> out){
        try {
            List<Item> input = new ArrayList<>(out);//set new List<Item> = local .CSV file
            while (!input.isEmpty()){//upload to database one item at a time
                Item entry = input.remove(0);
                if(entry.isDeleted()){System.out.println(entry.isDeleted());addDeleted(entry);}
                else {addItem(entry);}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Item> Download(String db){

        List<Item> itemList = new ArrayList<>();
        //PreparedStatement pstmt = null;
        System.out.println(conn);
        try {
            PreparedStatement pstmt=null;
            if(db == "Item_454"){//for update else is for restore
            pstmt = conn.prepareStatement("SELECT * FROM Item_454 WHERE email_own = ? ");}
            else if(db == "DeletedItems_454"){pstmt = conn.prepareStatement("SELECT * FROM DeletedItems_454 WHERE email_own = ? ");}
            pstmt.setString(1,currentProfile.getEmail());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int itemNo = Integer.valueOf(rs.getString("item_id"));
                Room room = new Room(rs.getString("item_room"));
                Category category = new Category(rs.getString("item_category"));
                Type type = new Type(rs.getString("item_type"));
                String make = rs.getString("item_make");
                String model = rs.getString("item_model");
                String serial = rs.getString("item_serial_num");
                String receipt = rs.getString("item_receipt");
                String photo = rs.getString("item_image");
                float value = Float.valueOf(rs.getString("item_price"));
                String comments = rs.getString("item_comments");
                Item item = new Item(itemNo, /*user,*/ room, category, type, make, model, serial, receipt, photo, value, comments);
                itemList.add(item);
            }
            /*try {
                CSVWriter.writeCSV(itemList);
            }
            catch (IOException e) {
                e.printStackTrace();
            }*/
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return itemList;
    }

    public static void addDeleted(Item newItem)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM DeletedItems_454 WHERE item_id = ? ");
            pstmt.setInt( 1, newItem.getItemNo());
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("INSERT INTO DeletedItems_454 (item_id, email_own, item_room, item_category, item_type, item_make, item_model, item_serial_num, item_receipt, item_image, item_price, item_comments) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1,newItem.getItemNo());
            pstmt.setString(2,currentProfile.getEmail());
            pstmt.setString(3,newItem.getRoom().getRoom());
            pstmt.setString(4,newItem.getCategory().getCategory().toString());
            pstmt.setString(5,newItem.getType().getProductTypeString());
            pstmt.setString(6,newItem.getMake());
            pstmt.setString(7,newItem.getModel());
            pstmt.setString(8,newItem.getSerial());
            pstmt.setString(9,newItem.getReceipt());
            pstmt.setString(10,newItem.getPhoto());
            pstmt.setFloat(11,newItem.getValue());
            pstmt.setString(12,newItem.getComments());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Add an Item
    public static void addItem(Item newItem)
    {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Item_454 WHERE item_no = ? ");
            pstmt.setInt( 1, newItem.getItemNo());
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("INSERT INTO Item_454 (item_no, email_own, item_room, item_category, item_type, item_make, item_model, item_serial_num, item_receipt, item_image, item_price, item_comments, item_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1,newItem.getItemNo());
            pstmt.setString(2,currentProfile.getEmail());
            pstmt.setString(3,newItem.getRoom().getRoom());
            pstmt.setString(4,newItem.getCategory().getCategory().toString());
            pstmt.setString(5,newItem.getType().getProductTypeString());
            pstmt.setString(6,newItem.getMake());
            pstmt.setString(7,newItem.getModel());
            pstmt.setString(8,newItem.getSerial());
            pstmt.setString(9,newItem.getReceipt());
            pstmt.setString(10,newItem.getPhoto());
            pstmt.setFloat(11,newItem.getValue());
            pstmt.setString(12,newItem.getComments());
            pstmt.setBoolean(13,newItem.isDeleted());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*try {
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM Item_454 WHERE item_no = newItem.itemNo";
            stmt.executeQuery(query);
            query = "INSERT INTO Item_454 (item_no, /*user_own,*/ /*item_room, item_category, item_type, item_make, item_model, item_serial_num, item_receipt, item_image, item_price, item_comments)" +
                    "VALUES (newItem.itemNo, /*newItem.username,*//* newItem.room, newItem.category, newItem.type, newItem.make, newItem.model, newItem.serial, newItem.receipt, newItem.photo, newItem.value, newItem.comments)";
           /* stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public static List<Item> compare(List<Item> local, List<Item> remote){
        List<Item> ret = new ArrayList<>(local);
        int iter1 = 0;
        int iter2 = 0;
        System.out.println(ret.size());
        System.out.println(remote.size());
        /*for(int i=0; i<ret.size();){

        }*/

        while(iter1 < ret.size() && iter2 < remote.size()){
            if(ret.get(iter1).Compare(remote.get(iter2))){ret.remove(iter1); }//compare local w/ remote List
            else {iter1++;}
            iter2++;
        }

        return ret;
    }


}
