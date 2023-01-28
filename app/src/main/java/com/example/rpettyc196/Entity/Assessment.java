package com.example.rpettyc196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private int courseID;
    private String assessmentName;
    private String start;
    private String end;
    private String examType;

    public Assessment(int assessmentID, int courseID, String assessmentName, String start, String end, String examType) {
        this.assessmentID = assessmentID;
        this.courseID = courseID;
        this.assessmentName = assessmentName;
        this.start = start;
        this.end = end;
        this.examType = examType;
    }


    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

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

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentID=" + assessmentID +
                "courseID=" + courseID +
                ", assessmentName='" + assessmentName + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", examType=" + examType +
                '}';
    }
}
