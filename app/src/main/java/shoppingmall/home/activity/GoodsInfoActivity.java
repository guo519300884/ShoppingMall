package shoppingmall.home.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import shoppingmall.cart.utils.CartStorage;
import shoppingmall.home.adapter.HomeAdapter;
import shoppingmall.home.bean.GoodsBean;
import shoppingmall.home.utils.Constants;
import shoppingmall.shoppingmall.R;

public class GoodsInfoActivity extends AppCompatActivity {

    @InjectView(R.id.ib_good_info_back)
    ImageButton ibGoodInfoBack;
    @InjectView(R.id.ib_good_info_more)
    ImageButton ibGoodInfoMore;
    @InjectView(R.id.iv_good_info_image)
    ImageView ivGoodInfoImage;
    @InjectView(R.id.tv_good_info_name)
    TextView tvGoodInfoName;
    @InjectView(R.id.tv_good_info_desc)
    TextView tvGoodInfoDesc;
    @InjectView(R.id.tv_good_info_price)
    TextView tvGoodInfoPrice;
    @InjectView(R.id.tv_good_info_store)
    TextView tvGoodInfoStore;
    @InjectView(R.id.tv_good_info_style)
    TextView tvGoodInfoStyle;
    @InjectView(R.id.wb_good_info_more)
    WebView wbGoodInfoMore;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.tv_good_info_callcenter)
    TextView tvGoodInfoCallcenter;
    @InjectView(R.id.tv_good_info_collection)
    TextView tvGoodInfoCollection;
    @InjectView(R.id.tv_good_info_cart)
    TextView tvGoodInfoCart;
    @InjectView(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;
    @InjectView(R.id.ll_goods_root)
    LinearLayout llGoodsRoot;
    @InjectView(R.id.tv_more_share)
    TextView tvMoreShare;
    @InjectView(R.id.tv_more_search)
    TextView tvMoreSearch;
    @InjectView(R.id.tv_more_home)
    TextView tvMoreHome;
    @InjectView(R.id.btn_more)
    Button btnMore;
    @InjectView(R.id.ll_root)
    LinearLayout llRoot;
    private GoodsBean goodsBean;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.inject(this);
        getData();
    }

    private void getData() {
        goodsBean = (GoodsBean) getIntent().getSerializableExtra(HomeAdapter.GOODS_BEAN);

        setData();
//        if (goodsBean != null) {
//            Toast.makeText(this, "嘿嘿嘿" + goodsBean.toString(), Toast.LENGTH_SHORT).show();
//        }
    }

    private void setData() {
        //设置图片
        Glide.with(this)
                .load(Constants.BASE_URL_IMAGE + goodsBean.getFigure())
                .into(ivGoodInfoImage);
        //设置名字
        tvGoodInfoName.setText(goodsBean.getName());
        //设置价格
        tvGoodInfoPrice.setText("￥" + goodsBean.getCover_price());

        //设置商品详情要加载的网页  实际工作是需要她动态传入
        loadWeb("http://mp.weixin.qq.com/s/Cf3DrW2lnlb-w4wYaxOEZg");
    }

    private void loadWeb(String url) {

        webSettings = wbGoodInfoMore.getSettings();
        //设置支出Js
        webSettings.setJavaScriptEnabled(true);
        //设置添加缩放屏幕按钮
        webSettings.setBuiltInZoomControls(true);
        //设置屏幕双击变化大小
        webSettings.setUseWideViewPort(true);
        //设置WebViewClient,若不设置，打开新链接时将调起系统自带浏览器
        wbGoodInfoMore.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //判断版本
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }

                return true;
            }
        });

        wbGoodInfoMore.loadUrl(url);

    }

    @OnClick({R.id.ib_good_info_back, R.id.ib_good_info_more, R.id.tv_good_info_callcenter, R.id.tv_good_info_collection, R.id.tv_good_info_cart, R.id.btn_good_info_addcart, R.id.tv_more_share, R.id.tv_more_search, R.id.tv_more_home, R.id.btn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.ib_good_info_more:
                if (llRoot.isShown()) {
                    llRoot.setVisibility(View.GONE);
                } else {
                    llRoot.setVisibility(View.VISIBLE);
                }
                Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_callcenter:
                Toast.makeText(this, "客服中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_collection:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_cart:
                Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_good_info_addcart:
//                Toast.makeText(this, "加入购物车", Toast.LENGTH_SHORT).show();
                CartStorage.getInstance(this).addData(goodsBean);
                break;
            case R.id.tv_more_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_home:
                Toast.makeText(this, "回到首页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_more:
                llRoot.setVisibility(View.GONE);
                break;
        }
    }
}
