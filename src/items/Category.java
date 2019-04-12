package items;
import java.util.*;

public class Category {
    private categories category;

    public Category(String c) {
        category = categories.valueOf(c);
    }

    public Category(categories c) {
        category = c;
    }

    public enum categories {
        Antiques, Appliances, Art, Automotive, Clothing, Electronics, Furniture, Jewelry, Tools, Other
    }

    public categories getCategory() {
        return category;
    }

    public void setCategory(String c) {
        category = categories.valueOf(c);
    }

    public void setCategory(categories c) {
        category = c;
    }


    //An array list of all the categories
    EnumSet<categories> allCategories = EnumSet.allOf(categories.class);
    List<categories> listOfCategories = new ArrayList<>(allCategories);
}
