package models;

public class CartModel {
    private String qty;
    private String mesaj;


    public CartModel ( ) {

    }

    public CartModel (String keyword , String result) {
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
