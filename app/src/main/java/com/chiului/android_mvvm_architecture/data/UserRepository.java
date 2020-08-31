package com.chiului.android_mvvm_architecture.data;

import com.chiului.android_mvvm_architecture.api.UserService;
import com.chiului.android_mvvm_architecture.bean.UserBean;

/**
 * 用户储存库$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/23 16:44
 */
public class UserRepository {

    private UserRepository(){}

    /**
     * Room 的数据库表对象
     */
    private static UserDao mUserDao;

    /**
     * Retrofit 的接口
     */
    private static UserService mUserService;

    public static UserRepository getInstance(UserDao userDao, UserService userService){
        mUserDao = userDao;
        mUserService = userService;
        return SingletonHolder.Instance;
    }

    private static class SingletonHolder{
        private static final UserRepository Instance = new UserRepository();
    }

    public void saveUser(UserBean user){
        mUserDao.insertUser(user);
    }

    public void updateUser(UserBean user){
        mUserDao.updateUser(user);
    }

    public void deleteUser(UserBean user){
        mUserDao.deleteUser(user);
    }

    public UserBean getUser(){
        return mUserDao.getUser("1");
    }

}
