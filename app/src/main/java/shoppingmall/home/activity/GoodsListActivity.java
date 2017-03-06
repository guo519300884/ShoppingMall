package shoppingmall.home.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import shoppingmall.home.adapter.GoosListAdapter;
import shoppingmall.home.bean.TypeListBean;
import shoppingmall.shoppingmall.R;
import shoppingmall.utils.Constants;

public class GoodsListActivity extends AppCompatActivity {

    @InjectView(R.id.ib_goods_list_back)
    ImageButton ibGoodsListBack;
    @InjectView(R.id.tv_goods_list_search)
    TextView tvGoodsListSearch;
    @InjectView(R.id.ib_goods_list_home)
    ImageButton ibGoodsListHome;
    @InjectView(R.id.tv_goods_list_sort)
    TextView tvGoodsListSort;
    @InjectView(R.id.tv_goods_list_price)
    TextView tvGoodsListPrice;
    @InjectView(R.id.iv_goods_list_arrow)
    ImageView ivGoodsListArrow;
    @InjectView(R.id.ll_goods_list_price)
    LinearLayout llGoodsListPrice;
    @InjectView(R.id.tv_goods_list_select)
    TextView tvGoodsListSelect;
    @InjectView(R.id.ll_goods_list_head)
    LinearLayout llGoodsListHead;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.dl_left)
    DrawerLayout dlLeft;

    private int position;
    /**
     * 请求网络
     */
    private String[] urls = new String[]{Constants.CLOSE_STORE, Constants.GAME_STORE,
            Constants.COMIC_STORE, Constants.COSPLAY_STORE, Constants.GUFENG_STORE, Constants.STICK_STORE,
            Constants.WENJU_STORE, Constants.FOOD_STORE, Constants.SHOUSHI_STORE,
    };
    private TypeListBean typeListBean;
    private GoosListAdapter goosListAdapter;
    private List<TypeListBean.ResultBean.PageDataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.inject(this);

        getData();
    }

    private void getData() {
        position = getIntent().getIntExtra("position", 0);
        getDataFromNet(urls[position]);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get()
                .url(url)
                .id(100)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(GoodsListActivity.this, "联网失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }
        });
    }

    private void processData(String response) {
        typeListBean = JSON.parseObject(response, TypeListBean.class);
        data = typeListBean.getResult().getPage_data();
        //设置适配器
        goosListAdapter = new GoosListAdapter(this, data);
        recyclerview.setAdapter(goosListAdapter);
        //设置布局管理器  2列
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @OnClick({R.id.ib_goods_list_back, R.id.tv_goods_list_search, R.id.ib_goods_list_home, R.id.tv_goods_list_sort, R.id.tv_goods_list_price, R.id.tv_goods_list_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_goods_list_back:
                finish();
                break;
            case R.id.tv_goods_list_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_goods_list_home:
//                Toast.makeText(this, "首页", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.tv_goods_list_sort:
                Toast.makeText(this, "综合排序", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_price:
                Toast.makeText(this, "价格", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_select:
                Toast.makeText(this, "筛选", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
