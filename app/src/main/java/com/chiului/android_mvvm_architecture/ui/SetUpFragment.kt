package com.chiului.android_mvvm_architecture.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chiului.android_mvvm_architecture.R
import com.chiului.android_mvvm_architecture.base.BaseNavFragment
import com.chiului.android_mvvm_architecture.databinding.FragmentSetUpBinding
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils
import com.chiului.android_mvvm_architecture.viewmodel.LoginViewModel

/**
 * 设置页面
 * @author 神经大条蕾弟
 * @date   2021/02/19 9:42 AM
 */
class SetUpFragment : BaseNavFragment(), View.OnClickListener {

    private lateinit var binding: FragmentSetUpBinding
    private val viewModel: LoginViewModel by viewModels {
        InjectorUtils.provideLoginViewModelFactory(requireActivity())
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: SetUpFragment? = null

        fun getInstance(): SetUpFragment {
            return instance ?: synchronized(this) {
                instance ?: SetUpFragment().also { instance = it }
            }
        }

    }

    override fun initViewModel(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_up, container, false)
        binding.lifecycleOwner = this
        binding.clickListener = this

        return binding.root
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.image_back -> navUp()
            R.id.btn_logout -> {
                // 退出登录
                viewModel.logout()
                // 跳转登录页
                var toLoginFragment = SetUpFragmentDirections.actionSetUpFragmentToLoginFragment()
                findNavController().navigate(toLoginFragment)
            }
        }
    }

}