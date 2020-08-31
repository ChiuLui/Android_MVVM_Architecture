package com.chiului.android_mvvm_architecture.api;

import android.util.Log;

import com.chiului.android_mvvm_architecture.BuildConfig;
import com.chiului.android_mvvm_architecture.utilities.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * 网络请求基础服务$
 *
 * @author 神经大条蕾弟
 * @date 2020/08/31 15:26
 */
public class BaseApiService {

    private static Retrofit mRetrofit;

    /**
     * 获取Retrofit
     *
     * @return 获取Retrofit
     */
    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {

            HttpLoggingInterceptor logger = new HttpLoggingInterceptor(message -> {
                if (BuildConfig.DEBUG) {
                    Log.e(TAG, message);
                }
            });
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);// 设置打印级别
            logger.redactHeader("Authorization");// 删除敏感信息
            logger.redactHeader("Cookie");// 删除敏感信息

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(Constants.AIP_BASE)// 配置BaseUrl
                    .client(client)// 设置client
                    .addConverterFactory(GsonConverterFactory.create())// gson转换器
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());// RxJava 支持
            mRetrofit = builder.build();
        }
        return mRetrofit;
    }

}
