package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddClassActivity extends AppCompatActivity {

    private EditText edtClassName, edtClassDescription;
    private Button btnAddClass;
    private DatabaseHelper dbHelper;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        edtClassName = findViewById(R.id.edtClassName);
        edtClassDescription = findViewById(R.id.edtClassDescription);
        btnAddClass = findViewById(R.id.btnAddClass);

        dbHelper = new DatabaseHelper(this);

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClassToDatabase();
            }
        });
    }

    private boolean addClassToDatabase() {
        String className = edtClassName.getText().toString().trim();
        String classDescription = edtClassDescription.getText().toString().trim();

        if (className.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên lớp học!", Toast.LENGTH_SHORT).show();
            return result != -1;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("class_name", className);
        values.put("class_description", classDescription);

        long result = db.insert("classes", null, values);
        db.close();

        if (result != -1) {
            Toast.makeText(this, "Thêm lớp học thành công!", Toast.LENGTH_SHORT).show();
            finish(); // Đóng Activity sau khi thêm thành công
        } else {
            Toast.makeText(this, "Lỗi khi thêm lớp học!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}