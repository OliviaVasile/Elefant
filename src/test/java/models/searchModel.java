package models;

public class searchModel {
    private String keyword;
    private String result;
    private String productTitle;

    public searchModel ( ) {

    }

    public searchModel (String keyword , String result, String productTitle) {
        this.keyword = keyword;
        this.result = result;
        this.productTitle = productTitle;
    }

    public String getKeyword ( ) {
        return keyword;
    }

    public void setKeyword (String keyword) {
        this.keyword = keyword;
    }

    public String getResult ( ) {
        return result;
    }

    public void setResult (String result) {
        this.result = result;
    }

    public String getProductTitle ( ) {
        return productTitle;
    }

    public void setProductTitle (String productTitle) {
        this.productTitle = productTitle;
    }
}
