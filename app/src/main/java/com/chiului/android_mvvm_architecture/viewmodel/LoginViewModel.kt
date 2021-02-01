package com.chiului.android_mvvm_architecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chiului.android_mvvm_architecture.api.exception.ApiException
import com.chiului.android_mvvm_architecture.api.observer.ApiObserver
import com.chiului.android_mvvm_architecture.bean.ApiResult
import com.chiului.android_mvvm_architecture.data.LoginRepository

/**
 * 登录 ViewModel$
 * <p>
 * 注意：ViewModel 绝不能引用视图、Lifecycle 或可能存储对 Activity 上下文的引用的任何类。
 * <p>
 * 注意：请确保用于更新界面的 LiveData 对象存储在 ViewModel 对象中，而不是将其存储在 Activity 或 Fragment 中，原因如下：
 * 避免 Activity 和 Fragment 过于庞大。现在，这些界面控制器负责显示数据，但不负责存储数据状态。
 * 将 LiveData 实例与特定的 Activity 或 Fragment 实例分离开，并使 LiveData 对象在配置更改后继续存在。
 *
 * @author    神经大条蕾弟
 * @date      2021/01/29 14:17
 */
class LoginViewModel internal constructor(
        private val repository: LoginRepository
) : ViewModel() {

    /**
     * 提示
     */
    val toast = MutableLiveData<String>()

    /**
     * token
     */
    val token = MutableLiveData<String>()

    /**
     * 账号
     */
    val account = MutableLiveData<String>()

    /**
     * 密码
     */
    val password = MutableLiveData<String>()

    /**
     * 从 Repository 获取数据
     * 注意：您必须调用 setValue(T) 方法以从主线程更新 LiveData 对象。如果在 worker 线程中执行代码，则您可以改用 postValue(T) 方法来更新 LiveData 对象。
     */
    fun loadBeforeAccount() {
        val beforeAccount = repository.getBeforeAccount()
        if (!beforeAccount.isNullOrBlank()) {
            this.account.value = beforeAccount
        }
    }

    /**
     * 因为采用 LiveData 与 DataBinding 双向绑定：
     * 输入框的数据会及时反映到 LiveData ，但不会刷新界面
     */
    fun login() {
        repository.getToken(account.value, password.value)
                .subscribeWith(object : ApiObserver<ApiResult<String>>() {
                    override fun onSuccess(apiResult: ApiResult<String>?) {
                        // 请求成功
                        var data = apiResult?.data
                        // 保存到账号 Repository
                        repository.saveBeforeAccount(account.value ?: "")
                        // 保存到 Token 到 Repository（存储库）
                        repository.saveToken(data ?: "")
                        // 登录成功通知界面
                        if (data.isNullOrBlank()) {
                            toast.postValue(apiResult?.msg)
                        } else {
                            token.postValue(data)
                        }
                    }

                    override fun onFail(error: ApiException?) {
                        toast.postValue(error?.message)
                    }
                })
    }

}