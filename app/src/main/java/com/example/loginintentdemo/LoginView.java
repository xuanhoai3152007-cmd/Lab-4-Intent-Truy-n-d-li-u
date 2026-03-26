package com.example.loginintentdemo;

public interface LoginView {
    void showLoading();
    void hideLoading();
    void loginSuccess(String username);
    void loginError(String message);
}
