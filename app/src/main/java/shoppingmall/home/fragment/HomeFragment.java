package shoppingmall.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import shoppingmall.base.BaseFragment;
import shoppingmall.home.activity.CallCenterActivity;
import shoppingmall.home.activity.GoodsInfoActivity;
import shoppingmall.home.activity.SearchActivity;
import shoppingmall.home.adapter.HomeAdapter;
import shoppingmall.home.bean.GoodsBean;
import shoppingmall.home.bean.HomeBean;
import shoppingmall.shoppingmall.R;
import shoppingmall.utils.Constants;

/**
 * Created by 皇 上 on 2017/2/22.
 */

public class HomeFragment extends BaseFragment {

    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.tv_scan)
    TextView tvScan;
    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    @InjectView(R.id.ib_top)
    ImageButton ibTop;
    @InjectView(R.id.tv_news)
    TextView tvNews;

    private View view;
    private HomeAdapter adapter;
    private HomeBean homeBean;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    public void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.HOME_URL)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "HomeFragment onError() 联网失败：" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "HomeFragment onResponse()联网成功");
                        processData(response);
                    }
                });
    }

    //json解析方式有三种：1.手动解析json；2.Gson解析；3.fastjson解析

    private void processData(String response) {
        homeBean = JSON.parseObject(response, HomeBean.class);
        Log.e("TAG", "HomeFragment processData()+++联网获取数据" + homeBean.getResult().getHot_info().get(1).getName());
        //设置适配器
        adapter = new HomeAdapter(context, homeBean.getResult());
        rvHome.setAdapter(adapter);
        //设置布局管理器
//        rvHome.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        //gridView的布局管理器  线性的管理器没有回滚到顶部的点击事件
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        rvHome.setLayoutManager(gridLayoutManager);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position >= 4) {
                    ibTop.setVisibility(View.VISIBLE);
                } else {
                    ibTop.setVisibility(View.GONE);
                }
                return 1;
            }
        });

    }


    @OnClick({R.id.tv_search, R.id.tv_scan, R.id.ib_top, R.id.tv_news})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                Intent SIntent = new Intent(context, SearchActivity.class);
                startActivity(SIntent);
                break;
            case R.id.tv_scan:
                Toast.makeText(context, "扫呀", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(context, CaptureActivity.class);
                startActivityForResult(intent1, 0);
                break;
            case R.id.tv_news:
//                Toast.makeText(context, "来消息了", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, CallCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.ib_top:
                rvHome.scrollToPosition(0);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    /**
     * 处理二维码扫描结果
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(context, "解析结果:" + result, Toast.LENGTH_LONG).show();

//                    List<HomeBean.ResultBean.RecommendInfoBean> recommend_info = homeBean.getResult().getRecommend_info();
                    GoodsBean goodsBean = new GoodsBean();
                    String[] s = result.split(",");

                    goodsBean.setProduct_id(s[0]);
                    goodsBean.setFigure(s[1]);
                    goodsBean.setName(s[2]);
                    goodsBean.setCover_price(s[3]);

                    Intent intent = new Intent(context, GoodsInfoActivity.class);
                    intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                    startActivity(intent);

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(context, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


}
