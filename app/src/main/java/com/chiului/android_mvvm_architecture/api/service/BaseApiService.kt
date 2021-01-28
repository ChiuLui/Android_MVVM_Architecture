package com.chiului.android_mvvm_architecture.api.service

import com.chiului.android_mvvm_architecture.BuildConfig
import com.chiului.android_mvvm_architecture.MainApplication
import com.chiului.android_mvvm_architecture.api.converter.CustomGsonConverterFactory
import com.chiului.android_mvvm_architecture.data.AppDatabase
import com.chiului.android_mvvm_architecture.utilities.AIP_BASE
import com.chiului.android_mvvm_architecture.utilities.TOKEN
import com.chiului.android_mvvm_architecture.utilities.logI
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

/**
 * 获取 Retorfit Service$
 * @author    神经大条蕾弟
 * @date      2021/01/14 15:43
 */
object BaseApiService {

    fun getRetrofit(): Retrofit{

        // 日志拦截器
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                if (BuildConfig.DEBUG) {
                    // 打印
                    message.logI()
                }
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BASIC // 设置打印级别
            redactHeader("Authorization")// 删除敏感信息
            redactHeader("Cookie")// 删除敏感信息
        }


        // 请求头拦截器
        val headerInterceptor = Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                    .header("token", getToken()) // 添加或替换 token 到请求头
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

        val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .build()

        val builder: Retrofit.Builder = Retrofit.Builder()
                .baseUrl(AIP_BASE) // 配置BaseUrl
                .client(client) // 设置client
//                .addConverterFactory(GsonConverterFactory.create()) // gson转换器
                .addConverterFactory(CustomGsonConverterFactory.create()) // 自定义 gson 转换器
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // RxJava3 支持

        return builder.build()
    }

    private fun getToken(): String {
        var appCache = AppDatabase.getInstance(MainApplication.context).appCacheDao().getAppCache(TOKEN)
        return if(appCache == null) "" else appCache.value ?: ""
    }

    @JvmStatic
    fun getJsonRequestBody(json: String): RequestBody {
        return json.toRequestBody("application/json;charset=UTF-8".toMediaType())
    }

}