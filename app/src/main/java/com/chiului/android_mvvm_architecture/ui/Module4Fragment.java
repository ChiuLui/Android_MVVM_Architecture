package com.chiului.android_mvvm_architecture.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.adapter.Module4Adapter;
import com.chiului.android_mvvm_architecture.base.BaseFragment;
import com.chiului.android_mvvm_architecture.databinding.FragmentModule4Binding;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * 模块4碎片
 * @author 神经大条蕾弟
 * @date   2020/09/28 16:59
 */
public class Module4Fragment extends BaseFragment {

    private FragmentModule4Binding mBinding;

    public Module4Fragment() {}

    public static Module4Fragment newInstance() {
        Module4Fragment fragment = new Module4Fragment();
        return fragment;
    }

    @Override
    public int setContentViewID() {
        return R.layout.fragment_module4;
    }

    @Override
    public void initViewModel(LayoutInflater inflater, int layoutId, @Nullable ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        mBinding.setLifecycleOwner(this);
    }

    @Override
    public View onCreating(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = mBinding.getRoot();

        // 初始化适配器
        Module4Adapter adapter = new Module4Adapter(this);

        // 给 ViewPager 设置适配器
        mBinding.viewPager.setAdapter(adapter);

        // 把 TabLayout 与 ViewPager 绑定
        new TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager, (tab, position) -> {
            tab.setText(getTabText(position));
        }).attach();

        return rootView;
    }

    /**
     * 根据 Position 获取 TabLayout 文字
     * @param position
     * @return
     */
    private String getTabText(int position){
        String tab = "";
        switch (position){
            case 0:
                tab = "普通列表";
                break;
            case 1:
                tab = "下拉刷新列表";
                break;
            case 2:
            default:
                tab = "刷新和加载列表";
        }
        return tab;
    }

}