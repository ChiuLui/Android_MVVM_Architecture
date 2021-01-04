package com.chiului.android_mvvm_architecture.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chiului.android_mvvm_architecture.lifecycler.BaseLifecycle;

import org.jetbrains.annotations.NotNull;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

/**
 * 基础Activity$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/20 22:29
 */
public abstract class BaseNavFragment extends Fragment {

    /**
     * 是否为导航图栈顶（最上层）（该参数为子类控制）
     */
    private boolean isTopStack = false;
    private View mRootView;

    public boolean isTopStack() {
        return isTopStack;
    }

    public void setTopStack(boolean topStack) {
        isTopStack = topStack;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getLifecycle().addObserver(new BaseLifecycle());
        initBaseWidget();
        mRootView = initViewModel(inflater, setContentViewID(), container);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBackPressDispatcher();
        initView();
    }

    /**
     * 监听回退操作
     */
    private void initBackPressDispatcher() {
        getActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navUp();
            }
        });
    }

    /**
     * 返回处理
     */
    public void navUp() {
        if (isTopStack) {
            // 最上层直接关闭
            getActivity().finish();
        } else {
            // 由系统判断
            boolean navigateUp = findNavController(BaseNavFragment.this).navigateUp();
            if (!navigateUp) {
                // 没有上一层就关闭 Activity
                getActivity().finish();
            }
        }
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
     * @return 布局文件ID
     */
    public abstract @LayoutRes int setContentViewID();

    /**
     * 初始化 ViewModel
     * @param inflater
     * @param layoutId
     * @param container
     * @return  碎片根布局
     */
    public abstract View initViewModel(LayoutInflater inflater, int layoutId, @Nullable ViewGroup container);

    /**
     * 初始化 View
     */
    public abstract void initView();

}
