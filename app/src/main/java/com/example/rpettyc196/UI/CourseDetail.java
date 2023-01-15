package com.example.rpettyc196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rpettyc196.Database.Repository;
import com.example.rpettyc196.Entity.Assessment;
import com.example.rpettyc196.Entity.Course;
import com.example.rpettyc196.Entity.Term;
import com.example.rpettyc196.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CourseDetail extends AppCompatActivity {


    int termId;
    int courseId;
    String name;
    String status;
    String ciName;
    String ciPhone;
    String email;
    String note;
    EditText editTerm;
    EditText editCourse;
    EditText editName;
    EditText editStatus;
    EditText editCiName;
    EditText editCiPhone;
    EditText editEmail;
    EditText editNote;
    Term term;
    Course course;
    Term currentTerm;
    int numTerms;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        termId = getIntent().getIntExtra("termID", -1);
        courseId = getIntent().getIntExtra("courseID", -1);
        name = getIntent().getStringExtra("courseName");
        status = getIntent().getStringExtra("status");
        ciName = getIntent().getStringExtra("ciName");
        ciPhone = getIntent().getStringExtra("ciPhone");
        email = getIntent().getStringExtra("email");
        note = getIntent().getStringExtra("note");

        editTerm = findViewById(R.id.termID);
        editTerm.setText(Integer.toString(termId));
        editCourse = findViewById(R.id.courseID);
        editCourse.setText(Integer.toString(courseId));
        editName = findViewById(R.id.courseName);
        editName.setText(name);
        editStatus = findViewById(R.id.courseStatus);
        editStatus.setText(status);
        editCiName = findViewById(R.id.courseInstructor);
        editCiName.setText(ciName);
        editCiPhone = findViewById(R.id.coursePhone);
        editCiPhone.setText(ciPhone);
        editEmail = findViewById(R.id.courseEmail);
        editEmail.setText(email);
        editNote = findViewById(R.id.courseNote);
        editNote.setText(note);

        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView1);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<Assessment> allAssessements = repository.getAllAssessments();
        for (Assessment a : repository.getAllAssessments()) {
            if (a.getCourseID() == courseId) allAssessements.add(a);
        }
        assessmentAdapter.setAssessment(allAssessements);


        Button button = findViewById(R.id.saveCourse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editName = findViewById(R.id.courseName);
                name = editName.getText().toString();

                if (courseId == -1) {
                    course = new Course(termId, Integer.getInteger(editCourse.getText().toString()), editName.getText().toString(), "01/01/22", "12/31/22", editStatus.getText().toString(), editCiName.getText().toString(), editCiPhone.getText().toString(), editEmail.getText().toString(), editNote.getText().toString());
                    repository.insert(course);
                    Intent intent = new Intent(CourseDetail.this, CourseList.class);
                    intent.putExtra("courseID", courseId);
                    startActivity(intent);
                    // Toast.makeText(CourseDetail.this,"ERROR",Toast.LENGTH_LONG).show();
                } else {
                    course = new Course(termId, Integer.getInteger(editCourse.getText().toString()), editName.getText().toString(), "01/01/22", "12/31/22", editStatus.getText().toString(), editCiName.getText().toString(), editCiPhone.getText().toString(), editEmail.getText().toString(), editNote.getText().toString());
                    repository.update(course);
                    Intent intent = new Intent(CourseDetail.this, CourseList.class);
                    //intent.putExtra("courseID", courseId);
                    startActivity(intent);
                }
            }
        });
        FloatingActionButton fab = findViewById(R.id.termActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetail.this, CourseList.class);
                intent.putExtra("termID", termId);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView1);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<Assessment> allAssessements = repository.getAllAssessments();
        for (Assessment a : repository.getAllAssessments()) {
            if (a.getCourseID() == courseId) allAssessements.add(a);
        }
        assessmentAdapter.setAssessment(allAssessements);
    }
}