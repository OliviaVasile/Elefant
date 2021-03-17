package models;

public class ProductModel {
    private String product;

    public ProductModel(){

    }
    public ProductModel(String product){
        this.product = product;
    }

    public String getProduct ( ) {
        return product;
    }

    public void setProduct (String product) {
        this.product = product;
    }
}
