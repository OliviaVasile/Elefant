package models;

public class cartModel {
    private String qty;
    private String mesaj;


    public cartModel ( ) {

    }

    public cartModel (String keyword , String result) {
        this.qty = qty;
        this.mesaj = mesaj;

    }

    public String getQty ( ) {
        return qty;
    }

    public void setQty (String qty) {
        this.qty = qty;
    }

    public String getMesaj ( ) {
        return mesaj;
    }

    public void setMesaj (String mesaj) {
        this.mesaj = mesaj;
    }


}
