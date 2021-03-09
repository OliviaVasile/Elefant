package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginModel {
    private AccountModel account;
    private String userError;
    private String passwordError;
    private String generalError;

    public AccountModel getAccount ( ) {
        return account;
    }
@XmlElement
    public void setAccount (AccountModel account) {
        this.account = account;
    }

    public String getUserError ( ) {
        return userError;
    }
@XmlElement
    public void setUserError (String userError) {
        this.userError = userError;
    }

    public String getPasswordError ( ) {
        return passwordError;
    }
@XmlElement
    public void setPasswordError (String passwordError) {
        this.passwordError = passwordError;
    }

    public String getGeneralError ( ) {
        return generalError;
    }
@XmlElement
    public void setGeneralError (String generalError) {
        this.generalError = generalError;
    }
}
