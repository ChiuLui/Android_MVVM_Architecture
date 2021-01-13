package com.chiului.android_mvvm_architecture.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.chiului.android_mvvm_architecture.bean.AppCacheBean;

/**
 * $AppCache的DAO(数据访问对象)
 *
 * @author
 * @date 2020/07/23 10:50
 */
@Dao
public interface AppCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAppCache(AppCacheBean cache);

    @Delete
    void deleteAppCache(AppCacheBean cache);

    @Query("SELECT * FROM app_cache WHERE appKey == :key")
    AppCacheBean getAppCache(String key);

}
