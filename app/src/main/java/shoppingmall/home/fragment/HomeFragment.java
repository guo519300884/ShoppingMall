package shoppingmall.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import shoppingmall.base.BaseFragment;
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

    private View view;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.tv_search, R.id.tv_scan, R.id.ib_top})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_scan:
                Toast.makeText(context, "扫死你", Toast.LENGTH_SHORT).show();
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
