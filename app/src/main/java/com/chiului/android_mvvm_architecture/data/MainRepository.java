package com.chiului.android_mvvm_architecture.data;


import com.chiului.android_mvvm_architecture.api.UserService;
import com.chiului.android_mvvm_architecture.bean.ApiResult;
import com.chiului.android_mvvm_architecture.bean.AppCacheBean;
import com.chiului.android_mvvm_architecture.bean.UserBean;
import com.chiului.android_mvvm_architecture.utilities.AppCacheConstantsKt;

import io.reactivex.rxjava3.core.Single;

/**
 * 首页碎片数据储存库$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/23 16:44
 */
public class MainRepository {

    /**
     * Room 的数据库表对象
     */
    private static AppCacheDao mAppCacheDao;

    /**
     * Room 的数据库表对象
     */
    private static UserDao mUserDao;

    /**
     * retrofit 对象
     */
    private static UserService mUserService;

    private MainRepository(){}

    public static MainRepository getInstance(UserDao userDao, AppCacheDao appCacheDao, UserService userService){
        mUserDao = userDao;
        mAppCacheDao = appCacheDao;
        mUserService = userService;
        return SingletonHolder.Instance;
    }

    private static class SingletonHolder{
        private static final MainRepository Instance = new MainRepository();
    }

    /**
     * 把用户信息保存到 Room
     * @param bean
     */
    public void saveUserInfo(UserBean bean){
        if (bean != null) {
            AppCacheBean tokenCache = mAppCacheDao.getAppCache(AppCacheConstantsKt.TOKEN);
            bean.setToken(tokenCache.getValue());
            mUserDao.insertUser(bean);
        }
    }

    /**
     * 从本地数据库获取用户信息
     * @return
     */
    public UserBean getLocalUserInfo(){
        AppCacheBean tokenCache = mAppCacheDao.getAppCache(AppCacheConstantsKt.TOKEN);
        UserBean user = mUserDao.getUser(tokenCache.getValue());
        return user;
    }

    /**
     * 请求接口获取用户信息然后保存到本地数据库
     * @return
     */
    public Single<ApiResult<UserBean>> getRemoteUserInfo(){
        return mUserService.getUserInfo();
    }

}
