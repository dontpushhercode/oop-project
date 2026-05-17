package university_system;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 * Console menu for managers.
 */
public class ManagerMenu {
    private final Manager manager;
    private final Scanner scanner;
    private final Database db = Database.getDb();
    private final CourseService courseService = OfficeRegister.getCourseService();
    private final RequestService requestService = OfficeRegister.getRequestService();
    private final EnrollmentService enrollmentService = OfficeRegister.getEnrollmentService();
    private final ReportService reportService = OfficeRegister.getReportService();

    public ManagerMenu(Manager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    public void run() {
        switch (manager.getManagerType()) {
            case OR -> runOfficeRegistrationMenu();
            case ACADEMIC -> runAcademicMenu();
            case ADMINISTRATIVE -> runAdministrativeMenu();
            case DEPARTMENT -> runDepartmentMenu();
            default -> System.out.println("Unsupported manager type: " + manager.getManagerType());
        }
    }

    private void runOfficeRegistrationMenu() {
        while (true) {
            System.out.println("\n--- OR Manager Menu: " + manager.getFullName() + " ---");
            System.out.println("1. Create course");
            System.out.println("2. Create section");
            System.out.println("3. Assign teacher to section");
            System.out.println("4. Add lesson to section");
            System.out.println("5. View registration requests");
            System.out.println("6. Approve/reject registration request");
            System.out.println("7. Assign student to section");
            System.out.println("8. View courses");
            System.out.println("9. View sections");
            System.out.println("10. View students");
            System.out.println("0. Logout");
            System.out.print("Choose option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> runSafely(this::createCourse);
                case "2" -> runSafely(this::createSection);
                case "3" -> runSafely(this::assignTeacher);
                case "4" -> runSafely(this::addLessonToSection);
                case "5" -> viewRegistrationRequests();
                case "6" -> runSafely(this::updateRegistrationRequest);
                case "7" -> runSafely(this::assignStudent);
                case "8" -> viewCourses();
                case "9" -> viewSections();
                case "10" -> viewStudents();
                case "0" -> {
                    manager.logout();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void runAdministrativeMenu() {
        while (true) {
            System.out.println("\n--- Administrative Manager Menu: " + manager.getFullName() + " ---");
            System.out.println("1. View employee requests");
            System.out.println("2. Approve/reject employee request");
            System.out.println("3. View employees");
            System.out.println("0. Logout");
            System.out.print("Choose option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> viewEmployeeRequests();
                case "2" -> runSafely(this::updateEmployeeRequest);
                case "3" -> viewEmployees();
                case "0" -> {
                    manager.logout();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void runAcademicMenu() {
        while (true) {
            System.out.println("\n--- Academic Manager Menu: " + manager.getFullName() + " ---");
            System.out.println("1. Generate reports");
            System.out.println("2. View courses");
            System.out.println("3. View students");
            System.out.println("4. View teachers");
            System.out.println("0. Logout");
            System.out.print("Choose option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> runSafely(this::generateReports);
                case "2" -> viewCourses();
                case "3" -> viewStudents();
                case "4" -> viewTeachers();
                case "0" -> {
                    manager.logout();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void runDepartmentMenu() {
        while (true) {
            System.out.println("\n--- Department Manager Menu: " + manager.getFullName() + " ---");
            System.out.println("1. View courses");
            System.out.println("2. View sections");
            System.out.println("3. View teachers");
            System.out.println("0. Logout");
            System.out.print("Choose option: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> viewCourses();
                case "2" -> viewSections();
                case "3" -> viewTeachers();
                case "0" -> {
                    manager.logout();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void runSafely(MenuAction action) {
        try {
            action.run();
        } catch (RuntimeException e) {
            System.out.println("Operation failed: " + e.getMessage());
        }
    }

    private void createCourse() {
        System.out.print("Code: ");
        String code = scanner.nextLine().trim();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Description: ");
        String description = scanner.nextLine().trim();
        int credits = readInt("Credits: ");
        School school = readEnum("School", School.class);
        Course course = courseService.createCourse(manager, code, name, description, school, credits);
        System.out.println("Created course: " + course.getCourseCode() + " " + course.getCourseName());
    }

    private void createSection() {
        Course course = chooseCourse();
        if (course == null) return;
        Semester semester = readEnum("Semester", Semester.class);
        Section section = courseService.createSection(manager, course, semester);
        System.out.println("Created section id " + section.getId() + " for " + course.getCourseName());
    }

    private void assignTeacher() {
        Section section = chooseSection();
        Teacher teacher = chooseTeacher();
        if (section == null || teacher == null) return;
        Course course = section.getCourse();
        if (!course.getInstructors().contains(teacher)) {
            courseService.addInstructor(manager, course, teacher);
        }
        courseService.addTeacher(manager, section, teacher);
        System.out.println("Assigned " + teacher.getFullName() + " to section " + section.getId());
    }
    
    private void addLessonToSection() {
        Section section = chooseSection();
        if (section == null) return;
        LessonType type = readEnum("Lesson type", LessonType.class);
        DayOfWeek day = readEnum("Day", DayOfWeek.class);
        System.out.print("Start time (HH:MM): ");
        LocalTime start = LocalTime.parse(scanner.nextLine().trim());
        System.out.print("End time (HH:MM): ");
        LocalTime end = LocalTime.parse(scanner.nextLine().trim());
        Lesson lesson = courseService.createLesson(manager, type, day, start, end);
        courseService.addLesson(manager, section, lesson);
        System.out.println("Added lesson to section " + section.getId() + ": " + lesson);
    }

    private void viewRegistrationRequests() {
        List<RegistrationRequest> requests = db.getFilteredRegistrationRequests(RequestStatus.PENDING);
        if (requests.isEmpty()) {
            System.out.println("No pending registration requests.");
            return;
        }
        for (RegistrationRequest request : requests) {
            printRequest(request);
        }
    }

    private void updateRegistrationRequest() {
        RegistrationRequest request = chooseRegistrationRequest();
        if (request == null) return;
        System.out.println("1. Approve");
        System.out.println("2. Reject");
        String choice = scanner.nextLine().trim();
        RequestStatus status = choice.equals("1") ? RequestStatus.APPROVED : RequestStatus.REJECTED;
        requestService.setStatus(manager, request, status);
        System.out.println("Request updated to " + status);
    }
    
    private void viewEmployeeRequests() {
        List<EmployeeRequest> requests = db.getFilteredEmployeeRequests(RequestStatus.PENDING);
        if (requests.isEmpty()) {
            System.out.println("No pending employee requests.");
            return;
        }
        for (EmployeeRequest request : requests) {
            printEmployeeRequest(request);
        }
    }
    
    private void updateEmployeeRequest() {
        EmployeeRequest request = chooseEmployeeRequest();
        if (request == null) return;
        System.out.println("1. Approve");
        System.out.println("2. Reject");
        String choice = scanner.nextLine().trim();
        RequestStatus status = choice.equals("1") ? RequestStatus.APPROVED : RequestStatus.REJECTED;
        requestService.setStatus(manager, request, status);
        if (status == RequestStatus.APPROVED) {
            request.setSign(manager);
        }
        System.out.println("Employee request updated to " + status);
    }

    private void assignStudent() {
        Student student = chooseStudent();
        Section section = chooseSection();
        if (student == null || section == null) return;
        enrollmentService.assign(student, section);
        System.out.println("Student assigned to " + section.getCourse().getCourseName());
    }

    private void generateReports() {
        System.out.println(reportService.generateAcademicReport(manager));
        System.out.println(reportService.generateCourseReport(manager));
        System.out.println(reportService.generateEnrollmentReport(manager));
    }

    private void viewCourses() {
        if (db.getCourses().isEmpty()) {
            System.out.println("No courses.");
            return;
        }
        for (Course course : db.getCourses()) {
            System.out.println(course.getId() + ". " + course.getCourseCode() + " " + course.getCourseName()
                    + " | credits: " + course.getCredits() + " | school: " + course.getSchool());
        }
    }

    private void viewSections() {
        if (db.getSections().isEmpty()) {
            System.out.println("No sections.");
            return;
        }
        for (Section section : db.getSections()) {
            String teacher = section.getTeacher() == null ? "no teacher" : section.getTeacher().getFullName();
            System.out.println(section.getId() + ". " + section.getCourse().getCourseName()
                    + " | " + section.getSemester() + " | " + teacher);
        }
    }

    private void viewStudents() {
        List<Student> students = db.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        for (Student student : students) {
            System.out.println(student.getId() + ". " + student.getFullName()
                    + " | year: " + student.getYear() + " | school: " + student.getSchool());
        }
    }

    private void viewTeachers() {
        List<Teacher> teachers = db.getTeachers();
        if (teachers.isEmpty()) {
            System.out.println("No teachers.");
            return;
        }
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getId() + ". " + teacher.getFullName()
                    + " | " + teacher.getTeacherType() + " | school: " + teacher.getSchool());
        }
    }
    
    private void viewEmployees() {
        List<Employee> employees = db.getEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees.");
            return;
        }
        for (Employee employee : employees) {
            System.out.println(employee.getId() + ". " + employee.getFullName()
                    + " | department: " + employee.getDepartment());
        }
    }

    private void printRequest(RegistrationRequest request) {
        System.out.println(request.getId() + ". " + request.getStudent().getFullName()
                + " -> " + request.getCourse().getCourseName()
                + " | status: " + request.getStatus());
    }
    
    private void printEmployeeRequest(EmployeeRequest request) {
        System.out.println(request.getId() + ". " + request.getEmployee().getFullName()
                + " | " + request.getContent()
                + " | status: " + request.getStatus()
                + " | signed: " + request.isSigned());
    }

    private RegistrationRequest chooseRegistrationRequest() {
        List<RegistrationRequest> requests = db.getFilteredRegistrationRequests(RequestStatus.PENDING);
        for (RegistrationRequest request : requests) {
            printRequest(request);
        }
        int id = readInt("Request id: ");
        for (RegistrationRequest request : requests) {
            if (request.getId() == id) {
                return request;
            }
        }
        System.out.println("Request not found.");
        return null;
    }
    
    private EmployeeRequest chooseEmployeeRequest() {
        List<EmployeeRequest> requests = db.getFilteredEmployeeRequests(RequestStatus.PENDING);
        for (EmployeeRequest request : requests) {
            printEmployeeRequest(request);
        }
        int id = readInt("Request id: ");
        for (EmployeeRequest request : requests) {
            if (request.getId() == id) {
                return request;
            }
        }
        System.out.println("Request not found.");
        return null;
    }

    private Course chooseCourse() {
        viewCourses();
        int id = readInt("Course id: ");
        Course course = db.getCourse(id);
        if (course == null) {
            System.out.println("Course not found.");
        }
        return course;
    }

    private Section chooseSection() {
        if (db.getSections().isEmpty()) {
            System.out.println("No sections.");
            return null;
        }
        for (Section section : db.getSections()) {
            String teacher = section.getTeacher() == null ? "no teacher" : section.getTeacher().getFullName();
            System.out.println(section.getId() + ". " + section.getCourse().getCourseName()
                    + " | " + section.getSemester() + " | " + teacher);
        }
        int id = readInt("Section id: ");
        Section section = db.getSection(id);
        if (section == null) {
            System.out.println("Section not found.");
        }
        return section;
    }

    private Teacher chooseTeacher() {
        List<Teacher> teachers = db.getTeachers();
        if (teachers.isEmpty()) {
            System.out.println("No teachers.");
            return null;
        }
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getId() + ". " + teacher.getFullName() + " | " + teacher.getTeacherType());
        }
        int id = readInt("Teacher id: ");
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        System.out.println("Teacher not found.");
        return null;
    }

    private Student chooseStudent() {
        List<Student> students = db.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students.");
            return null;
        }
        for (Student student : students) {
            System.out.println(student.getId() + ". " + student.getFullName());
        }
        int id = readInt("Student id: ");
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        System.out.println("Student not found.");
        return null;
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

    private interface MenuAction {
        void run();
    }
}
