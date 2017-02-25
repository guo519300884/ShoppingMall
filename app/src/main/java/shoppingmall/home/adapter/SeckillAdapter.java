package shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class SeckillAdapter extends RecyclerView.Adapter<SeckillAdapter.MyViewHolder> {

    private final Context context;
    private final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> seckill_info;
    private LayoutInflater inflater;

    public SeckillAdapter(Context context, HomeBean.ResultBean.SeckillInfoBean seckill_info) {
        this.context = context;
        this.seckill_info = seckill_info.getList();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return seckill_info.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_seckill, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        HomeBean.ResultBean.SeckillInfoBean.ListBean seckillBean = seckill_info.get(position);
        //价格
        holder.tvCoverPrice.setText("￥" + seckillBean.getCover_price());
        holder.tvOriginPrice.setText("￥" + seckillBean.getOrigin_price());
        //网络获取图片
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + seckillBean.getFigure())
                .into(holder.ivFigure);

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_figure)
        ImageView ivFigure;
        @InjectView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @InjectView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClickListener(getLayoutPosition());
                    }
                }
            });
        }

    }

    public interface onItemClickListener {
        void onItemClickListener(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    private onItemClickListener listener;

}
