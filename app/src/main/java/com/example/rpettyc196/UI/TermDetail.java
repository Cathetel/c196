package com.example.rpettyc196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.rpettyc196.Database.Repository;
import com.example.rpettyc196.Entity.Term;
import com.example.rpettyc196.R;

public class TermDetail extends AppCompatActivity {

    EditText editName;
    EditText editId;
    String name;
    int termId;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
        editName = findViewById(R.id.editTermName);
        editId = findViewById(R.id.editTermId);
        name = getIntent().getStringExtra("termName");
        termId = getIntent().getIntExtra("termID", 1);
        editName.setText(name);
        editId.setText(Integer.toString(termId));
        repo= new Repository(getApplication());
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_termdetail, menu);
        return true;
    }
    public boolean onOptionsTermSelected (MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"text from the note field");
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Message Title");
                sendIntent.setType("text/plain");
                Intent shareIntent=Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notify:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void saveButton(View view){
        Term term;
        if (termId == -1){
            int newID = repo.getAllTerms().get(repo.getAllTerms().size() -1).getTermID() +1;
            term = new Term(newID, editName.getText().toString());//TODO Correct variables for start/end dates
            repo.insert(term);
        } else{
            term = new Term(termId, editName.getText().toString());
            repo.update(term);
        }
    }
}