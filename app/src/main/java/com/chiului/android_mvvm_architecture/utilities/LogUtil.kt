package com.chiului.android_mvvm_architecture.utilities

import android.util.Log
import com.chiului.android_mvvm_architecture.BuildConfig

/**
 * 日志工具类$
 * @author    神经大条蕾弟
 * @date      2021/01/16 23:19
 */
object LogUtil {

    /**
     * 是否打印日志
     */
    private val mIsShow = BuildConfig.DEBUG

    /**
     * 日志打印 tag
     */
    private const val mTag = LOG_TAG

    fun v(msg: String) {
        if (mIsShow) Log.v(mTag, msg)
    }

    fun d(msg: String) {
        if (mIsShow) Log.d(mTag, msg)
    }

    fun i(msg: String) {
        if (mIsShow) Log.i(mTag, msg)
    }

    fun w(msg: String) {
        if (mIsShow) Log.w(mTag, msg)
    }

    fun e(msg: String) {
        if (mIsShow) Log.e(mTag, msg)
    }

    fun a(msg: String) {
        if (mIsShow) Log.wtf(mTag, msg)
    }

    fun log(level: Int = Log.INFO, msg: String) {
        when(level){
            Log.VERBOSE -> v(msg)
            Log.DEBUG -> d(msg)
            Log.INFO -> i(msg)
            Log.WARN -> w(msg)
            Log.ERROR -> e(msg)
            Log.ASSERT -> a(msg)
        }
    }

}

fun String.log(level: Int) = LogUtil.log(level, this)

fun String.logV() = LogUtil.v(this)

fun String.logD() = LogUtil.d(this)

fun String.logI() = LogUtil.i(this)

fun String.logW() = LogUtil.w(this)

fun String.logE() = LogUtil.e(this)

fun String.logA() = LogUtil.a(this)