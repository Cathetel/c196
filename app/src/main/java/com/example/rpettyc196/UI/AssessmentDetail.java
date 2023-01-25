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
    Assessment assessment;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);


        assessmentId = getIntent().getIntExtra("assessmentId", -1);
        courseId = getIntent().getIntExtra("courseId", -1);
        assessmentName = getIntent().getStringExtra("assessmentName");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");

            editAssessmentId= findViewById(R.id.assessmentID);
            editAssessmentId.setText(String.valueOf(assessmentId));
            editCourseID=findViewById(R.id.courseID);
            editCourseID.setText(String.valueOf(courseId));
            editAssessmentName=findViewById(R.id.assessmentName);
            editAssessmentName.setText(assessmentName);
            editAssessmentStart=findViewById(R.id.assessmentStart);
            editAssessmentStart.setText(start);
            editAssessmentEnd=findViewById(R.id.assessmentEnd);
            editAssessmentEnd.setText(end);


            RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerview);
            final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
            recyclerView.setAdapter(assessmentAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            repository = new Repository(getApplication());
            List<Assessment> allAssessements = repository.getAllAssessments();
            assessmentAdapter.setAssessment(allAssessements);


            Button button = findViewById(R.id.saveAssessment);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editAssessmentName = findViewById(R.id.assessmentName);
                    assessmentName = editAssessmentName.getText().toString();

                    if (assessmentId == -1) {
                        assessment = new Assessment(Integer.parseInt(editAssessmentId.getText().toString()), Integer.parseInt(editCourseID.getText().toString()), editAssessmentName.getText().toString(), "01/01/22", "12/31/22");
                        repository.insert(assessment);
                        Intent intent = new Intent(AssessmentDetail.this, AssessmentList.class);
                        intent.putExtra("courseID", courseId);
                        startActivity(intent);
                        // Toast.makeText(CourseDetail.this,"ERROR",Toast.LENGTH_LONG).show();
                    } else {
                        assessment = new Assessment(Integer.parseInt(editAssessmentId.getText().toString()), Integer.parseInt(editCourseID.getText().toString()), editAssessmentName.getText().toString(), "01/01/22", "12/31/22");
                        repository.update(assessment);
                        Intent intent = new Intent(AssessmentDetail.this, AssessmentList.class);
                        //intent.putExtra("courseID", courseId);
                        startActivity(intent);
                    }
                }
            });
//            FloatingActionButton fab = findViewById(R.id.assessmentActionButton);
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(com.example.rpettyc196.UI.AssessmentDetail.this, CourseList.class);
//                    //intent.putExtra("courseID", courseId);
//                    startActivity(intent);
//                }
//            });

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
//}