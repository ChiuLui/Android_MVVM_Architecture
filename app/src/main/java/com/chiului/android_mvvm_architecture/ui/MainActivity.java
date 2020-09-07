package com.chiului.android_mvvm_architecture.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.base.BaseActivity;
import com.chiului.android_mvvm_architecture.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mBinding;

    @Override
    public int setContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewModel() {
        mBinding = getDataBinding(ActivityMainBinding.class);

        mBinding.setLifecycleOwner(this);

    }

    @Override
    public void onCreating(@Nullable Bundle savedInstanceState) { }

    /**
     * 页面跳转
     * @param context
     */
    public static void actionStart(Context context) {
        startActivity(context, MainActivity.class);
    }

}