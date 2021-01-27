package com.chiului.android_mvvm_architecture.retrofit.converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Single 订阅网络请求错误处理
 * @author 神经大条蕾弟
 * @param <T> 需要处理的类型
 */
public abstract class ApiObserver<T> implements SingleObserver<T> {

    @Override
    public void onError(Throwable e) {

        ApiException error = null;

        if (e != null) {

            // 自定义抛出的错误进行解析
            if (e instanceof ApiException) {
                error = (ApiException) e;
            } else if (e instanceof HttpException) {
                // 后台返回错误
                HttpException httpException = (HttpException) e;
                int code = httpException.code();

                ResponseBody responseBody = httpException.response().errorBody();
                String json = "";
                try {
                    json = responseBody.string();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                JsonElement jsonElement = JsonParser.parseString(json);
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                String errorMeg = "";

                // 后台框架安全中心与接口返回格式有可能不一致，所以解析字段也不一致。
                JsonElement msgElement = jsonObject.get("msg");
                JsonElement errorElement = jsonObject.get("error");
                if (msgElement != null) {
                    // 这个为后台接口返回
                    errorMeg = msgElement.getAsString();
                } else if (errorElement != null) {
                    // 这个为后台安全中心返回
                    JsonElement pathElement = jsonObject.get("path");
                    if (pathElement == null) {
                        errorMeg = errorElement.getAsString();
                    } else {
                        errorMeg += errorElement.getAsString() + "：" + pathElement.getAsString();
                    }
                } else {
                    // 直接返回 json
                    errorMeg = json;
                }

                error = new ApiException(errorMeg, code);

            } else if (e instanceof UnknownHostException) {
                error = new ApiException(e, ApiException.ERROR_CODE_UNKNOWN_HOST_EXCEPTION);
            } else if (e instanceof JSONException || e instanceof JsonParseException) {
                error = new ApiException(e, ApiException.ERROR_CODE_JSON_EXCEPTION);
            } else if (e instanceof SSLHandshakeException) {
                error = new ApiException(e, ApiException.ERROR_CODE_SSL_HANDSHAKE_EXCEPTION);
            }  else if (e instanceof SocketTimeoutException) {
                error = new ApiException(e, ApiException.ERROR_CODE_SOCKET_TIMEOUT_EXCEPTION);
            } else if (e instanceof ConnectException) {
                error = new ApiException(e, ApiException.ERROR_CODE_CONNECT_EXCEPTION);
            }  else if (e instanceof MalformedJsonException) {
                error = new ApiException(e, ApiException.ERROR_CODE_MALFORMED_JSON_EXCEPTION);
            } else {
                error = new ApiException(e, ApiException.ERROR_CODE_OTHER);
            }

        }

        if (error == null){
            error = new ApiException("", ApiException.ERROR_CODE_OTHER);
        }

        // 回调抽象方法
        onFail(error);
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    /**
     * 回调错误
     * @param error 自定义失败回调
     */
    protected abstract void onFail(@NonNull ApiException error);

}