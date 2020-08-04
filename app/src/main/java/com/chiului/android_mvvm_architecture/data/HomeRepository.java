package com.chiului.android_mvvm_architecture.data;


/**
 * 首页碎片数据储存库$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/23 16:44
 */
public class HomeRepository {

    private HomeRepository(){}

    /**
     * Room 的数据库表对象
     */
    private static UserDao mAppCacheDao;

    public static HomeRepository getInstance(UserDao appCacheDao){
        mAppCacheDao = appCacheDao;
        return SingletonHolder.Instance;

    }

    private static class SingletonHolder{
        private static final HomeRepository Instance = new HomeRepository();
    }

}
