package com.chiului.android_mvvm_architecture.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.adapter.RefreshFragmentRecyclerViewAdapter;
import com.chiului.android_mvvm_architecture.base.BaseFragment;
import com.chiului.android_mvvm_architecture.bean.DummyItemBean;
import com.chiului.android_mvvm_architecture.databinding.FragmentRefreshBinding;
import com.chiului.android_mvvm_architecture.dummy.DummyContent;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 带刷新的列表示例
 * @author 神经大条蕾弟
 * @date   2020/09/09 15:16
 */
public class RefreshFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentRefreshBinding mBinding;

    // Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // Customize parameters
    private int mColumnCount = 1;
    private SwipeRefreshLayout mRefresh;
    private RefreshFragmentRecyclerViewAdapter mAdapter;
    private List<DummyItemBean> mDataList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RefreshFragment() {}

    public static RefreshFragment newInstance(int columnCount) {
        RefreshFragment fragment = new RefreshFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setContentViewID() {
        return R.layout.fragment_refresh;
    }

    @Override
    public void initViewModel(LayoutInflater inflater, int layoutId, @Nullable ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        mBinding.setLifecycleOwner(this);
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

        refresh();
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
        mAdapter = new RefreshFragmentRecyclerViewAdapter();
        recyclerView.setAdapter(mAdapter);
        // 设置数据
        mAdapter.submitList(DummyContent.ITEMS);
    }

    private void refresh() {
        Observable.timer(1, TimeUnit.SECONDS)
                .take(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        // 模拟改变数据
                        mDataList = new ArrayList<>();
                        mDataList.addAll(DummyContent.ITEMS);
                        Collections.shuffle(mDataList);
                        // 改变列表数据（提交的数据不能与原数据同一内存地址，否则将不更新）
                        mAdapter.submitList(mDataList);
                        // 关闭刷新
                        mRefresh.setRefreshing(false);
                    }
                });
    }

}