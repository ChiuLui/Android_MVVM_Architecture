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

    fun saveBeforeAccount(account: String) {
        appCacheDao.insertAppCache(AppCacheBean(ACCOUNT_BEFORE, account))
    }

    fun getBeforeAccount(): String {
        val appCache: AppCacheBean? = appCacheDao.getAppCache(ACCOUNT_BEFORE)
        return if (appCache == null) "" else appCache.value
    }

    fun saveToken(token: String) {
        val appCacheBean = AppCacheBean(TOKEN, token)
        appCacheDao.insertAppCache(appCacheBean)
    }

    fun getToken(account: String?, psw: String?): Single<ApiResult<String>> {
        val jsonObject = JsonObject()
        jsonObject.addProperty("userName", account)
        jsonObject.addProperty("password", psw)
        val requestBody = BaseApiService.getJsonRequestBody(jsonObject.toString())
        return userService.login(requestBody)
    }

}