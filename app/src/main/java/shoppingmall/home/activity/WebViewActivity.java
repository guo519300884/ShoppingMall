package shoppingmall.home.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import shoppingmall.home.adapter.HomeAdapter;
import shoppingmall.home.bean.WebViewBean;
import shoppingmall.home.utils.Constants;
import shoppingmall.shoppingmall.R;

public class WebViewActivity extends AppCompatActivity {

    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_more)
    ImageButton ibMore;
    @InjectView(R.id.webview)
    WebView webview;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.activity_web_view)
    LinearLayout activityWebView;
    private WebViewBean webViewBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.inject(this);
        getData();
    }

    private void getData() {
        webViewBean = (WebViewBean) getIntent().getSerializableExtra(HomeAdapter.WEBVIEW_BEAN);
        //设置标题显示文字
        tvTitle.setText(webViewBean.getName());
        //加载链接的地址  webView
        WebSettings webSettings = webview.getSettings();
        //支持Js
        webSettings.setJavaScriptEnabled(true);
        //添加显示屏幕缩放按钮
        webSettings.setBuiltInZoomControls(true);
        //设置双击屏幕变化大小
        webSettings.setUseWideViewPort(true);
        //设置WebViewClient 若没有此设置，则打开新链接时自动调用系统自带的浏览器
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }
        });
        //添加 addJavascriptInterface
        webview.addJavascriptInterface(new MyJavascriptInterface(), "cyc");
        //加载地址
        webview.loadUrl(Constants.BASE_URL_IMAGE + webViewBean.getUrl());
    }

    class MyJavascriptInterface {
        @JavascriptInterface
        public void jumpForAndroid(String data) {
            Toast.makeText(WebViewActivity.this, "嘿嘿嘿" + data, Toast.LENGTH_SHORT).show();

        }
    }

    @OnClick({R.id.ib_back, R.id.ib_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_more:
                Toast.makeText(this, "再来些", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
