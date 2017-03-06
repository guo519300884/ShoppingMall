package shoppingmall.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import shoppingmall.home.adapter.ExpandableListViewAdapter;
import shoppingmall.home.adapter.GoosListAdapter;
import shoppingmall.home.adapter.HomeAdapter;
import shoppingmall.home.bean.GoodsBean;
import shoppingmall.home.bean.TypeListBean;
import shoppingmall.home.view.SpaceItemDecoration;
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
    @InjectView(R.id.ib_drawer_layout_back)
    ImageButton ibDrawerLayoutBack;
    @InjectView(R.id.tv_ib_drawer_layout_title)
    TextView tvIbDrawerLayoutTitle;
    @InjectView(R.id.ib_drawer_layout_confirm)
    TextView ibDrawerLayoutConfirm;
    @InjectView(R.id.rb_select_hot)
    RadioButton rbSelectHot;
    @InjectView(R.id.rb_select_new)
    RadioButton rbSelectNew;
    @InjectView(R.id.rg_select)
    RadioGroup rgSelect;
    @InjectView(R.id.tv_drawer_price)
    TextView tvDrawerPrice;
    @InjectView(R.id.tv_drawer_recommend)
    TextView tvDrawerRecommend;
    @InjectView(R.id.rl_select_recommend_theme)
    RelativeLayout rlSelectRecommendTheme;
    @InjectView(R.id.tv_drawer_type)
    TextView tvDrawerType;
    @InjectView(R.id.rl_select_type)
    RelativeLayout rlSelectType;
    @InjectView(R.id.btn_select_all)
    Button btnSelectAll;
    @InjectView(R.id.ll_select_root)
    LinearLayout llSelectRoot;
    @InjectView(R.id.btn_drawer_layout_cancel)
    Button btnDrawerLayoutCancel;
    @InjectView(R.id.btn_drawer_layout_confirm)
    Button btnDrawerLayoutConfirm;
    @InjectView(R.id.iv_price_no_limit)
    ImageView ivPriceNoLimit;
    @InjectView(R.id.rl_price_nolimit)
    RelativeLayout rlPriceNolimit;
    @InjectView(R.id.iv_price_0_15)
    ImageView ivPrice015;
    @InjectView(R.id.rl_price_0_15)
    RelativeLayout rlPrice015;
    @InjectView(R.id.iv_price_15_30)
    ImageView ivPrice1530;
    @InjectView(R.id.rl_price_15_30)
    RelativeLayout rlPrice1530;
    @InjectView(R.id.iv_price_30_50)
    ImageView ivPrice3050;
    @InjectView(R.id.rl_price_30_50)
    RelativeLayout rlPrice3050;
    @InjectView(R.id.iv_price_50_70)
    ImageView ivPrice5070;
    @InjectView(R.id.rl_price_50_70)
    RelativeLayout rlPrice5070;
    @InjectView(R.id.iv_price_70_100)
    ImageView ivPrice70100;
    @InjectView(R.id.rl_price_70_100)
    RelativeLayout rlPrice70100;
    @InjectView(R.id.iv_price_100)
    ImageView ivPrice100;
    @InjectView(R.id.rl_price_100)
    RelativeLayout rlPrice100;
    @InjectView(R.id.et_price_start)
    EditText etPriceStart;
    @InjectView(R.id.v_price_line)
    View vPriceLine;
    @InjectView(R.id.et_price_end)
    EditText etPriceEnd;
    @InjectView(R.id.rl_select_price)
    RelativeLayout rlSelectPrice;
    @InjectView(R.id.ll_price_root)
    LinearLayout llPriceRoot;
    @InjectView(R.id.btn_drawer_theme_cancel)
    Button btnDrawerThemeCancel;
    @InjectView(R.id.btn_drawer_theme_confirm)
    Button btnDrawerThemeConfirm;
    @InjectView(R.id.iv_theme_all)
    ImageView ivThemeAll;
    @InjectView(R.id.rl_theme_all)
    RelativeLayout rlThemeAll;
    @InjectView(R.id.iv_theme_note)
    ImageView ivThemeNote;
    @InjectView(R.id.rl_theme_note)
    RelativeLayout rlThemeNote;
    @InjectView(R.id.iv_theme_funko)
    ImageView ivThemeFunko;
    @InjectView(R.id.rl_theme_funko)
    RelativeLayout rlThemeFunko;
    @InjectView(R.id.iv_theme_gsc)
    ImageView ivThemeGsc;
    @InjectView(R.id.rl_theme_gsc)
    RelativeLayout rlThemeGsc;
    @InjectView(R.id.iv_theme_origin)
    ImageView ivThemeOrigin;
    @InjectView(R.id.rl_theme_origin)
    RelativeLayout rlThemeOrigin;
    @InjectView(R.id.iv_theme_sword)
    ImageView ivThemeSword;
    @InjectView(R.id.rl_theme_sword)
    RelativeLayout rlThemeSword;
    @InjectView(R.id.iv_theme_food)
    ImageView ivThemeFood;
    @InjectView(R.id.rl_theme_food)
    RelativeLayout rlThemeFood;
    @InjectView(R.id.iv_theme_moon)
    ImageView ivThemeMoon;
    @InjectView(R.id.rl_theme_moon)
    RelativeLayout rlThemeMoon;
    @InjectView(R.id.iv_theme_quanzhi)
    ImageView ivThemeQuanzhi;
    @InjectView(R.id.rl_theme_quanzhi)
    RelativeLayout rlThemeQuanzhi;
    @InjectView(R.id.iv_theme_gress)
    ImageView ivThemeGress;
    @InjectView(R.id.rl_theme_gress)
    RelativeLayout rlThemeGress;
    @InjectView(R.id.ll_theme_root)
    LinearLayout llThemeRoot;
    @InjectView(R.id.btn_drawer_type_cancel)
    Button btnDrawerTypeCancel;
    @InjectView(R.id.btn_drawer_type_confirm)
    Button btnDrawerTypeConfirm;
    @InjectView(R.id.expandableListView)
    ExpandableListView expandableListView;
    @InjectView(R.id.ll_type_root)
    LinearLayout llTypeRoot;

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
    private int click_count;

    //组 数据
    private List<String> group;
    //组员 数据
    private List<List<String>> child;
    private ExpandableListViewAdapter exAdapter;

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

        initView();
    }

    private void initView() {
        //设置高亮显示
        tvGoodsListSort.setTextColor(Color.parseColor("#ed4141"));
        //设置价格 & 筛选为默认
        tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));
        tvGoodsListSelect.setTextColor(Color.parseColor("#333538"));
        //默认选中筛选项
        showLinearLayout(llSelectRoot);
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

        if (data != null && data.size() > 0) {
            //设置适配器
            goosListAdapter = new GoosListAdapter(this, data);
            recyclerview.setAdapter(goosListAdapter);
            //设置布局管理器  2列
            recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
            //设置间距
            recyclerview.addItemDecoration(new SpaceItemDecoration(10));

            goosListAdapter.setOnItemClickListener(new GoosListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(TypeListBean.ResultBean.PageDataBean datas) {

                    GoodsBean goodsBean = new GoodsBean();

                    goodsBean.setProduct_id(datas.getProduct_id());
                    goodsBean.setFigure(datas.getFigure());
                    goodsBean.setCover_price(datas.getCover_price());
                    goodsBean.setName(datas.getName());

                    Intent intent = new Intent(GoodsListActivity.this, GoodsInfoActivity.class);
                    intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                    startActivity(intent);
                }
            });
        }
    }

    @OnClick({R.id.ib_goods_list_back, R.id.btn_drawer_theme_confirm, R.id.tv_goods_list_search, R.id.ib_goods_list_home, R.id.tv_goods_list_sort, R.id.tv_goods_list_price, R.id.tv_goods_list_select, R.id.ib_drawer_layout_back, R.id.ib_drawer_layout_confirm, R.id.rl_select_price, R.id.rl_select_recommend_theme, R.id.rl_select_type, R.id.btn_drawer_layout_cancel, R.id.btn_drawer_layout_confirm, R.id.btn_drawer_theme_cancel, R.id.btn_drawer_type_cancel, R.id.btn_drawer_type_confirm})
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
//                Toast.makeText(this, "综合排序", Toast.LENGTH_SHORT).show();
                ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_normal);
                initView();
                break;
            case R.id.tv_goods_list_price:
//                Toast.makeText(this, "价格", Toast.LENGTH_SHORT).show();
                //设置高亮显示
                tvGoodsListPrice.setTextColor(Color.parseColor("#ed4141"));
                //设置价格 & 筛选为默认
                tvGoodsListSort.setTextColor(Color.parseColor("#333538"));
                tvGoodsListSelect.setTextColor(Color.parseColor("#333538"));

                click_count++;
                if (click_count % 2 == 1) {
                    ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_desc);
                } else {
                    ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_asc);
                }

                break;
            case R.id.tv_goods_list_select:
//                Toast.makeText(this, "筛选", Toast.LENGTH_SHORT).show();
                //设置高亮显示
                tvGoodsListSelect.setTextColor(Color.parseColor("#ed4141"));
                //设置价格 & 筛选为默认
                tvGoodsListSort.setTextColor(Color.parseColor("#333538"));
                tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));

                //打开DrawLayout
                dlLeft.openDrawer(Gravity.RIGHT);
                break;
            case R.id.ib_drawer_layout_back:
                dlLeft.closeDrawers();
                break;
            case R.id.ib_drawer_layout_confirm:
                Toast.makeText(this, "筛选 确定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_select_price:
//                showPriceLayout();
                showLinearLayout(llPriceRoot);
                break;
            case R.id.rl_select_recommend_theme:
//                showThemeLayout();
                showLinearLayout(llThemeRoot);
                break;
            case R.id.rl_select_type:
//                showTypeLayout();
                showLinearLayout(llTypeRoot);
                break;
            case R.id.btn_drawer_layout_cancel:
                showLinearLayout(llSelectRoot);
                break;
            case R.id.btn_drawer_layout_confirm:
                Toast.makeText(this, "价格 确定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_drawer_theme_cancel:
                showLinearLayout(llSelectRoot);
                break;
            case R.id.btn_drawer_theme_confirm:
                Toast.makeText(this, "主题 确定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_drawer_type_cancel:
                showLinearLayout(llSelectRoot);
                break;
            case R.id.btn_drawer_type_confirm:
                Toast.makeText(this, "嘎嘎", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    //显示l布局
    private void showLinearLayout(LinearLayout l) {
        llPriceRoot.setVisibility(View.GONE);
        llThemeRoot.setVisibility(View.GONE);
        llTypeRoot.setVisibility(View.GONE);
        llSelectRoot.setVisibility(View.GONE);
        l.setVisibility(View.VISIBLE);

        initExpandableListView();

    }

    private void initExpandableListView() {
        //创建集合
        group = new ArrayList<>();
        child = new ArrayList<>();
        //集合内添加数据
        addInfo("全部", new String[]{});
        addInfo("上衣", new String[]{"古风", "和风", "lolita", "日常"});
        addInfo("下装", new String[]{"日常", "泳衣", "汉风", "lolita", "创意T恤"});
        addInfo("外套", new String[]{"汉风", "古风", "lolita", "胖次", "南瓜裤", "日常"});

        //设置适配器
        exAdapter = new ExpandableListViewAdapter(this, group, child);
        expandableListView.setAdapter(exAdapter);

        //
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //把位置传入适配器
                exAdapter.isChildSelectable(groupPosition, childPosition);
                //刷新
                exAdapter.notifyDataSetChanged();

                return true;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (child.get(groupPosition).isEmpty()) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void addInfo(String father, String[] datas) {
        group.add(father);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < datas.length; i++) {
            list.add(datas[i]);
        }
        child.add(list);
    }


}
