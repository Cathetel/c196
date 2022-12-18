package com.example.rpettyc196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "terms")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int termID;
    private String termName;
    private String start;
    private String end;

    public Term(int termID, String termName, String start, String end) {
        this.termID = termID;
        this.termName = termName;
        this.start = start;
        this.end = end;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
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
        return "Term{" +
                "termID=" + termID +
                ", termName='" + termName + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
