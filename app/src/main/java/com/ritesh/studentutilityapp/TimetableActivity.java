package com.ritesh.studentutilityapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import java.util.ArrayList;

public class TimetableActivity extends AppCompatActivity {

    EditText subjectInput, dayInput, timeInput;
    Button addTimetableBtn;
    RecyclerView timetableRecyclerView;
    DBHelper dbHelper;
    ArrayList<TimetableItem> timetableList;
    TimetableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        subjectInput = findViewById(R.id.subjectInput);
        dayInput = findViewById(R.id.dayInput);
        timeInput = findViewById(R.id.timeInput);
        addTimetableBtn = findViewById(R.id.addTimetableBtn);
        timetableRecyclerView = findViewById(R.id.timetableRecyclerView);

        dbHelper = new DBHelper(this);
        timetableList = dbHelper.getAllTimetableItems();

        adapter = new TimetableAdapter(timetableList);
        timetableRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        timetableRecyclerView.setAdapter(adapter);

        addTimetableBtn.setOnClickListener(v -> {
            String subject = subjectInput.getText().toString().trim();
            String day = dayInput.getText().toString().trim();
            String time = timeInput.getText().toString().trim();

            if (!subject.isEmpty() && !day.isEmpty() && !time.isEmpty()) {
                dbHelper.addTimetableItem(subject, day, time);
                subjectInput.setText("");
                dayInput.setText("");
                timeInput.setText("");
                timetableList.clear();
                timetableList.addAll(dbHelper.getAllTimetableItems());
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
