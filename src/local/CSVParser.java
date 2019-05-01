package local;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import com.google.common.eventbus.EventBus;
import eventBus.EventBusFactory;
import items.Category;
import items.Item;
import items.Room;
import items.Type;
import org.apache.commons.csv.*;
//import users.User;
import local.ParseEvent;
import users.Profile;
import users.UserProfile;

/**
 * CSVParser is responsible for parsing CSV files to requried data structures
 */
public class CSVParser {

    /**
     * Parse from item list csv
     * @return @itemList
     * @throws Exception
     */
    static public List<Item> readFile() throws Exception {
        List<Item>  itemList = new ArrayList<>();

        String fileName= "src\\local\\useritems.csv";
        File file= new File(fileName);
        String path = file.getAbsolutePath();
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("itemNo","Room","Category","Type","make","model","serial","receipt","photo","value","comments","lastupdate","delete", "email_own").parse(in); // added email_own
        Profile p = UserProfile.getUserProfile();
        for (CSVRecord record : records) {
            if (!record.get("Room").equals("Room") && record.get("email_own").equals(p.getEmail())) {

                String i = record.get("itemNo");
                int itemNo;
                try {
                    itemNo = Integer.valueOf(i.toString());
                } catch (NumberFormatException e) {
                    itemNo = 0;
                }
                Room room = new Room(record.get("Room"));
                System.out.println(room.getStatus());
                Category category = new Category(record.get("Category"));
                Type type = new Type(record.get("Type"));
                String make = record.get("make");
                String model = record.get("model");
                String serial = record.get("serial");
                String receipt = record.get("receipt");
                String photo = record.get("photo");
                float value = Float.valueOf(record.get("value"));
                String comments = record.get("comments");
                boolean deleted = Boolean.parseBoolean(record.get("delete"));
                Item item = new Item(itemNo, /*user,*/ room, category, type, make, model, serial, receipt, photo, value, comments);
                if (deleted) {
                    item.itemDelete();
                }
                itemList.add(item);
            }
        }

        System.out.println("Parsed, attempting to push event");
        /**
         * Since we cannot return items between threads,
         * EventBus is used to send message for other thread
         * to get object
         */
        EventBus eventBus = EventBusFactory.getEventBus();
        ParseEvent pevent = new ParseEvent(itemList);
        eventBus.register(pevent);
        eventBus.post(pevent);

        return itemList;
    }

    /**
     * Parse user profile from CSV
     * @return user profile
     * @throws Exception
     */
    static public Profile readProfile() throws Exception {

        String firstName = "";
        String lastName = "";
        String email = "";
        String pw = "";
        String phoneNumber = "";
        String insuranceCompanyName = "";
        String insuranceCompanyFax = "";
        String insuranceCompanyEmail = "";


        String fileName= "src\\users\\userprofile.csv";
        File file= new File(fileName);
        String path = file.getAbsolutePath();
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("firstName","lastName","email","password","phoneNumber","insuranceCompanyName","insuranceCompanyFax", "insuranceCompanyEmail").parse(in);
        for (CSVRecord record : records) {

            firstName = record.get("firstName");
            lastName = record.get("lastName");
            email = record.get("email");
            pw = record.get("password");
            phoneNumber = record.get("phoneNumber");
            insuranceCompanyEmail = record.get("insuranceCompanyEmail");
            insuranceCompanyFax = record.get("insuranceCompanyFax");
            insuranceCompanyName = record.get("insuranceCompanyName");
        }

        System.out.println("Parsed user profile");

        Profile profile = new Profile(firstName, lastName, email, pw, phoneNumber, insuranceCompanyName, insuranceCompanyFax, insuranceCompanyEmail);

        return profile;
    }

    /**
     * Parse user profile from CSV
     * @return user profile
     * @throws Exception
     */
    static public List<Float> readDeviations() throws Exception {

        List<Float> dev = new ArrayList<>();


        String fileName= "src\\smart\\deviations.csv";
        File file= new File(fileName);
        String path = file.getAbsolutePath();
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("deviation").parse(in);
        for (CSVRecord record : records) {
            if (!record.get("deviation").equals("deviation")) {
                dev.add(Float.parseFloat(record.get("deviation")));
            }
       }

        System.out.println("Parsed user profile");

        if (dev.isEmpty()) {
            dev.add((float) 0);
        }
        return dev;
    }



}

