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
    static Connection conn = null;
    static Profile currentProfile;

    static {
        try {
            currentProfile = CSVParser.readProfile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void update(){
        try {
            conn = inventory.Connect();//establish database connection
        } catch (Exception e) {
            e.printStackTrace();
        }
        S3 s3 = new S3();
        List<Item> remote = Download("Item_454");
        for(int i=0;i<remote.size();i++){
            String filename = remote.get(i).getPhoto();
            String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
            String s = remote.get(i).getItemNo() + "_" + remote.get(i).getMake() + "_" + remote.get(i).getModel() + "." + extension;
            s3.downloadObject(s, currentProfile.getEmail() + "/" + "src\\local\\images\\" + s);
        }
        try {
            List<Item> local = CSVParser.readFile();
            List<Item> up = compare(local,remote);
            Upload(up);
            //CSVWriter.appendToCSV(remote);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(conn != null)conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void CreateProfile(Profile input){
        try { conn = inventory.Connect();//establish database connection
        } catch (Exception e) {e.printStackTrace(); }
        PreparedStatement pstmt=null;

        try {
            S3 s3 = new S3();
            pstmt = conn.prepareStatement("INSERT INTO Profile_454 (profile_firstname, profile_lastname, profile_email, profile_password, profile_phone_number, policy_company, policy_fax, policy_claims_email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1,input.getFirstName());
            pstmt.setString(2,input.getLastName());
            pstmt.setString(3,input.getEmail());
            pstmt.setString(4,input.getPw());
            pstmt.setString(5,input.getPhoneNumber());
            pstmt.setString(6,input.getInsuranceCompanyName());
            pstmt.setString(7,input.getInsuranceCompanyFax());
            pstmt.setString(8,input.getInsuranceCompanyEmail());
            s3.createFolder(input.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(pstmt != null)pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null)conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ProfileUpdate(){

        try { conn = inventory.Connect();//establish database connection
        } catch (Exception e) {e.printStackTrace(); }
        PreparedStatement pstmt=null;

        try {
            pstmt = conn.prepareStatement("UPDATE Profile_454 SET profile_firstname = ?, profile_lastname = ?, profile_email = ?, profile_password = ?, profile_phone_number = ?, policy_company = ?, policy_fax = ?, policy_claims_email = ? WHERE (profile_email = ?)");
            pstmt.setString(1,currentProfile.getFirstName());
            pstmt.setString(2,currentProfile.getLastName());
            pstmt.setString(3,currentProfile.getEmail());
            pstmt.setString(4,currentProfile.getPw());
            pstmt.setString(5,currentProfile.getPhoneNumber());
            pstmt.setString(6,currentProfile.getInsuranceCompanyName());
            pstmt.setString(7,currentProfile.getInsuranceCompanyFax());
            pstmt.setString(8,currentProfile.getInsuranceCompanyEmail());
            pstmt.setString(9,currentProfile.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(pstmt != null)pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null)conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void restore(){
        List<Item> remote = Download("DeletedItems_454");
        try {
            CSVWriter.appendToCSV(remote);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Upload(List<Item> out){
        try {
            List<Item> input = new ArrayList<>(out);//set new List<Item> = local .CSV file
            while (!input.isEmpty()){//upload to database one item at a time
                Item entry = input.remove(0);
                if(entry.isDeleted()){System.out.print(entry.getItemNo());System.out.println(entry.isDeleted());addDeleted(entry);}
                else {addItem(entry);}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Item> Download(String db){

        try {
            conn = inventory.Connect();//establish database connection
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Item> itemList = new ArrayList<>();
        ResultSet rs =null;
        PreparedStatement pstmt = null;
        System.out.println(conn);
        try {
            if(db == "Item_454"){//for update else is for restore
            pstmt = conn.prepareStatement("SELECT * FROM Item_454 WHERE email_own = ? ");}
            else if(db == "DeletedItems_454"){pstmt = conn.prepareStatement("SELECT * FROM DeletedItems_454 WHERE email_own = ? ");}
            pstmt.setString(1,currentProfile.getEmail());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int itemNo;
                if(db == "Item_454"){itemNo = Integer.valueOf(rs.getString("item_no"));}
                else{itemNo = Integer.valueOf((rs.getString("item_id")));}
                //System.out.println("room" + rs.getString("item_room"));
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
        } catch (SQLException e ) {
            e.printStackTrace();
        }finally {
            try { if(rs != null)rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if(pstmt != null)pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if(conn != null)conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return itemList;
    }

    public static void addDeleted(Item newItem)
    {

        try {
            conn = inventory.Connect();//establish database connection
        } catch (Exception e) {
            e.printStackTrace();
        }

        PreparedStatement pstmt =null;
        try {
            pstmt = conn.prepareStatement("DELETE FROM DeletedItems_454 WHERE (item_id = ? AND email_own = ?) ");
            pstmt.setInt( 1, newItem.getItemNo());
            pstmt.setString(2,currentProfile.getEmail());
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
            pstmt = conn.prepareStatement("DELETE FROM Item_454 WHERE (item_id = ? AND email_own = ?) ");
            pstmt.setInt( 1, newItem.getItemNo());
            pstmt.setString(2,currentProfile.getEmail());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(pstmt != null)pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null)conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Add an Item
    public static void addItem(Item newItem)
    {

        try {
            conn = inventory.Connect();//establish database connection
        } catch (Exception e) {
            e.printStackTrace();
        }

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("DELETE FROM Item_454 WHERE (item_no = ? AND email_own = ?) ");
            pstmt.setInt( 1, newItem.getItemNo());
            pstmt.setString(2,currentProfile.getEmail());
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
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null)pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null)conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<Item> compare(List<Item> local, List<Item> remote){
        List<Item> ret = new ArrayList<>(local);
        int iter1 = 0;
        int iter2 = 0;
        System.out.println(ret.size());
        System.out.println(remote.size());
        for(int i=0; i<ret.size();i++){
            for(int j=0; j<remote.size();j++){
                if(ret.get(i).Compare(remote.get(j))){ret.remove(i);i--;break;}
            }
        }

        /*while(iter1 < ret.size() && iter2 < remote.size()){
            if(ret.get(iter1).Compare(remote.get(iter2))){ret.remove(iter1); }//compare local w/ remote List
            else {iter1++;}
            iter2++;
        }*/

        return ret;
    }


}
