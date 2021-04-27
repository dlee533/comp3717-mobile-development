package com.bcit.lec4;

public class Student {

    private String studentName;
    private int studentGrade;

    public Student(String studentName, int studentGrade) {
        this.studentName = studentName;
        this.studentGrade = studentGrade;
    }

    public String GetStudentName() {
        return this.studentName;
    }

    public int GetGrade() {
        return this.studentGrade;
    }
}
