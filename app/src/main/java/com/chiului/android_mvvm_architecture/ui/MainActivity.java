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
    public void onCreating(@Nullable Bundle savedInstanceState) {
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.ly_root, mainFragment, "Main").commit();
    }

    /**
     * 页面跳转
     * @param context
     */
    public static void actionStart(Context context) {
        Bundle bundle = new Bundle();
        intentToActivity(context, MainActivity.class, bundle);
    }

}