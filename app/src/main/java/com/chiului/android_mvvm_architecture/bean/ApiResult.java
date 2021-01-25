package com.chiului.android_mvvm_architecture.bean;

/**
 * 基础返回对象实体类$
 *
 * @author 神经大条蕾弟
 * @date 2021/01/15 10:37
 */
public class ApiResult<T> {

    /**
     * success : true
     * msg : ok
     * code : 200
     * timestamp : 1610271439614
     * data : {}
     */

    private boolean success;
    private String msg;
    private int code;
    private long timestamp;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
