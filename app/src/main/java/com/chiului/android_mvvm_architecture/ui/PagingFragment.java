package com.chiului.android_mvvm_architecture.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.adapter.PagingFragmentRecyclerViewAdapter;
import com.chiului.android_mvvm_architecture.base.BaseFragment;
import com.chiului.android_mvvm_architecture.bean.DummyItemBean;
import com.chiului.android_mvvm_architecture.databinding.FragmentPagingBinding;
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils;
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 带刷新与分页加载的列表示例
 * @author 神经大条蕾弟
 * @date   2020/09/09 15:16
 */
public class PagingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    // Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // Customize parameters
    private int mColumnCount = 1;

    private MainViewModel mViewModel;
    private FragmentPagingBinding mBinding;
    private SwipeRefreshLayout mRefresh;
    private PagingFragmentRecyclerViewAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PagingFragment() {}

    public static PagingFragment newInstance(int columnCount) {
        PagingFragment fragment = new PagingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setContentViewID() {
        return R.layout.fragment_paging;
    }

    @Override
    public void initViewModel(LayoutInflater inflater, int layoutId, @Nullable ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        mViewModel = new ViewModelProvider(this, InjectorUtils.provideMainViewModelFactory(getActivity())).get(MainViewModel.class);
        mBinding.setLifecycleOwner(this);

        mViewModel.getPagingDate().observe(this, list -> {
            // 改变列表数据（提交的数据不能与原数据同一内存地址，否则将不更新）
            mAdapter.submitList(list);
            // 关闭刷新
            mRefresh.setRefreshing(false);
        });
    }

    @Override
    public View onCreating(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = mBinding.getRoot();

        initSwipeRefresh();
        initRecyclerView();

        return rootView;
    }

    @Override
    public void initBundle(@NotNull Bundle bundle) {
        super.initBundle(bundle);
        if (getArguments() != null) {
            mColumnCount = bundle.getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public void onRefresh() {
        // 下拉刷新
        mViewModel.getPagingFragmentDate();
    }

    private void initSwipeRefresh() {
        mRefresh = mBinding.refresh;
        mRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        mRefresh.setOnRefreshListener(this);
    }

    private void initRecyclerView() {
        // Set the adapter
        Context context = getActivity();
        RecyclerView recyclerView = mBinding.recycler;

        // 可在代码里动态设置 LayoutManager。
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        // 也可以在 XML 里面设置 LayoutManager。
        // app:layoutManager="GridLayoutManager"
        // app:spanCount="2"

        // 绑定适配器
        mAdapter = new PagingFragmentRecyclerViewAdapter();
        recyclerView.setAdapter(mAdapter);
        // 获取数据
        mViewModel.getPagingFragmentDate();
    }

}