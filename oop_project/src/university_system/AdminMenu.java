package university_system;

import java.util.List;
import java.util.Scanner;

/**
 * Console menu for administrators.
 */
public class AdminMenu {
    private final Admin admin;
    private final Scanner scanner;
    private final Database db = Database.getDb();
    private final UserService userService = OfficeRegister.getUserService();

    public AdminMenu(Admin admin, Scanner scanner) {
        this.admin = admin;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Admin Menu: " + admin.getFullName() + " ---");
            System.out.println("1. Create user and assign role");
            System.out.println("2. View all users");
            System.out.println("3. Delete user");
            System.out.println("4. Change user info");
            System.out.println("5. View logs");
            System.out.println("0. Logout");
            System.out.print("Choose option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> addUser();
                case "2" -> viewAllUsers();
                case "3" -> deleteUser();
                case "4" -> changeInfo();
                case "5" -> viewLogs();
                case "0" -> {
                    admin.logout();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addUser() {
        System.out.println("\nRole: 1.Student 2.Teacher 3.Manager 4.Employee");
        System.out.print("Choose role: ");
        String type = scanner.nextLine().trim();
        System.out.print("First name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Surname: ");
        String surname = scanner.nextLine().trim();
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        try {
            User created;
            switch (type) {
                case "1" -> {
                    int year = readInt("Year (1-4): ");
                    School school = readEnum("School", School.class);
                    created = userService.createStudent(firstName, surname, password, username, year, school);
                }
                case "2" -> {
                    School school = readEnum("School", School.class);
                    TeacherType teacherType = readEnum("Teacher type", TeacherType.class);
                    created = userService.createTeacher(firstName, surname, password, username, school, teacherType);
                }
                case "3" -> {
                    ManagerType managerType = readEnum("Manager type", ManagerType.class);
                    created = userService.createManager(firstName, surname, password, username, managerType);
                }
                case "4" -> {
                    DepartmentType department = readEnum("Department", DepartmentType.class);
                    created = userService.createEmployee(firstName, surname, password, username, department);
                }
                default -> {
                    System.out.println("Unknown role.");
                    return;
                }
            }
            System.out.println("Created user: " + created.getFullName() + " (id " + created.getId() + ")");
        } catch (RuntimeException e) {
            System.out.println("Could not create user: " + e.getMessage());
        }
    }

    private void viewAllUsers() {
        List<User> users = userService.getUsers();
        if (users.isEmpty()) {
            System.out.println("No users.");
            return;
        }
        for (User user : users) {
            System.out.println(user.getId() + " | " + user);
        }
    }

    private void deleteUser() {
        viewAllUsers();
        int id = readInt("User id to delete: ");
        User user = userService.getUser(id);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        if (id == 1) {
        	System.out.println("Admin can not be deleted.");
        	return;
        }
        userService.deleteUser(user);
        System.out.println("Deleted: " + user);
    }

    private void viewLogs() {
        if (db.getLogs().isEmpty()) {
            System.out.println("No logs.");
            return;
        }
        for (Log log : db.getLogs()) {
            System.out.println(log);
        }
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private <T extends Enum<T>> T readEnum(String label, Class<T> enumType) {
        System.out.println(label + ":");
        T[] values = enumType.getEnumConstants();
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i]);
        }
        int index = readInt("Choose: ") - 1;
        if (index < 0 || index >= values.length) {
            throw new IllegalArgumentException("Invalid " + label.toLowerCase());
        }
        return values[index];
    }
    
    private void changeInfo() {
        viewAllUsers();

        int id = readInt("User id to edit: ");
        User user = userService.getUser(id);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("\nEditing user: " + user.getFullName());
        System.out.println("1. First name");
        System.out.println("2. Surname");
        System.out.println("3. Username");
        System.out.print("Choose field: ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> {
                System.out.print("New first name: ");
                String value = scanner.nextLine().trim();
                user.setFirstName(value);
            }
            case "2" -> {
                System.out.print("New surname: ");
                String value = scanner.nextLine().trim();
                user.setSurName(value);
            }
            case "3" -> {
                System.out.print("New username: ");
                String value = scanner.nextLine().trim();
                user.setUsername(value);
            }
            default -> {
                System.out.println("Invalid option.");
                return;
            }
        }

        System.out.println("User updated: " + user.getFullName());
    }
}
