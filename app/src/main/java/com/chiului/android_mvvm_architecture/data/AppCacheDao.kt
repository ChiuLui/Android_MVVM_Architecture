package com.chiului.android_mvvm_architecture.data

import androidx.room.*
import com.chiului.android_mvvm_architecture.bean.AppCacheBean

/**
 * AppCache 的 DAO(数据访问对象)
 * @author    神经大条蕾弟
 * @date      2021/02/01 11:09
 */
@Dao
interface AppCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAppCache(cache: AppCacheBean)

    @Delete
    fun deleteAppCache(cache: AppCacheBean)

    @Query("SELECT * FROM app_cache WHERE `key` == :key")
    fun getAppCache(key: String): AppCacheBean?

}