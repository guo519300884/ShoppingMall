package shoppingmall.community.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
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
import shoppingmall.utils.DensityUtil;

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
        //根据位置获取相应的数据
        HotPostBean.ResultBean resultBean = hotPostBeanResult.get(position);
        //头像
        Glide.with(context).load(Constants.BASE_URL_IMAGE + resultBean.getAvatar())
                .into(viewHolder.ivNewPostAvatar);
        //图片
        Glide.with(context).load(Constants.BASE_URL_IMAGE + resultBean.getFigure())
                .into(viewHolder.ivHotFigure);

        viewHolder.tvHotComments.setText(resultBean.getComments());
        viewHolder.tvHotLikes.setText(resultBean.getLikes());
        viewHolder.tvHotSaying.setText(resultBean.getSaying());
        viewHolder.tvHotUsername.setText(resultBean.getUsername());
//        viewHolder.tvHotAddtime.setText(resultBean.getAdd_time());

        //设置置顶
        String is_top = resultBean.getIs_top();
        if (is_top.equals("1")) {
            //显示置顶
            TextView hotTextView = new TextView(context);
            //显示的字
            hotTextView.setText("置頂");
            //居中
            hotTextView.setGravity(Gravity.CENTER);
            //字体颜色
            hotTextView.setTextColor(Color.WHITE);
            //设置背景颜色
            hotTextView.setBackgroundResource(R.drawable.is_top_shape);

            //设置边距
            hotTextView.setPadding(DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5),
                    DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5));
            //添加之前必须先移除原来视图
            viewHolder.llHotPost.removeAllViews();

            //参数
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            textViewLp.setMargins(DensityUtil.dip2px(context, 5), 0, 0, 0);

            //添加进视图
            viewHolder.llHotPost.addView(hotTextView, textViewLp);
        }

        //设置热门
        String is_hot = resultBean.getIs_hot();
        if (is_hot.equals("1")) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(context);
            //设置外边距
            textViewLp.setMargins(DensityUtil.dip2px(context, 5), 0, DensityUtil.dip2px(context, 5), 0);
            //显示的字
            textView.setText("热门");
            //居中
            textView.setGravity(Gravity.CENTER);
            //字体颜色
            textView.setTextColor(Color.WHITE);
            //设置内边距
            textView.setPadding(DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5),
                    DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5));
            //设置背景颜色
            textView.setBackgroundResource(R.drawable.is_hot_shape);
            //添加到视图中
            viewHolder.llHotPost.addView(textView, textViewLp);
        }

        //设置 精华
        String is_essence = resultBean.getIs_essence();
        if (is_essence.equals("1")) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(context);
            //距离右边距离
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(context, 5), 0);
            //显示的字
            textView.setText("精华");
            //设置内边距
            textView.setPadding(DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5),
                    DensityUtil.dip2px(context, 5), DensityUtil.dip2px(context, 5));
            //设置居中
            textView.setGravity(Gravity.CENTER);
            //设置字体颜色
            textView.setTextColor(Color.WHITE);
            //设置背景颜色
            textView.setBackgroundResource(R.drawable.is_essence_shape);
            //添加
            viewHolder.llHotPost.addView(textView, textViewLp);
        }


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
