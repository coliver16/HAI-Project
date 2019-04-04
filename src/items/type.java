package items;

public class type {
    public String productType;

    //Constructer for naming a new product type
    public type(String typeName) {
        this.productType = typeName;
    }

    //Getter
    public String getProductTypeString() {
        return productType;
    }
}
