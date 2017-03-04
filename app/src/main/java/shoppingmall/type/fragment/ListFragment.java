package shoppingmall.type.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;
import shoppingmall.base.BaseFragment;
import shoppingmall.home.utils.Constants;
import shoppingmall.shoppingmall.R;
import shoppingmall.type.adapter.TypeLeftAdapter;
import shoppingmall.type.adapter.TypeRightAdapter;
import shoppingmall.type.bean.TypeBean;

/**
 * Created by 皇 上 on 2017/3/3.
 */

public class ListFragment extends BaseFragment {

    @InjectView(R.id.lv_left)
    ListView lvLeft;
    @InjectView(R.id.rv_right)
    RecyclerView rvRight;

    private TypeLeftAdapter leftAdapter;
    private View view;

    //实际情况为网络请求得到数据
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};

    //联网的url的集合
    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};
    private TypeRightAdapter rightAdapter;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        leftAdapter = new TypeLeftAdapter(context, titles);
        lvLeft.setAdapter(leftAdapter);
        //点击监听
        initListener();
    }

    private void initListener() {
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //1.被点击的位置
                leftAdapter.changeSeleted(position);
                //2.刷新适配器
                leftAdapter.notifyDataSetChanged();
                //联网请求
                getDataFromNet(urls[position]);
            }
        });

        //联网请求数据 默认位置
        getDataFromNet(urls[0]);
    }


    private void getDataFromNet(String url) {

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(context, "联网失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });

    }

    private void processData(String response) {
        TypeBean typeBean = JSON.parseObject(response, TypeBean.class);
        List<TypeBean.ResultBean> result = typeBean.getResult();

        if (result != null && result.size() > 0) {

            //设置适配器
            rightAdapter = new TypeRightAdapter(context, result);
            rvRight.setAdapter(rightAdapter);
            //设置布局管理器
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0) {
                        return 3;
                    } else {
                        return 1;
                    }
                }
            });
            rvRight.setLayoutManager(gridLayoutManager);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
