package shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.home.bean.TypeListBean;
import shoppingmall.shoppingmall.R;
import shoppingmall.utils.Constants;

/**
 * Created by 皇 上 on 2017/3/6.
 */

public class GoosListAdapter extends RecyclerView.Adapter<GoosListAdapter.MyViewHolder> {

    private final Context context;
    private final List<TypeListBean.ResultBean.PageDataBean> data;
    private LayoutInflater inflater;

    public GoosListAdapter(Context context, List<TypeListBean.ResultBean.PageDataBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_goods_list, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //根据位置获取到相应的数据
        TypeListBean.ResultBean.PageDataBean pageDataBean = data.get(position);

        //获取图片
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + pageDataBean.getFigure())
                .placeholder(R.drawable.new_img_loading_2)
                .into(holder.ivHot);
        //设置名称
        holder.tvName.setText(pageDataBean.getName());
        //设置价格
        holder.tvPrice.setText("￥" + pageDataBean.getCover_price());


    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_hot)
        ImageView ivHot;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(data.get(getLayoutPosition()));
                    }
                }
            });
        }
    }


    //定义一个接口
    public interface OnItemClickListener {
        void onItemClick(TypeListBean.ResultBean.PageDataBean datas);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
