package com.chiului.android_mvvm_architecture.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.chiului.android_mvvm_architecture.R
import com.chiului.android_mvvm_architecture.base.BaseActivity
import com.chiului.android_mvvm_architecture.databinding.ActivityMainBinding
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModel

/**
 * 主 Activity$
 * @author    神经大条蕾弟
 * @date      2021/02/02 17:30
 */
class MainActivity : BaseActivity() {

    override fun setContentViewID(): Int {
        return R.layout.activity_main
    }

    override fun initViewModel() {
        var binding = getDataBinding(ActivityMainBinding::class.java)
        var viewModel = ViewModelProvider(this, InjectorUtils.provideMainViewModelFactory(this)).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
    }

    override fun onCreating(savedInstanceState: Bundle?) {}

}