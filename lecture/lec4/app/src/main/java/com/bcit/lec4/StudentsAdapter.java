package com.bcit.lec4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    private List<Student> students;

    public StudentsAdapter(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View studentView = inflater.inflate(R.layout.item_student, parent, false);

        StudentViewHolder viewHolder = new StudentViewHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);

        TextView textViewStudentName = holder.nameTextView;
        textViewStudentName.setText(student.GetStudentName());

        TextView textViewGrade = holder.gradeTextView;
        textViewGrade.setText(String.valueOf(student.GetGrade()));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
