package com.chiului.android_mvvm_architecture.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.chiului.android_mvvm_architecture.ui.HomeFragment;
import com.chiului.android_mvvm_architecture.ui.ListSampleFragment;

/**
 * 主页的 ViewPager 2 适配器$
 *
 * @author 神经大条蕾弟
 * @date 2020/08/05 12:24
 */
public class MainAdapter extends FragmentStateAdapter {

    public MainAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment;
        switch (position){
            case 3:
                fragment = ListSampleFragment.newInstance();
                break;
            case 2:
            case 1:
            case 0:
            default:
                fragment = new HomeFragment();
                Bundle args = new Bundle();
                // Our object is just an integer :-P
                args.putString(HomeFragment.ARG_OBJECT, String.valueOf(position + 1));
                fragment.setArguments(args);
                break;
        }
        return fragment;

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
