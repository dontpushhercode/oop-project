package university_system;

import java.util.List;
import java.util.Scanner;

/**
 * Console menu for students.
 */
public class StudentMenu {
    private final Student student;
    private final Scanner scanner;
    private final RequestService requestService = OfficeRegister.getRequestService();
    private final AcademicService academicService = OfficeRegister.getAcademicService();
    private final UserService userService = OfficeRegister.getUserService();
    private final CourseService courseService = OfficeRegister.getCourseService();
    private final EnrollmentService enrollmentService = OfficeRegister.getEnrollmentService();

    public StudentMenu(Student student, Scanner scanner) {
        this.student = student;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Student Menu: " + student.getFullName() + " ---");
            System.out.println("1. Request course registration");
            System.out.println("2. View my enrollments");
            System.out.println("3. View transcript");
            System.out.println("4. Rate teacher");
            System.out.println("5. View teachers");
            System.out.println("6. View courses");
            System.out.println("7. View sections");
            System.out.println("8. Choose section");
            System.out.println("0. Logout");
            System.out.print("Choose option: ");

            try {
                switch (scanner.nextLine().trim()) {
                    case "1" -> requestCourse();
                    case "2" -> viewEnrollments();
                    case "3" -> viewTranscript();
                    case "4" -> rateTeacher();
                    case "5" -> viewTeachers();
                    case "6" -> viewCourses();
                    case "7" -> viewSections();
                    case "8" -> selectSection();
                    case "0" -> {
                        student.logout();
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }
    
    private void viewCourses() {
    	List<Course> courses = courseService.getCourses();
    	if(courses.isEmpty()) {
    		System.out.println("No courses.");
            return;
    	}
    	for (Course course : courses) {
    		System.out.println(course);
    	}
    }
    
    private void viewSections() {
        List<Section> sections = courseService.getSections();
        if (sections.isEmpty()) {
            System.out.println("No sections.");
            return;
        }

        for (Section section : sections) {
            System.out.println(section);
        }
    }
    
    private Section chooseSection() {
        viewSections();

        int id = readInt("Section id: ");
        for (Section section : courseService.getSections()) {
            if (section.getId() == id) {
                return section;
            }
        }

        System.out.println("Section not found.");
        return null;
    }
    
    private void selectSection() {
        Section section = chooseSection();
        if (section == null) return;

        System.out.println("Selected section: " + section);
    }

    private void requestCourse() {
        Course course = chooseCourse();
        if (course == null) return;
        RegistrationRequest request = requestService.createRegistrationRequest(student, course);
        System.out.println("Created request " + request.getId()+" | " + course.getCourseCode());

    }

    private void viewEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(student);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments.");
            return;
        }
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private void viewTranscript() {
        Transcript transcript = academicService.getTranscript(student);
        System.out.println("GPA: " + transcript.getGpa());
        viewEnrollments();
    }

    private void rateTeacher() {
        Teacher teacher = chooseTeacher();
        Course course = chooseCourse();
        if (teacher == null || course == null) return;
        System.out.print("Score: ");
        double score = Double.parseDouble(scanner.nextLine().trim());
        academicService.rateTeacher(student, teacher, course, score);
        System.out.println("Teacher rated.");
    }

    private void viewTeachers() {
        List<Teacher> teachers = userService.getTeachers();
        if (teachers.isEmpty()) {
            System.out.println("No teachers.");
            return;
        }
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }

    private Course chooseCourse() {
    	List<Course> courses = courseService.getCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses.");
            return null;
        }
        for (Course course : courses) {
            System.out.println(course.toString());
        }
        int id = readInt("Course id: ");
        Course course = courseService.getCourse(id);
        if (course == null) {
            System.out.println("Course not found.");
        }
        return course;
    }

    private Teacher chooseTeacher() {
        viewTeachers();
        int id = readInt("Teacher id: ");
        for (Teacher teacher : userService.getTeachers()) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        System.out.println("Teacher not found.");
        return null;
    }
    

    private int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine().trim());
    }
}
