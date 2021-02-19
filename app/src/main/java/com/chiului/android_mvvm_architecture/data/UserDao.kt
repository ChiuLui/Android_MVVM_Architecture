package com.chiului.android_mvvm_architecture.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chiului.android_mvvm_architecture.bean.UserBean

/**
 * 用户表的 DAO(数据访问对象)$
 * @author    神经大条蕾弟
 * @date      2021/02/01 11:17
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserBean)

    @Update
    fun updateUser(user: UserBean)

    @Delete
    fun deleteUser(user: UserBean)

    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<List<UserBean>>

    @Query("SELECT * FROM user WHERE token == :token")
    fun getUser(token: String): UserBean?

}