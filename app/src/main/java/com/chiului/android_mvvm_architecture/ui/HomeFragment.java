package com.chiului.android_mvvm_architecture.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.base.BaseFragment;
import com.chiului.android_mvvm_architecture.databinding.HomeFragmentBinding;
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils;
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;

/**
 * 首页碎片
 * @author 神经大条蕾弟
 * @date   2020/08/04 17:29
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    public static final String ARG_OBJECT = "object";

    private MainViewModel mViewModel;
    private HomeFragmentBinding mBinding;
    private String mPage;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HomeFragment() {}

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
        mViewModel = new ViewModelProvider(this, InjectorUtils.provideMainViewModelFactory(getActivity())).get(MainViewModel.class);
        mBinding.setLifecycleOwner(this);

        mBinding.setViewModel(mViewModel);
        mBinding.setClickListener(this);
    }

    @Override
    public View onCreating(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding.txPage.setText(mPage);

        return mBinding.getRoot();
    }

    @Override
    public void initBundle(@NotNull Bundle bundle) {
        super.initBundle(bundle);
        mPage = bundle.getString(HomeFragment.ARG_OBJECT);
    }

    @Override
    public void onClick(View view) {

    }
}