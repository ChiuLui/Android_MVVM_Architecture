package com.chiului.android_mvvm_architecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chiului.android_mvvm_architecture.databinding.ItemFragmentBinding
import com.chiului.android_mvvm_architecture.dummy.DummyItemBean
import com.chiului.android_mvvm_architecture.utilities.toast

/**
 * 带刷新列表 Fragment 适配器$
 * @author    神经大条蕾弟
 * @date      2021/02/05 16:24
 */
class RefreshFragmentAdapter : ListAdapter<DummyItemBean, RefreshFragmentAdapter.ViewHolder>(RefreshFragmentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ItemFragmentBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(val binding: ItemFragmentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemBean: DummyItemBean) {
            // 绑定数据
            binding.bean = itemBean
            // 绑定点击事件
            binding.setClickListener {
                itemBean.getContent().toast(it.context)
            }
        }

    }

    /**
     * 比较 item 差异
     */
    class RefreshFragmentDiffCallback : DiffUtil.ItemCallback<DummyItemBean>() {
        override fun areItemsTheSame(oldItem: DummyItemBean, newItem: DummyItemBean): Boolean {
            // 是否是相同的 item
            return oldItem.getId() == newItem.getId()
        }

        override fun areContentsTheSame(oldItem: DummyItemBean, newItem: DummyItemBean): Boolean {
            // 是否是相同的内容
            return oldItem.getDetails() == newItem.getDetails()
        }
    }

}