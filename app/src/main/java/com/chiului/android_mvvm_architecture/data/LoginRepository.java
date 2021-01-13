package com.chiului.android_mvvm_architecture.data;

import com.chiului.android_mvvm_architecture.api.UserService;
import com.chiului.android_mvvm_architecture.bean.AppCacheBean;
import com.chiului.android_mvvm_architecture.utilities.AppCacheConstantsKt;

/**
 * 用户储存库$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/23 16:44
 */
public class LoginRepository {

    private LoginRepository(){}

    /**
     * Room 的数据库表对象
     */
    private static AppCacheDao mAppCacheDao;

    /**
     * Retrofit 的接口
     */
    private static UserService mUserService;

    public static LoginRepository getInstance(AppCacheDao appCacheDao, UserService userService){
        mAppCacheDao = appCacheDao;
        mUserService = userService;
        return SingletonHolder.Instance;
    }

    private static class SingletonHolder{
        private static final LoginRepository Instance = new LoginRepository();
    }

    public void saveAccount(String account){
        mAppCacheDao.insertAppCache(new AppCacheBean(AppCacheConstantsKt.ACCOUNT_BEFORE, account));
    }

    public String getAccount(){
        AppCacheBean appCache = mAppCacheDao.getAppCache(AppCacheConstantsKt.ACCOUNT_BEFORE);
        if (appCache != null) {
            return appCache.getAppValue();
        } else {
            return "";
        }
    }

}
