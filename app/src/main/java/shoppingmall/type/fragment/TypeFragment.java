package shoppingmall.type.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import shoppingmall.base.BaseFragment;
import shoppingmall.shoppingmall.R;

/**
 * Created by 皇 上 on 2017/2/22.
 */

public class TypeFragment extends BaseFragment {
    @InjectView(R.id.tl_1)
    SegmentTabLayout tl1;
    @InjectView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    @InjectView(R.id.fl_type)
    FrameLayout flType;
    private View view;
    private String[] titles = new String[]{"分类", "标签"};
    List<BaseFragment> fragments;
    //显示当前页面前 显示的上一个页面
    private Fragment tempFragment;


    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_type, null);
        ButterKnife.inject(this, view);

        initFragment();


        return view;
    }

    @Override
    public void initData() {
        super.initData();
        tl1.setTabData(titles);
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                Toast.makeText(context, "嘿嘿嘿" + position, Toast.LENGTH_SHORT).show();
                switchFragement(fragments.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.iv_type_search)
    public void onClick() {
    }

    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new TagFragment());
    }

    private void switchFragement(Fragment currentFragment) {

        //判断切换的页面与上一个页面是不是同一个界面
        if (tempFragment != currentFragment) {

            //获取Fragment 事务并开始
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

            //判断当前页面是否存在   不存在就添加
            if (!currentFragment.isAdded()) {
                //判断上一页面是否为空
                if (tempFragment != null) {
                    //上一页面缓存不是空的就隐藏
                    ft.hide(tempFragment);
                }
                //添加当前的页面
                ft.add(R.id.fl_type, currentFragment);

            } else {//当前页面缓存存在  就让它显示

                //判断上一页面是否为空
                if (tempFragment != null) {
                    //上一页面不是空的就隐藏
                    ft.hide(tempFragment);
                }
                //显示当前页面
                ft.show(currentFragment);
            }

            //提交事务
            ft.commit();

            //将当前页面转为缓存
            tempFragment = currentFragment;
        }
    }

}
