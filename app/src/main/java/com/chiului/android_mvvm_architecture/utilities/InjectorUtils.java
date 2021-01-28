package com.chiului.android_mvvm_architecture.utilities;

import android.content.Context;

import com.chiului.android_mvvm_architecture.api.service.UserService;
import com.chiului.android_mvvm_architecture.data.AppDatabase;
import com.chiului.android_mvvm_architecture.data.MainRepository;
import com.chiului.android_mvvm_architecture.data.LoginRepository;
import com.chiului.android_mvvm_architecture.viewmodel.LoginViewModelFactory;
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModelFactory;

/**
 * 获取存储库工具类$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/23 17:05
 */
public final class InjectorUtils {

    private static final LoginRepository getLoginRepository(Context context) {
        return LoginRepository.getInstance(
                AppDatabase.getInstance(context.getApplicationContext()).appCacheDao(),
                UserService.create()
        );
    }

    public static final LoginViewModelFactory provideLoginViewModelFactory(Context context) {
        return new LoginViewModelFactory(getLoginRepository(context));
    }

    private static final MainRepository getMainRepository(Context context) {
        return MainRepository.getInstance(
                AppDatabase.getInstance(context.getApplicationContext()).userDao(),
                AppDatabase.getInstance(context.getApplicationContext()).appCacheDao(),
                UserService.create()
        );
    }

    public static final MainViewModelFactory provideMainViewModelFactory(Context context) {
        return new MainViewModelFactory(getMainRepository(context));
    }

}
