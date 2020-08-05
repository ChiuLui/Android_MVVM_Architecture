package com.chiului.android_mvvm_architecture.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.adapter.MainAdapter;
import com.chiului.android_mvvm_architecture.base.BaseFragment;
import com.chiului.android_mvvm_architecture.databinding.FragmentMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * 主页碎片
 * @author 神经大条蕾弟
 * @date   2020/08/05 14:51
 */
public class MainFragment extends BaseFragment {

    private FragmentMainBinding mBinding;

    @Override
    public int setContentViewID() {
        return R.layout.fragment_main;
    }

    @Override
    public void initViewModel(LayoutInflater inflater, int layoutId, @Nullable ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        mBinding.setLifecycleOwner(this);
    }

    @Override
    public View onCreating(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 初始化适配器
        MainAdapter mainAdapter = new MainAdapter(this);

        // 给 ViewPager 设置适配器
        mBinding.viewPager.setAdapter(mainAdapter);

        // 把 TabLayout 与 ViewPager 绑定
        new TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager, (tab, position) -> {
            tab.setIcon(getTabIcon(position));
            tab.setText(getTabText(position));
        }).attach();

        return mBinding.getRoot();
    }

    /**
     * 根据 Position 获取 TabLayout 图标
     * @param position
     * @return
     */
    private int getTabIcon(int position){
        int resId = R.drawable.garden_tab_selector;
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
        }
        return resId;
    }

    /**
     * 根据 Position 获取 TabLayout 文字
     * @param position
     * @return
     */
    private String getTabText(int position){
        String tab = "Module" + (position + 1);
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
        }
        return tab;
    }

}