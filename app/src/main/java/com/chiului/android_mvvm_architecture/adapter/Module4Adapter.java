package com.chiului.android_mvvm_architecture.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.chiului.android_mvvm_architecture.ui.ListFragment;
import com.chiului.android_mvvm_architecture.ui.PagingFragment;
import com.chiului.android_mvvm_architecture.ui.RefreshFragment;

/**
 * Module4 ViewPager 适配器
 * @author 神经大条蕾弟
 * @date   2020/09/28 18:11
 */
public class Module4Adapter extends FragmentStateAdapter {

    public Module4Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment;
        switch (position){
            case 2:
                fragment = PagingFragment.newInstance(2);
                break;
            case 1:
                fragment = RefreshFragment.newInstance(2);
                break;
            case 0:
            default:
                fragment = ListFragment.newInstance(1);
                break;
        }
        return fragment;

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
