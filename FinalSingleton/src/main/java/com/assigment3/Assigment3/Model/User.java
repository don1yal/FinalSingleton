package com.assigment3.Assigment3.Model;

public class User {
    private int hostname;
    private String username;
    private String password;
    private String databaseName;
    public User() {

    }

    public User(int hostname, String username, String password, String databaseName) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
    }

    public int getHostname() {
        return hostname;
    }

    public void setHostname(int hostname) {
        this.hostname = hostname;
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

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public String toString() {
        return "User{" +
                "hostname=" + hostname +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", databaseName='" + databaseName + '\'' +
                '}';
    }
}
