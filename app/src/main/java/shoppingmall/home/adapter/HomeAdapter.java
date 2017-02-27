package shoppingmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.iwgang.countdownview.CountdownView;
import shoppingmall.home.activity.GoodsInfoActivity;
import shoppingmall.home.activity.WebViewActivity;
import shoppingmall.home.bean.GoodsBean;
import shoppingmall.home.bean.HomeBean;
import shoppingmall.home.bean.WebViewBean;
import shoppingmall.home.utils.Constants;
import shoppingmall.home.view.MyGridView;
import shoppingmall.shoppingmall.R;

/**
 * Created by 皇 上 on 2017/2/23.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    /**
     * 六种类型
     */

    /**
     * 横幅广告
     */
    public static final int BANNER = 0;

    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;
    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;


    /**
     * 当前类型
     */
    public int currentType = BANNER;


    public static final String WEBVIEW_BEAN = "webview_bean";
    public static final String GOODS_BEAN = "goods_bean";

    private final Context context;
    private final HomeBean.ResultBean result;


    private LayoutInflater inflater;


    public HomeAdapter(Context context, HomeBean.ResultBean result) {
        this.context = context;
        this.result = result;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 根据位置获取相应的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == ACT) {
            currentType = ACT;
        } else if (position == SECKILL) {
            currentType = SECKILL;
        } else if (position == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (position == HOT) {
            currentType = HOT;
        }
        return currentType;
    }


    @Override
    public int getItemCount() {
        //根据类型数量填写返回值
        return 6;
    }

    /**
     * 得到当前视图
     *
     * @param parent
     * @param viewType 当前布局
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(context, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(context, inflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(context, inflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(context, inflater.inflate(R.layout.seckill_item, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(context, inflater.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(context, inflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == BANNER) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            //banner绑定数据
            viewHolder.setData(result.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            //channel绑定数据
            channelViewHolder.setData(result.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            //act绑定数据
            actViewHolder.setData(result.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            //seckill绑定数据
            seckillViewHolder.setData(result.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            //recommend绑定数据
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(result.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(result.getHot_info());
        }
    }


    private class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private Banner banner;

        public BannerViewHolder(Context context, View itemView) {
            //当前布局回传给父类 父类无空参构造器  必须显示调用父类构造器 传view参数
            super(itemView);
            this.context = context;
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(final List<HomeBean.ResultBean.BannerInfoBean> banner_info) {

            //数据已经得到  为banner设置数据
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }

            //设置banner样式
//            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            //设置图片加载器
//            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
//            banner.setImages(images);
            //设置banner动画效果
//            banner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
//            banner.setBannerTitles(titles);
            //设置自动轮播，默认为true
//            banner.isAutoPlay(true);
            //设置轮播时间
//            banner.setDelayTime(1500);
            //设置指示器位置（当banner模式中有指示器时）
            banner.setIndicatorGravity(BannerConfig.RIGHT);
            //banner设置方法全部调用完毕时最后调用
//            banner.start();


            //设置banner延时
            banner.setDelayTime(4000);

            //设置banner变换样式
//            banner.setBannerAnimation(Transformer.Accordion);
//            banner.setBannerAnimation(Transformer.BackgroundToForeground);
//            banner.setBannerAnimation(Transformer.CubeIn);
//            banner.setBannerAnimation(Transformer.CubeOut);
//            banner.setBannerAnimation(Transformer.Default);
//            banner.setBannerAnimation(Transformer.DepthPage);
//            banner.setBannerAnimation(Transformer.FlipHorizontal);
            banner.setBannerAnimation(Transformer.FlipVertical);
//            banner.setBannerAnimation(Transformer.ForegroundToBackground);
//            banner.setBannerAnimation(Transformer.RotateDown);
//            banner.setBannerAnimation(Transformer.RotateUp);
//            banner.setBannerAnimation(Transformer.ScaleInOut);
//            banner.setBannerAnimation(Transformer.Stack);
//            banner.setBannerAnimation(Transformer.Tablet);
//            banner.setBannerAnimation(Transformer.ZoomIn);
//            banner.setBannerAnimation(Transformer.ZoomOut);
//            banner.setBannerAnimation(Transformer.ZoomOutSlide);

            banner.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            //具体方法内容自己去选择，
                            // 此方法是为了减少banner过多的依赖第三方包，
                            // 所以将这个权限开放给使用者去选择
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    }).start();


            //设置banner点击事件
//            banner.setOnBannerClickListener(new OnBannerClickListener() {
//                @Override
//                public void OnBannerClick(int position) {
//                    int bannerPosition = position - 1;
//                    Toast.makeText(context, "这是第" + bannerPosition, Toast.LENGTH_SHORT).show();
//                }
//            });
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                    int realPosition = position;
                    if (realPosition < banner_info.size()) {
                        String product_id = "";
                        String name = "";
                        String cover_price = "";
                        String image = "";
                        if (realPosition == 0) {
                            product_id = "627";
                            cover_price = "32.00";
                            name = "剑三T恤批发";
                        } else if (realPosition == 1) {
                            product_id = "21";
                            cover_price = "8.00";
                            name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                        } else {
                            product_id = "1341";
                            cover_price = "50.00";
                            name = "【蓝诺】《天下吾双》 剑网3同人本";
                        }

                        image = banner_info.get(position).getImage();

                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setName(name);
                        goodsBean.setCover_price(cover_price);
                        goodsBean.setFigure(image);
                        goodsBean.setProduct_id(product_id);

                        Intent intent = new Intent(context, GoodsInfoActivity.class);
                        intent.putExtra(GOODS_BEAN, goodsBean);
                        context.startActivity(intent);
                    }

                }
            });

        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @InjectView(R.id.gv_channel)
        GridView gvChannel;
        private ChannelAdapter channelAdapter;

        public ChannelViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.context = context;
        }

        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {

            //为 GridView设置适配器
            channelAdapter = new ChannelAdapter(context, channel_info);
            gvChannel.setAdapter(channelAdapter);

            //设置GridVie的item点击事件
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "点我我就是：" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @InjectView(R.id.vp_act)
        ViewPager vpAct;
        private ActAdapter actAdapter;

        public ActViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.context = context;

        }

        public void setData(final List<HomeBean.ResultBean.ActInfoBean> act_info) {
            //设置viewPager适配器
            actAdapter = new ActAdapter(context, act_info);


            //美化ViewPager库
            vpAct.setPageMargin(20);//设置page间间距，自行根据需求设置
            vpAct.setOffscreenPageLimit(3);//>=3
//            vpAct.setAdapter...//写法不变

            //setPageTransformer 决定动画效果
//            vpAct.setPageTransformer(true, new
//                    RotateDownPageTransformer());

            //组合动画效果
            vpAct.setPageTransformer(true,
                    new RotateDownPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));


            vpAct.setAdapter(actAdapter);
            //设置点击事件

            actAdapter.setOnItemClickListener(new ActAdapter.onItemClickListener() {
                @Override
                public void onItemClickListener(View view, int position) {
//                    Toast.makeText(context, "嘿嘿嘿" + position, Toast.LENGTH_SHORT).show();
                    HomeBean.ResultBean.ActInfoBean actInfoBean = act_info.get(position);

                    WebViewBean webViewBean = new WebViewBean();
                    //设置名称
                    webViewBean.setName(actInfoBean.getName());
                    webViewBean.setIcon_url(actInfoBean.getIcon_url());
                    webViewBean.setUrl(actInfoBean.getUrl());

                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra(WEBVIEW_BEAN, webViewBean);
                    context.startActivity(intent);
                }
            });
        }
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.countdownview)
        CountdownView countdownview;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.rv_seckill)
        RecyclerView rvSeckill;
        private final Context context;

        public SeckillViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.context = context;
        }

        public void setData(final HomeBean.ResultBean.SeckillInfoBean seckill_info) {

            //设置适配器
            SeckillAdapter seckillAdapter = new SeckillAdapter(context, seckill_info);
            rvSeckill.setAdapter(seckillAdapter);

            //设置布局管理器
            rvSeckill.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            //设置点击事件
            seckillAdapter.setOnItemClickListener(new SeckillAdapter.onItemClickListener() {
                @Override
                public void onItemClickListener(int position) {
//                    Toast.makeText(context, "嘿嘿嘿" + position, Toast.LENGTH_SHORT).show();

                    HomeBean.ResultBean.SeckillInfoBean.ListBean seckillListBean = seckill_info.getList().get(position);

                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(seckillListBean.getName());
                    goodsBean.setProduct_id(seckillListBean.getProduct_id());
                    goodsBean.setFigure(seckillListBean.getFigure());
                    goodsBean.setCover_price(seckillListBean.getCover_price());

                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    context.startActivity(intent);


                }

            });

            //设置秒杀器时间
            countdownview.setTag("time");
            long time = Long.parseLong(seckill_info.getEnd_time()) - Long.parseLong(seckill_info.getStart_time());
            countdownview.start(time);
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @InjectView(R.id.tv_more_recommend)
        TextView tvMoreRecommend;
        @InjectView(R.id.gv_recommend)
        GridView gvRecommend;

        public RecommendViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.context = context;
        }

        public void setData(final List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {

            RecommendAdapter recommendAdapter = new RecommendAdapter(context, recommend_info);
            gvRecommend.setAdapter(recommendAdapter);
            //设置点击事件
            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "嘿嘿嘿" + position, Toast.LENGTH_SHORT).show();

                    HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);
                    //传递数据
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                    goodsBean.setFigure(recommendInfoBean.getFigure());

                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    context.startActivity(intent);
                }
            });
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @InjectView(R.id.tv_more_hot)
        TextView tvMoreHot;
        @InjectView(R.id.gv_hot)
        MyGridView gvHot;


        public HotViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.context = context;
        }

        public void setData(final List<HomeBean.ResultBean.HotInfoBean> hot_info) {
            HotAdapter hotAdapter = new HotAdapter(context, hot_info);
            gvHot.setAdapter(hotAdapter);

            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(context, "嘿嘿嘿" + position, Toast.LENGTH_SHORT).show();

                    HomeBean.ResultBean.HotInfoBean hotInfoBean = hot_info.get(position);

                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                    goodsBean.setName(hotInfoBean.getName());

                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);
                    context.startActivity(intent);
                }
            });
        }
    }
}
