package com.chiului.android_mvvm_architecture.viewmodel;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chiului.android_mvvm_architecture.bean.BaseBean;
import com.chiului.android_mvvm_architecture.data.LoginRepository;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 用户 ViewModel$
 * <p>
 * 注意：ViewModel 绝不能引用视图、Lifecycle 或可能存储对 Activity 上下文的引用的任何类。
 * <p>
 * 注意：请确保用于更新界面的 LiveData 对象存储在 ViewModel 对象中，而不是将其存储在 Activity 或 Fragment 中，原因如下：
 * 避免 Activity 和 Fragment 过于庞大。现在，这些界面控制器负责显示数据，但不负责存储数据状态。
 * 将 LiveData 实例与特定的 Activity 或 Fragment 实例分离开，并使 LiveData 对象在配置更改后继续存在。
 *
 * @author 神经大条蕾弟
 * @date 2020/07/21 09:34
 */
public class LoginViewModel extends ViewModel {

    private final LoginRepository mRepository;

    private MutableLiveData<String> mToken;

    /**
     * 提示
     */
    private MutableLiveData<String> mToast;

    /**
     * 账号
     */
    private MutableLiveData<String> mAccount;

    /**
     * 密码
     */
    private MutableLiveData<String> mPassword;

    public LoginViewModel(LoginRepository repository) {
        mRepository = repository;
    }

    public MutableLiveData<String> getToast() {
        if (mToast == null) {
            mToast = new MutableLiveData<String>();
        }
        return mToast;
    }

    public MutableLiveData<String> getToken() {
        if (mToken == null) {
            mToken = new MutableLiveData<String>();
        }
        return mToken;
    }

    public MutableLiveData<String> getAccount() {
        if (mAccount == null) {
            mAccount = new MutableLiveData<String>();
            loadAccount();
        }
        return mAccount;
    }

    public MutableLiveData<String> getPassword() {
        if (mPassword == null) {
            mPassword = new MutableLiveData<String>();
        }
        return mPassword;
    }

    /**
     * 从 Repository 获取数据
     * 注意：您必须调用 setValue(T) 方法以从主线程更新 LiveData 对象。如果在 worker 线程中执行代码，则您可以改用 postValue(T) 方法来更新 LiveData 对象。
     */
    public void loadAccount() {
        // Do an asynchronous operation to fetch users.
        String account = mRepository.getAccount();
        if (!TextUtils.isEmpty(account)) {
            getAccount().setValue(account);
        }
    }

    /**
     * 因为采用 LiveData 与 DataBinding 双向绑定：
     * 输入框的数据会及时反映到 LiveData ，但不会刷新界面
     */
    public void login() {
        // 获取 token
        mRepository.getToken(getAccount().getValue(), getPassword().getValue())
                // TODO: 1/16/21 神经大条蕾弟：还差请求预处理
                .subscribe(bean -> {
                    // 请求成功
                    // 保存到账号 Repository
                    mRepository.saveAccount(getAccount().getValue());
                    // 保存到 Token 到 Repository（存储库）
                    String token = bean.getData();
                    mRepository.saveToken(token);
                    // 登录成功通知界面
                    if (!TextUtils.isEmpty(token)) {
                        getToken().postValue(token);
                    } else {
                        getToast().postValue(bean.getMsg());
                    }
                }, throwable -> {
                    // 请求失败
                    String message;
                    if (throwable instanceof HttpException) {
                        // 后台返回错误
                        ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                        String json = responseBody.string();
                        if (!TextUtils.isEmpty(json)) {
                            Gson gson = new Gson();
                            BaseBean baseBean = gson.fromJson(json, BaseBean.class);
                            message = baseBean.getMsg();
                        } else {
                            message = throwable.getMessage();
                        }
                    } else {
                        // 其他错误
                        message = throwable.getMessage();
                    }
                    if (!TextUtils.isEmpty(message)) {
                        getToast().postValue(message);
                    }
                });
    }

}
