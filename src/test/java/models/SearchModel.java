package models;

public class SearchModel {
    private String keyword;
    private String result;

    public SearchModel ( ) {

    }

    public SearchModel (String keyword , String result) {
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
