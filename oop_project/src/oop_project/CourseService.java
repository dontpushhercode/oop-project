package oop_project;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class CourseService {

	private final Database db;

    public CourseService(Database db) {
        this.db = db;
    }
	
	Course createCourse(String code, String name, String description, School school, int credits) {
		Course course = new Course(code, name, description, school, credits);
		this.db.setOrCreateCourse(course);
		return course;
	}
	
	Course getCourse(Course course) {
		return this.db.getCourse(course.getId());
	}
	
	Section createSection(Course course, Semester semester) {
		Section section = new Section(course, semester);
		this.db.setOrCreateSection(section);
		return section;
	}
	
	Section getSection(Section sec) {
		return this.db.getSection(sec.getId());
	}
	
	Lesson createLesson(LessonType type, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
		Lesson lesson = new Lesson(type, day, startTime, endTime);
		return lesson;
	}
	
	List<Course> getCourses(Teacher teacher){
		return this.db.getFilteredCourses(teacher);
	}
	
	List<Section> getSections(Teacher teacher){
		return this.db.getFilteredSections(teacher);
	}
	
	void updateCourse(Course course, String name, String description) {
		Course c = this.db.getCourse(course.getId());
		c.setName(name);
		c.setDescription(description);
	}
	
	void updateCourse(Course course, String description) {
		Course c = this.db.getCourse(course.getId());
		c.setDescription(description);
	}
	
	void addInstructor(Course course, Teacher teacher) {
		Course c = this.db.getCourse(course.getId());
		for(Teacher t:c.getCoordinators()) {
			if(t.equals(teacher)) {
				throw new IllegalStateException("Teacher already assigned to this course!");
			}
		}
		c.addInstructor(teacher);
	}
	
	void addTeacher(Section sec, Teacher teacher) {
		Section s = this.db.getSection(sec.getId());
		if (s.getTeacher() != null) {
		    if (s.getTeacher().getId() == teacher.getId()) {
		        throw new IllegalStateException("Teacher already assigned to this section!");
		    }
		    throw new IllegalStateException("Other teacher already assigned to this section!");
		}
		s.setTeacher(teacher);
	}
	
	void dropInstructor(Course course, Teacher teacher) {
		Course c = this.db.getCourse(course.getId());
		for(Teacher t:c.getCoordinators()) {
			if(t.equals(teacher)) {
				c.dropInstructor(teacher);
				return;
			}
		}
		throw new IllegalStateException("Teacher hasn't been assigned to this course!");
	}

	void dropTeacher(Section sec) {
		Section s = this.db.getSection(sec.getId());
		if(s.getTeacher()==null) {
			throw new IllegalStateException("No teacher is assigned to this section!");
		}
		s.setTeacher(null);
	}
	
	void addLesson(Section sec, Lesson lesson) {
		Section s = this.db.getSection(sec.getId());
		for(Lesson l:s.getLessons()) {
			if(l.equals(lesson)) {
				throw new IllegalStateException("Lesson already assigned to this section!");
			}
		}
		s.addLesson(lesson);
	}
	
	void dropLesson(Section sec, Lesson lesson) {
		Section s = this.db.getSection(sec.getId());
		for(Lesson l:s.getLessons()) {
			if(l.equals(lesson)) {
				sec.dropLesson(lesson);
				return;
			}
		}
		throw new IllegalStateException("No such lesson in provided section!");
	}
}
