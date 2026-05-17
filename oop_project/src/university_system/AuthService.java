package university_system;

import java.util.Scanner;

/**
 * Console authentication helper.
 */
public class AuthService {
    private static final int MAX_ATTEMPTS = 3;

    private AuthService() {
    }

    public static User login(Scanner scanner) {
        UserService userService = OfficeRegister.getUserService();
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Password: ");
            String password = scanner.nextLine().trim();

            try {
                User user = userService.login(username, password);
                System.out.println("Welcome, " + user.getFullName() + "!");
                return user;
            } catch (AuthenticationException e) {
                int left = MAX_ATTEMPTS - attempt;
                System.out.println("Invalid username or password." + (left > 0 ? " Attempts left: " + left : ""));
            }
        }
        return null;
    }
}
