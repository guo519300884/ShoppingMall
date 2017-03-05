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
import shoppingmall.community.adapter.NewPostAdapter;
import shoppingmall.community.bean.NewPostBean;
import shoppingmall.shoppingmall.R;
import shoppingmall.utils.Constants;

/**
 * Created by 皇 上 on 2017/3/4.
 */

public class NewPostFragment extends BaseFragment {
    @InjectView(R.id.lv_new_post)
    ListView lvNewPost;

    private View view;
    private NewPostBean newPostBean;
    private List<NewPostBean.ResultBean> newPostBeanResult;
    private NewPostAdapter newPostAdapter;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_news_post, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFormNet();
    }

    private void getDataFormNet() {
        OkHttpUtils
                .get()
                .url(Constants.NEW_POST_URL)
                .id(100)
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
        newPostBean = JSON.parseObject(response, NewPostBean.class);
        newPostBeanResult = newPostBean.getResult();

        if (newPostBeanResult != null && newPostBeanResult.size() > 0) {
            newPostAdapter = new NewPostAdapter(context, newPostBeanResult);
            lvNewPost.setAdapter(newPostAdapter);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
