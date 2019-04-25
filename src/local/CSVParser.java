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

public class CSVParser {

        static public List<Item> readFile() throws Exception {

            //Item Item = new Item();
            List<Item>  itemList = new ArrayList<>();

            String fileName= "src\\local\\useritems.csv";
            File file= new File(fileName);
            String path = file.getAbsolutePath();
            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("itemNo","Room","Category","Type","make","model","serial","receipt","photo","value","comments","lastupdate","delete").parse(in);
            for (CSVRecord record : records) {
                if (!record.get("Room").equals("Room")) {

                    //Item thing = new Item(record.get("itemNo"),record.get("Category"),record.get("Type"),record.get("make"),record.get("model"),record.get("serial"),record.get("receipt"),record.get("photo"),record.get("value"),record.get("comments"));

                    String i = record.get("itemNo");
                    int itemNo;
                    try {
                        itemNo = Integer.valueOf(i.toString());
                    } catch (NumberFormatException e) {
                        itemNo = 0;
                    }
                    //int itemNo = Integer.valueOf(record.get("itemNo"));
                    Room room = new Room(record.get("Room")); //(record.get("Room"));
                    System.out.println(room.getStatus());
                    //Room room = new Room(Room.rooms.Bathroom);
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
                    //User user = new User(123456);
                    Item item = new Item(itemNo, /*user,*/ room, category, type, make, model, serial, receipt, photo, value, comments);
                    if (deleted) {
                        item.itemDelete();
                    }
                    itemList.add(item);
                }
                //System.out.println(itemNo);
            }

           // return itemList;

            //EventBus eventBus = new EventBus("parse");
            //EventListener listener = new EventListener();
           // ParseEvent pEvent = new ParseEvent(itemList);
            //eventBus.register(new ParseEvent(itemList));
            //EventBus eventBus = new EventBus();
            System.out.println("Parsed, attempting to push event");
            EventBus eventBus = EventBusFactory.getEventBus();
            ParseEvent pevent = new ParseEvent(itemList);
            eventBus.register(pevent);
            eventBus.post(pevent);

            //eventBus.post(pEvent);


            return itemList;
        }

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

        // return itemList;

        //EventBus eventBus = new EventBus("parse");
        //EventListener listener = new EventListener();
        // ParseEvent pEvent = new ParseEvent(itemList);
        //eventBus.register(new ParseEvent(itemList));
        //EventBus eventBus = new EventBus();
        System.out.println("Parsed user profile");
        //EventBus eventBus = EventBusFactory.getEventBus();
        //ParseEvent pevent = new ParseEvent(itemList);
        //eventBus.register(pevent);
        //eventBus.post(pevent);

        //eventBus.post(pEvent);
        Profile profile = new Profile(firstName, lastName, email, pw, phoneNumber, insuranceCompanyName, insuranceCompanyFax, insuranceCompanyEmail);

        return profile;
    }




}

