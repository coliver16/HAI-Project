package database;
import local.*;
import userInterface.Main;
import items.*;
//import users.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.Statement;


public class update{
    database inventory = new database();
    Connection conn;

    {
        try {
            conn = inventory.Connect();//establish database connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(){
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

    public void Upload(List<Item> out ){
        try {
            List<Item> input = new ArrayList<>(out);//set new List<Item> = local .CSV file
            while (!input.isEmpty()){//upload to database one item at a time
                Item entry = input.remove(0);
                if(entry.isDeleted()){addDeleted(entry);}
                else {addItem(entry);}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Item> Download(String db){

        List<Item> itemList = new ArrayList<>();
        String query = "SELECT * FROM db WHERE profile_email = 'cmuney13@gmail.com' ";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int itemNo = Integer.valueOf(rs.getString("item_id"));
                //User user = new User(Integer.valueOf(rs.getString("user_name")));
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
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return itemList;
    }

    public void addDeleted (Item newItem){
        try {
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM DeletedItems_454 WHERE item_no = newItem.itemNo";
            stmt.executeQuery(query);
            query = "INSERT INTO DeletedItems_454 (item_no, /*user_own,*/ item_room, item_category, item_type, item_make, item_model, item_serial_num, item_receipt, item_image, item_price, item_comments)" +
                "VALUES (newItem.itemNo, /*newItem.username,*/ newItem.room, newItem.category, newItem.type, newItem.make, newItem.model, newItem.serial, newItem.receipt, newItem.photo, newItem.value, newItem.comments)";
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Add an Item
    public void addItem (Item newItem)
    {
        try {
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM Item_454 WHERE item_no = newItem.itemNo";
            stmt.executeQuery(query);
            query = "INSERT INTO Item_454 (item_no, /*user_own,*/ item_room, item_category, item_type, item_make, item_model, item_serial_num, item_receipt, item_image, item_price, item_comments)" +
                    "VALUES (newItem.itemNo, /*newItem.username,*/ newItem.room, newItem.category, newItem.type, newItem.make, newItem.model, newItem.serial, newItem.receipt, newItem.photo, newItem.value, newItem.comments)";
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> compare(List<Item> local, List<Item> remote){
        List<Item> ret = local;
        int iter1 = 0;
        int iter2 = 0;
        while(ret.get(iter1)!=null && remote.get(iter2) != null){
            if(ret.get(iter1).Compare(remote.get(iter2))){ret.remove(iter1); }//compare local w/ remote List
            else {iter1++;}
            iter2++;
        }

        return ret;
    }


}
