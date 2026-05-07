package oop_project_reference_code;

import java.util.List;

public class Transcript {
	private Student student;
	
	Transcript(Student student){
		this.student = student;
    }

    List<Enrollment> getEnrollments() {
        return OfficeRegister.getOfficeRegister().getEnrollmentService().getStudentEnrollments(student);
    }
    
    double getGpa() {
    	double total = 0;
        int count = 0;
        for (Enrollment e : getEnrollments()) {
            if (e.getStatus() == EnrollmentStatus.COMPLETED) {
                total += e.getMark().getTotalPoints();
                count++;
            }
        }
        return count == 0?0:total/count;
    }
}
