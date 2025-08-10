package com.ritesh.studentutilityapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button notesBtn, remindersBtn, timetableBtn, cgpaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesBtn = findViewById(R.id.notesBtn);
        remindersBtn = findViewById(R.id.remindersBtn);
        timetableBtn = findViewById(R.id.timetableBtn);
        cgpaBtn = findViewById(R.id.cgpaBtn);

        notesBtn.setOnClickListener(v ->
                startActivity(new Intent(this, NotesActivity.class)));

        remindersBtn.setOnClickListener(v ->
                startActivity(new Intent(this, RemindersActivity.class)));

        timetableBtn.setOnClickListener(v ->
                startActivity(new Intent(this, TimetableActivity.class)));

        cgpaBtn.setOnClickListener(v ->
                startActivity(new Intent(this, CgpaCalculatorActivity.class)));
    }
}
