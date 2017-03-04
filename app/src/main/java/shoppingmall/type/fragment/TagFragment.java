package shoppingmall.type.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
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
import shoppingmall.type.adapter.TagAdapter;
import shoppingmall.type.bean.TagBean;

/**
 * Created by 皇 上 on 2017/3/3.
 */

public class TagFragment extends BaseFragment {

    @InjectView(R.id.gv_tag)
    GridView gvTag;
    private View view;
    private TagAdapter adapter;
    private List<TagBean.ResultBean> result;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_tag, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(Constants.TAG_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(context, "联网失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });

    }

    private void processData(final String response) {
        TagBean tagBean = JSON.parseObject(response, TagBean.class);
        result = tagBean.getResult();

        if (result != null && result.size() > 0) {

            adapter = new TagAdapter(context, result);
            gvTag.setAdapter(adapter);

            //设置点击事件
            gvTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TagBean.ResultBean resultBean = result.get(position);
                    Toast.makeText(context, "" + resultBean.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
