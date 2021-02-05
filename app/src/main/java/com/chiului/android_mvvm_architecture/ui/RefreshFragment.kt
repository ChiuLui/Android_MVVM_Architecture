package com.chiului.android_mvvm_architecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chiului.android_mvvm_architecture.R
import com.chiului.android_mvvm_architecture.adapter.RefreshFragmentAdapter
import com.chiului.android_mvvm_architecture.base.BaseFragment
import com.chiului.android_mvvm_architecture.databinding.FragmentRefreshBinding
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModel

/**
 * 带刷新的列表示例
 * @author    神经大条蕾弟
 * @date      2021/02/04 16:28
 */
class RefreshFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentRefreshBinding
    private val viewModel: MainViewModel by viewModels {
        InjectorUtils.provideMainViewModelFactory(requireActivity())
    }

    private var columnCount: Int = 1

    private lateinit var refresh: SwipeRefreshLayout

    private lateinit var adapter: RefreshFragmentAdapter

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        // For Singleton instantiation
        @Volatile
        private var instance: RefreshFragment? = null

        fun getInstance(columnCount: Int): RefreshFragment {
            return instance ?: synchronized(this) {
                instance ?: RefreshFragment().also {
                    val args = Bundle()
                    args.putInt(ARG_COLUMN_COUNT, columnCount)
                    it.arguments = args
                    instance = it
                }
            }
        }
    }

    override fun initBundle(bundle: Bundle) {
        super.initBundle(bundle)
        columnCount = bundle.getInt(ARG_COLUMN_COUNT)
    }

    override fun initViewModel(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_refresh, container, false)
        binding.lifecycleOwner = this

        viewModel.refreshDate.observe(this) {
            // 改变列表数据（提交的数据不能与原数据同一内存地址，否则将不更新）
            adapter.submitList(it);
            // 关闭刷新
            refresh.isRefreshing = false;
        }

        return binding.root
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        initSwipeRefresh()
        initRecyclerView()
    }

    private fun initSwipeRefresh() {
        refresh = binding.refresh
        refresh.setColorSchemeResources(R.color.color_primary, R.color.color_primary_dark, R.color.color_accent)
        refresh.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        // 下拉刷新
        viewModel.getRefreshFragmentDate()
    }

    private fun initRecyclerView() {
        var recycler = binding.recycler

        if (columnCount <= 1) {
            recycler.layoutManager = LinearLayoutManager(context)
        } else {
            recycler.layoutManager = GridLayoutManager(context, columnCount)
        }

        // 也可以在 XML 里面设置 LayoutManager。
        // app:layoutManager="GridLayoutManager"
        // app:spanCount="2"
        // 绑定适配器
        adapter = RefreshFragmentAdapter()
        recycler.adapter = adapter
        // 获取数据
        viewModel.getRefreshFragmentDate()
    }

}