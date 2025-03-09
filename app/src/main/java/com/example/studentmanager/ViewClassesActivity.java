package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewClassesActivity extends AppCompatActivity {
    private ListView listViewClasses;
    private DatabaseHelper dbHelper;
    private ArrayList<String> classList;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classes);

        listViewClasses = findViewById(R.id.listViewClasses);
        dbHelper = new DatabaseHelper(this);

        loadClassList();
    }

    private void loadClassList() {
        classList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM classes", null);
        if (cursor.moveToFirst()) {
            do {
                String className = cursor.getString(cursor.getColumnIndexOrThrow("class_name"));
                String classDescription = cursor.getString(cursor.getColumnIndexOrThrow("class_description"));
                classList.add("📚 " + className + "\n📝 " + classDescription);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();


        if (classList.isEmpty()) {
            Toast.makeText(this, "Chưa có lớp học nào!", Toast.LENGTH_SHORT).show();
        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classList);
        listViewClasses.setAdapter(adapter);
    }
}