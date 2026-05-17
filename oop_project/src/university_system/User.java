package university_system;
import java.io.*;
import java.util.*;

/**
 *
 * Represents a user in the university system.
 */
public abstract class User implements Serializable {

    /**
     * Auto-incremented counter for generating unique ids.
     */
    private static int counter = 0;

    /**
     * Unique id of this user.
     */
    private int id;

    {
        this.id = ++counter;
    }

    /**
     * Default constructor.
     */
    User() {
    }

    /**
     * Constructor that initializes user with all fields.
     */
    User(String firstName, String surName, String username, String password) {
        this.firstName = firstName;
        this.surName = surName;
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }

    /**
     * Username used for authentication.
     */
    private String username;

    /**
     * Password used for authentication.
     */
    private String password;

    /**
     * First name of this user.
     */
    private String firstName;

    /**
     * Last name of this user.
     */
    private String surName;

    /**
     * Indicates whether this user is currently logged in.
     */
    private Boolean isLoggedIn;

    /**
     * Research profile associated with this user.
     */
    private Researcher researchProfile;

    /**
     * Returns the unique id of this user.
     */
    int getId() {
        return this.id;
    }

    /**
     * Returns the username of this user.
     */
    String getUserName() {
        return this.username;
    }

    /**
     * Returns the full name of this user.
     */
    String getFullName() {
        return this.firstName + " " + this.surName;
    }

    /**
     * Checks if the provided password matches this user's password.
     */
    boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Logs in this user.
     */
    void login() {
        this.isLoggedIn = true;
    }

    /**
     * Logs out this user.
     */
    void logout() {
        this.isLoggedIn = false;
    }

    /**
     * Sets the password of this user.
     */
    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the first name of this user.
     */
    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of this user.
     */
    void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * Sets the username of this user.
     */
    void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the research profile of this user.
     */
    void setResearchProfile(Researcher researchProfile) {
        this.researchProfile = researchProfile;
    }

    /**
     * Returns the research profile of this user.
     */
    Researcher getResearchProfile() {
        return this.researchProfile;
    }

    /**
     * Removes the research profile of this user.
     */
    void deleteResearchProfile() {
        this.researchProfile = null;
    }

    /**
     * Returns whether this user is currently logged in.
     */
    Boolean getIsLoggedIn() {
        return this.isLoggedIn;
    }

    /**
     * Returns string representation of this user
     * including full name and id.
     */
    @Override
    public String toString() {
        return this.firstName + " " + this.surName + ", id: " + this.id + "\n";
    }

    /**
     * Compares this user to another object by id.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return this.id == other.id;
    }

    /**
     * Returns hash code based on user id.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}