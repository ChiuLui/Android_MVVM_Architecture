package com.chiului.android_mvvm_architecture.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.chiului.android_mvvm_architecture.R
import com.chiului.android_mvvm_architecture.adapter.ListSampleAdapter
import com.chiului.android_mvvm_architecture.base.BaseFragment
import com.chiului.android_mvvm_architecture.databinding.FragmentListSampleBinding
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * 列表示例 Fragment$
 * @author    神经大条蕾弟
 * @date      2021/02/05 15:22
 */
class ListSampleFragment : BaseFragment() {

    private lateinit var binding: FragmentListSampleBinding
    private val viewModel: MainViewModel by viewModels {
        InjectorUtils.provideMainViewModelFactory(requireActivity())
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: ListSampleFragment? = null

        fun getInstance(): ListSampleFragment {
            return instance ?: synchronized(this) {
                instance ?: ListSampleFragment().also { instance = it }
            }
        }

    }

    override fun initViewModel(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_sample, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        var viewPager = binding.viewPager
        var tabLayout = binding.tabLayout

        // 初始化适配器
        val adapter = ListSampleAdapter(this)

        // 给 ViewPager 设置适配器
        viewPager.adapter = adapter

        // 把 TabLayout 与 ViewPager 绑定
        TabLayoutMediator(tabLayout, viewPager) { tab: TabLayout.Tab, position: Int ->
            tab.text = getTabText(position)
        }.attach()
    }

    /**
     * 根据 Position 获取 TabLayout 文字
     * @param position
     * @return
     */
    private fun getTabText(position: Int): String {
        return when (position) {
            0 -> "普通列表"
            1 -> "下拉刷新列表"
            2 -> "刷新和加载列表"
            else -> "刷新和加载列表"
        }
    }

}