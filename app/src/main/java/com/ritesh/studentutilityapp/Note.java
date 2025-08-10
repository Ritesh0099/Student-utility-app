package com.ritesh.studentutilityapp;

public class Note {
    int id;
    String content;

    public Note(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
