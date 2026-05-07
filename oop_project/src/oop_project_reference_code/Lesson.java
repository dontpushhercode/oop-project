package oop_project_reference_code;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Lesson {
	private static int counter = 0;
	
	private int id;
	private LessonType type;
	private DayOfWeek day;
	private LocalTime startTime;
    private LocalTime endTime;
    
    {
    	this.id = ++counter;
    }
    
    Lesson(LessonType type, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
    	this.type = type;
    	this.day = day;
    	this.startTime = startTime;
    	this.endTime = endTime;
    }
}
