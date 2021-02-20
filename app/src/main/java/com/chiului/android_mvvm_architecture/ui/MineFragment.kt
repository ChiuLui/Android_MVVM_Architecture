package com.chiului.android_mvvm_architecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chiului.android_mvvm_architecture.R
import com.chiului.android_mvvm_architecture.base.BaseFragment
import com.chiului.android_mvvm_architecture.databinding.FragmentMineBinding
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils
import com.chiului.android_mvvm_architecture.utilities.load
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModel

/**
 * 我的页面
 * @author 神经大条蕾弟
 * @date   2021/02/18 2:18 PM
 */
class MineFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentMineBinding
    private val viewModel: MainViewModel by viewModels {
        InjectorUtils.provideMainViewModelFactory(requireActivity())
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: MineFragment? = null

        fun getInstance(): MineFragment {
            return instance ?: synchronized(this) {
                instance ?: MineFragment().also { instance = it }
            }
        }

    }

    override fun initViewModel(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false)
        binding.lifecycleOwner = this

        binding.clickListener = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        viewModel.getUserInfo()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.image_set_up -> {
                if (viewModel.isLogin()) {
                    // 跳转设置页面
                    var toSetUpFragment = MainFragmentDirections.actionMainFragmentToSetUpFragment()
                    findNavController().navigate(toSetUpFragment)
                } else {
                    // 登录页面
                    var toLoginFragment = MainFragmentDirections.actionMainFragmentToLoginFragment()
                    findNavController().navigate(toLoginFragment)
                }
            }
        }
    }
}