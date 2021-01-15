package com.chiului.android_mvvm_architecture;

import android.app.Application;
import android.content.Context;

/**
 * Application$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/23 11:57
 */
public class MyApplication extends Application {

    /**
     * 全局的context
     */
    public static Context ApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationContext = getApplicationContext();
    }
}
