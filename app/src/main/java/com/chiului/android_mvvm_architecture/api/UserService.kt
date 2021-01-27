package com.chiului.android_mvvm_architecture.api

import com.chiului.android_mvvm_architecture.bean.ApiResult
import com.chiului.android_mvvm_architecture.bean.UserBean
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 用户模块网络请求$
 * @author    神经大条蕾弟
 * @date      2021/01/14 19:43
 */
interface UserService {

    companion object {

        const val API_LOGIN = "api/base/login/"

        const val API_USER_INFO = "api/base/memberInfo/"

        @JvmStatic
        fun create(): UserService {
            return BaseApiService.getRetrofit().create(UserService::class.java)
        }

    }

    @POST(API_LOGIN)
    fun login(
            @Body body: RequestBody
    ): Single<ApiResult<String>>

    @POST(API_USER_INFO)
    fun getUserInfo(): Single<ApiResult<UserBean>>

}