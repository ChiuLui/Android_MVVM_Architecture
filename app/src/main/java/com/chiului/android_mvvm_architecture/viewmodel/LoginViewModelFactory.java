package com.chiului.android_mvvm_architecture.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.chiului.android_mvvm_architecture.data.LoginRepository;

/**
 * $ UserViewModel 的工厂方法
 *
 * @author 神经大条蕾弟
 * @date 2020/07/24 09:30
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private LoginRepository loginRepository;

    public LoginViewModelFactory(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(loginRepository);
    }
}
