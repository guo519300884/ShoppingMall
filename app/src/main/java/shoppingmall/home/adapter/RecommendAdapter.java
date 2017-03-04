package shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.home.bean.HomeBean;
import shoppingmall.utils.Constants;
import shoppingmall.shoppingmall.R;

/**
 * Created by 皇 上 on 2017/2/25.
 */
public class RecommendAdapter extends BaseAdapter {
    private final Context context;
    private final List<HomeBean.ResultBean.RecommendInfoBean> recommend_info;

    public RecommendAdapter(Context context, List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
        this.context = context;
        this.recommend_info = recommend_info;
    }

    @Override
    public int getCount() {
        return recommend_info == null ? 0 : recommend_info.size();
    }

    @Override
    public Object getItem(int position) {
        return recommend_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_recommend, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);

        viewHolder.tvName.setText(recommendInfoBean.getName());
        viewHolder.tvPrice.setText("￥" + recommendInfoBean.getCover_price());
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + recommendInfoBean.getFigure())
                .into(viewHolder.ivRecommend);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_recommend)
        ImageView ivRecommend;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
