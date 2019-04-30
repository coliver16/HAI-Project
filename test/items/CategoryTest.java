package items;

import org.junit.Test;
import org.hamcrest.*;

import static org.junit.Assert.*;

public class CategoryTest {

    String cat = "Art";
    Category c = new Category(cat);

    @Test
    public void getCategory()
    {
      assertEquals(c.getCategory(), cat);
      assertNotEquals(c.getCategory(), "Appliances");
    }

    @Test
    public void setCategory()
    {
      c.setCategory("Appliances");
      assertEquals(c.getCategory(), "Appliances");
      assertNotEquals(c.getCategory(), cat);
    }

    @Test
    public void setCategory1()
    {
      c.setCategory1(categories.Art);
      assertEquals(c.getCategory(), cat);
      assertNotEquals(c.getCategory(), "Appliances");      
    }
}
