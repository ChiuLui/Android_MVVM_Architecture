package com.chiului.android_mvvm_architecture.data;

import com.chiului.android_mvvm_architecture.api.service.BaseApiService;
import com.chiului.android_mvvm_architecture.api.service.UserService;
import com.chiului.android_mvvm_architecture.bean.AppCacheBean;
import com.chiului.android_mvvm_architecture.bean.ApiResult;
import com.chiului.android_mvvm_architecture.utilities.AppCacheConstantsKt;
import com.google.gson.JsonObject;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;

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

    public void saveBeforeAccount(String account){
        mAppCacheDao.insertAppCache(new AppCacheBean(AppCacheConstantsKt.ACCOUNT_BEFORE, account));
    }

    public String getBeforeAccount(){
        AppCacheBean appCache = mAppCacheDao.getAppCache(AppCacheConstantsKt.ACCOUNT_BEFORE);
        if (appCache != null) {
            return appCache.getValue();
        } else {
            return "";
        }
    }

    public void saveToken(String token){
        AppCacheBean appCacheBean = new AppCacheBean(AppCacheConstantsKt.TOKEN, token);
        mAppCacheDao.insertAppCache(appCacheBean);
    }

    public Single<ApiResult<String>> getToken(String account, String psw){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName", account);
        jsonObject.addProperty("password", psw);
        RequestBody requestBody = BaseApiService.getJsonRequestBody(jsonObject.toString());
        Single<ApiResult<String>> single = mUserService.login(requestBody);
        return single;
    }

}
