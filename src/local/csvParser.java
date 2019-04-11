package local;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.csv.*;
import items.item;

public class csvParser {

        public List<Item> readFile() throws Exception {

            //Item item = new Item();
            List<Item>  itemList = new ArrayList<>();

            String fileName= "src\\local\\useritems.csv";
            File file= new File(fileName);
            String path = file.getAbsolutePath();
            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("itemNo","room","category","type","make","model","serial","receipt","photo","value","comments","lastupdate","delete").parse(in);
            for (CSVRecord record : records) {

               //Item thing = new Item(record.get("itemNo"),record.get("category"),record.get("type"),record.get("make"),record.get("model"),record.get("serial"),record.get("receipt"),record.get("photo"),record.get("value"),record.get("comments"));

                String itemNo = record.get("itemNo");
                String room = record.get("room");
                String category = record.get("category");
                String type = record.get("type");
                String make = record.get("make");
                String model = record.get("model");
                String serial = record.get("serial");
                String receipt = record.get("receipt");
                String photo = record.get("photo");
                String value = record.get("value");
                String comments = record.get("comments");
                Item item = new Item(itemNo, room, category, type, make, model, serial, receipt, photo, value, comments);
                itemList.add(item);
                //System.out.println(itemNo);
            }

            return itemList;
        }



    public class Item {
        //private boolean delete;
        private String itemNo;
        private String room;
        private String category;
        private String type;
        private String make;
        private String model;
        private String serial;
        private String receipt;
        private String photo;
        private String value;
        private String comments;

        public Item() {}

        public Item(String item, String room, String cat, String type, String make, String model, String serial, String receipt, String photo, String value, String comment) {
            //this.delete = del;
            this.itemNo = item;
            this.room = room;
            this.category = cat;
            this.type = type;
            this.make = make;
            this.model = model;
            this.serial = serial;
            this.receipt = receipt;
            this.photo = photo;
            this.value = value;
            this.comments = comment;
        }

        // public boolean getDelete() { return delete;}
        public String getItemNo() { return itemNo;}
        public String getRoom() { return room;}
        public String getCategory() { return category;}
        public String getType() { return type;}
        public String getMake() { return make;}
        public String getModel() { return model;}
        public String getSerial() { return serial;}
        public String getReceipt() { return receipt;}
        public String getPhoto() { return photo;}
        public String getValue() { return value;}
        public String getComments() { return comments;}

        //public void setDelete(boolean b) { delete = b; }

    }

}

