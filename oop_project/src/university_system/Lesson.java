package university_system;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

/**
 * 
 */
public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static void setCounter(int value) {
        counter = value;
    }
    
    {
    	this.id=++counter;
    }

    /**
     * Default constructor
     */
    public Lesson() {
    }

    Lesson(LessonType type, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.type = type;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     *  Type of the lesson.
     */
    private LessonType type;

    /**
     * Day of the week when the lesson takes place.
     */
    private DayOfWeek day;

    /**
     * Lesson start time.
     */
    private LocalTime startTime;

    /**
     * Lesson end time.
     */
    private LocalTime endTime;

    private static int counter = 0;
    
    private int id;

    /**
     * Returns the lesson ID.
     *
     * @return the lesson ID
     */
    int getId() {
        return this.id;
    }
    
    LessonType getLessonType() {
    	return this.type;
    }
    
    /**
     * Updates the start and end time of the lesson.
     *
     * @param startTime the new start time
     * @param endTime the new end time
     */
    void setTime(LocalTime startTime, LocalTime endTime) {
    	this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Updates the day of the lesson.
     *
     * @param day the new day of the week
     */
    void setDay(DayOfWeek day) {
    	this.day = day;
    }

    /**
     * Updates the lesson type.
     *
     * @param type the new lesson type
     */
    void setType(LessonType type) {
    	this.type = type;
    }

    /**
     *  Returns the lesson time as a string.
     *
     * @return lesson time in the format startTime - endTime
     */
    String getTime() {
    	return this.startTime + " - " + this.endTime;
    }

    /**
     * Returns the lesson day.
     *
     * @return the day of the week
     */
    DayOfWeek getDay() {
    	return this.day;
    }

    /**
     * Returns the lesson type.
     *
     * @return the lesson type
     */
    LessonType getType() {
    	return this.type;
    }


    /**
     * Returns the lesson start time.
     *
     * @return the start time
     */  
    LocalTime getStartTime() {
        return this.startTime;
    }
    
    /**
     * Returns the lesson end time.
     *
     * @return the end time
     */
    LocalTime getEndTime() {
        return this.endTime;
    }
    
    /**
     * Returns a string representation of the lesson.
     *
     * @return lesson information as a string
     */
    @Override
    public String toString() {
        return this.type + ", " + this.day + ", " + 
               this.startTime + "-" + this.endTime + 
               ", id: " + this.id + "\n";
    }
    /**
     * Compares this lesson with another object.
     *
     * Two lessons are considered equal if they have the same ID.
     *
     * @param obj the object to compare with
     * @return true if both lessons have the same ID, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Lesson lesson = (Lesson) obj;

        return id == lesson.id;
    }            
    /**
    * Returns hash code based on the lesson ID.
    *
    * @return hash code value
    */
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }


}
