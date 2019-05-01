package local;

import com.google.common.eventbus.EventBus;
import eventBus.EventBusFactory;
import items.Category;
import items.Item;
import items.Room;
import items.Type;
import org.apache.commons.csv.*;
import users.Profile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * CSVWriter handles the responsibilites of writing requried *.csv files
 */
public class CSVWriter {

    // locations of local files
    private static final String USER_ITEMS_FILE = "src\\local\\useritems.csv";
    private static final String USER_PROFILE = "src\\users\\userprofile.csv";
    private static final String DEVIATION_LIST = "src\\smart\\deviations.csv";
    // date format
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Write item list csv
     * @param items list of user items
     * @throws IOException
     */
    public static void writeCSV(List<Item> items) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(USER_ITEMS_FILE));

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader("itemNo","Room","Category","Type","make","model","serial","receipt","photo","value","comments","lastupdate","delete","email_own"));//4/30 JGP added email_own header
        ) {
          for  (Item i: items) {
              if (!"itemNo".equals(i.getItemNo())) {
                  csvPrinter.printRecord(i.getItemNo(), i.getRoom().getStatus(), i.getCategory().getCategory(), i.getType().getProductTypeString(), i.getMake(), i.getModel(), i.getSerial(), i.getReceipt(), i.getPhoto(), i.getValue(), i.getComments(), FORMATTER.format(new Date()).toString(), i.isDeleted().booleanValue(), i.email);//4/30 JGP added i.email string
              }
          }
            csvPrinter.flush();
        }

       /* EventBus eventBus = EventBusFactory.getEventBus();
        ParseEvent pevent = new ParseEvent(items);
        eventBus.register(pevent);
        eventBus.post(pevent);*/
    }

    /**
     * Append new items to item list csv
     * @param items List of new items to add
     * @throws IOException
     */
    public static void appendToCSV(List<Item> items) throws IOException {
        File file = new File(USER_ITEMS_FILE);
        FileWriter fileWriter = new FileWriter(file, true);
        try (BufferedWriter writer = new BufferedWriter(fileWriter);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL);
        ){
            for (Item i: items) {
                if (!"itemNo".equals(i.getItemNo())) {
                    csvPrinter.printRecord(i.getItemNo(), i.getRoom().getStatus(), i.getCategory().getCategory(), i.getType().getProductTypeString(), i.getMake(), i.getModel(), i.getSerial(), i.getReceipt(), i.getPhoto(), i.getValue(), i.getComments(), FORMATTER.format(new Date()).toString(), i.isDeleted().booleanValue(), i.email);//4/30 JGP added i.email string
                }
            }
        }
    }

    /**
     * Write user profile to csv
     * @param profile user profile
     * @throws IOException
     */
    public static void writeUserProfile(Profile profile) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(USER_PROFILE));

             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader("firstName","lastName","email","password","phoneNumber","insuranceCompanyName","insuranceCompanyFax", "insuranceCompanyEmail"));
        ) {
            csvPrinter.printRecord(profile.getFirstName().toString(), profile.getLastName(), profile.getEmail(), profile.getPw(), profile.getPhoneNumber(), profile.getInsuranceCompanyName(), profile.getInsuranceCompanyFax(), profile.getInsuranceCompanyEmail());
            csvPrinter.flush();
        }
    }

    /**
     * Write deviation list csv
     * @param deviations list of user items
     * @throws IOException
     */
    public static void deviationCSV(List<Float> deviations) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(DEVIATION_LIST));

             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader("deviation"));
        ) {
            for  (Float i: deviations) {
                csvPrinter.printRecord(i);
            }
            csvPrinter.flush();
        }
    }
}
