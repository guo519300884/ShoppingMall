package shoppingmall.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.community.bean.HotPostBean;
import shoppingmall.shoppingmall.R;
import shoppingmall.utils.Constants;

/**
 * Created by 皇 上 on 2017/3/4.
 */

public class HotPostAdapter extends BaseAdapter {

    private final Context context;
    private final List<HotPostBean.ResultBean> hotPostBeanResult;

    public HotPostAdapter(Context context, List<HotPostBean.ResultBean> hotPostBeanResult) {
        this.context = context;
        this.hotPostBeanResult = hotPostBeanResult;
    }

    @Override
    public int getCount() {
        return hotPostBeanResult.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_hotpost_listview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HotPostBean.ResultBean resultBean = hotPostBeanResult.get(position);

        Glide.with(context).load(Constants.BASE_URL_IMAGE + resultBean.getAvatar())
                .into(viewHolder.ivNewPostAvatar);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_hot_username)
        TextView tvHotUsername;
        @InjectView(R.id.tv_hot_addtime)
        TextView tvHotAddtime;
        @InjectView(R.id.rl)
        RelativeLayout rl;
        @InjectView(R.id.iv_new_post_avatar)
        ImageView ivNewPostAvatar;
        @InjectView(R.id.iv_hot_figure)
        ImageView ivHotFigure;
        @InjectView(R.id.ll_hot_post)
        LinearLayout llHotPost;
        @InjectView(R.id.tv_hot_saying)
        TextView tvHotSaying;
        @InjectView(R.id.tv_hot_likes)
        TextView tvHotLikes;
        @InjectView(R.id.tv_hot_comments)
        TextView tvHotComments;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
