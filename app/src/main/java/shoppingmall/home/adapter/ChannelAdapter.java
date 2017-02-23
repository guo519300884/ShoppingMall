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
 * Created by 皇 上 on 2017/2/23.
 */
public class ChannelAdapter extends BaseAdapter {

    private final List<HomeBean.ResultBean.ChannelInfoBean> channel_info;
    private final Context context;

    public ChannelAdapter(Context context, List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
        this.context = context;
        this.channel_info = channel_info;
    }

    @Override
    public int getCount() {
        return channel_info.size();
    }

    @Override
    public Object getItem(int position) {
        return channel_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_channel, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置获取相应的数据
        HomeBean.ResultBean.ChannelInfoBean channelInfoBean = channel_info.get(position);
        //设置相应的名字
        viewHolder.tvChannel.setText(channelInfoBean.getChannel_name());
        //请求获取相应的图片
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + channelInfoBean.getImage())
                .crossFade()
                .into(viewHolder.ivChannel);

        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.iv_channel)
        ImageView ivChannel;
        @InjectView(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
