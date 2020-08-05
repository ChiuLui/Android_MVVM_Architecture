package com.chiului.android_mvvm_architecture.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chiului.android_mvvm_architecture.data.HomeRepository;


/**
 * 首页碎片 ViewModel$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/28 10:44
 */
public class HomeViewModel extends ViewModel {

    private HomeRepository mRepository;

    /**
     * 未读消息红点状态
     */
    private MutableLiveData<Boolean> mUnRead;

    public HomeViewModel(HomeRepository repository){
        mRepository = repository;
    }

//    public MutableLiveData<Boolean> getUnRead() {
//        if (mUnRead == null) {
//            mUnRead = new MutableLiveData<Boolean>();
//            mUnRead.setValue(false);
//        }
//        return mUnRead;
//    }

}
