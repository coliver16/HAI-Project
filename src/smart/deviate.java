package smart;

import java.util.List;

//Create a class "deviate" which has the values userInputs and suggested
public class deviate {
    static float userInput;
    static float suggested;
    public static List<Float> deviations;

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
    }

    //Check if there are enough deviations to update dev
    public static boolean checkDeviationsSize() {
        int size = deviations.size();
        boolean check;
        if (size <= 10) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }

    //Calculates the mean of all single deviations in order to return an adjusted/updated overall deviation to be accounted for during future suggestions
    public static float devUpdate() {
        float devDec = 0;
        boolean proceed = checkDeviationsSize();
        float sum = 0;
        float standard = .8f;
        float negStandard = -.8f;
        Float[] arrDeviations = new Float[0];
        if (proceed) {
            //calculate mean of all deviations
            arrDeviations = deviations.toArray(arrDeviations);
            //Float[] arrDeviations = deviations.toArray(new Float[deviations.size()]);
            for (int i = 0; i < deviations.size(); i++) {
                if ((Float.compare(arrDeviations[i], standard) < 1) && (Float.compare(arrDeviations[i], negStandard) > 1)) {
                    sum += arrDeviations[i];
                }
            }
            devDec = sum / deviations.size();
        }
        //empty the array, so that the next 10 values can be stored + devUpdate can update dev accordingly
        deviations.clear();
        return devDec;
    }
}
