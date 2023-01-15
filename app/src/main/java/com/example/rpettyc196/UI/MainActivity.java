package com.example.rpettyc196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rpettyc196.Database.Repository;
import com.example.rpettyc196.Entity.Assessment;
import com.example.rpettyc196.Entity.Course;
import com.example.rpettyc196.Entity.Term;
import com.example.rpettyc196.R;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button termButton = findViewById(R.id.termListButton);
        Button courseButton = findViewById(R.id.courseListButton);
        Button assessmentButton = findViewById(R.id.assessmentListButton);

        termButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, TermList.class);
                junkData();
                startActivity(intent);
            }
        });
        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, CourseList.class);
                junkData();
                startActivity(intent);
            }
        });
        assessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AssessmentList.class);
                junkData();
                startActivity(intent);
            }
        });
    }


    public void junkData() {
        Repository repo = new Repository(getApplication());
        Term term = new Term(2, "Roys Term 2", null, null);
        repo.insert(term);
        Assessment assessment1=new Assessment(1,3,"Roys Test", "1/1/22", "12/1/22");
        repo.insert(assessment1);
        Course class1=new Course(2,3,"course1", null, null,"In Progress","Carolyn Sher-DeCusatis","555-555-5555","carolyn@wgu.edu","test note");
        repo.insert(class1);

    }
}