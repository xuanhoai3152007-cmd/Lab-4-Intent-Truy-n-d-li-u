package com.example.loginintentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView txtHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtHello = findViewById(R.id.txtHello);

        // nhận dữ liệu từ Intent
        String username = getIntent().getStringExtra("username");

        txtHello.setText("Xin chào, " + username);
    }
}