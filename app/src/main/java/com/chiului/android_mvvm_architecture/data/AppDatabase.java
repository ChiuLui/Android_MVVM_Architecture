package com.chiului.android_mvvm_architecture.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.chiului.android_mvvm_architecture.bean.UserBean;

import static com.chiului.android_mvvm_architecture.utilities.ConstantsKt.DATABASE_NAME;

/**
 * AppDatabase room 数据库$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/23 12:09
 */
@Database(entities = {UserBean.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static Context mContext;

    public AppDatabase(){}

    public static AppDatabase getInstance(Context applicationContext){
        mContext = applicationContext;
        return SingletonHolder.Instance;

    }

    private static class SingletonHolder {

        private static final AppDatabase Instance = Room.databaseBuilder(mContext, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

    }

}
