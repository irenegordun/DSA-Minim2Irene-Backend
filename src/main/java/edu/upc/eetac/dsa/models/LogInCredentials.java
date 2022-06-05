package edu.upc.eetac.dsa.models;

public class LogInCredentials {
    private String username;
    private String password;

    public LogInCredentials(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public LogInCredentials() {

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
