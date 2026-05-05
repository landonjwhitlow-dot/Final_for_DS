/**
 * Assignment.java
 * Represents a single assignment in the study planner.
 * Fields:
 * - title: name of the assignment
 * - course: class it belongs to
 * - dueDate: due date in MM/DD/YYYY format
 * - isCompleted: tracks completion status
 * Author: Landon Whitlow
 * Course: Data Structures
 */


public class Assignment {


    private String title;
    private String course;
    private String dueDate;   // Stored as "MM/DD/YYYY" for simplicity
    private boolean isCompleted;

    public Assignment(String title, String course, String dueDate) {
        this.title = title;
        this.course = course;
        this.dueDate = dueDate;
        this.isCompleted = false;  // New assignments start as not completed
    }


    public String getTitle() {
        return title;
    }
    public String getCourse() {
        return course;
    }
    public String getDueDate() {
        return dueDate;
    }
    public boolean getIsCompleted() {
        return isCompleted;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String toString() {
        String status;
        if (isCompleted) {
            status = "[DONE]";
        } else {
            status = "[TODO]";
        }
        return status + " " + title + " (" + course + ") - Due: " + dueDate;
    }
}
