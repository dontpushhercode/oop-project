package oop_project_reference_code;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class CourseService {

	private final Database db;

    public CourseService(Database db) {
        this.db = db;
    }
    
    private void checkPermission(Manager manager) {
		if (manager == null || manager.getDepartment() != DepartmentType.MANAGEMENT) {
	        throw new IllegalStateException("No permission");
	    }
	}
	
	Course createCourse(Manager manager, String code, String name, String description, School school, int credits) {
		
		checkPermission(manager);
		
		Course course = new Course(code, name, description, school, credits);
		this.db.setOrCreateCourse(course);
		return course;
	}
	
	Course getCourse(Course course) {
		return this.db.getCourse(course.getId());
	}
	
	Section createSection(Manager manager, Course course, Semester semester) {
		
		checkPermission(manager);
		
		Section section = new Section(course, semester);
		this.db.setOrCreateSection(section);
		return section;
	}
	
	Section getSection(Section sec) {
		return this.db.getSection(sec.getId());
	}
	
	Lesson createLesson(Manager manager, LessonType type, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
		
		checkPermission(manager);
		
		Lesson lesson = new Lesson(type, day, startTime, endTime);
		return lesson;
	}
	
	List<Course> getCourses(Teacher teacher){
		return this.db.getFilteredCourses(teacher);
	}
	
	List<Section> getSections(Teacher teacher){
		return this.db.getFilteredSections(teacher);
	}
	
	void updateCourse(Manager manager, Course course, String name, String description) {
		
		checkPermission(manager);
		
		Course c = this.db.getCourse(course.getId());
		c.setName(name);
		c.setDescription(description);
	}
	
	void updateCourse(Manager manager, Course course, String description) {
		
		checkPermission(manager);
		
		Course c = this.db.getCourse(course.getId());
		c.setDescription(description);
	}
	
	void addInstructor(Manager manager, Course course, Teacher teacher) {
		
		checkPermission(manager);
		
		Course c = this.db.getCourse(course.getId());
		for(Teacher t:c.getCoordinators()) {
			if(t.equals(teacher)) {
				throw new IllegalStateException("Teacher already assigned to this course!");
			}
		}
		c.addInstructor(teacher);
	}
	
	void addTeacher(Manager manager, Section sec, Teacher teacher) {
		
		checkPermission(manager);
		
		Section s = this.db.getSection(sec.getId());
		if (s.getTeacher() != null) {
		    if (s.getTeacher().equals(teacher)) {
		        throw new IllegalStateException("Teacher already assigned to this section!");
		    }
		    throw new IllegalStateException("Other teacher already assigned to this section!");
		}
		s.setTeacher(teacher);
	}
	
	void dropInstructor(Manager manager, Course course, Teacher teacher) {
		
		checkPermission(manager);
		
		Course c = this.db.getCourse(course.getId());
		for(Teacher t:c.getCoordinators()) {
			if(t.equals(teacher)) {
				c.dropInstructor(teacher);
				return;
			}
		}
		throw new IllegalStateException("Teacher hasn't been assigned to this course!");
	}

	void dropTeacher(Manager manager, Section sec) {
		
		checkPermission(manager);
		
		Section s = this.db.getSection(sec.getId());
		if(s.getTeacher()==null) {
			throw new IllegalStateException("No teacher is assigned to this section!");
		}
		s.setTeacher(null);
	}
	
	void addLesson(Manager manager, Section sec, Lesson lesson) {
		
		checkPermission(manager);
		
		Section s = this.db.getSection(sec.getId());
		for(Lesson l:s.getLessons()) {
			if(l.equals(lesson)) {
				throw new IllegalStateException("Lesson already assigned to this section!");
			}
		}
		s.addLesson(lesson);
	}
	
	void dropLesson(Manager manager, Section sec, Lesson lesson) {
		
		checkPermission(manager);
		
		Section s = this.db.getSection(sec.getId());
		for(Lesson l:s.getLessons()) {
			if(l.equals(lesson)) {
				s.dropLesson(lesson);
				return;
			}
		}
		throw new IllegalStateException("No such lesson in provided section!");
	}
}
