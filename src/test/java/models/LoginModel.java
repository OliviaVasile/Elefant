package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class LoginModel {
    private AccountModel account;
    private String userError;
    private String passwordError;
    private String generalError;

    public AccountModel getAccount ( ) {
        return account;
    }

    public void setAccount (AccountModel account) {
        this.account = account;
    }

    public String getUserError ( ) {
        return userError;
    }

    public void setUserError (String userError) {
        this.userError = userError;
    }

    public String getPasswordError ( ) {
        return passwordError;
    }

    public void setPasswordError (String passwordError) {
        this.passwordError = passwordError;
    }

    public String getGeneralError ( ) {
        return generalError;
    }

    public void setGeneralError (String generalError) {
        this.generalError = generalError;
    }

    public LoginModel ( ) {

    }

    public LoginModel (AccountModel account , String userError , String passwordError , String generalError) {
        this.account = account;
        this.userError = userError;
        this.passwordError = passwordError;
        this.generalError = generalError;
    }

    public LoginModel (String username , String password , String userError , String passwordError , String generalError) {
        AccountModel ac = new AccountModel();
        ac.setUsername(username);
        ac.setPassword(password);
        this.account = ac;
        this.userError = userError;
        this.passwordError = passwordError;
        this.generalError = generalError;

    }
}
