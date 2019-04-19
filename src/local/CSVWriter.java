package local;

import items.Item;
import org.apache.commons.csv.*;
import users.Profile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

public class CSVWriter {

    private static final String USER_ITEMS_FILE = "src\\local\\useritems1.csv";
    private static final String USER_PROFILE = "src\\users\\userprofile.csv";

    public static void writeCSV(List<Item> items) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(USER_ITEMS_FILE));

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader("itemNo","Room","Category","Type","make","model","serial","receipt","photo","value","comments","lastupdate","delete"));
        ) {
          for  (Item i: items) {
              csvPrinter.printRecord(i.getItemNo(), i.getRoom(), i.getCategory(), i.getType(), i.getMake(), i.getModel(), i.getSerial(), i.getReceipt(), i.getPhoto(), i.getValue(), i.getComments(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
          }
            csvPrinter.flush();
        }
    }

    public static void writeUserProfile(Profile profile) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(USER_PROFILE));

             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader("firstName","lastName","email","password","phoneNumber","insuranceCompanyName","insuranceCompanyFax"));
        ) {
            csvPrinter.printRecord(profile.getFirstName(), profile.getLastName(), profile.getEmail(), profile.getPw(), profile.getPhoneNumber(), profile.getInsuranceCompanyName(), profile.getInsuranceCompanyFax(), profile.getInsuranceCompanyEmail());
            csvPrinter.flush();
        }
    }
}
