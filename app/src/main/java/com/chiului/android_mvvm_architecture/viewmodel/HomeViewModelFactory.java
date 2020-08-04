package com.chiului.android_mvvm_architecture.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.chiului.android_mvvm_architecture.data.HomeRepository;


/**
 * $ 首页碎片 ViewModel 的工厂方法
 *
 * @author 神经大条蕾弟
 * @date 2020/07/24 09:30
 */
public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private HomeRepository mRepository;

    public HomeViewModelFactory(HomeRepository repository) {
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(mRepository);
    }
}
