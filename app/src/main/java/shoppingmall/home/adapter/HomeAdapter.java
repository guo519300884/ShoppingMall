package shoppingmall.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

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


    /**
     * 根据位置获取相应的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (currentType == BANNER) {
            return currentType;
        } else if (currentType == CHANNEL) {

        } else if (currentType == ACT) {

        } else if (currentType == SECKILL) {

        } else if (currentType == RECOMMEND) {

        } else if (currentType == HOT) {

        }

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
