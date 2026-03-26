package com.example.loginintentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginView {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    LoginPresenter presenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        presenter = new LoginPresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang đăng nhập...");

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            presenter.login(username, password);
        });
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void loginSuccess(String username) {
        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    @Override
    public void loginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}