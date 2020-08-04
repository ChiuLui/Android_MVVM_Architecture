package com.chiului.android_mvvm_architecture.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.View;
import android.widget.Toast;

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.base.BaseActivity;
import com.chiului.android_mvvm_architecture.databinding.ActivityLoginBinding;
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils;
import com.chiului.android_mvvm_architecture.viewmodel.LoginViewModel;

/**
 * 登录页面
 * @author 神经大条蕾弟
 * @date   2020/08/04 18:15
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ActivityLoginBinding mBinding;
    private LoginViewModel mViewModel;

    @Override
    public int setContentViewID() {
        return R.layout.activity_login;
    }

    @Override
    public void initViewModel() {
        mBinding = getDataBinding(ActivityLoginBinding.class);

        mViewModel = new ViewModelProvider(this, InjectorUtils.provideLoginViewModelFactory(this)).get(LoginViewModel.class);

        mBinding.setLifecycleOwner(this);

        mBinding.setLoginModel(mViewModel);

        mBinding.setClickListener(this);
    }

    @Override
    public void onCreating(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {
        mViewModel.login();
        Toast.makeText(LoginActivity.this, "登录并缓存登录信息", Toast.LENGTH_LONG).show();
    }

}