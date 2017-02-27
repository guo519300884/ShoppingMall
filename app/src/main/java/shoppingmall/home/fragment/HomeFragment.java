package shoppingmall.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import shoppingmall.base.BaseFragment;
import shoppingmall.home.adapter.HomeAdapter;
import shoppingmall.home.bean.HomeBean;
import shoppingmall.home.utils.Constants;
import shoppingmall.shoppingmall.R;

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
        HomeBean homeBean = JSON.parseObject(response, HomeBean.class);
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
                break;
            case R.id.tv_scan:
                Toast.makeText(context, "扫呀", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_news:
                Toast.makeText(context, "来消息了", Toast.LENGTH_SHORT).show();
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


}
