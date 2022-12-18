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
        editId = findViewById(R.id.termID);
        name = getIntent().getStringExtra("termName");
        termId = getIntent().getIntExtra("termID", -1);
        editName.setText(name);
        editId.setText(Integer.toString(termId));
        repository = new Repository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.termRecyclerview);
        repository = new Repository(getApplication());
        final TermAdapter partAdapter = new TermAdapter(this);
        recyclerView.setAdapter(partAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Term> filteredTerms = new ArrayList<>();
        for (Term t : repository.getAllTerms()) {
            if (t.getTermID() == termId) filteredTerms.add(t);
        }
        partAdapter.setTerms(filteredTerms);

        Button button = findViewById(R.id.saveTerm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (termId == -1) {
                    term = new Term(0, editName.toString(), "01/01/22", "12/31/22");
                    repository.insert(term);

                } else {
                    term = new Term(0, editName.toString(), "01/01/22", "12/31/22");
                    repository.insert(term);

                }
            }
        });
        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermDetail.this, CourseList.class);
                intent.putExtra("prodID", termId);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.termRecyclerview);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> filteredCourses = new ArrayList<>();
        for (Course c : repository.getAllCourses()) {
            if (c.getCourseID() == termId) filteredCourses.add(c);
        }
        courseAdapter.setCourse(filteredCourses);

        Toast.makeText(TermDetail.this, "refresh list", Toast.LENGTH_LONG).show();
    }
}