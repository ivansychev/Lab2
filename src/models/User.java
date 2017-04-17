package models;

public class User {
    private int user_id;
    private String user_rigths;
    private String user_firstname;
    private String user_surname;
    private String user_email;
    private String user_password;
    private String user_mobile;


    public User(int user_id, String user_rigths, String user_firstname, String user_surname, String user_email, String user_password, String user_mobile) {
        this.user_id = user_id;
        this.user_rigths = user_rigths;
        this.user_firstname = user_firstname;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_mobile = user_mobile;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_rigths() {
        return user_rigths;
    }

    public String getUser_firstname() {
        return user_firstname;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public String getUser_mobile() {
        return user_mobile;
    }
}
