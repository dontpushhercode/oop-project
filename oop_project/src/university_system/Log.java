package university_system; 
import java.io.*; 
import java.util.*;
import java.time.LocalDate; 

/**
 * Represents a system log entry.
 *
 * Each log records an action performed by an actor
 * at a specific time.
 */
public class Log {

    /**
     * Name or identifier of the actor who performed the action.
     */
    private final String actor;

    /**
     * Description of the action performed.
     */
    private final String action;

    /**
     * Time when the action occurred.
     */
    private final LocalDate time;

    /**
     * Creates a new log entry with current date.
     *
     * @param actor person/system that performed the action
     * @param action description of the action
     */
    public Log(String actor, String action) {
        this.actor = actor;
        this.action = action;
        this.time = LocalDate.now();
    }

    /**
     * Returns actor name.
     *
     * @return actor
     */
    public String getActor() {
        return actor;
    }

    /**
     * Returns action description.
     *
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * Returns timestamp of the log.
     *
     * @return date of event
     */
    public LocalDate getTime() {
        return time;
    }

    /**
     * Returns string representation of log entry.
     *
     * @return formatted log string
     */
    @Override
    public String toString() {
        return "[" + time + "] " + actor + " -> " + action;
    }
}