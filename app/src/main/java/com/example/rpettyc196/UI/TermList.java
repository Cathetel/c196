package com.example.rpettyc196.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rpettyc196.Database.Repository;
import com.example.rpettyc196.Entity.Term;
import com.example.rpettyc196.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TermList extends AppCompatActivity {
    private Repository repository;
    int termId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        termId = getIntent().getIntExtra("termID", -1);
        setContentView(R.layout.activity_term_list);
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<Term> allTerms = repository.getAllTerms();
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        termAdapter.setTerms(allTerms);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermList.this, TermDetail.class);
                intent.putExtra("termID", termId);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        List<Term> allTerms = repository.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);

        Toast.makeText(TermList.this,"refresh list",Toast.LENGTH_LONG).show();
    }
}