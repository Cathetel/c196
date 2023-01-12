package com.example.rpettyc196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rpettyc196.Database.Repository;
import com.example.rpettyc196.Entity.Assessment;
import com.example.rpettyc196.Entity.Course;
import com.example.rpettyc196.Entity.Term;
import com.example.rpettyc196.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AssessmentDetail extends AppCompatActivity {

    int assessmentId;
    int courseId;
    String assessmentName;
    String start;
    String end;
    EditText editAssessmentId;
    EditText editCourseID;
    EditText editAssessmentName;
    EditText editAssessmentStart;
    EditText editAssessmentEnd;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);


        assessmentId = getIntent().getIntExtra("assessmentID", -1);
        assessmentName = getIntent().getStringExtra("assessmentName");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");

            editAssessmentId= findViewById(R.id.assessmentID);
            editAssessmentId.setText(assessmentId);
//            editCourse=findViewById(R.id.courseID);
//            editCourse.setText(Integer.toString(courseId));
//            editName = findViewById(R.id.courseName);
//            editName.setText(name);
//            editStatus=findViewById(R.id.courseStatus);
//            editStatus.setText(status);
//            editCiName=findViewById(R.id.courseInstructor);
//            editCiName.setText(ciName);
//            editCiPhone=findViewById(R.id.coursePhone);
//            editCiPhone.setText(ciPhone);
//            editEmail=findViewById(R.id.courseEmail);
//            editEmail.setText(email);
//            editNote=findViewById(R.id.courseNote);
//            editNote.setText(note);

//            RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerview);
//            final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
//            recyclerView.setAdapter(assessmentAdapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            repository = new Repository(getApplication());
//            List<Assessment> allAssessements = repository.getAllAssessments();
//            assessmentAdapter.setAssessment(allAssessements);


            Button button = findViewById(R.id.saveAssessment);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    editName = findViewById(R.id.termName);
//                    name = editName.getText().toString();
//
//                    if (termId == -1) {
//                        // TODO set toast: "NEED TERM TO ASSOCIATE"
//                    } else {
//                        term = new Term(termId, editName.getText().toString(), "01/01/22", "12/31/22");
//                        repository.update(term);
//                        Intent intent = new Intent(com.example.rpettyc196.UI.AssessmentDetail.this, CourseList.class);
//                        intent.putExtra("termID", termId);
//                        startActivity(intent);
//                    }
                }
            });
            FloatingActionButton fab = findViewById(R.id.assessmentActionButton);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(com.example.rpettyc196.UI.AssessmentDetail.this, CourseList.class);
                    intent.putExtra("courseID", courseId);
                    startActivity(intent);
                }
            });

        }

        @Override
        protected void onResume() {

            super.onResume();
            RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerview);
            final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
            recyclerView.setAdapter(assessmentAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            List<Assessment> filteredAssessment = new ArrayList<>();
            for (Assessment a : repository.getAllAssessments()) {
                if (a.getAssessmentID() == courseId) filteredAssessment.add(a);
            }
            assessmentAdapter.setAssessment(filteredAssessment);
        }
    }
}