package university_system;
import java.io.*;
import java.util.*;

/**
 * Abstract base class representing any user in the university system.
 * All users have a unique auto-incremented id, credentials, and login state.
 * Users must authenticate before accessing the system.
 */
public abstract class User {

	/**
     * Static counter for auto-incrementing user ids.
     */
    private static int counter = 0;

    /**
     * Initializer block that assigns unique id to every user.
     */
    {
        this.id = ++counter;
    }

    User() {
    }

    /**
     * Constructor that initializes user with personal info and credentials.
     * @param firstName first name of the user
     * @param surName last name of the user
     * @param username username for authentication
     * @param password password for authentication
     */
    User(String firstName, String surName, String username, String password) {
        this.firstName = firstName;
        this.surName = surName;
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }
    
    /**
     * Unique auto-incremented identifier for this user.
     */
    private int id;
    
    /**
     * Username used for authentication.
     */
    private String username;
    
    /**
     * Password used for authentication.
     */
    private String password;
    
    /**
     * First name of the user.
     */
    private String firstName;
    
    /**
     * Last name of the user.
     */
    private String surName;
    
    /**
     * Whether the user is currently logged in.
     */
    private Boolean isLoggedIn;
    
    /**
     * Research profile of the user, if they are a researcher.
     */
    private Researcher researchProfile;
    
    /**
     * Returns the unique id of this user.
     * @return user id
     */
    int getId() {
        return this.id;
    }
    
    /**
     * Returns the username of this user.
     * @return username
     */
    String getUserName() {
        return this.username;
    }
    
    /**
     * Returns the full name of this user.
     * @return full name as firstName + surName
     */
    String getFullName() {
        return this.firstName + " " + this.surName;
    }
    
    /**
     * Returns the login state of this user.
     * @return true if user is logged in
     */
    Boolean getIsLoggedIn() {
        return this.isLoggedIn;
    }
    
    /**
     * Returns the research profile of this user.
     * @return research profile or null if not a researcher
     */
    Researcher getResearchProfile() {
        return this.researchProfile;
    }
    
    /**
     * Logs the user into the system.
     * Sets isLoggedIn to true.
     */
    void login() {
        this.isLoggedIn = true;
    }
    
    /**
     * Logs the user out of the system.
     * Sets isLoggedIn to false.
     */
    void logout() {
        this.isLoggedIn = false;
    }
    /**
     * Sets the password of this user.
     * Private to prevent external modification.
     * @param password the password to set
     */
    private void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Sets the first name of this user.
     * @param firstName the first name to set
     */
    void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Sets the last name of this user.
     * @param surName the last name to set
     */
    void setSurName(String surName) {
        this.surName = surName;
    }
    /**
     * Sets the username of this user.
     * @param username the username to set
     */
    void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Sets the research profile of this user.
     * @param researchProfile the research profile to assign
     */
    void setResearchProfile(Researcher researchProfile) {
        this.researchProfile = researchProfile;
    }
    
    /**
     * Removes the research profile of this user.
     */
    void deleteResearchProfile() {
        this.researchProfile = null;
    }

    /**
     * Returns string representation of this user
     * including full name and id.
     * @return string representation
     */
    @Override
    public String toString() {
        return this.firstName + " " + this.surName + ", id: " + this.id + "\n";
    }

    /**
     * Compares this user to another object by id.
     * @param obj the object to compare to
     * @return true if ids are equal
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
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}