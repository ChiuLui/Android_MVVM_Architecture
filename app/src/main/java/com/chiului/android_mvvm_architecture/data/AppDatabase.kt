package com.chiului.android_mvvm_architecture.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chiului.android_mvvm_architecture.bean.AppCacheBean
import com.chiului.android_mvvm_architecture.bean.UserBean
import com.chiului.android_mvvm_architecture.utilities.DATABASE_NAME

/**
 * AppDatabase room 数据库$
 * @author    神经大条蕾弟
 * @date      2021/02/01 10:39
 */
@Database(entities = [AppCacheBean::class, UserBean::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    /**
     * app 缓存表
     * @return AppCacheDao
     */
    abstract fun appCacheDao(): AppCacheDao

    /**
     * 用户表
     * @return AppCacheDao
     */
    abstract fun userDao(): UserDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }

    }

}