package com.example.rpettyc196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rpettyc196.Database.Repository;
import com.example.rpettyc196.Entity.Assessment;
import com.example.rpettyc196.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AssessmentDetail extends AppCompatActivity {

    int assessmentId;
    int courseId;
    String assessmentName;
    String start;
    String end;
    String examtype;
    EditText editAssessmentId;
    EditText editCourseID;
    EditText editAssessmentName;
    EditText editAssessmentStart;
    EditText editAssessmentEnd;
    EditText editExamType;
    Assessment assessment;
    Repository repository;
    Assessment currentAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);


        assessmentId = getIntent().getIntExtra("assessmentId", -1);
        courseId = getIntent().getIntExtra("courseId", -1);
        assessmentName = getIntent().getStringExtra("assessmentName");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        examtype = getIntent().getStringExtra("examType");

        editAssessmentId = findViewById(R.id.assessmentID);
        editAssessmentId.setText(String.valueOf(assessmentId));
        editCourseID = findViewById(R.id.courseID);
        editCourseID.setText(String.valueOf(courseId));
        editAssessmentName = findViewById(R.id.assessmentName);
        editAssessmentName.setText(assessmentName);
        editAssessmentStart = findViewById(R.id.assessmentStart);
        editAssessmentStart.setText(start);
        editAssessmentEnd = findViewById(R.id.assessmentEnd);
        editAssessmentEnd.setText(end);
        editExamType=findViewById(R.id.examType);
        editExamType.setText(examtype);


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
                assessmentId = Integer.parseInt(editAssessmentId.getText().toString());
                for (Assessment a : repository.getAllAssessments()) {
                    if (a.getAssessmentID() == assessmentId) {
                        if (assessmentId == -1) {
                            assessment = new Assessment(0, Integer.parseInt(editCourseID.getText().toString()), editAssessmentName.getText().toString(), editAssessmentStart.getText().toString(), editAssessmentEnd.getText().toString(),editExamType.getText().toString());
                            repository.insert(assessment);
                            Intent intent = new Intent(AssessmentDetail.this, AssessmentList.class);
                            intent.putExtra("assessmentID", assessmentId);
                            startActivity(intent);
                            // Toast.makeText(CourseDetail.this,"ERROR",Toast.LENGTH_LONG).show();
                        } else {
                            assessment = new Assessment(Integer.parseInt(editAssessmentId.getText().toString()), Integer.parseInt(editCourseID.getText().toString()), editAssessmentName.getText().toString(), editAssessmentStart.getText().toString(), editAssessmentEnd.getText().toString(),editExamType.getText().toString());
                            repository.update(assessment);
                            Intent intent = new Intent(AssessmentDetail.this, AssessmentList.class);
                            startActivity(intent);
                        }
                    }
                    else{
                        assessment = new Assessment(Integer.parseInt(editAssessmentId.getText().toString()), Integer.parseInt(editCourseID.getText().toString()), editAssessmentName.getText().toString(), editAssessmentStart.getText().toString(), editAssessmentEnd.getText().toString(),editExamType.getText().toString());
                        repository.insert(assessment);
                        Intent intent = new Intent(AssessmentDetail.this, AssessmentList.class);
                        intent.putExtra("assessmentID", assessmentId);
                        startActivity(intent);
                    }
                }
            }
        });
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessmentdetail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                return true;

            case R.id.delete:
                for (Assessment assess : repository.getAllAssessments()) {
                    if (assess.getAssessmentID() == assessmentId) currentAssessment = assess;
                }
                repository.delete(currentAssessment);
                Toast.makeText(AssessmentDetail.this, currentAssessment.getAssessmentName() + " was deleted", Toast.LENGTH_LONG).show();

                return true;

            case R.id.notifystart:
                String dateFromScreen = editAssessmentStart.getText().toString();
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(AssessmentDetail.this, MyReceiver.class);
                intent.putExtra("key", dateFromScreen + " should trigger");
                PendingIntent sender = PendingIntent.getBroadcast(AssessmentDetail.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

            case R.id.notifyend:
                dateFromScreen = editAssessmentEnd.getText().toString();
                myFormat = "MM/dd/yy";
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                trigger = myDate.getTime();
                intent = new Intent(AssessmentDetail.this, MyReceiver.class);
                intent.putExtra("key", dateFromScreen + " should trigger");
                sender = PendingIntent.getBroadcast(AssessmentDetail.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

        }
        return super.onOptionsItemSelected(item);

    }
}
