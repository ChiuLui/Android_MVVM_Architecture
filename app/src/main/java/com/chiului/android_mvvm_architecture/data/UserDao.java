package com.chiului.android_mvvm_architecture.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.chiului.android_mvvm_architecture.bean.UserBean;

import java.util.List;

/**
 * $用户表的DAO(数据访问对象)
 *
 * @author 神经大条蕾弟
 * @date 2020/07/23 10:50
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserBean user);

    @Update
    void updateUser(UserBean user);

    @Delete
    void deleteUser(UserBean user);

    @Query("SELECT * FROM user")
    LiveData<List<UserBean>> getUsers();

    @Query("SELECT * FROM user WHERE id == :id")
    UserBean getUser(String id);

}
