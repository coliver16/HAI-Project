package items;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeTest {

    //Create type variable
    Type t0 = new Type("type");

    //Testing for getProductTypeString
    @Test
    public void getProductTypeString()
    {
      assertEquals(t0.getProductTypeString(),"type");
      assertNotEquals(t0.getProductTypeString(), "not type");
    }

    //Testing for constructor
    @Test
    public void constructor()
    {
      assertEquals(t0.getProductTypeString(),"type");
      assertNotEquals(t0.getProductTypeString(), "not type");
    }

    //testing for setProductType
    @Test
    public void setProductType()
    {
      t0.setProductType("new type");
      assertEquals(t0.getProductTypeString(), "new type");
      assertNotEquals(t0.getProductTypeString(), "not type");
    }
}
