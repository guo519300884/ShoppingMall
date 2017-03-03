package shoppingmall.type.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.base.BaseFragment;
import shoppingmall.shoppingmall.R;
import shoppingmall.type.adapter.TypeLeftAdapter;

/**
 * Created by 皇 上 on 2017/3/3.
 */

public class ListFragment extends BaseFragment {

    @InjectView(R.id.lv_left)
    ListView lvLeft;
    @InjectView(R.id.rv_right)
    RecyclerView rvRight;

    private View view;

    //实际情况为网络请求得到数据
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};
    private TypeLeftAdapter leftAdapter;

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
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
