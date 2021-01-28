package com.chiului.android_mvvm_architecture.utilities

import android.content.Context
import com.chiului.android_mvvm_architecture.api.service.UserService
import com.chiului.android_mvvm_architecture.data.AppDatabase
import com.chiului.android_mvvm_architecture.data.LoginRepository
import com.chiului.android_mvvm_architecture.data.MainRepository
import com.chiului.android_mvvm_architecture.viewmodel.LoginViewModelFactory
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModelFactory

/**
 * 获取 Repository 工具类$
 * @author    神经大条蕾弟
 * @date      2021/01/28 16:43
 */
object InjectorUtils {

    private fun getLoginRepository(context: Context): LoginRepository {
        return LoginRepository.getInstance(
                AppDatabase.getInstance(context.applicationContext).appCacheDao(),
                UserService.create()
        )
    }

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {
        return LoginViewModelFactory(getLoginRepository(context))
    }

    private fun getMainRepository(context: Context): MainRepository {
        return MainRepository.getInstance(
                AppDatabase.getInstance(context.applicationContext).userDao(),
                AppDatabase.getInstance(context.applicationContext).appCacheDao(),
                UserService.create()
        )
    }

    fun provideMainViewModelFactory(context: Context): MainViewModelFactory {
        return MainViewModelFactory(getMainRepository(context))
    }

}