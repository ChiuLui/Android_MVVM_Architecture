package com.chiului.android_mvvm_architecture.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chiului.android_mvvm_architecture.ui.HomeFragment
import com.chiului.android_mvvm_architecture.ui.ListSampleFragment
import com.chiului.android_mvvm_architecture.ui.MineFragment

/**
 * 主页 Adapter$
 * @author    神经大条蕾弟
 * @date      2021/02/05 14:42
 */
class MainAdapter constructor(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val args = Bundle()
        // Our object is just an integer :-P
        args.putString(HomeFragment.ARG_OBJECT, (position + 1).toString())

        return when (position) {
            2 -> ListSampleFragment.getInstance()
            3 -> MineFragment.getInstance()
            else -> {
                val homeFragment = HomeFragment()
                homeFragment.arguments = args
                return homeFragment
            }
        }
    }
}