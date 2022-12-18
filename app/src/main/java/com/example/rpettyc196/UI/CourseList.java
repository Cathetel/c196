package com.example.rpettyc196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.rpettyc196.R;

public class CourseList extends AppCompatActivity {

    EditText editName;
    EditText editId;
    String name;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        editName=findViewById(R.id.editTermName);
        editId=findViewById(R.id.editTermId);
        name = getIntent().getStringExtra("name");
        id = getIntent().getIntExtra("id", 1);
        editName.setText(name);
        editId.setText(Integer.toString(id));
    }

    public void detailScreen(View view){
        Intent intent = new Intent(CourseList.this, CourseDetail.class);
        startActivity(intent);
    }
}