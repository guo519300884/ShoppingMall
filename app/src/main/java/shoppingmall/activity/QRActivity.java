package shoppingmall.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.home.bean.GoodsBean;
import shoppingmall.shoppingmall.R;
import shoppingmall.utils.Constants;

import static shoppingmall.home.adapter.HomeAdapter.GOODS_BEAN;

public class QRActivity extends AppCompatActivity {

    @InjectView(R.id.iv_qr)
    ImageView ivQr;
    private GoodsBean goodsBean;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        ButterKnife.inject(this);

        goodsBean = (GoodsBean) getIntent().getSerializableExtra(GOODS_BEAN);

//        Glide.with(this).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).asBitmap().into(ivMinqr);

        Glide.with(this)
                .load(Constants.BASE_URL_IMAGE + goodsBean.getFigure())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                        String qrData = goodsBean.getProduct_id() + "," + goodsBean.getFigure() + ","
                                + goodsBean.getName() + "," + goodsBean.getCover_price();

                        bitmap = CodeUtils.createImage(qrData, 300, 300, resource);

                        ivQr.setImageBitmap(bitmap);
                    }
                });
    }
}
