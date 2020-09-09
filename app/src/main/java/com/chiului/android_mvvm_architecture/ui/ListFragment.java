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

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.adapter.ListFragmentRecyclerViewAdapter;
import com.chiului.android_mvvm_architecture.base.BaseFragment;
import com.chiului.android_mvvm_architecture.databinding.FragmentListBinding;
import com.chiului.android_mvvm_architecture.dummy.DummyContent;

import org.jetbrains.annotations.NotNull;

/**
 * RecyclerView 的示例
 * @author 神经大条蕾弟
 * @date   2020/09/07 16:35
 */
public class ListFragment extends BaseFragment {

    private FragmentListBinding mBinding;

    // Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListFragment() {}

    // Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListFragment newInstance(int columnCount) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setContentViewID() {
        return R.layout.fragment_list;
    }

    @Override
    public void initViewModel(LayoutInflater inflater, int layoutId, @Nullable ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        mBinding.setLifecycleOwner(this);
    }

    @Override
    public View onCreating(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = mBinding.getRoot();

        // Set the adapter
        if (rootView instanceof RecyclerView) {
            Context context = rootView.getContext();
            RecyclerView recyclerView = (RecyclerView) rootView;

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
            recyclerView.setAdapter(new ListFragmentRecyclerViewAdapter(DummyContent.ITEMS));
        }

        return rootView;
    }

    @Override
    public void initBundle(@NotNull Bundle bundle) {
        super.initBundle(bundle);
        if (getArguments() != null) {
            mColumnCount = bundle.getInt(ARG_COLUMN_COUNT);
        }
    }
}