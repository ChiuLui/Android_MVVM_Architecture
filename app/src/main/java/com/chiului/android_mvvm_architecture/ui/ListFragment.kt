package com.chiului.android_mvvm_architecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chiului.android_mvvm_architecture.R
import com.chiului.android_mvvm_architecture.adapter.ListFragmentAdapter
import com.chiului.android_mvvm_architecture.base.BaseFragment
import com.chiului.android_mvvm_architecture.databinding.FragmentListBinding
import com.chiului.android_mvvm_architecture.dummy.DummyItemBean
import com.chiului.android_mvvm_architecture.utilities.InjectorUtils
import com.chiului.android_mvvm_architecture.viewmodel.MainViewModel
import java.util.*

/**
 * RecyclerView 的示例$
 * @author    神经大条蕾弟
 * @date      2021/02/04 16:28
 */
class ListFragment : BaseFragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: MainViewModel by viewModels {
        InjectorUtils.provideMainViewModelFactory(requireActivity())
    }

    private var dataList: MutableList<DummyItemBean> = ArrayList()
    private lateinit var adapter: ListFragmentAdapter

    private var columnCount: Int = 1

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        // For Singleton instantiation
        @Volatile
        private var instance: ListFragment? = null

        fun getInstance(columnCount: Int): ListFragment {
            return instance ?: synchronized(this) {
                instance ?: ListFragment().also {
                    val args = Bundle()
                    args.putInt(ARG_COLUMN_COUNT, columnCount)
                    it.arguments = args
                    instance = it
                }
            }
        }

    }

    override fun initViewModel(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.lifecycleOwner = this

        viewModel.listDate.observe(this) {
            dataList.clear();
            dataList.addAll(it);
            // 设置数据
            adapter.submitList(dataList);
        }

        return binding.root
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
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
        adapter = ListFragmentAdapter(dataList)
        recycler.adapter = adapter
        // 获取数据
        viewModel.getListFragmentDate()
    }

    override fun initBundle(bundle: Bundle) {
        super.initBundle(bundle)
        columnCount = bundle.getInt(ARG_COLUMN_COUNT)
    }

}