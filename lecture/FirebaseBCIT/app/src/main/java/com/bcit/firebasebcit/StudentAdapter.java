package com.bcit.firebasebcit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;

    private OnAdapterItemListener onAdapterItemListener;

    public void setOnAdapterItemListener(OnAdapterItemListener listener) {
        this.onAdapterItemListener = listener;
    }

    public StudentAdapter(List<Student> students)
    {
        this.studentList = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View movieView = inflater.inflate(R.layout.row_layout, parent, false);

        StudentViewHolder viewHolder = new StudentViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position)
    {
        TextView tvName = holder.nameTextView;
        TextView tvSchool = holder.schoolTextView;

        Student student = studentList.get(position);
        tvName.setText(student.getStudentFirstName()
                + " " + student.getStudentLastName());
        tvSchool.setText(student.getSchool());

        tvName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                onAdapterItemListener.OnLongClick(student);

                return false;
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView schoolTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.textViewName);
            schoolTextView = itemView.findViewById(R.id.textViewSchool);
        }
    }

}

