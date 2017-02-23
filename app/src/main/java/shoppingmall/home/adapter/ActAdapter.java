package shoppingmall.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import shoppingmall.home.bean.HomeBean;

/**
 * Created by 皇 上 on 2017/2/23.
 */

public class ActAdapter extends PagerAdapter {

    private final Context context;
    private final List<HomeBean.ResultBean.ActInfoBean> act_info;

    public ActAdapter(Context context, List<HomeBean.ResultBean.ActInfoBean> act_info) {
        this.context = context;
        this.act_info = act_info;
    }

    @Override
    public int getCount() {
        return act_info.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
