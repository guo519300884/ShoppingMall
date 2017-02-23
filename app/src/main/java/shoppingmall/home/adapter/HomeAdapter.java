package shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import shoppingmall.home.bean.HomeBean;
import shoppingmall.shoppingmall.R;

/**
 * Created by 皇 上 on 2017/2/23.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;


    /**
     * 当前类型
     */
    public int currentType = BANNER;

    private final Context context;
    private final HomeBean.ResultBean result;
    private LayoutInflater inflater;

    public HomeAdapter(Context context, HomeBean.ResultBean result) {
        this.context = context;
        this.result = result;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 根据位置获取相应的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (currentType == BANNER) {
            currentType = BANNER;
        } else if (currentType == CHANNEL) {
            currentType = CHANNEL;
        } else if (currentType == ACT) {
            currentType = ACT;
        } else if (currentType == SECKILL) {
            currentType = SECKILL;
        } else if (currentType == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (currentType == HOT) {
            currentType = HOT;
        }
        return currentType;
    }


    @Override
    public int getItemCount() {
        //根据类型数量填写返回值
        return 1;
    }

    /**
     * 得到当前视图
     *
     * @param parent
     * @param viewType 当前布局
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(context, inflater.inflate(R.layout.banner_viewpager, null));
        }
        return null;
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == BANNER) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;

            viewHolder.setData(result.getBanner_info());

        }
    }


    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        public TextView tv_title;

        public BannerViewHolder(Context context, View itemView) {
            //当前布局回传给父类 父类无空参构造器  必须显示调用父类构造器 传view参数
            super(itemView);
            this.context = context;
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            tv_title.setText("6666666666666666666");
        }
    }
}
