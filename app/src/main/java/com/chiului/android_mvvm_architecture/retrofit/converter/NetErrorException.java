package com.chiului.android_mvvm_architecture.retrofit.converter;

import android.text.TextUtils;

import java.io.IOException;

/**
 * 网络请求异常处理$
 * @author    神经大条蕾弟
 * @date      2021/01/25 10:13
 */
public class NetErrorException extends IOException {

    private int mCode;
    private String mMessage;
    private Throwable mException;

    /**
     * 请求成功
     */
    public static final int SUCCEED_CODE = 200;

    /**
     * 服务器错误
     */
    public static final int ERROR_CODE_500 = 500;

    /**
     * 没有权限
     */
    public static final int ERROR_CODE_401 = 401;

    /**
     * 无法找到
     */
    public static final int ERROR_CODE_404 = 404;

    /**
     * 无连接异常
     */
    public static final int ERROR_CODE_UNKNOWN_HOST_EXCEPTION = 1001;

    /**
     * 数据解析异常
     */
    public static final int ERROR_CODE_JSON_EXCEPTION = 1002;

    /**
     * 证书验证失败
     */
    public static final int ERROR_CODE_SSL_HANDSHAKE_EXCEPTION = 1003;

    /**
     * 网络连接超时
     */
    public static final int ERROR_CODE_SOCKET_TIMEOUT_EXCEPTION = 1004;

    /**
     * 无法连接到服务器
     */
    public static final int ERROR_CODE_CONNECT_EXCEPTION = 1005;

    /**
     * 其他
     */
    public static final int ERROR_CODE_OTHER = -999;

    public NetErrorException(Throwable exception, int code) {
        this.mException = exception;
        this.mCode = code;
    }

    public NetErrorException(String message, int code) {
        super(message);
        this.mCode = code;
        this.mMessage = message;
    }


    @Override
    public String getMessage() {
        if (!TextUtils.isEmpty(mMessage)) {
            return mMessage;
        }
        switch (mCode) {
            case ERROR_CODE_500:
                return "服务器发生错误";
            case ERROR_CODE_401:
                return "没有权限";
            case ERROR_CODE_404:
                return "无法找到";
            case ERROR_CODE_UNKNOWN_HOST_EXCEPTION:
                return "未知主机异常，无法连接服务器。";
            case ERROR_CODE_JSON_EXCEPTION:
                return "数据解析异常";
            case ERROR_CODE_SSL_HANDSHAKE_EXCEPTION:
                return "证书验证失败";
            case ERROR_CODE_SOCKET_TIMEOUT_EXCEPTION:
                return "网络连接超时";
            case ERROR_CODE_CONNECT_EXCEPTION:
                return "无法连接到服务器，请检查网络连接后再试！";
            case ERROR_CODE_OTHER:
            default:
                return mException.getMessage();
        }
    }


    /**
     * 获取错误类型
     */
    public int getErrorType() {
        return mCode;
    }

}
