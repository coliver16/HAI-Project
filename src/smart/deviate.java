package smart;

import items.ItemList;
import local.CSVParser;
import local.CSVWriter;

import java.util.ArrayList;
import java.util.List;

//Create a class "deviate" which has the values userInputs and suggested
public class deviate {
    static float userInput;
    static float suggested;
    public static List<Float> deviations = new ArrayList<>();
    public static float devDec = 0;

    //Constructor
    public deviate(float in, float s) {
         userInput = in;
         suggested = s;
    }

    //Getters
    public static float getUserInput() {
        return userInput;
    }

    public static float getSuggested() {
        return suggested;
    }

    public List<Float> getDeviations() {
        return deviations;
    }

    //Setters
    public void setUserInput(float in) {
        userInput = in;
    }

    public void setSuggested(float s) {
        suggested = s;
    }


    //Calculates the percentage of deviation from the suggested price and store as a float, and return that float
    public float calculateSingleDeviation() {
        float singleDev;
        singleDev = (deviate.getUserInput() - deviate.getSuggested()) / (deviate.getSuggested());
        return singleDev;
    }

    //Adds the calculated percentage (float) to a list of other deviations
    public static void addDeviationDecimal(float suggested, float input) {
        float singleDevAddition;
        deviate d = new deviate(suggested, input);
        singleDevAddition = d.calculateSingleDeviation();
        deviations.add(singleDevAddition);

        Thread thread = new Thread() {
            public void run() {
                try {
                    CSVWriter.deviationCSV(deviations);
                    System.out.println("wrote file deviations");
                } catch (Exception e) {
                    System.out.println("Error");
                    e.printStackTrace();
                    System.out.println(e);
                }
            }
        };
        thread.start();

    }

    //Check if there are enough deviations to update dev
    public static boolean checkDeviationsSize() {
        int size = deviations.size();
        boolean check;
        if (size < 10) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }

    //Calculates the mean of all single deviations in order to return an adjusted/updated overall deviation to be accounted for during future suggestions
    public static float devUpdate() {
        if (deviations.isEmpty()) {
            try {
                deviations = CSVParser.readDeviations();
                System.out.println("wrote file deviations");
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
                System.out.println(e);
            }
        }
        boolean proceed = checkDeviationsSize();
        float sum = 0;
        float standard = .8f;
        float negStandard = -.8f;
        Float[] arrDeviations = new Float[0];
        int numDeviationsCounted = 0;
        //Enough new data has been collected for dev to be updated
        if (proceed) {
            //calculate mean of all new deviations between -.8 and .8
            arrDeviations = deviations.toArray(arrDeviations);
            for (int i = 0; i < deviations.size(); i++) {
                if ((Float.compare(arrDeviations[i], standard) < 1) && (Float.compare(arrDeviations[i], negStandard) > 1)) {
                    sum += arrDeviations[i];
                    numDeviationsCounted++;

                }
            }
            devDec = sum / numDeviationsCounted;
            priceSuggestor.numDevUpdates++;
        }
        //Dev has gone through no updates meaning, no data has been collected yet, therefore dev is 0
        else if (priceSuggestor.numDevUpdates == 0) {devDec = 0;}
        //Dev has gone through atleast one update, but not enough new data has been collected for an update, therefore the old dev value is returned
        else {devDec = priceSuggestor.devPointer;}
        //empty the array, so that the next 10 values can be stored + devUpdate can update dev accordingly
        deviations.clear();
        return devDec;
    }
}
