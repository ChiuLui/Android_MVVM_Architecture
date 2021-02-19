package com.chiului.android_mvvm_architecture.data

import com.chiului.android_mvvm_architecture.api.service.UserService
import com.chiului.android_mvvm_architecture.bean.ApiResult
import com.chiului.android_mvvm_architecture.bean.AppCacheBean
import com.chiului.android_mvvm_architecture.bean.UserBean
import com.chiului.android_mvvm_architecture.utilities.TOKEN
import io.reactivex.rxjava3.core.Single

/**
 * 主页资料库$
 * @author    神经大条蕾弟
 * @date      2021/02/01 09:58
 */
class MainRepository private constructor(val appCacheDao: AppCacheDao, val userDao: UserDao, val userService: UserService) {

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(appCacheDao: AppCacheDao, userDao: UserDao, userService: UserService) =
                instance ?: synchronized(this) {
                    instance
                            ?: MainRepository(appCacheDao, userDao, userService).also { instance = it }
                }
    }

    /**
     * 把用户信息保存到 Room
     * @param bean
     */
    fun saveUserInfo(bean: UserBean) {
        val tokenCache: AppCacheBean? = appCacheDao.getAppCache(TOKEN)
        if (tokenCache != null) {
            bean.token = tokenCache.value
            userDao.insertUser(bean)
        }
    }

    /**
     * 从本地数据库获取用户信息
     * @return
     */
    fun getLocalUserInfo(): UserBean? {
        val tokenCache: AppCacheBean? = getLocalToken()
        return userDao.getUser(if (tokenCache == null) "" else tokenCache.value)
    }

    /**
     * 请求接口获取用户信息然后保存到本地数据库
     * @return
     */
    fun getRemoteUserInfo(): Single<ApiResult<UserBean>> {
        return userService.getUserInfo()
    }

    /**
     * 获取本地数据库 token
     */
    fun getLocalToken(): AppCacheBean? {
        return appCacheDao.getAppCache(TOKEN)
    }

}