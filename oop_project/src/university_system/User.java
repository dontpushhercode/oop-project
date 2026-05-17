package university_system;
import java.io.*;
import java.util.*;
/**
 *
 */
public abstract class User {

    private static int counter = 0;

    {
        this.id = ++counter;
    }

    User() {
    }

    User(String firstName, String surName, String username, String password) {
        this.firstName = firstName;
        this.surName = surName;
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }
    /**
     *
     */
    private int id;
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String password;
    /**
     *
     */
    private String firstName;
    /**
     *
     */
    private String surName;
    /**
     *
     */
    private Boolean isLoggedIn;
    /**
     *
     */
    private Researcher researchProfile;
    /**
     *
     */
    int getId() {
        return this.id;
    }
    /**
     *
     */
    String getUserName() {
        return this.username;
    }
    /**
     *
     */
    String getFullName() {
        return this.firstName + " " + this.surName;
    }
    /**
     *
     */
    void login() {
        this.isLoggedIn = true;
    }
    /**
     *
     */
    void logout() {
        this.isLoggedIn = false;
    }
    /**
     *
     */
    private void setPassword(String password) {
        this.password = password;
    }
    /**
     *
     */
    void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     *
     */
    void setSurName(String surName) {
        this.surName = surName;
    }
    /**
     *
     */
    void setUsername(String username) {
        this.username = username;
    }
    /**
     *
     */
    void setResearchProfile(Researcher researchProfile) {
        this.researchProfile = researchProfile;
    }
    /**
     *
     */
    Researcher getResearchProfile() {
        return this.researchProfile;
    }
    /**
     *
     */
    void deleteResearchProfile() {
        this.researchProfile = null;
    }
    /**
     *
     */
    Boolean getIsLoggedIn() {
        return this.isLoggedIn;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.surName + ", id: " + this.id + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}