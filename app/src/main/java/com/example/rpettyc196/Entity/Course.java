package com.example.rpettyc196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)

    private int courseID;
    private String courseName;
    private String start;
    private String end;
    private String status;
    private String ciName;
    private String ciPhone;
    private String email;
    private String note;

    public Course(int courseID, String courseName, String start, String end, String status, String ciName, String ciPhone, String email, String note) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.start = start;
        this.end = end;
        this.status = status;
        this.ciName = ciName;
        this.ciPhone = ciPhone;
        this.email = email;
        this.note = note;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCiName() {
        return ciName;
    }

    public void setCiName(String ciName) {
        this.ciName = ciName;
    }

    public String getCiPhone() {
        return ciPhone;
    }

    public void setCiPhone(String ciPhone) {
        this.ciPhone = ciPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", status='" + status + '\'' +
                ", ciName='" + ciName + '\'' +
                ", ciPhone='" + ciPhone + '\'' +
                ", email='" + email + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
