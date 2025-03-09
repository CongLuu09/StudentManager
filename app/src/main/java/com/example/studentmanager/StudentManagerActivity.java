package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentManagerActivity extends AppCompatActivity {
    private Button btnAddStudent, btnViewStudents, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager);

        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnViewStudents = findViewById(R.id.btnViewStudents);
        btnBack = findViewById(R.id.btnBack);


        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentManagerActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });

        btnViewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentManagerActivity.this, ViewStudentsActivity.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}