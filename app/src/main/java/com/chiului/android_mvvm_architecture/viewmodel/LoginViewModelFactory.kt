package com.chiului.android_mvvm_architecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chiului.android_mvvm_architecture.data.LoginRepository

/**
 * LoginViewModel 的工厂方法$
 * @author    神经大条蕾弟
 * @date      2021/01/29 11:06
 */
class LoginViewModelFactory(
        private val repository: LoginRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }

}