package shoppingmall.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.cart.utils.CartStorage;
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

    //显示合计价格
    public void showTotalPrice() {
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
        final GoodsBean goodsBean = datas.get(position);
        //绑定数据

        //设置选择状态
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

        holder.addSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void OnNumberChange(int value) {
                //回调数量
                goodsBean.setNumber(value);

                CartStorage.getInstance(context).updataData(goodsBean);

                showTotalPrice();
            }
        });


    }

    //校验是否全选
    public void checkAll() {

        int number = 0;

        if (datas != null && datas.size() > 0) {

            for (int i = 0; i < datas.size(); i++) {

                GoodsBean goodsBean = datas.get(i);

                if (!goodsBean.isChecked()) {
                    //有一条没有选中就不是全选
                    checkboxAll.setChecked(false);
                    checkboxDeleteAll.setChecked(false);

                } else {
                    // 每选中一条 选中数就加 1
                    number++;
                }
                //若选中的数与购物车内的商品类数相等 就是全选
                if (datas.size() == number) {
                    checkboxAll.setChecked(true);
                    checkboxDeleteAll.setChecked(true);
                }
            }
        } else {
            checkboxAll.setChecked(true);
            checkboxDeleteAll.setChecked(true);
        }
    }

    //删除数据
    public void deleteData() {
        if (datas != null && datas.size() > 0) {
//            for (int i = 0; i < datas.size(); i++) {
//                GoodsBean goodsBean = datas.get(i);
//                if (goodsBean.isChecked()) {
//                    //1.内存中删除
//                    datas.remove(goodsBean);
//                    //2.本地
//                    CartStorage.getInstance(context).deleteData(goodsBean);
//                    //3.刷新数据
//                    notifyItemRemoved(i);
//
//                    i--;
//                }
//            }
            for (Iterator iterator = datas.iterator(); iterator.hasNext(); ) {
                GoodsBean goodsBean = (GoodsBean) iterator.next();
                if (goodsBean.isChecked()) {
                    int position = datas.indexOf(goodsBean);
                    //内存中移除
                    iterator.remove();
                    //本地同步
                    CartStorage.getInstance(context).deleteData(goodsBean);
                    //删除后刷新页面
                    notifyItemRemoved(position);
                }

            }
        }


    }

    public void checkAll_none(boolean isChecked) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                //设置是否勾选状态
                goodsBean.setChecked(isChecked);
                checkboxAll.setChecked(isChecked);
                checkboxDeleteAll.setChecked(isChecked);
                //刷新视图
                notifyItemChanged(i);
            }
        }
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

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.OnItemClick(v, getLayoutPosition());
                    }
                }
            });
        }
    }

    //点击item的监听
    public interface OnItemClickListener {
        public void OnItemClick(View view, int position);
    }

    //回调点击事件的监听
    private OnItemClickListener itemClickListener;

    //设置itme的监听
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
