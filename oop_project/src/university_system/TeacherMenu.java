package university_system;

import java.util.List;
import java.util.Scanner;

/**
 * Console menu for teachers.
 */
public class TeacherMenu {
    private final Teacher teacher;
    private final Scanner scanner;
    private final AcademicService academicService = OfficeRegister.getAcademicService();
    private final CourseService courseService = OfficeRegister.getCourseService();
    private final EnrollmentService enrollmentService = OfficeRegister.getEnrollmentService();

    public TeacherMenu(Teacher teacher, Scanner scanner) {
        this.teacher = teacher;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Teacher Menu: " + teacher.getFullName() + " ---");
            System.out.println("1. View my sections");
            System.out.println("2. View students in section");
            System.out.println("3. Put mark");
            System.out.println("4. Complete enrollment");
            System.out.println("5. View rating");
            System.out.println("0. Logout");
            System.out.print("Choose option: ");

            try {
                switch (scanner.nextLine().trim()) {
                    case "1" -> viewMySections();
                    case "2" -> viewStudents();
                    case "3" -> putMark();
                    case "4" -> completeEnrollment();
                    case "5" -> System.out.println("Rating: " + teacher.getRating());
                    case "0" -> {
                        teacher.logout();
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private void viewMySections() {
    	List<Section> sections = courseService.getSections(teacher);
        if (sections.isEmpty()) {
            System.out.println("No assigned sections.");
            return;
        }
        for (Section section : sections) {
            System.out.println(section.getId() + " | " + section);
        }
    }

    private void viewStudents() {
        Section section = chooseMySection();
        if (section == null) return;
        List<Enrollment> enrollments = enrollmentService.getTeacherCourseEnrollments(teacher, section.getCourse(), EnrollmentStatus.ACTIVE);
        if (enrollments.isEmpty()) {
            System.out.println("No students in this section.");
            return;
        }
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.getStudent().getId() + " | " + enrollment.getStudent().getFullName()+ 
                    " | total: " + enrollment.getMark().getTotalPoints());
        }
    }

    private void putMark() {
    	List<Section> sections = courseService.getSections(teacher);
        if (sections.isEmpty()) {
            System.out.println("No assigned sections.");
            return;
        }
    	
        Section section = chooseMySection();
        Student student = chooseStudent(section);
        if (section == null || student == null) return;

        Mark mark = new Mark();
        mark.setFirstAttestation(readDouble("First attestation: "));
        mark.setSecondAttestation(readDouble("Second attestation: "));
        mark.setFinalExam(readDouble("Final exam: "));
        academicService.putMark(teacher, student, section, mark);
        System.out.println("Mark saved. Total: " + mark.getTotalPoints() + " (" + mark.getLiteralGrade() + ")");
    }

    private void completeEnrollment() {
        Section section = chooseMySection();
        Student student = chooseStudent(section);
        if (section == null || student == null) return;
        for (Enrollment enrollment : enrollmentService.getStudentEnrollments(student)) {
            if (enrollment.getSection().equals(section)) {
                enrollment.completeCourse();
                System.out.println("Enrollment completed.");
                return;
            }
        }
        System.out.println("Enrollment not found.");
    }

    private Section chooseMySection() {
        viewMySections();
        int id = readInt("Section id: ");
        Section section = courseService.getSection(id);
        if (section == null || !teacher.equals(section.getTeacher())) {
            System.out.println("Section not found for this teacher.");
            return null;
        }
        return section;
    }

    private Student chooseStudent(Section section) {
        if (section == null) return null;
        List<Enrollment> enrollments = enrollmentService.getSectionEnrollments(section);
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.getStudent().getId() + "| " + enrollment.getStudent().getFullName());
        }
        int id = readInt("Student id: ");
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getId() == id) {
                return enrollment.getStudent();
            }
        }
        System.out.println("Student not found in this section.");
        return null;
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private double readDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine().trim());
    }
}
