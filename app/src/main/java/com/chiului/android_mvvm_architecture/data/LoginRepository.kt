package com.chiului.android_mvvm_architecture.data

import com.chiului.android_mvvm_architecture.api.service.BaseApiService
import com.chiului.android_mvvm_architecture.api.service.UserService
import com.chiului.android_mvvm_architecture.bean.ApiResult
import com.chiului.android_mvvm_architecture.bean.AppCacheBean
import com.chiului.android_mvvm_architecture.utilities.ACCOUNT_BEFORE
import com.chiului.android_mvvm_architecture.utilities.TOKEN
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single

/**
 * 用户存储库$
 * @author    神经大条蕾弟
 * @date      2021/01/29 16:58
 */
class LoginRepository private constructor(
        private val appCacheDao: AppCacheDao,
        private val userService: UserService
) {

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: LoginRepository? = null

        fun getInstance(appCacheDao: AppCacheDao, userService: UserService) =
                instance ?: synchronized(this) {
                    instance ?: LoginRepository(appCacheDao, userService).also { instance = it }
                }
    }

    /**
     * 保存上次登录账号
     * @param account String 登录成功的账号
     */
    fun saveBeforeAccount(account: String) {
        appCacheDao.insertAppCache(AppCacheBean(ACCOUNT_BEFORE, account))
    }

    /**
     * 获取上次登录的账号
     * @return String 上次登录的账号
     */
    fun getBeforeAccount(): String {
        val appCache: AppCacheBean? = appCacheDao.getAppCache(ACCOUNT_BEFORE)
        return appCache?.value ?: ""
    }

    /**
     * 保存登录成功的 token
     * @param token String token
     */
    fun saveToken(token: String) {
        val appCacheBean = AppCacheBean(TOKEN, token)
        appCacheDao.insertAppCache(appCacheBean)
    }

    /**
     * 从后台获取 token
     * @param account String?
     * @param psw String?
     * @return Single<ApiResult<String>>
     */
    fun getRemoteToken(account: String?, psw: String?): Single<ApiResult<String>> {
        val jsonObject = JsonObject()
        jsonObject.addProperty("userName", account)
        jsonObject.addProperty("password", psw)
        val requestBody = BaseApiService.getJsonRequestBody(jsonObject.toString())
        return userService.login(requestBody)
    }

    /**
     * 获取本地数据库 token
     */
    fun getLocalToken(): AppCacheBean? {
        return appCacheDao.getAppCache(TOKEN)
    }

    /**
     * 删除本地 token
     */
    fun deleteToken() {
        val tokenCache: AppCacheBean? = getLocalToken()
        if (tokenCache != null) {
            appCacheDao.deleteAppCache(tokenCache)
        }
    }

}