package shoppingmall.type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.shoppingmall.R;

/**
 * Created by 皇 上 on 2017/3/3.
 */
public class TypeLeftAdapter extends BaseAdapter {

    private final Context context;
    private final String[] titles;
    //选中的位置
    private int selectPosition;

    public TypeLeftAdapter(Context context, String[] titles) {
        this.context = context;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_type, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到相应的数据
        viewHolder.tvTitle.setText(titles[position]);

        if (selectPosition == position) {
            //设置选中状态为高亮
            convertView.setBackgroundResource(R.drawable.type_item_background_selector);
            viewHolder.tvTitle.setTextColor(Color.parseColor("#fd3f3f"));
        } else {
            //其他为默認
            convertView.setBackgroundResource(R.drawable.bg2);
            viewHolder.tvTitle.setTextColor(Color.parseColor("#323437"));
        }
        return convertView;
    }

    //接受到被点击的位置
    public void changeSeleted(int position) {
        selectPosition = position;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
