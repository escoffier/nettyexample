package util;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "USer: {" + userName + " , " + password + ", " + description +"}";
    }
}
