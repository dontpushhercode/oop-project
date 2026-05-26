package university_system;

import java.io.Serializable;

/**
 * Represents an administrator in the university system.
 */
public class Admin extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    Admin() {
        super();
    }

    Admin(String firstname, String surname, String password) {
        super(firstname, surname, password, DepartmentType.ADMINISTRATION);
    }
}
