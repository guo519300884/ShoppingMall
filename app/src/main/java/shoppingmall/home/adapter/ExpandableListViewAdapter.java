package shoppingmall.home.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.shoppingmall.R;

/**
 * Created by 皇 上 on 2017/3/6.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<String> group;
    private final List<List<String>> child;
    private int childP;
    private int groupP;

    public ExpandableListViewAdapter(Context context, List<String> group, List<List<String>> child) {
        this.context = context;
        this.group = group;
        this.child = child;

    }

    /**
     * 返回组数量
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        return group.size();
    }

    /**
     * 返回组员数量
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    /**
     * 返回组对象
     *
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    /**
     * 返回组员对象
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    /**
     * 返回组id
     *
     * @param groupPosition
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 返回组员id
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.group_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置组名
        viewHolder.textView.setText(group.get(groupPosition));
        viewHolder.textView.setPadding(0, 10, 0, 10);

        //设置是否展开的状态
        if (isExpanded) {
            viewHolder.imageView.setImageResource(R.drawable.filter_list_selected);
        } else {
            viewHolder.imageView.setImageResource(R.drawable.filter_list_unselected);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_list_item, null);
//            convertView = View.inflate(context, R.layout.child_list_item, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        //组不是0时设置组员的文本
        if (groupPosition != 0) {
            childViewHolder.textView.setText(child.get(groupPosition).get(childPosition));
        }

        //被点击的组员
        if (childP == childPosition && groupP == groupPosition) {
            //被点击的组员图片显示
            childViewHolder.childImageView.setVisibility(View.VISIBLE);
        } else {
            //被点击的组员图片隐藏
            childViewHolder.childImageView.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        //接受位置
        groupP = groupPosition;
        childP = childPosition;

        return true;
    }


    class ViewHolder {
        @InjectView(R.id.imageView)
        ImageView imageView;
        @InjectView(R.id.textView)
        TextView textView;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    class ChildViewHolder {
        @InjectView(R.id.textView)
        TextView textView;
        @InjectView(R.id.childImageView)
        ImageView childImageView;
        @InjectView(R.id.ll_child_root)
        LinearLayout llChildRoot;

        ChildViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
