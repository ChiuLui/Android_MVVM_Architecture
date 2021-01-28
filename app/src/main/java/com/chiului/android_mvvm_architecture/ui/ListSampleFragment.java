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
import com.chiului.android_mvvm_architecture.databinding.FragmentListSampleBinding;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * 模块4碎片
 * @author 神经大条蕾弟
 * @date   2020/09/28 16:59
 */
public class ListSampleFragment extends BaseFragment {

    private FragmentListSampleBinding mBinding;

    public ListSampleFragment() {}

    public static ListSampleFragment newInstance() {
        ListSampleFragment fragment = new ListSampleFragment();
        return fragment;
    }

    @Override
    public int setContentViewID() {
        return R.layout.fragment_list_sample;
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