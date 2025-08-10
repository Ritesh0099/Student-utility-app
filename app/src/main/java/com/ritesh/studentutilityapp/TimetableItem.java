package com.ritesh.studentutilityapp;

public class TimetableItem {
    int id;
    String subject, day, time;

    public TimetableItem(int id, String subject, String day, String time) {
        this.id = id;
        this.subject = subject;
        this.day = day;
        this.time = time;
    }

    public int getId() { return id; }
    public String getSubject() { return subject; }
    public String getDay() { return day; }
    public String getTime() { return time; }
}
