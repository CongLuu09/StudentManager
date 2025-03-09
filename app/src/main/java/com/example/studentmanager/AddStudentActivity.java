package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {
    private EditText edtStudentName, edtStudentID;
    private Spinner spinnerClass;
    private Button btnAddStudent;
    private DatabaseHelper dbHelper;
    private ArrayList<String> classList;
    private ArrayList<Integer> classIdList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        edtStudentName = findViewById(R.id.edtStudentName);
        edtStudentID = findViewById(R.id.edtStudentID);
        spinnerClass = findViewById(R.id.spinnerClass);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        dbHelper = new DatabaseHelper(this);

        loadClassesIntoSpinner();

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudentToDatabase();
            }
        });
    }

    private void addStudentToDatabase() {
        String studentName = edtStudentName.getText().toString().trim();
        String studentID = edtStudentID.getText().toString().trim();
        int selectedClassIndex = spinnerClass.getSelectedItemPosition();

        if (studentName.isEmpty() || studentID.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedClassIndex < 0) {
            Toast.makeText(this, "Chưa có lớp học nào, hãy thêm lớp học trước!", Toast.LENGTH_SHORT).show();
            return;
        }

        int classId = classIdList.get(selectedClassIndex);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("student_name", studentName);
        values.put("student_id", studentID);
        values.put("class_id", classId);

        try {
            long result = db.insert("students", null, values);
            db.close();

            if (result != -1) {
                Toast.makeText(this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
                finish(); // Đóng Activity
            } else {
                Toast.makeText(this, "Lỗi khi thêm sinh viên!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace(); // In lỗi vào Logcat
        }
    }

    private void loadClassesIntoSpinner() {
        classList = new ArrayList<>();
        classIdList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, class_name FROM classes", null);

        if (cursor.moveToFirst()) {
            do {
                classIdList.add(cursor.getInt(0));
                classList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, classList);
        spinnerClass.setAdapter(adapter);
    }
}