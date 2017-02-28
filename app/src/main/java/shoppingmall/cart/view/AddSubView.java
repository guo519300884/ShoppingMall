package shoppingmall.cart.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import shoppingmall.shoppingmall.R;

/**
 * Created by 皇 上 on 2017/2/28.
 */

public class AddSubView extends LinearLayout {

    private ImageView iv_sub;
    private TextView tv_value;
    private ImageView iv_add;

    private int value = 1;
    private int minValue = 1;
    private int maxValue = 7;

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        //设置的值为 String 类型
        tv_value.setText(value + "");
    }

    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //将布局实例化为view 并添加到AddSubView类中，使之成为此类的子视图
        View.inflate(context, R.layout.add_sub_view, AddSubView.this);

        iv_sub = (ImageView) findViewById(R.id.iv_sub);
        tv_value = (TextView) findViewById(R.id.tv_value);
        iv_add = (ImageView) findViewById(R.id.iv_add);

        //设置俩按钮的点击事件
        iv_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //不能大于最大值
                if (value < maxValue) {
                    value++;
                }
                setValue(value);
                if (listener != null) {
                    listener.OnNumberChange(value);
                }
            }
        });

        iv_sub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //不能小于最小值
                if (value > minValue) {
                    value--;
                }
                setValue(value);

                if (listener != null) {
                    listener.OnNumberChange(value);
                }
            }
        });

        //将属性取出
        if (attrs != null) {
            TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.AddSubView);
            int value = tintTypedArray.getInt(R.styleable.AddSubView_value, 0);
            if (value > 0) {
                setValue(value);
            }
            int minValue = tintTypedArray.getInt(R.styleable.AddSubView_minValue, 0);
            if (value > 0) {
                setMinValue(minValue);
            }
            int maxValue = tintTypedArray.getInt(R.styleable.AddSubView_maxValue, 0);
            if (value > 0) {
                setMaxValue(maxValue);
            }
            Drawable addDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberAddBackground);
            if (addDrawable != null) {
                iv_add.setImageDrawable(addDrawable);
            }
            Drawable subDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberSubBackground);
            if (subDrawable != null) {
                iv_sub.setImageDrawable(subDrawable);
            }
        }
    }

    //定义接口
    public interface OnNumberChangeListener {
        public void OnNumberChange(int value);
    }

    private OnNumberChangeListener listener;

    /**
     * 设置数字变化的监听  在Activity 或 适配器中使用
     *
     * @param listener
     */
    public void setOnNumberChangeListener(OnNumberChangeListener listener) {
        this.listener = listener;
    }
}
