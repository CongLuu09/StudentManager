package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewStudentsActivity extends AppCompatActivity {
    private ListView listViewStudents;
    private DatabaseHelper dbHelper;
    private ArrayList<String> studentList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        listViewStudents = findViewById(R.id.listViewStudents);
        dbHelper = new DatabaseHelper(this);

        loadStudentList();
    }

    private void loadStudentList() {
        studentList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT s.student_name, s.student_id, c.class_name FROM students s " +
                "LEFT JOIN classes c ON s.class_id = c.id", null);

        if (cursor.moveToFirst()) {
            do {
                String studentName = cursor.getString(cursor.getColumnIndexOrThrow("student_name"));
                String studentId = cursor.getString(cursor.getColumnIndexOrThrow("student_id"));
                String className = cursor.getString(cursor.getColumnIndexOrThrow("class_name"));

                studentList.add("👨‍🎓 " + studentName + " (" + studentId + ")\n📚 Lớp: " + (className != null ? className : "Chưa có lớp"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        if (studentList.isEmpty()) {
            Toast.makeText(this, "Chưa có sinh viên nào!", Toast.LENGTH_SHORT).show();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listViewStudents.setAdapter(adapter);
    }
}