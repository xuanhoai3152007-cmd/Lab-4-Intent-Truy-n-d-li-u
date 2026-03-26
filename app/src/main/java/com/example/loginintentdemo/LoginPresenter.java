package com.example.loginintentdemo;

import android.os.Handler;

public class LoginPresenter {
    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            view.loginError("Vui lòng nhập đầy đủ thông tin");
            return;
        }

        view.showLoading();

        // Giả lập gọi API với delay 2 giây
        new Handler().postDelayed(() -> {
            view.hideLoading();
            if (username.equals("admin") && password.equals("12345")) {
                view.loginSuccess(username);
            } else {
                view.loginError("Sai tên đăng nhập hoặc mật khẩu");
            }
        }, 2000);
    }
}
