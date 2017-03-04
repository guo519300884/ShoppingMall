package shoppingmall.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import shoppingmall.home.bean.HomeBean;
import shoppingmall.utils.Constants;

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
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        final ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);

        HomeBean.ResultBean.ActInfoBean actInfoBean = act_info.get(position);
        //联网请求得到相应的数据
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + actInfoBean.getIcon_url())
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClickListener(imageView, position);
                }
            }
        });

        return imageView;
    }

    public interface onItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    private onItemClickListener listener;

}
