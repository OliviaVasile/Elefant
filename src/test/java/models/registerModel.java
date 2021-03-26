package models;

public class registerModel {
    private String prenume;
    private String nume;
    private String judet;
    private String oras;
    private String adresa;
    private String telefon;
    private String email;
    private String parola;
    private String confParola;
    private String eroareNume;
    private String eroarePrenume;
    private String eroareJudet;
    private String eroareOras;
    private String eroareAdresa;
    private String eroareTelefon;
    private String eroareEmail;
    private String eroareParola;
    private String eroareConfParola;
    private String validEmailError;
    private String validPasswordError;
    private String mismatchPassError;
    private String alreadyRegisteredUser;

    public registerModel ( ) {

    }

    public registerModel (String prenume , String nume , String judet , String oras ,
                          String adresa , String telefon , String email ,
                          String parola , String confParola ,
                          String eroarePrenume , String eroareNume ,
                          String eroareJudet , String eroareOras ,
                          String eroareAdresa , String eroareTelefon , String eroareEmail ,
                          String eroareParola , String eroareConfParola ,
                          String validEmailError , String validPasswordError ,
                          String mismatchPassError , String alreadyRegisteredUser) {


        this.prenume = prenume;
        this.nume = nume;
        this.judet = judet;
        this.oras = oras;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
        this.parola = parola;
        this.confParola = confParola;
        this.eroareNume = eroareNume;
        this.eroarePrenume = eroarePrenume;
        this.eroareJudet = eroareJudet;
        this.eroareOras = eroareOras;
        this.eroareAdresa = eroareAdresa;
        this.eroareTelefon = eroareTelefon;
        this.eroareEmail = eroareEmail;
        this.eroareParola = eroareParola;
        this.eroareConfParola = eroareConfParola;
        this.validEmailError = validEmailError;
        this.validPasswordError = validPasswordError;
        this.mismatchPassError = mismatchPassError;
        this.alreadyRegisteredUser = alreadyRegisteredUser;

    }

    public String getPrenume ( ) {
        return prenume;
    }

    public void setPrenume (String prenume) {
        this.prenume = prenume;
    }

    public String getNume ( ) {
        return nume;
    }

    public void setNume (String nume) {
        this.nume = nume;
    }

    public String getJudet ( ) {
        return judet;
    }

    public void setJudet (String judet) {
        this.judet = judet;
    }

    public String getOras ( ) {
        return oras;
    }

    public void setOras (String oras) {
        this.oras = oras;
    }

    public String getAdresa ( ) {
        return adresa;
    }

    public void setAdresa (String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon ( ) {
        return telefon;
    }

    public void setTelefon (String telefon) {
        this.telefon = telefon;
    }

    public String getEmail ( ) {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getParola ( ) {
        return parola;
    }

    public void setParola (String parola) {
        this.parola = parola;
    }

    public String getConfParola ( ) {
        return confParola;
    }

    public void setConfParola (String confParola) {
        this.confParola = confParola;
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

    public String getValidEmailError ( ) {
        return validEmailError;
    }

    public void setValidEmailError (String validEmailError) {
        this.validEmailError = validEmailError;
    }

    public String getValidPasswordError ( ) {
        return validPasswordError;
    }

    public void setValidPasswordError (String validPasswordError) {
        this.validPasswordError = validPasswordError;
    }

    public String getMismatchPassError ( ) {
        return mismatchPassError;
    }

    public void setMismatchPassError (String mismatchPassError) {
        this.mismatchPassError = mismatchPassError;
    }

    public String getAlreadyRegisteredUser ( ) {
        return alreadyRegisteredUser;
    }

    public void setAlreadyRegisteredUser (String alreadyRegisteredUser) {
        this.alreadyRegisteredUser = alreadyRegisteredUser;
    }
}