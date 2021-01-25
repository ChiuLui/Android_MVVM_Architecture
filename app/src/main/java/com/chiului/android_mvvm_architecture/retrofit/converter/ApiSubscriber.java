package com.chiului.android_mvvm_architecture.retrofit.converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.rxjava3.core.SingleObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 统一订阅管理
 *
 * @param <T> 泛型
 */
public abstract class ApiSubscriber<T> implements SingleObserver<T> {

    @Override
    public void onError(Throwable e) {
        NetErrorException error;

        if (e != null) {

            // 自定义抛出的错误进行解析
            if (e instanceof NetErrorException) {
                error = (NetErrorException) e;
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

                error = new NetErrorException(errorMeg, code);

            } else if (e instanceof UnknownHostException) {
                error = new NetErrorException(e, NetErrorException.ERROR_CODE_UNKNOWN_HOST_EXCEPTION);
            } else if (e instanceof JSONException || e instanceof JsonParseException) {
                error = new NetErrorException(e, NetErrorException.ERROR_CODE_JSON_EXCEPTION);
            } else if (e instanceof SSLHandshakeException) {
                error = new NetErrorException(e, NetErrorException.ERROR_CODE_SSL_HANDSHAKE_EXCEPTION);
            }  else if (e instanceof SocketTimeoutException) {
                error = new NetErrorException(e, NetErrorException.ERROR_CODE_SOCKET_TIMEOUT_EXCEPTION);
            } else if (e instanceof ConnectException) {
                error = new NetErrorException(e, NetErrorException.ERROR_CODE_CONNECT_EXCEPTION);
            } else {
                error = new NetErrorException(e, NetErrorException.ERROR_CODE_OTHER);
            }

        } else {

            error = new NetErrorException(e, NetErrorException.ERROR_CODE_OTHER);

        }

        // 回调抽象方法
        onFail(error);

    }

    /**
     * 回调错误
     */
    protected abstract void onFail(NetErrorException error);
}