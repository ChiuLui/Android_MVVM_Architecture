package com.chiului.android_mvvm_architecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.chiului.android_mvvm_architecture.R
import com.chiului.android_mvvm_architecture.adapter.MainAdapter
import com.chiului.android_mvvm_architecture.base.BaseNavFragment
import com.chiului.android_mvvm_architecture.databinding.FragmentMainBinding
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils
import com.chiului.android_mvvm_architecture.utilities.toast
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator

/**
 * 主页碎片$
 * @author    神经大条蕾弟
 * @date      2021/02/03 10:15
 */
class MainFragment : BaseNavFragment() {

    lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels {
        InjectorUtils.provideMainViewModelFactory(requireActivity())
    }

    override fun initViewModel(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this

        viewModel.toast.observe(this, {
            it.toast(requireContext())
        })

        viewModel.userInfoBean.observe(this, {
            viewModel.toast.value = it.nickName
        })

        return binding.root
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        isTopStack = true
        initFragments()
        viewModel.initUserInfo()
    }

    private fun initFragments() {
        // 初始化适配器
        val mainAdapter = MainAdapter(this)

        // 给 ViewPager 设置适配器
        binding.viewPager.adapter = mainAdapter

        // 把 TabLayout 与 ViewPager 绑定
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.setText(getTabText(position))
        }.attach()
    }

    /**
     * 根据 Position 获取 TabLayout 图标
     * @param position
     * @return
     */
    private fun getTabIcon(position: Int): Int {
        val resId = R.drawable.garden_tab_selector
        when (position) {
            0 -> {
            }
            1 -> {
            }
            2 -> {
            }
            3 -> {
            }
        }
        return resId
    }

    /**
     * 根据 Position 获取 TabLayout 文字
     * @param position
     * @return
     */
    private fun getTabText(position: Int): String {
        val tab = "Module" + (position + 1)
        when (position) {
            0 -> {
            }
            1 -> {
            }
            2 -> {
            }
            3 -> {
            }
        }
        return tab
    }

}