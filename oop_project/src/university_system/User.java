package university_system;
import java.io.*;
import java.util.*;
/**
 *
 */
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int counter = 0;

    /**
     * Unique id of this user.
     */
    private int id;

    /**
     * Initializer block that assigns unique id to every user.
     */
    {
        this.id = ++counter;
    }

    /**
     * Default constructor.
     */
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
     * Username used for authentication.
     */
    private String username;
    /**
     * Password used for authentication.
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
    void login(String password) {
    	if(this.password.equals(password)) {
    		this.isLoggedIn = true;
    	}
    	else {
    		throw new IllegalArgumentException("Wrong password!");
    	}
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
    void setPassword(String password, String newPassword) {
    	if(this.password == password) {
    		this.password = password;
    	}
    	else {
    		throw new IllegalArgumentException("Wrong password!");
    	}
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
    boolean isResearcher() {
        return this.researchProfile != null;
    }
    /**
     * Removes the research profile of this user.
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
