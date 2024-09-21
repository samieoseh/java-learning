package com.test.auth.models;

import jakarta.persistence.Id;
public class Student {
    private Long id;
    private String name;
    private int marks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }

    public Student(Long id, String name, int marks ) {
        this.marks = marks;
        this.name = name;
        this.id = id;
    }
}
