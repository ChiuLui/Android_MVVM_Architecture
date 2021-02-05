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
 * 普通列表示例适配器$
 * @author    神经大条蕾弟
 * @date      2021/02/05 10:56
 */
class ListFragmentAdapter constructor(private val dataList: MutableList<DummyItemBean>) : ListAdapter<DummyItemBean, ListFragmentAdapter.ViewHolder>(
        ListFragmentDiffCallback()
) {

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

    /**
     * 插入 Item
     * @param position
     * @param itemBean
     */
    fun addItem(position: Int, itemBean: DummyItemBean) {
        // 插入数据
        dataList.add(position, itemBean)
        // 显示插入动画
        notifyItemInserted(position)
        // 重新计算大小(不加 RecyclerView 可能会数组越界)
        notifyItemRangeChanged(position, itemCount)
    }

    /**
     * 删除 Item
     * @param position
     */
    fun removeItem(position: Int) {
        // 删除数据
        dataList.removeAt(position)
        // 显示删除动画
        notifyItemRemoved(position)
        // 重新计算大小(不加 RecyclerView 可能会数组越界)
        notifyItemRangeChanged(position, itemCount)
    }

    class ViewHolder(val binding: ItemFragmentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemBean: DummyItemBean) {
            // 绑定数据
            binding.bean = itemBean
            // 绑定点击事件
            binding.setClickListener {
                itemBean.content.toast(binding.root.context)
            }
        }

    }

    /**
     * 比较 item 差异
     */
    class ListFragmentDiffCallback : DiffUtil.ItemCallback<DummyItemBean>() {
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