package com.chiului.android_mvvm_architecture.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.base.BaseFragment;
import com.chiului.android_mvvm_architecture.databinding.HomeFragmentBinding;
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils;
import com.chiului.android_mvvm_architecture.utilities.ToastUtil;
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;

/**
 * 首页碎片
 * @author 神经大条蕾弟
 * @date   2020/08/04 17:29
 */
public class HomeFragment extends BaseFragment {

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
    }

    @Override
    public View onCreating(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initTitleBar();

        mBinding.txPage.setText(mPage);

        return mBinding.getRoot();
    }

    @Override
    public void initBundle(@NotNull Bundle bundle) {
        super.initBundle(bundle);
        mPage = bundle.getString(HomeFragment.ARG_OBJECT);
    }

    /**
     * 初始化标题栏
     */
    private void initTitleBar() {
        // 将 toolbar 设置成页面的 ActionBar（初始化）
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        Toolbar toolbar = mBinding.toolbar;
        activity.setSupportActionBar(toolbar);
        // 启用回退按钮
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 设置回退按钮监听
        toolbar.setNavigationOnClickListener(view -> getActivity().finish());

        // 开启 ActionBar 菜单
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // 设置菜单布局
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 监听菜单
            switch (item.getItemId()){
                case R.id.menu_more:
                    ToastUtil.INSTANCE.show(getActivity(), item.getTitle(), Toast.LENGTH_SHORT);
                    return true;
                default:
            }
        return super.onOptionsItemSelected(item);
    }
}