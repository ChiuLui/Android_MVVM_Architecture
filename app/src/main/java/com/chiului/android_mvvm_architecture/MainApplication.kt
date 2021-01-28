package com.chiului.android_mvvm_architecture

import android.app.Application
import android.content.Context

/**
 * Application$
 * @author    神经大条蕾弟
 * @date      2021/01/28 16:55
 */
class MainApplication : Application() {

    companion object  {
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}