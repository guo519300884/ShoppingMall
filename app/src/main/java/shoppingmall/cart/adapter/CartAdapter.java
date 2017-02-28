package shoppingmall.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.cart.view.AddSubView;
import shoppingmall.home.bean.GoodsBean;
import shoppingmall.home.utils.Constants;
import shoppingmall.shoppingmall.R;

/**
 * Created by 皇 上 on 2017/2/28.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private final Context context;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxDeleteAll;
    private final CheckBox checkboxAll;

    public CartAdapter(Context context, List<GoodsBean> list, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox checkboxDeleteAll) {
        this.context = context;
        this.datas = list;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.checkboxDeleteAll = checkboxDeleteAll;

        showTotalPrice();
    }

    private void showTotalPrice() {
        //显示合计价格
        tvShopcartTotal.setText("合计：" + getTotalPrice());
    }

    //返回的是总价格
    public double getTotalPrice() {

        double totalPrice = 0;

        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isChecked()) {
                    totalPrice += Double.parseDouble(goodsBean.getCover_price()) * goodsBean.getNumber();
                }
            }
        }
        return totalPrice;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 创建视图 —— 得到视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context, R.layout.item_shop_cart, null));
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //得到数据
        GoodsBean goodsBean = datas.get(position);

        //绑定数据


        holder.cbGov.setChecked(goodsBean.isChecked());
        //图片
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE + goodsBean.getFigure())
                .into(holder.ivGov);
        //设置价格
        holder.tvPriceGov.setText("￥" + goodsBean.getCover_price());
        //设置名称
        holder.tvDescGov.setText(goodsBean.getName());
        //设置数量
        holder.addSubView.setValue(goodsBean.getNumber());

//        holder.addSubView.setMinValue(1);

        //设置库存  实际情况库存量来源于服务器
        holder.addSubView.setMaxValue(100);


    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.cb_gov)
        CheckBox cbGov;
        @InjectView(R.id.iv_gov)
        ImageView ivGov;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @InjectView(R.id.addSubView)
        AddSubView addSubView;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
