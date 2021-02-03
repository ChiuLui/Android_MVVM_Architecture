package com.chiului.android_mvvm_architecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.chiului.android_mvvm_architecture.R
import com.chiului.android_mvvm_architecture.base.BaseNavFragment
import com.chiului.android_mvvm_architecture.databinding.FragmentLoginBinding
import com.chiului.android_mvvm_architecture.utilities.*
import com.chiului.android_mvvm_architecture.viewmodel.LoginViewModel

/**
 * 登录页$
 * @author    神经大条蕾弟
 * @date      2021/02/01 17:27
 */
class LoginFragment: BaseNavFragment(), View.OnClickListener {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        InjectorUtils.provideLoginViewModelFactory(requireContext())
    }

    override fun initViewModel(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = this
        binding.loginModel = viewModel
        binding.clickListener = this

        /**
         * 账号
         */
        viewModel.account.observe(this, Observer {
            checkLogin()
        })

        /**
         * 密码
         */
        viewModel.password.observe(this, {
            checkLogin()
        })

        /**
         * 显示 Toast
         */
        viewModel.toast.observe(this, {
            it.toast(requireContext())
        })

        /**
         * 登录成功
         */
        viewModel.token.observe(this, {
            toMain()
        })

        return binding.root
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        // 设置是否为回退关闭页面
        isTopStack = !MODE_GUEST
        // 加载上次登录账号
        viewModel.loadBeforeAccount()
    }

    private fun checkLogin() {
        var isLogin: Boolean
        val account: String? = viewModel.account.value
        val password: String? = viewModel.password.value
        when {
            account.isNullOrEmpty() && password.isNullOrEmpty() -> isLogin = false
            account.isNullOrEmpty() -> {
                //账号为空
                isLogin = false
                binding.edAccount.error = getString(R.string.invalid_account)
            }
            account.length < ACCOUNT_MIN_LENGTH || account.length > ACCOUNT_MAX_LENGTH -> {
                //账号长度
                isLogin = false
                binding.edAccount.error = getString(R.string.invalid_account_size)
            }
            password.isNullOrEmpty() -> {
                //密码为空
                isLogin = false
                binding.edPaw.error = getString(R.string.invalid_password)
            }
            password.length < PASSWORD_MIN_LENGTH || password.length > PASSWORD_MAX_LENGTH -> {
                //密码长度
                isLogin = false
                binding.edPaw.error = getString(R.string.invalid_password_size)
            }
            else -> isLogin = true
        }
        binding.btnLogin.isEnabled = isLogin
    }

    /**
     * 跳转主页
     */
    private fun toMain() {
        val toMainFragment = LoginFragmentDirections.actionLoginFragmentToMainFragment()
        binding.btnLogin.findNavController().navigate(toMainFragment)
    }

    override fun onClick(p0: View?) {
        viewModel.login()
    }
}