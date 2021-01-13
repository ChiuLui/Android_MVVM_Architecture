package com.chiului.android_mvvm_architecture.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chiului.android_mvvm_architecture.bean.DummyItemBean;
import com.chiului.android_mvvm_architecture.bean.UserBean;
import com.chiului.android_mvvm_architecture.data.MainRepository;
import com.chiului.android_mvvm_architecture.dummy.DummyContent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * 首页碎片 ViewModel$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/28 10:44
 */
public class MainViewModel extends ViewModel {

    private MainRepository mRepository;

    /**
     * 用户信息
     */
    private MutableLiveData<UserBean> mUserInfoBean;

    /**
     * ListFragment 数据
     */
    private MutableLiveData<List<DummyItemBean>> mListDate;

    /**
     * RefreshFragment 数据
     */
    private MutableLiveData<List<DummyItemBean>> mRefreshDate;

    /**
     * PagingFragment 数据
     */
    private MutableLiveData<List<DummyItemBean>> mPagingDate;

    public MainViewModel(MainRepository repository){
        mRepository = repository;
    }

    public MutableLiveData<UserBean> getUserInfoBean() {
        if (mUserInfoBean == null) {
            mUserInfoBean = new MutableLiveData<UserBean>();
        }
        return mUserInfoBean;
    }

    public MutableLiveData<List<DummyItemBean>> getListDate() {
        if (mListDate == null) {
            mListDate = new MutableLiveData<List<DummyItemBean>>();
        }
        return mListDate;
    }

    public MutableLiveData<List<DummyItemBean>> getRefreshDate() {
        if (mRefreshDate == null) {
            mRefreshDate = new MutableLiveData<List<DummyItemBean>>();
        }
        return mRefreshDate;
    }

    public MutableLiveData<List<DummyItemBean>> getPagingDate() {
        if (mPagingDate == null) {
            mPagingDate = new MutableLiveData<List<DummyItemBean>>();
        }
        return mPagingDate;
    }

    public void getListFragmentDate(){
        getListDate().setValue(DummyContent.ITEMS);
    }

    public void getRefreshFragmentDate(){
        List<DummyItemBean> items = new ArrayList<>();
        items.addAll(DummyContent.ITEMS);
        Collections.shuffle(items);
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getRefreshDate().setValue(items);
                    }
                });
    }

    public void getPagingFragmentDate(){
        List<DummyItemBean> items = new ArrayList<>();
        items.addAll(DummyContent.ITEMS);
        Collections.shuffle(items);
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getPagingDate().setValue(items);
                    }
                });
    }

    public void getUserInfo() {
        // TODO: 1/13/21 神经大条蕾弟：获取 token 然后去本地数据库查询用户信息
        UserBean userInfo = mRepository.getLocalUserInfo("token");
        getUserInfoBean().setValue(userInfo);
    }

    public void initUserInfo(){
        mRepository.getRemoteUserInfo();
    }

}
