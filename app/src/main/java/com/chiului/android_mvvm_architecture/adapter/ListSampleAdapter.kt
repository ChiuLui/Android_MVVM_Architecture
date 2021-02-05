package com.chiului.android_mvvm_architecture.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chiului.android_mvvm_architecture.ui.ListFragment
import com.chiului.android_mvvm_architecture.ui.PagingFragment
import com.chiului.android_mvvm_architecture.ui.RefreshFragment

/**
 * 列表示例适配器$
 * @author    神经大条蕾弟
 * @date      2021/02/05 10:27
 */
class ListSampleAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        return when(position){
            0 -> ListFragment.getInstance(1)
            1 -> RefreshFragment.newInstance(2)
            2 -> PagingFragment.newInstance(2)
            else -> ListFragment.getInstance(position)
        }
    }

}