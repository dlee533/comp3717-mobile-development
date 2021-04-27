package com.bcit.firebasebcit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextFirstName;
    EditText editTextLastName;
    Spinner spinnerSchool;
    Button buttonAddStudent;

    RecyclerView rvStudents;
    List<Student> studentList;

    DatabaseReference databaseStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvStudents = findViewById(R.id.rvStudents);
        studentList = new ArrayList<Student>();

        editTextFirstName = findViewById(R.id.dialog_editTextFirstName);
        editTextLastName = findViewById(R.id.dialog_editTextLastName);
        buttonAddStudent = findViewById(R.id.buttonAddStudent);
        spinnerSchool = findViewById(R.id.dialog_spinnerSchool);

        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        databaseStudents = FirebaseDatabase.getInstance().getReference("students");
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();
                for (DataSnapshot studentsSnapshot : snapshot.getChildren()) {
                    Student student = studentsSnapshot.getValue(Student.class);
                    studentList.add(student);
                }

                StudentAdapter adapter = new StudentAdapter(studentList);
                rvStudents.setAdapter(adapter);
                rvStudents.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adapter.setOnAdapterItemListener(new OnAdapterItemListener(){
                    @Override
                    public void OnLongClick(Student student) {
                        showUpdateDialog(student.getStudentId(),
                        student.getStudentFirstName(),
                        student.getStudentLastName(),
                        student.getSchool());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void addStudent() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String school = spinnerSchool.getSelectedItem().toString().trim();

        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(this, "You must enter first name", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, "You must enter last name", Toast.LENGTH_LONG).show();
            return;
        }

        String id = databaseStudents.push().getKey();
        Student student = new Student(id, firstName, lastName, school);

        Task<Void> setValueTask = databaseStudents.child(id).setValue(student);

        setValueTask.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object c) {
                Toast.makeText(MainActivity.this, "Student added", Toast.LENGTH_LONG).show();
                editTextFirstName.setText("");
                editTextLastName.setText("");
                spinnerSchool.setSelection(0);
            }
        });

        setValueTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateStudent(String id, String firstName, String lastName, String school) {
        Student student = new Student(id, firstName, lastName, school);
        Task<Void> setValueTask = databaseStudents.child(id).setValue(student);
        setValueTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Student updated", Toast.LENGTH_SHORT).show();

            }
        });

        setValueTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "\"Something went wrong\"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteStudent(String id) {
        //Dongmin Lee
        Task<Void> setValueTask = databaseStudents.child(id).removeValue();
        setValueTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Student deleted", Toast.LENGTH_SHORT).show();

            }
        });

        setValueTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showUpdateDialog(final String studentId, String firstName, String lastName, String school) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        EditText d_editTextFirstName = dialogView.findViewById(R.id.dialog_editTextFirstName);
        d_editTextFirstName.setText(firstName);

        EditText d_editTextLastName = dialogView.findViewById(R.id.dialog_editTextLastName);
        d_editTextLastName.setText(lastName);

        Spinner d_spinnerSchool = dialogView.findViewById(R.id.dialog_spinnerSchool);
        d_spinnerSchool.setSelection(((ArrayAdapter<String>)spinnerSchool.getAdapter()).getPosition(school));

        Button btnUpdate = dialogView.findViewById(R.id.dialog_btnUpdate);

        dialogBuilder.setTitle("Update Student " + firstName + " " + lastName);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = d_editTextFirstName.getText().toString().trim();
                String lastName = d_editTextLastName.getText().toString().trim();
                String school = d_spinnerSchool.getSelectedItem().toString().trim();

                if (TextUtils.isEmpty(firstName)) {
                    d_editTextFirstName.setError("First Name is required");
                    return;
                } else if (TextUtils.isEmpty(lastName)) {
                    d_editTextLastName.setError("Last Name is required");
                    return;
                }

                updateStudent(studentId, firstName, lastName, school);

                alertDialog.dismiss();
            }
        });

        final Button btnDelete = dialogView.findViewById(R.id.dialog_btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent(studentId);
                alertDialog.dismiss();
            }
        });
    }
}