package com.chiului.android_mvvm_architecture.data;


import com.chiului.android_mvvm_architecture.bean.UserBean;

/**
 * 首页碎片数据储存库$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/23 16:44
 */
public class MainRepository {

    private MainRepository(){}

    /**
     * Room 的数据库表对象
     */
    private static UserDao mAppCacheDao;

    public static MainRepository getInstance(UserDao appCacheDao){
        mAppCacheDao = appCacheDao;
        return SingletonHolder.Instance;
    }

    private static class SingletonHolder{
        private static final MainRepository Instance = new MainRepository();
    }

    public UserBean getLocalUserInfo(){
        // TODO: 1/13/21 神经大条蕾弟：从本地数据库获取用户信息
        UserBean user = mAppCacheDao.getUser("token");
        return user;
    }

    public void getRemoteUserInfo(){
        // TODO: 1/13/21 神经大条蕾弟：请求接口获取用户信息然后保存到本地数据库
    }

}
