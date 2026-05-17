package university_system;

import java.util.Scanner;

/**
 * Console entry point for the university system.
 */
public class Main {
    private static final String DATA_FILE = "data.ser";

    public static void main(String[] args) {
        Database db = Database.getDb();
        seedDefaultsIfEmpty(db);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== University System ===");
            User user = AuthService.login(scanner);
            if (user != null) {
                dispatch(user, scanner);
            }
        } finally {
            db.saveToFile(DATA_FILE);
            System.out.println("Data saved. Goodbye!");
        }
    }

    private static void dispatch(User user, Scanner scanner) {
        if (user.isResearcher() && (user instanceof Teacher || user instanceof Student)) {
            dispatchMultiRoleUser(user, scanner);
        } else if (user instanceof Admin admin) {
            new AdminMenu(admin, scanner).run();
        } else if (user instanceof Manager manager) {
            new ManagerMenu(manager, scanner).run();
        } else if (user instanceof Teacher teacher) {
            new TeacherMenu(teacher, scanner).run();
        } else if (user instanceof Student student) {
            new StudentMenu(student, scanner).run();
        } else if (user.isResearcher()) {
            new ResearcherMenu(user.getResearchProfile(), scanner).run();
        } else {
            System.out.println("No console menu for role: " + user.getClass().getSimpleName());
        }
    }

    private static void dispatchMultiRoleUser(User user, Scanner scanner) {
        while (true) {
            System.out.println("\nChoose workspace:");
            if (user instanceof Teacher) {
                System.out.println("1. Teacher");
            } else if (user instanceof Student) {
                System.out.println("1. Student");
            }
            System.out.println("2. Researcher");
            System.out.println("0. Logout");
            System.out.print("Choose option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> {
                    if (user instanceof Teacher teacher) {
                        new TeacherMenu(teacher, scanner).run();
                    } else if (user instanceof Student student) {
                        new StudentMenu(student, scanner).run();
                    }
                    return;
                }
                case "2" -> {
                    new ResearcherMenu(user.getResearchProfile(), scanner).run();
                    user.logout();
                    return;
                }
                case "0" -> {
                    user.logout();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void seedDefaultsIfEmpty(Database db) {
        if (!db.getUsers().isEmpty()) {
            return;
        }

        UserService users = OfficeRegister.getUserService();
        Admin admin = users.createAdmin("System", "Admin", "admin", "admin");

        System.out.println("Seeded users:");
        System.out.println("admin/admin");
        System.out.println("Use the admin account to create all other users and assign roles.");

        admin.logout();
    }
}
