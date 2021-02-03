package com.chiului.android_mvvm_architecture.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
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

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels{
        InjectorUtils.provideMainViewModelFactory(this)
    }

    override fun initViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    override fun onCreating(savedInstanceState: Bundle?) {}

}