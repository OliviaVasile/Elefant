package models;

public class searchModel {
    private String keyword;
    private String result;

    public searchModel ( ) {

    }

    public searchModel (String keyword , String result) {
        this.keyword = keyword;
        this.result = result;
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
}
