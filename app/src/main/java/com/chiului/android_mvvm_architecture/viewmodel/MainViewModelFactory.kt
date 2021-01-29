package com.chiului.android_mvvm_architecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chiului.android_mvvm_architecture.data.MainRepository

/**
 * MainViewModel 的工厂方法$
 * @author    神经大条蕾弟
 * @date      2021/01/29 11:15
 */
class MainViewModelFactory(
        private val repository: MainRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}