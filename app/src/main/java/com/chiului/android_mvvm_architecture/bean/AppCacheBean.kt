package com.chiului.android_mvvm_architecture.bean

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * AppCache 实体类$
 * @author    神经大条蕾弟
 * @date      2021/02/01 16:11
 */
@Entity(tableName = "app_cache")
data class AppCacheBean(
        @NonNull
        @PrimaryKey
        var key: String,
        var value: String
)
