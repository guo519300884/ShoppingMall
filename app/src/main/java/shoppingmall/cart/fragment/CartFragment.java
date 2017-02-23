package shoppingmall.cart.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import shoppingmall.base.BaseFragment;

/**
 * Created by 皇 上 on 2017/2/22.
 */

public class CartFragment extends BaseFragment {
    private View view;
    private TextView textView;

    @Override
    public View initView() {
//        view = View.inflate(context, R.layout.  ,null);
        textView = new TextView(context);
        textView.setTextColor(Color.GREEN);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "HomeFragment initData() + 4444444444444");
        textView.setText("购物车");
    }
}
