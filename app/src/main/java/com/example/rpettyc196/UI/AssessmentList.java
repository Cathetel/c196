package com.example.rpettyc196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rpettyc196.Database.Repository;
import com.example.rpettyc196.Entity.Assessment;
import com.example.rpettyc196.Entity.Term;
import com.example.rpettyc196.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AssessmentList extends AppCompatActivity {
        private Repository repository;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_assessment_list);
            RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
            final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
            recyclerView.setAdapter(assessmentAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            repository = new Repository(getApplication());
            List<Assessment> allAssessments = repository.getAllAssessments();
            FloatingActionButton fab = findViewById(R.id.assessmentListFab);
            assessmentAdapter.setAssessment(allAssessments);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AssessmentList.this, AssessmentDetail.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        protected void onResume() {

            super.onResume();
            List<Assessment> allAssessments = repository.getAllAssessments();
            RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
            final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
            recyclerView.setAdapter(assessmentAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            assessmentAdapter.setAssessment(allAssessments);

            Toast.makeText(AssessmentList.this,"refresh list",Toast.LENGTH_LONG).show();
        }
    }