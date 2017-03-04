package shoppingmall.type.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.home.activity.GoodsInfoActivity;
import shoppingmall.home.adapter.HomeAdapter;
import shoppingmall.home.bean.GoodsBean;
import shoppingmall.home.utils.Constants;
import shoppingmall.shoppingmall.R;
import shoppingmall.type.bean.TypeBean;
import shoppingmall.utils.DensityUtil;

/**
 * Created by 皇 上 on 2017/3/3.
 */
public class TypeRightAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<TypeBean.ResultBean.HotProductListBean> hot;
    private final List<TypeBean.ResultBean.ChildBean> child;

    private LayoutInflater inflater;

    /**
     * 热卖推荐
     */
    private static final int HOT = 0;
    /**
     * 常用分类
     */
    private static final int COMMON = 1;

    private int currentType = HOT;

    public TypeRightAdapter(Context context, List<TypeBean.ResultBean> result) {
        this.context = context;
        hot = result.get(0).getHot_product_list();
        child = result.get(0).getChild();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return 1 + child.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            currentType = HOT;
        } else {
            currentType = COMMON;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOT) {
            return new HotViewHolder(inflater.inflate(R.layout.item_hot_right, null));
        } else if (viewType == COMMON) {
            return new CommonViewHolder(inflater.inflate(R.layout.item_common_right, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(hot);
        } else if (getItemViewType(position) == COMMON) {
            CommonViewHolder commonViewHolder = (CommonViewHolder) holder;
            int realPosition = position - 1;
            commonViewHolder.setData(child.get(realPosition));
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.ll_hot_right)
        LinearLayout llHotRight;
        @InjectView(R.id.hsl_hot_right)
        HorizontalScrollView hslHotRight;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final List<TypeBean.ResultBean.HotProductListBean> hot) {

            for (int i = 0; i < hot.size(); i++) {
                TypeBean.ResultBean.HotProductListBean hotProductListBean = hot.get(i);

                //最外层线性布局
                LinearLayout layout = new LinearLayout(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
                params.setMargins((DensityUtil.dip2px(context, 5)),
                        0, DensityUtil.dip2px(context, 5),
                        DensityUtil.dip2px(context, 20));

                //设置居中
                layout.setGravity(Gravity.CENTER);
                //设置竖直方向
                layout.setOrientation(LinearLayout.VERTICAL);

                /**
                 * 创建图片
                 */
                ImageView imageView = new ImageView(context);
                //设置图片的宽高
                LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(context, 80),
                        DensityUtil.dip2px(context, 80));
                //设置图片间距
                ivParams.setMargins(0, 0, 0, DensityUtil.dip2px(context, 10));
                //网络请求图片
                Glide.with(context)
                        .load(Constants.BASE_URL_IMAGE + hotProductListBean.getFigure())
                        .placeholder(R.drawable.new_img_loading_2)
                        .into(imageView);
                //将图片添加进线性布局
                layout.addView(imageView, ivParams);

                /**
                 * 创建文本
                 */
                TextView textView = new TextView(context);
                LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.parseColor("#ed3f3f"));
                textView.setText("￥" + hotProductListBean.getCover_price());
                //将文本添加进线性布局
                layout.addView(textView, tvParams);
                /**
                 * 将线性布局添加到最外层的线性布局内
                 */
                llHotRight.addView(layout, params);

                //设置点击事件
                layout.setTag(i);

                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag();
//                        Toast.makeText(context, "" + hot.get(position).getName(), Toast.LENGTH_SHORT).show();
                        String name = hot.get(position).getName();
                        String cover_price = hot.get(position).getCover_price();
                        String product_id = hot.get(position).getProduct_id();
                        String figure = hot.get(position).getFigure();

                        //创建
                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setName(name);
                        goodsBean.setCover_price(cover_price);
                        goodsBean.setFigure(figure);
                        goodsBean.setProduct_id(product_id);

                        Intent intent = new Intent(context, GoodsInfoActivity.class);
                        intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                        context.startActivity(intent);
                    }
                });
            }
        }
    }

    class CommonViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_ordinary_right)
        ImageView ivOrdinaryRight;
        @InjectView(R.id.tv_ordinary_right)
        TextView tvOrdinaryRight;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;

        public CommonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final TypeBean.ResultBean.ChildBean childBean) {

            //网络请求图片
            Glide.with(context)
                    .load(Constants.BASE_URL_IMAGE + childBean.getPic())
                    .placeholder(R.drawable.new_img_loading_2)
                    .into(ivOrdinaryRight);
            //设置文本
            tvOrdinaryRight.setText(childBean.getName());

            llRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "" + childBean.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
