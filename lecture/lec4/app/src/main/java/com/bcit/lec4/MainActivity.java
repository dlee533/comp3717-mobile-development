package com.bcit.lec4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int currentStudentId;
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvStudents = findViewById(R.id.rv_Main);

        students = CreateStudentList(10);

        StudentsAdapter adapter = new StudentsAdapter(students);
        rvStudents.setAdapter(adapter);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<Student> CreateStudentList(int num) {
        ArrayList<Student> students = new ArrayList<>();
        Random ran = new Random();

        for (int i = 1; i <= num; i++) {
            students.add(new Student("Person" + ++currentStudentId, ran.nextInt(100) + 1));
        }

        return students;
    }
}

// // Demonstration of three different methods to add event handler
//public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Button button = findViewById(R.id.button_main);
////        button.setOnClickListener(myOnClickListener);
//        button.setOnClickListener(this);
//    }
//
//    // Adding events through layout: first method to add event handler
//    public void DoSomething(View v)
//    {
//        Log.d("DEBUG", "CLICK 1");
//    }
//
//    // Adding events through anonymous class: second method to add event handler
//    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Log.d("DEBUG", "CLICK 2");
//        }
//    };
//
//    // Class Activity:
//    // Dongmin(Mina) Lee
//    // A01165938
//    private View.OnLongClickListener myOnLongClickListener = new View.OnLongClickListener() {
//        @Override
//        public boolean onLongClick(View v) {
//            // in OnCreate a button is assigned the listener
//            // Button button = findViewById(R.id.buttonHello);
//            // button.setOnLongClickListener(myOnLongClickListener);
//            Log.d("DEBUG", "This is Anonymous Class");
//            return true;
//        }
//    };
//
//    // Setting an on click as an implemented interface: third method to add event handler
//    @Override
//    public void onClick(View v) {
//        Log.d("DEBUG", "CLICK 3");
//        Toast toast = Toast.makeText(getApplicationContext(), "Show me some toast!", Toast.LENGTH_SHORT);
//        toast.show();
//    }
//}

