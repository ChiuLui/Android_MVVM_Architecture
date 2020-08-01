package com.chiului.android_mvvm_architecture.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chiului.android_mvvm_architecture.lifecycler.BaseLifecycle;

/**
 * 基础Activity$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/20 22:29
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new BaseLifecycle());
        initBaseWidget();
        initViewModel();
        onCreating(savedInstanceState);
    }

    /**
     * 获取 DataBinding
     * @param modelClass
     * @param <T>
     * @return
     */
    public <T extends ViewDataBinding> T getDataBinding(@NonNull Class<T> modelClass){
        return DataBindingUtil.setContentView(this, setContentViewID());
    }

    /**
     * 初始化基类小工具
     * 在onCreating()之前
     */
    protected void initBaseWidget(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            initBundle(bundle);
        }
    }

    /**
     * 初始化数据
     */
    protected void initBundle(Bundle bundle) {}

    /**
     * @return 布局文件ID
     */
    protected abstract @LayoutRes int setContentViewID();

    /**
     * 初始化 ViewModel
     */
    protected abstract void initViewModel();

    /**
     * 初始化操作
     * @param savedInstanceState
     */
    protected abstract void onCreating(@Nullable Bundle savedInstanceState);

}
