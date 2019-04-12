package items;

public class Type {
    public String productType;

    //Constructer for naming a new product Type
    public Type(String typeName) {
        this.productType = typeName;
    }

    //Getter
    public String getProductTypeString() {
        return productType;
    }

    public void setProductType(String s) {
        productType = s;
    }
}
