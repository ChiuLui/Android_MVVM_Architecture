package com.chiului.android_mvvm_architecture.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chiului.android_mvvm_architecture.lifecycler.BaseLifecycle;

import org.jetbrains.annotations.NotNull;

/**
 * 基础Activity$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/20 22:29
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getLifecycle().addObserver(new BaseLifecycle());
        initBaseWidget();
        initViewModel(inflater, setContentViewID(), container);
        return onCreating(inflater, container, savedInstanceState);
    }

    /**
     * 初始化基类小工具
     * 在onCreating()之前
     */
    private void initBaseWidget(){
        Bundle data = getArguments();
        if (data != null) {
            initBundle(data);
        }
    }

    /**
     * 初始化参数
     */
    public void initBundle(@NotNull Bundle bundle) {}

    /**
     * @return 布局文件ID
     */
    public abstract @LayoutRes int setContentViewID();

    /**
     * 初始化 ViewModel
     */
    public abstract void initViewModel(LayoutInflater inflater, int layoutId, @Nullable ViewGroup container);

    public abstract View onCreating(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

}
