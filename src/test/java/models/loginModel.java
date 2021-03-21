package models;


public class loginModel {
    private accountModel account;
    private String userError;
    private String passwordError;
    private String generalError;

    public accountModel getAccount ( ) {
        return account;
    }

    public void setAccount (accountModel account) {
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

    public loginModel ( ) {

    }

    public loginModel (accountModel account , String userError , String passwordError , String generalError) {
        this.account = account;
        this.userError = userError;
        this.passwordError = passwordError;
        this.generalError = generalError;
    }

    public loginModel (String username , String password , String userError , String passwordError , String generalError) {
        accountModel ac = new accountModel();
        ac.setUsername(username);
        ac.setPassword(password);
        this.account = ac;
        this.userError = userError;
        this.passwordError = passwordError;
        this.generalError = generalError;

    }
}
