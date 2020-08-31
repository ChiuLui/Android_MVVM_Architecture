package com.chiului.android_mvvm_architecture.utilities;

import android.content.Context;

import com.chiului.android_mvvm_architecture.api.UserService;
import com.chiului.android_mvvm_architecture.data.AppDatabase;
import com.chiului.android_mvvm_architecture.data.HomeRepository;
import com.chiului.android_mvvm_architecture.data.UserRepository;
import com.chiului.android_mvvm_architecture.viewmodel.HomeViewModelFactory;
import com.chiului.android_mvvm_architecture.viewmodel.LoginViewModelFactory;

/**
 * 获取存储库工具类$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/23 17:05
 */
public final class InjectorUtils {

    private static final UserRepository getLoginRepository(Context context) {
        return UserRepository.getInstance(
                AppDatabase.getInstance(context.getApplicationContext()).userDao(),
                UserService.create()
        );
    }

    public static final LoginViewModelFactory provideLoginViewModelFactory(Context context) {
        return new LoginViewModelFactory(getLoginRepository(context));
    }

    private static final HomeRepository getHomeRepository(Context context) {
        return HomeRepository.getInstance(
                AppDatabase.getInstance(context.getApplicationContext()).userDao()
        );
    }

    public static final HomeViewModelFactory provideHomeViewModelFactory(Context context) {
        return new HomeViewModelFactory(getHomeRepository(context));
    }

}
