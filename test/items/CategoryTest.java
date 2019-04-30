/*package items;

import org.junit.Test;
import org.hamcrest.*;

import static org.junit.Assert.*;

public class CategoryTest {

    //Create string and enum for contstructor testing
    String cat = "Art";
    Category c = new Category(cat);

    Category cEnum = new Category(categories.Art);
    Category d = new Category(categories.Appliances);

    //Constructor testing
    @Test
    public void category()
    {
      assertEquals(c, cEnum);
      assertNotEquals(c, d);
    }

    //Testing for getter
    @Test
    public void getCategory()
    {
      assertEquals(c.getCategory(), cat);
      assertNotEquals(c.getCategory(), "Appliances");
    }

    //Testing for string setter
    @Test
    public void setCategory()
    {
      c.setCategory("Appliances");
      assertEquals(c.getCategory(), "Appliances");
      assertNotEquals(c.getCategory(), cat);
    }

    //Testing for enum setter
    @Test
    public void setCategory1()
    {
      c.setCategory1(categories.Art);
      assertEquals(c.getCategory(), cat);
      assertNotEquals(c.getCategory(), "Appliances");
    }
}*/
