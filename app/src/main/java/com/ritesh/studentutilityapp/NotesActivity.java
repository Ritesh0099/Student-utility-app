package com.ritesh.studentutilityapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    EditText noteInput;
    Button addNoteBtn;
    RecyclerView notesRecyclerView;
    DBHelper dbHelper;
    ArrayList<Note> noteList;
    NoteAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        noteInput = findViewById(R.id.noteInput);
        addNoteBtn = findViewById(R.id.addNoteBtn);
        notesRecyclerView = findViewById(R.id.notesRecyclerView);

        dbHelper = new DBHelper(this);
        noteList = dbHelper.getAllNotes();

        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter(noteList, note -> {
            dbHelper.deleteNote(note.getId());
            noteList.clear();
            noteList.addAll(dbHelper.getAllNotes());
            adapter.notifyDataSetChanged();
        });
        notesRecyclerView.setAdapter(adapter);

        addNoteBtn.setOnClickListener(v -> {
            String content = noteInput.getText().toString().trim();
            if (!content.isEmpty()) {
                dbHelper.addNote(content);
                noteInput.setText("");
                noteList.clear();
                noteList.addAll(dbHelper.getAllNotes());
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Note is empty!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
