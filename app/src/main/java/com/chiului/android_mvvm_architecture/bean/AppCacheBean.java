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
    private String key;
    private String value;

    public AppCacheBean(String key, String value){
        this.key = key;
        this.value = value;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
