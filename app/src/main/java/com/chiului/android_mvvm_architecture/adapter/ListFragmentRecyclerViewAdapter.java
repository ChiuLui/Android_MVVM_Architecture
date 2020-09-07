package com.chiului.android_mvvm_architecture.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.chiului.android_mvvm_architecture.bean.DummyItemBean;
import com.chiului.android_mvvm_architecture.databinding.ItemFragmentBinding;

import java.util.List;

/**
 * 列表 Fragment 适配器
 */
public class ListFragmentRecyclerViewAdapter extends RecyclerView.Adapter<ListFragmentRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItemBean> mValues;

    public ListFragmentRecyclerViewAdapter(List<DummyItemBean> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemFragmentBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false)
        );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DummyItemBean dummyItemBean = mValues.get(position);
        holder.bind(dummyItemBean);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemFragmentBinding binding;

        public ViewHolder(ItemFragmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DummyItemBean itemBean){
            // 绑定数据
            binding.setBean(itemBean);
            // 绑定点击事件
            binding.setClickListener(view -> {
                Toast.makeText(binding.getRoot().getContext(), itemBean.getContent(), Toast.LENGTH_SHORT).show();
            });
        }

    }
}