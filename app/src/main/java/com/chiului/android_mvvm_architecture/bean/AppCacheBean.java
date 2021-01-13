package com.chiului.android_mvvm_architecture.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * AppCache实体类$
 *
 * @author
 * @date 2020/07/21 09:56
 */
@Entity(tableName = "app_cache")
public class AppCacheBean {

    @NonNull
    @PrimaryKey
    private String appKey;
    private String appValue;

    public AppCacheBean(String appKey, String appValue){
        this.appKey = appKey;
        this.appValue = appValue;
    }

    @NonNull
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(@NonNull String appKey) {
        this.appKey = appKey;
    }

    public String getAppValue() {
        return appValue;
    }

    public void setAppValue(String appValue) {
        this.appValue = appValue;
    }
}
