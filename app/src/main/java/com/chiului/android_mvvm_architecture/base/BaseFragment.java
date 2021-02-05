package com.chiului.android_mvvm_architecture.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getLifecycle().addObserver(new BaseLifecycle());
        initBaseWidget();
        mRootView = initViewModel(inflater, container, savedInstanceState);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view, savedInstanceState);
    }

    /**
     * 初始化基类小工具
     * 在 onCreating()之前
     */
    private void initBaseWidget(){
        Bundle data = getArguments();
        if (data != null) {
            initBundle(data);
        }
    }

    /**
     * 初始化参数（可选）
     */
    public void initBundle(@NotNull Bundle bundle) {}

    /**
     * 初始化 ViewModel
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return  碎片根布局
     */
    public abstract View initViewModel(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 初始化 View
     */
    public abstract void initView(@NonNull View view, @Nullable Bundle savedInstanceState);

}
