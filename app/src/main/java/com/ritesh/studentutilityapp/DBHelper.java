package com.ritesh.studentutilityapp;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "student_utility.db";
    private static final int DB_VERSION = 1;

    // Notes Table
    private static final String TABLE_NOTES = "notes";
    private static final String COLUMN_NOTE_ID = "id";
    private static final String COLUMN_CONTENT = "content";

    // Timetable Table
    private static final String TABLE_TIMETABLE = "timetable";
    private static final String COLUMN_TIMETABLE_ID = "id";
    private static final String COLUMN_SUBJECT = "subject";
    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_TIME = "time";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNotesTable = "CREATE TABLE " + TABLE_NOTES + " (" +
                COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CONTENT + " TEXT)";
        db.execSQL(createNotesTable);

        String createTimetableTable = "CREATE TABLE " + TABLE_TIMETABLE + " (" +
                COLUMN_TIMETABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SUBJECT + " TEXT, " +
                COLUMN_DAY + " TEXT, " +
                COLUMN_TIME + " TEXT)";
        db.execSQL(createTimetableTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMETABLE);
        onCreate(db);
    }

    // ---------------- Notes Methods ----------------
    public void addNote(String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTENT, content);
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NOTES + " ORDER BY id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NOTE_ID));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT));
                notes.add(new Note(id, content));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, COLUMN_NOTE_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // ---------------- Timetable Methods ----------------
    public void addTimetableItem(String subject, String day, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT, subject);
        values.put(COLUMN_DAY, day);
        values.put(COLUMN_TIME, time);
        db.insert(TABLE_TIMETABLE, null, values);
        db.close();
    }

    public ArrayList<TimetableItem> getAllTimetableItems() {
        ArrayList<TimetableItem> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TIMETABLE + " ORDER BY id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TIMETABLE_ID));
                String subject = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT));
                String day = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DAY));
                String time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME));
                items.add(new TimetableItem(id, subject, day, time));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return items;
    }
}
