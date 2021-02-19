package com.chiului.android_mvvm_architecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chiului.android_mvvm_architecture.R
import com.chiului.android_mvvm_architecture.base.BaseNavFragment
import com.chiului.android_mvvm_architecture.databinding.FragmentLauncherBinding
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils
import com.chiului.android_mvvm_architecture.utilities.MODE_GUEST
import com.chiului.android_mvvm_architecture.viewmodel.LoginViewModel
import kotlinx.coroutines.*

/**
 * 启动页
 * @author 神经大条蕾弟
 * @date   2021/01/13 3:32 PM
 */
class LauncherFragment : BaseNavFragment() {

    private lateinit var binding: FragmentLauncherBinding
    private val viewModel: LoginViewModel by viewModels {
        InjectorUtils.provideLoginViewModelFactory(requireActivity())
    }

    override fun initViewModel(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_launcher, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        GlobalScope.launch(Dispatchers.Main) {
            startDelay()

            when {
                // 游客模式
                MODE_GUEST -> toMainFragment()
                // 已登录
                viewModel.isLogin() -> toMainFragment()
                // 非游客模式 或 未登录
                else -> toLoginFragment()
            }

        }
    }

    private fun toMainFragment() {
        var toMainFragment = LauncherFragmentDirections.actionLauncherFragmentToMainFragment()
        findNavController().navigate(toMainFragment)
    }

    private fun toLoginFragment() {
        var toLoginFragment = LauncherFragmentDirections.actionLauncherFragmentToLoginFragment()
        findNavController().navigate(toLoginFragment)
    }

    private suspend fun startDelay() {
        withContext(Dispatchers.IO) {
            delay(1000L)
        }
    }

}