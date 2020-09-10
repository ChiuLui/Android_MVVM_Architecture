package com.chiului.android_mvvm_architecture.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.chiului.android_mvvm_architecture.bean.DummyItemBean;
import com.chiului.android_mvvm_architecture.databinding.ItemFragmentBinding;

/**
 * 刷新列表 Fragment 适配器
 * @author 神经大条蕾弟
 * @date   2020/09/09 09:12
 */
public class RefreshFragmentRecyclerViewAdapter extends ListAdapter<DummyItemBean, RefreshFragmentRecyclerViewAdapter.ViewHolder> {

    public RefreshFragmentRecyclerViewAdapter(){
        super(new RefreshFragmentDiffCallback());
    }

    public RefreshFragmentRecyclerViewAdapter(@NonNull RefreshFragmentDiffCallback diffCallback) {
        super(diffCallback);
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
        DummyItemBean dummyItemBean = getItem(position);
        holder.bind(dummyItemBean);
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

    /**
     * 比较 item 差异
     */
    public static class RefreshFragmentDiffCallback extends DiffUtil.ItemCallback<DummyItemBean> {

        @Override
        public boolean areItemsTheSame(@NonNull DummyItemBean oldItem, @NonNull DummyItemBean newItem) {
            // 是否是相同的 item
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull DummyItemBean oldItem, @NonNull DummyItemBean newItem) {
            // 是否是相同的内容
            return oldItem.getDetails().equals(newItem.getDetails());
        }
    }

}