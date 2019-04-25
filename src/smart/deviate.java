package smart;

import java.util.List;

//Create a class "deviate" that creates deviate objects which have values userInputs and suggested
public class deviate {
    static float userInput;
    static float suggested;
    public static List<Float> deviations;
    float dev = 0;

    //Constructor
    public deviate(float in, float s) {
        float userInput = in;
        float suggested = s;
    }

    //Getters
    public static float getUserInput() {return userInput;}
    public static float getSuggested() {return suggested;}
    public List<Float> getDeviations() { return deviations; }
    //Setters
    public void setUserInput(float in) {userInput = in;}
    public void setSuggested(float s) {suggested = s;}


    //Calculates the percentage of deviation from the suggested price and store as a float, and return that float
    public float calculateSingleDeviation() {
        float singleDev = 0;
        singleDev = (deviate.getUserInput() - deviate.getSuggested())/(deviate.getSuggested());
        return singleDev;
    }

    //Adds the calculated percentage (float) to a list
    public static void addDeviationDecimal(float suggested, float input) {
        float singleDevAddition = 0;
        deviate d = new deviate(suggested, input);
        singleDevAddition = d.calculateSingleDeviation();
        deviations.add(singleDevAddition);
    }

    //Check if there are enough deviations to update dev
    public boolean checkDeviationsSize() {
        int size = deviations.size();
        boolean check;
        if (size <= 10) {check = false;}
        else {check = true;}
        return check;
    }

    //Calculates the mean of all single deviations in order to return an adjusted/updated overall deviation to be accounted for during future suggestions
    public float devUpdate() {
        boolean proceed = checkDeviationsSize();
        float sum = 0;
        if (proceed = true) {
            //calculate mean of all deviations
            Float[] arrdeviations = deviations.toArray(new Float[deviations.size()]);
            for (int i = 0; i < deviations.size(); i++) {
                sum += arrdeviations[i];
            }
            dev = sum/deviations.size();
        }
        return dev;
    }
}
