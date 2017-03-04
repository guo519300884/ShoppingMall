package shoppingmall.community.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import shoppingmall.base.BaseFragment;
import shoppingmall.community.adapter.CommunityAdapter;
import shoppingmall.shoppingmall.R;

/**
 * Created by 皇 上 on 2017/2/22.
 */

public class CommunityFragment extends BaseFragment {

    @InjectView(R.id.ib_community_icon)
    ImageButton ibCommunityIcon;
    @InjectView(R.id.ib_community_message)
    ImageButton ibCommunityMessage;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    private View view;
    private CommunityAdapter adapter;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_community, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        adapter = new CommunityAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ib_community_icon, R.id.ib_community_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_community_icon:
                Toast.makeText(context, "666", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_community_message:
                Toast.makeText(context, "消息", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
