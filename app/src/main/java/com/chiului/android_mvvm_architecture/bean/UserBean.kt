package com.chiului.android_mvvm_architecture.bean

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用户实体类$
 * @author    神经大条蕾弟
 * @date      2021/02/01 15:16
 */
@Entity(tableName = "user")
data class UserBean(
        @NonNull
        @PrimaryKey
        var token: String,
        var id: String?,
        var avatar: String?,
        var mobile: String?,
        var username: String?,// 用户名
        var realName: String?,// 真实姓名
        var nickName: String?,// 昵称
        var sex: Int
)
