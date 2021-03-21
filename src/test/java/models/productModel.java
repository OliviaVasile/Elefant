package models;

public class productModel {
    private String product;

    public productModel (){

    }
    public productModel (String product){
        this.product = product;
    }

    public String getProduct ( ) {
        return product;
    }

    public void setProduct (String product) {
        this.product = product;
    }
}
