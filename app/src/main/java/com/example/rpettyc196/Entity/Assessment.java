package com.example.rpettyc196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private int assessmentID;
    private String assessmentName;
    private String start;
    private String end;

    public Assessment(int courseID, int assessmentID, String assessmentName, String start, String end) {
        this.courseID = courseID;
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.start = start;
        this.end = end;
    }

    public int getCourseID(){return courseID;}

    public void setCourseID(int courseID){this.courseID = courseID;}

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
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

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentID=" + assessmentID +
                ", assessmentName='" + assessmentName + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
