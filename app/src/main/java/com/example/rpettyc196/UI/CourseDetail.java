package com.example.rpettyc196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rpettyc196.Database.Repository;
import com.example.rpettyc196.Entity.Assessment;
import com.example.rpettyc196.Entity.Course;
import com.example.rpettyc196.Entity.Term;
import com.example.rpettyc196.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseDetail extends AppCompatActivity {


    int termId;
    int courseId;
    String name;
    String status;
    String ciName;
    String ciPhone;
    String email;
    String note;
    EditText editTerm;
    EditText editCourse;
    EditText editName;
    EditText editStatus;
    EditText editCiName;
    EditText editCiPhone;
    EditText editEmail;
    EditText editNote;
    EditText editStart;
    EditText editEnd;
    DatePickerDialog.OnDateSetListener startDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarEnd = Calendar.getInstance();
    Term term;
    Course course;
    Term currentTerm;
    int numTerms;
    Repository repository;
    String myFormat = "MM/dd/yy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        termId = getIntent().getIntExtra("termID", -1);
        courseId = getIntent().getIntExtra("courseID", -1);
        name = getIntent().getStringExtra("courseName");
        status = getIntent().getStringExtra("status");
        ciName = getIntent().getStringExtra("ciName");
        ciPhone = getIntent().getStringExtra("ciPhone");
        email = getIntent().getStringExtra("email");
        note = getIntent().getStringExtra("note");

        editTerm = findViewById(R.id.termID);
        editTerm.setText(Integer.toString(termId));
        editCourse = findViewById(R.id.courseID);
        editCourse.setText(Integer.toString(courseId));
        editName = findViewById(R.id.courseName);
        editName.setText(name);
        editStatus = findViewById(R.id.courseStatus);
        editStatus.setText(status);
        editCiName = findViewById(R.id.courseInstructor);
        editCiName.setText(ciName);
        editCiPhone = findViewById(R.id.coursePhone);
        editCiPhone.setText(ciPhone);
        editEmail = findViewById(R.id.courseEmail);
        editEmail.setText(email);
        editNote = findViewById(R.id.courseNote);
        editNote.setText(note);
        editStart = findViewById(R.id.startdate);
        editStart.setText(sdf.format(new Date()));
        editEnd = findViewById(R.id.enddate);
        editStart.setText(sdf.format(new Date()));

        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView1);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<Assessment> allAssessements = repository.getAllAssessments();
        for (Assessment a : repository.getAllAssessments()) {
            if (a.getCourseID() == courseId) allAssessements.add(a);
        }
        assessmentAdapter.setAssessment(allAssessements);


        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<Course> productArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, repository.getAllCourses());
        spinner.setAdapter(productArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                editNote.setText(productArrayAdapter.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                editNote.setText("Nothing selected");
            }
        });


        Button button = findViewById(R.id.saveCourse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editName = findViewById(R.id.courseName);
                name = editName.getText().toString();

                if (courseId == -1) {
                    course = new Course(0, Integer.getInteger(editCourse.getText().toString()), editName.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editStatus.getText().toString(), editCiName.getText().toString(), editCiPhone.getText().toString(), editEmail.getText().toString(), editNote.getText().toString());
                    repository.insert(course);
                    Intent intent = new Intent(CourseDetail.this, CourseList.class);
                    intent.putExtra("courseID", courseId);
                    startActivity(intent);
                    // Toast.makeText(CourseDetail.this,"ERROR",Toast.LENGTH_LONG).show();
                } else {
                    course = new Course(termId, Integer.getInteger(editCourse.getText().toString()), editName.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editStatus.getText().toString(), editCiName.getText().toString(), editCiPhone.getText().toString(), editEmail.getText().toString(), editNote.getText().toString());
                    repository.update(course);
                    Intent intent = new Intent(CourseDetail.this, CourseList.class);
                    //intent.putExtra("courseID", courseId);
                    startActivity(intent);
                }
            }
        });
        FloatingActionButton fab = findViewById(R.id.termActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetail.this, CourseList.class);
                intent.putExtra("termID", termId);
                startActivity(intent);
            }
        });
        editStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info = editStart.getText().toString();
                if (info.equals("")) info = "02/10/22";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetail.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        startDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelStart();
            }

        };
        editEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info = editEnd.getText().toString();
                if (info.equals("")) info = "02/10/22";
                try {
                    myCalendarEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetail.this, endDate, myCalendarEnd
                        .get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
                        myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelEnd();
            }

        };
    }

    private void updateLabelStart() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editStart.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editEnd.setText(sdf.format(myCalendarEnd.getTime()));
    }

    @Override
    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView1);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<Assessment> allAssessements = repository.getAllAssessments();
        for (Assessment a : repository.getAllAssessments()) {
            if (a.getCourseID() == courseId) allAssessements.add(a);
        }
        assessmentAdapter.setAssessment(allAssessements);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coursedetail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Message Title");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notifystart:
                String dateFromScreen = editStart.getText().toString();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(CourseDetail.this, MyReceiver.class);
                intent.putExtra("key", dateFromScreen + " should trigger");
                PendingIntent sender = PendingIntent.getBroadcast(CourseDetail.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.notifyend:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}