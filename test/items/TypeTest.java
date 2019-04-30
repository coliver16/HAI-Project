package items;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeTest {

    Type t0 = new Type("type");

    @Test
    public void getProductTypeString()
    {
      assertEquals(t0.getProductTypeString(),"type");
      assertNotEquals(t0.getProductTypeString(), "not type");
    }

    @Test
    public void constructor()
    {
      assertEquals(t0.getProductTypeString(),"type");
      assertNotEquals(t0.getProductTypeString(), "not type");
    }

    @Test
    public void setProductType()
    {
      t0.setProductType("new type");
      assertEquals(t0.getProductTypeString(), "new type");
      assertNotEquals(t0.getProductTypeString(), "not type");
    }
}
