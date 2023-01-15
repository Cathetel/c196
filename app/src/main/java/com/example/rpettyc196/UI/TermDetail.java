package com.example.rpettyc196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    int courseId;
    String name;
    int termId;
    Term term;
    Term currentTerm;
    int numCourses;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        editName = findViewById(R.id.termName);
        name = getIntent().getStringExtra("termName");
        termId = getIntent().getIntExtra("termID", -1);
        editName.setText(name);
        courseId = getIntent().getIntExtra("courseID", -1);

        RecyclerView recyclerView = findViewById(R.id.courseRecyclerview);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<Course> allCourses = repository.getAllCourses();
        courseAdapter.setCourse(allCourses);


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

        Button button = findViewById(R.id.saveTerm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editName = findViewById(R.id.termName);
                name = editName.getText().toString();

                if (termId == -1) {
                    term = new Term(termId, editName.getText().toString(), "01/01/22", "12/31/22");
                    repository.insert(term);
                    Intent intent = new Intent(TermDetail.this, TermList.class);
                    intent.putExtra("termID", termId);
                    startActivity(intent);
                } else {
                    term = new Term(termId, editName.getText().toString(), "01/01/22", "12/31/22");
                    repository.update(term);
                    Intent intent = new Intent(TermDetail.this, TermList.class);
                    intent.putExtra("termID", termId);
                    startActivity(intent);
                }
            }
        });
        FloatingActionButton fab = findViewById(R.id.termActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermDetail.this, CourseDetail.class);
                intent.putExtra("termID", termId);
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deleteterm, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.deleteterm:
                for (Term term : repository.getAllTerms()) {
                    if (term.getTermID() == termId) currentTerm = term;
                }

                numCourses = 0;
                for (Course course : repository.getAllCourses()) {
                    if (course.getTermID() == termId) ++numCourses;
                }

                if (numCourses == 0) {
                    repository.delete(currentTerm);
                    Toast.makeText(TermDetail.this, currentTerm.getTermName() + " was deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(TermDetail.this, "Can't delete a term with courses. Please delete courses first.", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}