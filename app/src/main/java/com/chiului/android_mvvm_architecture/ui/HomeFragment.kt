package com.chiului.android_mvvm_architecture.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.chiului.android_mvvm_architecture.R
import com.chiului.android_mvvm_architecture.base.BaseFragment
import com.chiului.android_mvvm_architecture.databinding.HomeFragmentBinding
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils
import com.chiului.android_mvvm_architecture.utilities.toast
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModel

/**
 * HomeSampleFragment$
 * @author    神经大条蕾弟
 * @date      2021/02/04 14:53
 */
class HomeFragment : BaseFragment() {

    private lateinit var binding: HomeFragmentBinding
    private val viewModel: MainViewModel by viewModels {
        InjectorUtils.provideMainViewModelFactory(requireActivity())
    }
    private lateinit var page: String

    companion object {

        const val ARG_OBJECT = "object"

    }

    override fun initViewModel(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        initTitleBar()
        binding.txPage.text = page
    }

    override fun initBundle(bundle: Bundle) {
        super.initBundle(bundle)
        page = bundle.getString(ARG_OBJECT, "")
    }

    /**
     * 初始化标题栏
     */
    private fun initTitleBar() {
        // 将 toolbar 设置成页面的 ActionBar（初始化）
        val activity = requireActivity() as AppCompatActivity
        val toolbar: Toolbar = binding.toolbar
        activity.setSupportActionBar(toolbar)
        // 启用回退按钮
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 设置回退按钮监听
        toolbar.setNavigationOnClickListener { activity.finish() }
        // 开启 ActionBar 菜单
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        // 设置菜单布局
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 监听菜单
        when (item.itemId) {
            R.id.menu_more -> {
                item.title.toast(requireContext())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}