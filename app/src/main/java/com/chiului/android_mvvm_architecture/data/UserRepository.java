package com.chiului.android_mvvm_architecture.data;

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

    public static UserRepository getInstance(UserDao userDao){
        mUserDao = userDao;
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
