package com.chiului.android_mvvm_architecture.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.base.BaseFragment;
import com.chiului.android_mvvm_architecture.databinding.HomeFragmentBinding;
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils;
import com.chiului.android_mvvm_architecture.viewmodel.HomeViewModel;
/**
 * 首页碎片
 * @author 神经大条蕾弟
 * @date   2020/08/04 17:29
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding mBinding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int setContentViewID() {
        return R.layout.home_fragment;
    }

    @Override
    public void initViewModel(LayoutInflater inflater, int layoutId, @Nullable ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        mViewModel = new ViewModelProvider(this, InjectorUtils.provideHomeViewModelFactory(getActivity())).get(HomeViewModel.class);
        mBinding.setLifecycleOwner(this);

        mBinding.setViewModel(mViewModel);
        mBinding.setClickListener(this);
    }

    @Override
    public View onCreating(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onClick(View view) {

    }
}