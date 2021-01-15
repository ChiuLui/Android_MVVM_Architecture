package com.chiului.android_mvvm_architecture.api

import com.chiului.android_mvvm_architecture.bean.BaseBean
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

    @POST("api/base/login/")
    fun login(
            @Body body: RequestBody
    ): Single<BaseBean<String>>

    // TODO: 1/16/21 神经大条蕾弟：还差请求个人信息接口

    // TODO: 1/16/21 神经大条蕾弟：还差退出登录接口
//    @Headers("Content-type:application/json;charset=UTF-8")
//    @POST("api/base/login/")
//    suspend fun login(
//            @Body body: RequestBody
//    ): BaseBean<String>

    companion object {

        @JvmStatic
        fun create(): UserService {
            return BaseApiService.getRetrofit().create(UserService::class.java)
        }

    }

}