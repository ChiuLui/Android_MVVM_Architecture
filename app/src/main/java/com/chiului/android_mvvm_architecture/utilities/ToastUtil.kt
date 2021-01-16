package com.chiului.android_mvvm_architecture.utilities

import android.content.Context
import android.widget.Toast

/**
 * Toast 工具类$
 * @author    神经大条蕾弟
 * @date      2021/01/17 00:42
 */
object ToastUtil {

    fun show(context: Context, resId: Int, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context, resId, duration).show()
    }

    fun show(context: Context, txt: CharSequence, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context, txt, duration).show()
    }

}

fun CharSequence.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) = ToastUtil.show(context, this, duration)

fun Int.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) = ToastUtil.show(context, this, duration)