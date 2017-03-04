package shoppingmall.community.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import shoppingmall.community.fragment.HotPostFragment;
import shoppingmall.community.fragment.NewPostFragment;

/**
 * Created by 皇 上 on 2017/3/4.
 */

public class CommunityAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;

    public CommunityAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();
        NewPostFragment newPostFragment = new NewPostFragment();
        HotPostFragment hotPostFragment = new HotPostFragment();

        fragmentList.add(newPostFragment);
        fragmentList.add(hotPostFragment);
    }


    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
