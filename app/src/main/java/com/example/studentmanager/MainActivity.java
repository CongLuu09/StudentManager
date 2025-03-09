package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView classManager;
    private ImageView studentManager;
    private ImageView infoApp;
    private static ImageView logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classManager = findViewById(R.id.ic_class);
        studentManager = findViewById(R.id.ic_student);
        infoApp = findViewById(R.id.ic_info);
       logout = findViewById(R.id.ic_logout);

        classManager.setOnClickListener(view -> openActivity(ClassManagerActivity.class));
        studentManager.setOnClickListener(view -> openActivity(StudentManagerActivity.class));
        infoApp.setOnClickListener(view -> openActivity(InfoActivity.class));
        logout.setOnClickListener(view -> finish());
    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }
}