package models;

public class RegisterModel {
    private String Prenume;
    private String Nume;
    private String Judet;
    private String Oras;
    private String Adresa;
    private String Telefon;
    private String Email;
    private String Parola;
    private String ConfParola;
    private String eroareNume;
    private String eroarePrenume;
    private String eroareJudet;
    private String eroareOras;
    private String eroareAdresa;
    private String eroareTelefon;
    private String eroareEmail;
    private String eroareParola;
    private String eroareConfParola;

    public RegisterModel(){

    }
    public RegisterModel(String Prenume,String Nume, String Judet, String Oras,
                         String Adresa,String Telefon,String Email,
                         String Parola,String ConfParola, String eroareNume,
                         String eroarePrenume, String eroareJudet,String eroareOras,
                         String eroareAdresa,String eroareTelefon,String eroareEmail,
                         String eroareParola,String eroareConfParola){


        this.Prenume = Prenume;
        this.Nume = Nume;
        this.Judet = Judet;
        this.Oras = Oras;
        this.Adresa = Adresa;
        this.Telefon = Telefon;
        this.Email = Email;
        this.Parola = Parola;
        this.ConfParola = ConfParola;
        this.eroareNume = eroareNume;
        this.eroarePrenume = eroarePrenume;
        this.eroareJudet = eroareJudet;
        this.eroareOras = eroareJudet;
        this.eroareAdresa = eroareAdresa;
        this.eroareTelefon = eroareTelefon;
        this.eroareEmail = eroareEmail;
        this.eroareParola = eroareParola;
        this.eroareConfParola = eroareConfParola;

    }

    public String getPrenume ( ) {
        return Prenume;
    }

    public void setPrenume (String prenume) {
        Prenume = prenume;
    }

    public String getNume ( ) {
        return Nume;
    }

    public void setNume (String nume) {
        Nume = nume;
    }

    public String getJudet ( ) {
        return Judet;
    }

    public void setJudet (String judet) {
        Judet = judet;
    }

    public String getOras ( ) {
        return Oras;
    }

    public void setOras (String oras) {
        Oras = oras;
    }

    public String getAdresa ( ) {
        return Adresa;
    }

    public void setAdresa (String adresa) {
        Adresa = adresa;
    }

    public String getTelefon ( ) {
        return Telefon;
    }

    public void setTelefon (String telefon) {
        Telefon = telefon;
    }

    public String getEmail ( ) {
        return Email;
    }

    public void setEmail (String email) {
        Email = email;
    }

    public String getParola ( ) {
        return Parola;
    }

    public void setParola (String parola) {
        Parola = parola;
    }

    public String getConfParola ( ) {
        return ConfParola;
    }

    public void setConfParola (String confParola) {
        ConfParola = confParola;
    }

    public String getEroareNume ( ) {
        return eroareNume;
    }

    public void setEroareNume (String eroareNume) {
        this.eroareNume = eroareNume;
    }

    public String getEroarePrenume ( ) {
        return eroarePrenume;
    }

    public void setEroarePrenume (String eroarePrenume) {
        this.eroarePrenume = eroarePrenume;
    }

    public String getEroareJudet ( ) {
        return eroareJudet;
    }

    public void setEroareJudet (String eroareJudet) {
        this.eroareJudet = eroareJudet;
    }

    public String getEroareOras ( ) {
        return eroareOras;
    }

    public void setEroareOras (String eroareOras) {
        this.eroareOras = eroareOras;
    }

    public String getEroareAdresa ( ) {
        return eroareAdresa;
    }

    public void setEroareAdresa (String eroareAdresa) {
        this.eroareAdresa = eroareAdresa;
    }

    public String getEroareTelefon ( ) {
        return eroareTelefon;
    }

    public void setEroareTelefon (String eroareTelefon) {
        this.eroareTelefon = eroareTelefon;
    }

    public String getEroareEmail ( ) {
        return eroareEmail;
    }

    public void setEroareEmail (String eroareEmail) {
        this.eroareEmail = eroareEmail;
    }

    public String getEroareParola ( ) {
        return eroareParola;
    }

    public void setEroareParola (String eroareParola) {
        this.eroareParola = eroareParola;
    }

    public String getEroareConfParola ( ) {
        return eroareConfParola;
    }

    public void setEroareConfParola (String eroareConfParola) {
        this.eroareConfParola = eroareConfParola;
    }
}
