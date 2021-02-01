package com.chiului.android_mvvm_architecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chiului.android_mvvm_architecture.api.exception.ApiException
import com.chiului.android_mvvm_architecture.api.observer.ApiObserver
import com.chiului.android_mvvm_architecture.bean.ApiResult
import com.chiului.android_mvvm_architecture.dummy.DummyItemBean
import com.chiului.android_mvvm_architecture.bean.UserBean
import com.chiului.android_mvvm_architecture.data.MainRepository
import com.chiului.android_mvvm_architecture.dummy.DummyContent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * $首页碎片 ViewModel$
 * @author    神经大条蕾弟
 * @date      2021/01/29 15:44
 */
class MainViewModel internal constructor(
        private val repository: MainRepository
): ViewModel() {

    /**
     * 消息提示
     */
    val toast = MutableLiveData<String>()

    /**
     * 用户信息
     */
    val userInfoBean = MutableLiveData<UserBean>()

    /**
     * ListFragment 数据
     */
    val listDate = MutableLiveData<List<DummyItemBean>>()

    /**
     * RefreshFragment 数据
     */
    val refreshDate = MutableLiveData<List<DummyItemBean>>()

    /**
     * PagingFragment 数据
     */
    val pagingDate = MutableLiveData<List<DummyItemBean>>()

    fun getListFragmentDate() {
        listDate.value = DummyContent.ITEMS
    }

    fun getRefreshFragmentDate() {
        val items = ArrayList<DummyItemBean>()
        items.addAll(DummyContent.ITEMS)
        items.shuffle()
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long?> {
                    override fun onSubscribe(d: @NonNull Disposable?) {}
                    override fun onNext(aLong: @NonNull Long?) {}
                    override fun onError(e: @NonNull Throwable?) {}
                    override fun onComplete() {
                        refreshDate.value = items
                    }
                })
    }

    fun getPagingFragmentDate() {
        val items = ArrayList<DummyItemBean>()
        items.addAll(DummyContent.ITEMS)
        items.shuffle()
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long?> {
                    override fun onSubscribe(d: @NonNull Disposable?) {}
                    override fun onNext(aLong: @NonNull Long?) {}
                    override fun onError(e: @NonNull Throwable?) {}
                    override fun onComplete() {
                        pagingDate.value = items
                    }
                })
    }

    fun initUserInfo() {
        repository.getRemoteUserInfo()
                .subscribeWith(object : ApiObserver<ApiResult<UserBean>>() {
                    override fun onSuccess(apiResult: ApiResult<UserBean>) {
                        // 保存用户信息到本地数据库
                        repository.saveUserInfo(apiResult.data)
                        // 从本地查询用户信息并推送到界面
                        getUserInfo()
                    }

                    override fun onFail(error: ApiException) {
                        toast.postValue(error.message)
                    }

                })
    }

    fun getUserInfo() {
        // 去本地数据库查询用户信息
        val userInfo: UserBean = repository.getLocalUserInfo()
        userInfoBean.postValue(userInfo)
    }

}