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
import shoppingmall.home.utils.Constants;
import shoppingmall.shoppingmall.R;

/**
 * Created by 皇 上 on 2017/2/25.
 */
public class HotAdapter extends BaseAdapter {
    private final Context context;
    private final List<HomeBean.ResultBean.HotInfoBean> hot_info;

    public HotAdapter(Context context, List<HomeBean.ResultBean.HotInfoBean> hot_info) {
        this.context = context;
        this.hot_info = hot_info;
    }

    @Override
    public int getCount() {
        return hot_info == null ? 0 : hot_info.size();
    }

    @Override
    public Object getItem(int position) {
        return hot_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_hot, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HomeBean.ResultBean.HotInfoBean hotInfoBean = hot_info.get(position);

        viewHolder.tvName.setText(hotInfoBean.getName());
        viewHolder.tvPrice.setText(hotInfoBean.getCover_price());

        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + hotInfoBean.getFigure())
                .into(viewHolder.ivHot);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_hot)
        ImageView ivHot;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
