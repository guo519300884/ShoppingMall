package shoppingmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.shoppingmall.R;

public class WelcomeActivity extends AppCompatActivity {

    @InjectView(R.id.iv_gg)
    ImageView ivGg;
    @InjectView(R.id.tv_edition)
    TextView tvEdition;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);
        //等候两秒 跳转
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, 2000);

//        RotateAnimation ra = new RotateAnimation(0, 360,
//                Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.5f);
//        ra.setDuration(2000);
//        ra.setFillBefore(true);
//        tvEdition.startAnimation(ra);

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);
        aa.setFillBefore(true);
        ivGg.startAnimation(aa);
//        bindService();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //关闭当前页面
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //点击直接进入
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startMainActivity();
            //消费掉触摸事件
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除全部消息
        handler.removeCallbacksAndMessages(null);
    }
}
