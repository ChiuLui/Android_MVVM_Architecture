package com.chiului.android_mvvm_architecture.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.chiului.android_mvvm_architecture.data.UserRepository;

/**
 * $ BaseViewModel 的工厂方法
 *
 * @author 神经大条蕾弟
 * @date 2020/07/24 09:30
 */
public class UserViewModelFactory implements ViewModelProvider.Factory {

    private UserRepository userRepository;

    public UserViewModelFactory (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserViewModel(userRepository);
    }
}
