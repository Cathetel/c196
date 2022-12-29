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

    EditText editName;
    int courseId;
    String name;
    int termId;
    Term term;
    Term currentTerm;
    int numTerms;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        editName = findViewById(R.id.courseName);
        name = getIntent().getStringExtra("courseName");
        termId = getIntent().getIntExtra("termID", -1);
        courseId = getIntent().getIntExtra("courseID", -1);
        editName.setText(name);

        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerview);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<Assessment> allAssessements = repository.getAllAssessments();
        assessmentAdapter.setAssessment(allAssessements);


//        repository = new Repository(getApplication());
//        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView1);
//        repository = new Repository(getApplication());
//        final CourseAdapter courseAdapter = new CourseAdapter(this);
//        recyclerView.setAdapter(courseAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        List<Course> filteredCourses = new ArrayList<>();
//        for (Course c : repository.getAllCourses()) {
//            if (c.getTermID() == termId) filteredCourses.add(c);
//        }
//
//        courseAdapter.setCourse(filteredCourses);

        Button button = findViewById(R.id.saveCourse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editName = findViewById(R.id.termName);
                name = editName.getText().toString();

                if (termId == -1) {
                  // TODO set toast: "NEED TERM TO ASSOCIATE"
                }
                else {
                    term = new Term(termId, editName.getText().toString(), "01/01/22", "12/31/22");
                    repository.update(term);
                    Intent intent = new Intent(CourseDetail.this, CourseList.class);
                    intent.putExtra("termID", termId);
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
        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerview);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> filteredCourses = new ArrayList<>();
        for (Course c : repository.getAllCourses()) {
            if (c.getCourseID() == termId) filteredCourses.add(c);
        }
        courseAdapter.setCourse(filteredCourses);
    }
}