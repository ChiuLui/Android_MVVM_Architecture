package com.chiului.android_mvvm_architecture.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.chiului.android_mvvm_architecture.dummy.DummyItemBean;
import com.chiului.android_mvvm_architecture.databinding.ItemFragmentBinding;
import com.chiului.android_mvvm_architecture.utilities.ToastUtil;

import java.util.List;

/**
 * 列表 Fragment 适配器
 * @author 神经大条蕾弟
 * @date   2020/09/09 09:12
 */
public class ListFragmentRecyclerViewAdapter extends ListAdapter<DummyItemBean, ListFragmentRecyclerViewAdapter.ViewHolder> {

    private List<DummyItemBean> mDataList;

    public ListFragmentRecyclerViewAdapter(List<DummyItemBean> dataList){
        super(new ListFragmentDiffCallback());
        mDataList = dataList;
    }

    public ListFragmentRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<DummyItemBean> diffCallback) {
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
        holder.bind(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemFragmentBinding binding;

        public ViewHolder(ItemFragmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position){
            DummyItemBean itemBean = getItem(position);
            // 绑定数据
            binding.setBean(itemBean);
            // 绑定点击事件
            binding.setClickListener(view -> {
                ToastUtil.INSTANCE.show(binding.getRoot().getContext(), itemBean.getContent(), Toast.LENGTH_SHORT);
            });
        }

    }

    /**
     * 插入 Item
     * @param position
     * @param itemBean
     */
    public void addItem(int position, DummyItemBean itemBean) {
        // 插入数据
        mDataList.add(position, itemBean);
        // 显示插入动画
        notifyItemInserted(position);
        // 重新计算大小(不加 RecyclerView 可能会数组越界)
        notifyItemRangeChanged(position, getItemCount());
    }

    /**
     * 删除 Item
     * @param position
     */
    public void removeItem(int position) {
        // 删除数据
        mDataList.remove(position);
        // 显示删除动画
        notifyItemRemoved(position);
        // 重新计算大小(不加 RecyclerView 可能会数组越界)
        notifyItemRangeChanged(position, getItemCount());
    }

    /**
     * 比较 item 差异
     */
    public static class ListFragmentDiffCallback extends DiffUtil.ItemCallback<DummyItemBean> {

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