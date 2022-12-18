package com.example.rpettyc196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rpettyc196.Database.Repository;
import com.example.rpettyc196.Entity.Term;
import com.example.rpettyc196.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar_layout);
        //setSupportActionBar(toolbar);
        //createNotificationChannel();

    }


    public void termList(View view) {
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
        Repository repo = new Repository(getApplication());
        Term term = new Term(1, "TEST1", null, null);
        repo.insert(term);
        Term term1=new Term(2,"term2", null, null);
        repo.insert(term1);
        Term term2=new Term(3,"term3", null, null);
        repo.insert(term2);
        Term term3=new Term(4,"test4", null, null);
        repo.insert(term3);
    }
}