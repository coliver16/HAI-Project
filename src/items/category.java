package items;
import java.util.*;

public class category {
    public enum categories {
        Electronics, Appliances, Furniture, Jewelery, Tools;
    }
    //An array list of all the categories
    EnumSet<categories> allCategories = EnumSet.allOf(categories.class);
    List<categories> listOfCategories = new ArrayList<>(allCategories);
}
