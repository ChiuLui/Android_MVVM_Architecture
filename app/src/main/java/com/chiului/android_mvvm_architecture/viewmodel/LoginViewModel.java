package com.chiului.android_mvvm_architecture.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chiului.android_mvvm_architecture.bean.UserBean;
import com.chiului.android_mvvm_architecture.data.UserRepository;

/**
 * 用户 ViewModel$
 *
 * 注意：ViewModel 绝不能引用视图、Lifecycle 或可能存储对 Activity 上下文的引用的任何类。
 *
 * 注意：请确保用于更新界面的 LiveData 对象存储在 ViewModel 对象中，而不是将其存储在 Activity 或 Fragment 中，原因如下：
 * 避免 Activity 和 Fragment 过于庞大。现在，这些界面控制器负责显示数据，但不负责存储数据状态。
 * 将 LiveData 实例与特定的 Activity 或 Fragment 实例分离开，并使 LiveData 对象在配置更改后继续存在。
 *
 * @author 神经大条蕾弟
 * @date 2020/07/21 09:34
 */
public class LoginViewModel extends ViewModel {

    private final UserRepository mRepository;

    private MutableLiveData<UserBean> mUser;

    public LoginViewModel(UserRepository repository){
        mRepository = repository;
    }

    public MutableLiveData<UserBean> getUser() {
        if (mUser == null) {
            mUser = new MutableLiveData<UserBean>();
            loadUsers();
        }
        return mUser;
    }

    /**
     * 从 Repository 获取数据
     * 注意：您必须调用 setValue(T) 方法以从主线程更新 LiveData 对象。如果在 worker 线程中执行代码，则您可以改用 postValue(T) 方法来更新 LiveData 对象。
     */
    public void loadUsers() {
        // Do an asynchronous operation to fetch users.
        if (mRepository.getUser() != null) {
            UserBean user = mRepository.getUser();
            mUser.setValue(user);
        }
    }

    /**
     * 因为采用 LiveData 与 DataBinding 双向绑定：
     * 输入框的数据会及时反映到 LiveData ，但不会刷新界面
     */
    public void login() {
        UserBean user = mUser.getValue();
        if (user == null) {
            user = new UserBean();
        }
        user.setId("1");
        // 保存到 Repository
        mRepository.saveUser(user);
        // 通知刷新界面
        mUser.setValue(user);
    }

}
