package models;

public class changePasswordModel {
    private String username;
    private String password;
    private String newPassword;
    private String oldPassword;

    public changePasswordModel(){

    }
    public changePasswordModel( String username, String password, String newPassword, String oldPassword){
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getUsername ( ) {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword ( ) {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getNewPassword ( ) {
        return newPassword;
    }

    public void setNewPassword (String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword ( ) {
        return oldPassword;
    }

    public void setOldPassword (String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
