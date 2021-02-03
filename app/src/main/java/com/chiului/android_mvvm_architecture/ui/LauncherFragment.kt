package com.chiului.android_mvvm_architecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.chiului.android_mvvm_architecture.R
import com.chiului.android_mvvm_architecture.base.BaseNavFragment
import com.chiului.android_mvvm_architecture.databinding.FragmentLauncherBinding
import com.chiului.android_mvvm_architecture.utilities.MODE_GUEST
import kotlinx.coroutines.*

/**
 * 启动页
 * @author 神经大条蕾弟
 * @date   2021/01/13 3:32 PM
 */
class LauncherFragment : BaseNavFragment() {

    private lateinit var binding: FragmentLauncherBinding

    override fun initViewModel(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_launcher, container, false)
        return binding.root
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        GlobalScope.launch(Dispatchers.Main) {
            startDelay()
            if (MODE_GUEST) {
                // 游客模式
                toMainFragment();
            } else {
                // 非游客模式
                toLoginFragment()
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