package com.chiului.android_mvvm_architecture.data;

import com.chiului.android_mvvm_architecture.api.UserService;
import com.chiului.android_mvvm_architecture.bean.AppCacheBean;
import com.chiului.android_mvvm_architecture.bean.BaseBean;
import com.chiului.android_mvvm_architecture.utilities.AppCacheConstantsKt;
import com.google.gson.JsonObject;

import io.reactivex.rxjava3.core.Single;
import okhttp3.MediaType;
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

    public Single<BaseBean<String>> getToken(String account, String psw){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName", account);
        jsonObject.addProperty("password", psw);
        String json = jsonObject.toString();
        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=UTF-8"));
        Single<BaseBean<String>> single = mUserService.login(requestBody);
        return single;
    }

}
