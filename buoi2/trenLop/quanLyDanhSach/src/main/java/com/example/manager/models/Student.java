package com.example.manager.models;

public class Student {
    private String id;
    private String name;
    private float gpa;

    public Student() {}

    public Student(String id, String name, float gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public float getGpa() { return gpa; }
    public void setGpa(float gpa) { this.gpa = gpa; }

    public String getRank() {
        if (gpa >= 8) return "Giỏi";
        if (gpa >= 6.5) return "Khá";
        if (gpa >= 5) return "Trung bình";
        return "Yếu";
    }
}
