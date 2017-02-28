package shoppingmall.cart.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import shoppingmall.base.BaseFragment;
import shoppingmall.cart.adapter.CartAdapter;
import shoppingmall.cart.utils.CartStorage;
import shoppingmall.home.bean.GoodsBean;
import shoppingmall.shoppingmall.R;

/**
 * Created by 皇 上 on 2017/2/22.
 */

public class CartFragment extends BaseFragment {
    @InjectView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @InjectView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @InjectView(R.id.btn_check_out)
    Button btnCheckOut;
    @InjectView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @InjectView(R.id.checkbox_delete_all)
    CheckBox checkboxDeleteAll;
    @InjectView(R.id.btn_delete)
    Button btnDelete;
    @InjectView(R.id.btn_collection)
    Button btnCollection;
    @InjectView(R.id.ll_delete)
    LinearLayout llDelete;
    @InjectView(R.id.iv_empty)
    ImageView ivEmpty;
    @InjectView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @InjectView(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;

    private View view;
    private CartAdapter adapter;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_cart, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //获取到购物车里的所有数据
        List<GoodsBean> list = CartStorage.getInstance(context).getAllData();

        if (list != null && list.size() > 0) {
            //表示购物车内有数据
            llEmptyShopcart.setVisibility(View.GONE);
            //设置适配器
            adapter = new CartAdapter(context, list, tvShopcartTotal, checkboxAll, checkboxDeleteAll);
            recyclerview.setAdapter(adapter);
            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            //设置点击事件

        } else {//表示购物车内没有数据
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            for (int i = 0; i < CartStorage.getInstance(context).getAllData().size(); i++) {
                Log.e("TAG", "" + CartStorage.getInstance(context).getAllData().get(i).toString());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out, R.id.checkbox_delete_all, R.id.btn_delete, R.id.btn_collection, R.id.tv_empty_cart_tobuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                Toast.makeText(context, "编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_all:
                Toast.makeText(context, "全选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_check_out:
                Toast.makeText(context, "结算", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_delete_all:
                Toast.makeText(context, "删除面全选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                Toast.makeText(context, "删除按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_collection:
                Toast.makeText(context, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_empty_cart_tobuy:
                Toast.makeText(context, "去逛", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
