package local;

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

public class CSVWriter {

    private static final String USER_ITEMS_FILE = "src\\local\\useritems.csv";
    private static final String USER_PROFILE = "src\\users\\userprofile.csv";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void writeCSV(List<Item> items) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(USER_ITEMS_FILE));

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader("itemNo","Room","Category","Type","make","model","serial","receipt","photo","value","comments","lastupdate","delete"));
        ) {
          for  (Item i: items) {
              if (!"itemNo".equals(i.getItemNo())) {
                  csvPrinter.printRecord(i.getItemNo(), i.getRoom().getStatus(), i.getCategory().getCategory(), i.getType().getProductTypeString(), i.getMake(), i.getModel(), i.getSerial(), i.getReceipt(), i.getPhoto(), i.getValue(), i.getComments(), FORMATTER.format(new Date()).toString(), i.isDeleted().booleanValue());
              }
          }
            csvPrinter.flush();
        }
    }

    public static void appendToCSV(List<Item> items) throws IOException {
        File file = new File(USER_ITEMS_FILE);
        FileWriter fileWriter = new FileWriter(file, true);
        try (BufferedWriter writer = new BufferedWriter(fileWriter);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL);
        ){
            for (Item i: items) {
                if (!"itemNo".equals(i.getItemNo())) {
                    csvPrinter.printRecord(i.getItemNo(), i.getRoom().getStatus(), i.getCategory().getCategory(), i.getType().getProductTypeString(), i.getMake(), i.getModel(), i.getSerial(), i.getReceipt(), i.getPhoto(), i.getValue(), i.getComments(), FORMATTER.format(new Date()).toString(), i.isDeleted().booleanValue());
                }
            }
        }
    }

    public static void writeUserProfile(Profile profile) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(USER_PROFILE));

             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader("firstName","lastName","email","password","phoneNumber","insuranceCompanyName","insuranceCompanyFax", "insuranceCompanyEmail"));
        ) {
            csvPrinter.printRecord(profile.getFirstName().toString(), profile.getLastName(), profile.getEmail(), profile.getPw(), profile.getPhoneNumber(), profile.getInsuranceCompanyName(), profile.getInsuranceCompanyFax(), profile.getInsuranceCompanyEmail());
            csvPrinter.flush();
        }
    }
}
