package university_system;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

/**
 * 
 */
public class Lesson {
	private static int counter = 0;
	
	private int id;
	
    {
    	this.id = ++counter;
    }

    /**
     * Default constructor
     */
    Lesson(LessonType type, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
    	this.type = type;
    	this.day = day;
    	this.startTime = startTime;
    	this.endTime = endTime;
    }

    /**
     * 
     */
    private Lesson.LessonType type;

    /**
     * 
     */
    private DayOfWeek day;

    /**
     * 
     */
    private LocalTime startTime;

    /**
     * 
     */
    private LocalTime endTime;



    /**
     * 
     */
    int getId() {
        return this.id;
    }
    
    
    void setTime(ocalTime startTime, LocalTime endTime) {
    	this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 
     */
    void setDay(DayOfWeek day) {
    	this.day = day;
    }

    /**
     * 
     */
    void setType(LessonType type) {
    	this.type = type;
    }

    /**
     * 
     */
    String getTime() {
    	return this.startTime + " - " + this.endTime;
    }

    /**
     * 
     */
    DayOfWeek getDay() {
    	return this.day;
    }

    /**
     * 
     */
    LessonType getType() {
    	return this.type;
    }

    /**
     * 
     */
    
    LocalTime getStartTime() {
        return this.startTime;
    }

    LocalTime getEndTime() {
        return this.endTime;
    }
    
    @Override
    public String toString() {
        return this.type + ", " + this.day + ", " + 
               this.startTime + "-" + this.endTime + 
               ", id: " + this.id + "\n";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Lesson lesson = (Lesson) obj;

        return id == lesson.id &&
                type == lesson.type &&
                day == lesson.day &&
                Objects.equals(startTime, lesson.startTime) &&
                Objects.equals(endTime, lesson.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, day, startTime, endTime);
    }

}