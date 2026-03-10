package com.example.loginintentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView txtHello;
    Button btnLogout, btnNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtHello = findViewById(R.id.txtHello);
        btnLogout = findViewById(R.id.btnLogout);
        btnNotify = findViewById(R.id.btnNotify);

        // Nhận username từ LoginActivity
        String username = getIntent().getStringExtra("username");

        txtHello.setText("Xin chào, " + username);

        // Tạo Notification Channel (Android 8+)
        createNotificationChannel();

        // Nút thông báo
        btnNotify.setOnClickListener(v -> showNotification());

        // Nút đăng xuất
        btnLogout.setOnClickListener(v -> showLogoutDialog());
    }

    // AlertDialog xác nhận đăng xuất
    private void showLogoutDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc muốn thoát?");

        builder.setPositiveButton("Có", (dialog, which) -> {

            Toast.makeText(this,"Đã đăng xuất",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        });

        builder.setNegativeButton("Không",(dialog, which) -> {
            dialog.dismiss();
        });

        builder.show();
    }

    // Hiển thị Notification
    private void showNotification(){

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,"demo_channel")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Thông báo")
                        .setContentText("Bạn vừa nhận được một thông báo!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        notificationManager.notify(1,builder.build());
    }

    // Tạo Notification Channel
    private void createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = "Demo Channel";
            String description = "Channel for demo notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel =
                    new NotificationChannel("demo_channel",name,importance);

            channel.setDescription(description);

            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);
        }
    }
}