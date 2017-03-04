package shoppingmall.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.community.bean.NewPostBean;
import shoppingmall.shoppingmall.R;
import shoppingmall.utils.Constants;

/**
 * Created by 皇 上 on 2017/3/4.
 */

public class NewPostAdapter extends BaseAdapter {

    private final Context context;
    private final List<NewPostBean.ResultBean> newPostBeanResult;

    public NewPostAdapter(Context context, List<NewPostBean.ResultBean> newPostBeanResult) {
        this.context = context;
        this.newPostBeanResult = newPostBeanResult;
    }

    @Override
    public int getCount() {
        return newPostBeanResult.size();
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
            convertView = View.inflate(context, R.layout.item_listview_newpost, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置的到相应的数据
        NewPostBean.ResultBean resultBean = newPostBeanResult.get(position);
        //网络获取头像图片
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + resultBean.getAvatar())
                .into(viewHolder.ibNewPostAvatar);
        //得到名称
        viewHolder.tvCommunityUsername.setText(resultBean.getUsername());

        //网络获取图片
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + resultBean.getFigure())
                .into(viewHolder.ivCommunityFigure);

        viewHolder.tvCommunityComments.setText(resultBean.getComments());
        viewHolder.tvCommunitySaying.setText(resultBean.getSaying());
        viewHolder.tvCommunityLikes.setText(resultBean.getLikes());

        //显示弹幕
        List<String> strings = resultBean.getComment_list();
        if (strings != null && strings.size() > 0) {
            //有弹幕数据
            List<IDanmakuItem> list = initItens(viewHolder.danmakuView, strings);
            Collections.shuffle(list);
            viewHolder.danmakuView.addItem(list, true);
            viewHolder.danmakuView.show();
            viewHolder.danmakuView.setVisibility(View.VISIBLE);
        } else {
            viewHolder.danmakuView.hide();
            viewHolder.danmakuView.setVisibility(View.GONE);
        }


        viewHolder.tvCommunityLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "老子就爱你这种", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.tvCommunityComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "老子要参与说说", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    private List<IDanmakuItem> initItens(DanmakuView danmakuView, List<String> strings) {
        List<IDanmakuItem> list = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            DanmakuItem item = new DanmakuItem(context, strings.get(i), danmakuView.getWidth());
            list.add(item);
        }
        return list;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_community_username)
        TextView tvCommunityUsername;
        @InjectView(R.id.tv_community_addtime)
        TextView tvCommunityAddtime;
        @InjectView(R.id.rl)
        RelativeLayout rl;
        @InjectView(R.id.ib_new_post_avatar)
        ImageButton ibNewPostAvatar;
        @InjectView(R.id.iv_community_figure)
        ImageView ivCommunityFigure;
        @InjectView(R.id.danmakuView)
        DanmakuView danmakuView;
        @InjectView(R.id.tv_community_saying)
        TextView tvCommunitySaying;
        @InjectView(R.id.tv_community_likes)
        TextView tvCommunityLikes;
        @InjectView(R.id.tv_community_comments)
        TextView tvCommunityComments;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
