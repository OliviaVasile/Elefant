package models;

import org.openqa.selenium.support.ui.Select;



public class contactModel {


    private String name;
    private String phoneNo;
    private String email;
    private String invoice;
    private String details;
    private String nameErr;
    private String phoneErr;
    private String emailErr;
    private String invoiceErr;
    private String validEmailErr;


    public contactModel(){}

    public contactModel(String name ,String phoneNo, String email,  String invoice, String details,
                        String nameErr , String phoneErr, String emailErr, String invoiceErr, String validEmailErr){

        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
        this.invoice = invoice;
        this.details = details;
        this.nameErr = nameErr;
        this.emailErr = emailErr;
        this.phoneErr = phoneErr;
        this.invoiceErr = invoiceErr;
        this.validEmailErr = validEmailErr;
    }

    public String getName ( ) {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPhoneNo ( ) {
        return phoneNo;
    }

    public void setPhoneNo (String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail ( ) {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getInvoice ( ) {
        return invoice;
    }

    public void setInvoice (String invoice) {
        this.invoice = invoice;
    }

    public String getDetails ( ) {
        return details;
    }

    public void setDetails (String details) {
        this.details = details;
    }

    public String getNameErr ( ) {
        return nameErr;
    }

    public void setNameErr (String nameErr) {
        this.nameErr = nameErr;
    }

    public String getPhoneErr ( ) {
        return phoneErr;
    }

    public void setPhoneErr (String phoneErr) {
        this.phoneErr = phoneErr;
    }

    public String getEmailErr ( ) {
        return emailErr;
    }

    public void setEmailErr (String emailErr) {
        this.emailErr = emailErr;
    }

    public String getInvoiceErr ( ) {
        return invoiceErr;
    }

    public void setInvoiceErr (String invoiceErr) {
        this.invoiceErr = invoiceErr;
    }

    public String getValidEmailErr ( ) {
        return validEmailErr;
    }

    public void setValidEmailErr (String validEmailErr) {
        this.validEmailErr = validEmailErr;
    }
}
