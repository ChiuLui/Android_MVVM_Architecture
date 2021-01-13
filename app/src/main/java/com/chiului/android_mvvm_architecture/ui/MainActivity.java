package com.chiului.android_mvvm_architecture.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.base.BaseActivity;
import com.chiului.android_mvvm_architecture.databinding.ActivityMainBinding;
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils;
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModel;

/**
 * 主 Activity
 * @author 神经大条蕾弟
 * @date   2021/01/04 11:19 PM
 */
public class MainActivity extends BaseActivity {

    private ActivityMainBinding mBinding;
    private MainViewModel mViewModel;

    @Override
    public int setContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewModel() {
        mBinding = getDataBinding(ActivityMainBinding.class);
        mViewModel = new ViewModelProvider(this, InjectorUtils.provideMainViewModelFactory(this)).get(MainViewModel.class);
        mBinding.setLifecycleOwner(this);
    }

    @Override
    public void onCreating(@Nullable Bundle savedInstanceState) { }

}