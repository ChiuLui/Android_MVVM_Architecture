package com.chiului.android_mvvm_architecture.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.chiului.android_mvvm_architecture.lifecycler.BaseLifecycle;

/**
 * 基础Activity$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/20 22:29
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLifecycle().addObserver(new BaseLifecycle());
    }

}
