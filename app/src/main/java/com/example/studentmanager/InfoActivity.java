package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    private TextView txtUserName, txtUserEmail;
    private ImageView imgProfile;
    private Button btnEditInfo, btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        txtUserName = findViewById(R.id.txtUserName);
        txtUserEmail = findViewById(R.id.txtUserEmail);
        imgProfile = findViewById(R.id.imgProfile);
        btnEditInfo = findViewById(R.id.btnEditInfo);
        btnLogout = findViewById(R.id.btnLogout);

        txtUserName.setText("Lưu Viết Công");
        txtUserEmail.setText("cong090503@gmail.com");

        btnEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, EditInfoActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}