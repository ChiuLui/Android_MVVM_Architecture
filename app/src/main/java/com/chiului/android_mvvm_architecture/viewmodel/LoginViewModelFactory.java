package com.chiului.android_mvvm_architecture.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.chiului.android_mvvm_architecture.data.UserRepository;

/**
 * $ UserViewModel 的工厂方法
 *
 * @author 神经大条蕾弟
 * @date 2020/07/24 09:30
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private UserRepository userRepository;

    public LoginViewModelFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(userRepository);
    }
}
