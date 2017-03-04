package shoppingmall.community.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import shoppingmall.base.BaseFragment;

/**
 * Created by 皇 上 on 2017/3/4.
 */

public class CommunityAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> fragments;
    private String[] titles = new String[]{"新帖", "热帖"};

    public CommunityAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
