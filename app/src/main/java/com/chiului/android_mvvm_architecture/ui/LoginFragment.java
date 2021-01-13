package com.chiului.android_mvvm_architecture.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.chiului.android_mvvm_architecture.R;
import com.chiului.android_mvvm_architecture.base.BaseNavFragment;
import com.chiului.android_mvvm_architecture.databinding.FragmentLoginBinding;
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils;
import com.chiului.android_mvvm_architecture.viewmodel.LoginViewModel;

import static com.chiului.android_mvvm_architecture.utilities.ConfigsKt.ACCOUNT_MAX_LENGTH;
import static com.chiului.android_mvvm_architecture.utilities.ConfigsKt.ACCOUNT_MIN_LENGTH;
import static com.chiului.android_mvvm_architecture.utilities.ConfigsKt.PASSWORD_MAX_LENGTH;
import static com.chiului.android_mvvm_architecture.utilities.ConfigsKt.PASSWORD_MIN_LENGTH;

/**
 * 登录页面
 * @author 神经大条蕾弟
 * @date   2020/08/04 18:15
 */
public class LoginFragment extends BaseNavFragment implements View.OnClickListener {

    private FragmentLoginBinding mBinding;
    private LoginViewModel mViewModel;

    @Override
    public int setContentViewID() {
        return R.layout.fragment_login;
    }

    @Override
    public View initViewModel(LayoutInflater inflater, int layoutId, @Nullable ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        mViewModel = new ViewModelProvider(this, InjectorUtils.provideLoginViewModelFactory(getActivity())).get(LoginViewModel.class);
        mBinding.setLifecycleOwner(this);
        mBinding.setLoginModel(mViewModel);
        mBinding.setClickListener(this);

        /**
         * 账号
         */
        mViewModel.getAccount().observe(this, account -> {
            checkLogin();
        });

        /**
         * 密码
         */
        mViewModel.getPassword().observe(this, password -> {
            checkLogin();
        });

        mViewModel.getToken().observe(this, token -> {
            toMain(mBinding.btnLogin);
        });

        return mBinding.getRoot();
    }

    @Override
    public void initView() {

    }

    private void checkLogin() {
        boolean isLogin = false;
        String account = mViewModel.getAccount().getValue();
        String password = mViewModel.getPassword().getValue();
        if (!TextUtils.isEmpty(account) || !TextUtils.isEmpty(password)) {

            String mailFilter = "@";

            if (TextUtils.isEmpty(account)) {
                //账号为空
                isLogin = false;
                mBinding.edAccount.setError(getString(R.string.invalid_account));
            } else if (account.length() < ACCOUNT_MIN_LENGTH || account.length() > ACCOUNT_MAX_LENGTH) {
                //账号长度
                isLogin = false;
                mBinding.edAccount.setError(getString(R.string.invalid_account_size));
            } else if (!account.contains(mailFilter)) {
                //是否包含邮箱@
                isLogin = false;
                mBinding.edAccount.setError(getString(R.string.invalid_account_email));
            } else if (TextUtils.isEmpty(password)) {
                //密码为空
                isLogin = false;
                mBinding.edPaw.setError(getString(R.string.invalid_password));
            } else if (password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH) {
                //密码长度
                isLogin = false;
                mBinding.edPaw.setError(getString(R.string.invalid_password_size));
            } else {
                //通过
                isLogin = true;
            }
        }
        mBinding.btnLogin.setEnabled(isLogin);
    }

    @Override
    public void onClick(View view) {
        mViewModel.login();
    }

    private void toMain(View view) {
        NavDirections toMainFragment = LoginFragmentDirections.actionLoginFragmentToMainFragment();
        Navigation.findNavController(view).navigate(toMainFragment);
    }

}