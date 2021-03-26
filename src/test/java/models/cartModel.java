package models;

public class cartModel {
    private String keyword;
    private String result;
    private String qty;
    private String mesaj;
    private String pret;
    private String pretTotalProdus;
    private String totalGeneral;
    private String totalProduseInCos;





    public cartModel ( ) {

    }

    public cartModel (String keyword, String result, String qty , String mesaj, String pret,
                      String pretTotalProdus, String totalGeneral, String totalProduseInCos) {
        this.keyword = keyword;
        this.result = result;
        this.qty = qty;
        this.mesaj = mesaj;
        this.pret = pret;
        this.pretTotalProdus = pretTotalProdus;
        this.totalGeneral = totalGeneral;
        this.totalProduseInCos = totalProduseInCos;

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

    public String getMesaj ( ) {
        return mesaj;
    }

    public void setMesaj (String mesaj) {
        this.mesaj = mesaj;
    }

    public String getQty ( ) {
        return qty;
    }

    public void setQty (String qty) {
        this.qty = qty;
    }

    public String getPret ( ) {
        return pret;
    }

    public void setPret (String pret) {
        this.pret = pret;
    }

    public String getPretTotalProdus ( ) {
        return pretTotalProdus;
    }

    public void setPretTotalProdus (String pretTotalProdus) {
        this.pretTotalProdus = pretTotalProdus;
    }

    public String getTotalGeneral ( ) {
        return totalGeneral;
    }

    public void setTotalGeneral (String totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public String getTotalProduseInCos ( ) {
        return totalProduseInCos;
    }

    public void setTotalProduseInCos (String totalProduseInCos) {
        this.totalProduseInCos = totalProduseInCos;
    }
}
