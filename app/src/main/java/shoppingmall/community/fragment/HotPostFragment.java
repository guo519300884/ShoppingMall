package shoppingmall.community.fragment;

import android.view.View;
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
import shoppingmall.community.adapter.HotPostAdapter;
import shoppingmall.community.bean.HotPostBean;
import shoppingmall.shoppingmall.R;
import shoppingmall.utils.Constants;

/**
 * Created by 皇 上 on 2017/3/4.
 */

public class HotPostFragment extends BaseFragment {

    @InjectView(R.id.lv_hot_post)
    ListView lvHotPost;
    private View view;
    private HotPostBean hotPostBean;
    private List<HotPostBean.ResultBean> hotPostBeanResult;
    private HotPostAdapter hotPostAdapter;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_hot_post, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFormNet();
    }

    private void getDataFormNet() {
        OkHttpUtils.get()
                .url(Constants.HOT_POST_URL)
                .id(100)
                .build().execute(new StringCallback() {
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
        hotPostBean = JSON.parseObject(response, HotPostBean.class);
        hotPostBeanResult = hotPostBean.getResult();

        if (hotPostBeanResult != null && hotPostBeanResult.size() > 0) {
            hotPostAdapter = new HotPostAdapter(context, hotPostBeanResult);
            lvHotPost.setAdapter(hotPostAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
