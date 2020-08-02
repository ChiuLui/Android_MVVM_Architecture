package com.chiului.android_mvvm_architecture;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.chiului.android_mvvm_architecture.base.BaseActivity;
import com.chiului.android_mvvm_architecture.databinding.ActivityMainBinding;
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils;
import com.chiului.android_mvvm_architecture.viewmodel.UserViewModel;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mBinding;
    private UserViewModel mModel;

    @Override
    public int setContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewModel() {
        mBinding = getDataBinding(ActivityMainBinding.class);

        mModel = new ViewModelProvider(this, InjectorUtils.provideUserViewModelFactory(this)).get(UserViewModel.class);

        mBinding.setLifecycleOwner(this);

        mBinding.setUserModel(mModel);

        mBinding.setEventlistener(new EventListener());
    }

    @Override
    public void onCreating(@Nullable Bundle savedInstanceState) {

    }

    public class EventListener {
        public void btnClick(View view){
            mModel.login();
            Toast.makeText(MainActivity.this, "登录并缓存登录信息", Toast.LENGTH_LONG).show();
        }
        public boolean onLongClick(View view) {
            Toast.makeText(MainActivity.this, "长按", Toast.LENGTH_LONG).show();
            return false;
        }
    }

}