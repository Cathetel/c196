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
import com.example.rpettyc196.Entity.Course;
import com.example.rpettyc196.Entity.Term;
import com.example.rpettyc196.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TermDetail extends AppCompatActivity {

    EditText editName;
    EditText editId;
    String name;
    int termId;
    Term term;
    Term currentTerm;
    int numTerms;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        editName = findViewById(R.id.termName);
        //name = editName.getText().toString();
        //termId = Integer.getInteger(editId.toString());
        name = getIntent().getStringExtra("termName");
        termId = getIntent().getIntExtra("termID", -1);
        editName.setText(name);

        //editId.setText(Integer.toString(termId));

        repository = new Repository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerview);
        repository = new Repository(getApplication());
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> filteredCourses = new ArrayList<>();
        for (Course c : repository.getAllCourses()) {
            if (c.getTermID() == termId) filteredCourses.add(c);
        }
        courseAdapter.setCourse(filteredCourses);

        Button button = findViewById(R.id.saveTerm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editName = findViewById(R.id.termName);
                //editId = findViewById(R.id.termID);
                name = editName.getText().toString();
                //String id = editId.getText().toString();
                //termId = Integer.parseInt(id);

                if (termId == -1) {
                    term = new Term(termId, name, "01/01/22", "12/31/22");
                    repository.insert(term);
                    Intent intent = new Intent(TermDetail.this, TermList.class);
                    startActivity(intent);

                } else {
                    term = new Term(termId, name, "01/01/22", "12/31/22");
                    repository.update(term);
                    Intent intent = new Intent(TermDetail.this, TermList.class);
                    startActivity(intent);
                }
            }
        });
        FloatingActionButton fab = findViewById(R.id.termActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermDetail.this, CourseList.class);
                intent.putExtra("termID", termId);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerview);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> filteredCourses = new ArrayList<>();
        for (Course c : repository.getAllCourses()) {
            if (c.getCourseID() == termId) filteredCourses.add(c);
        }
        courseAdapter.setCourse(filteredCourses);

        //Toast.makeText(TermDetail.this, "refresh list", Toast.LENGTH_LONG).show();
    }
}