package shoppingmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import shoppingmall.cart.fragment.CartFragment;
import shoppingmall.community.fragment.CommunityFragment;
import shoppingmall.home.fragment.HomeFragment;
import shoppingmall.shoppingmall.R;
import shoppingmall.type.fragment.TypeFragment;
import shoppingmall.user.fragment.UserFragment;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rg_btn)
    RadioGroup rgBtn;

    //装有各个Fragment的集合
    private List<Fragment> fragments;

    //Fragment对应的位置
    private int position;
    //打开过的上一页面
    private Fragment tempFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //初始化Fragment
        initFragment();

        //监听 RadioGroup 切换RadioButton时切换相应的Fragment
        initListener();
    }

    private void initListener() {
        rgBtn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                }
                //根据选定的位置切换相应的页面
                Fragment currentFragment = fragments.get(position);
                switchFragement(currentFragment);
            }
        });
        //默认选中主页面 必须放后面
        rgBtn.check(R.id.rb_home);
    }

    private void switchFragement(Fragment currentFragment) {

        //判断切换的页面与上一个页面是不是同一个界面
        if (tempFragment != currentFragment) {

            //获取Fragment 事务并开始
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            //判断当前页面是否存在   不存在就添加
            if (!currentFragment.isAdded()) {
                //判断上一页面是否为空
                if (tempFragment != null) {
                    //上一页面缓存不是空的就隐藏
                    ft.hide(tempFragment);
                }
                //添加当前的页面
                ft.add(R.id.fl_main, currentFragment);

            } else {//当前页面缓存存在  就让它显示

                //判断上一页面是否为空
                if (tempFragment != null) {
                    //上一页面不是空的就隐藏
                    ft.hide(tempFragment);
                }
                //显示当前页面
                ft.show(currentFragment);
            }

            //提交事务
            ft.commit();

            //将当前页面转为缓存
            tempFragment = currentFragment;
        }
    }

    //初始化Fragment
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new CartFragment());
        fragments.add(new UserFragment());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //得到id
        int  checkId = intent.getIntExtra("checkId",R.id.rb_home);
        switch (checkId){
            case R.id.rb_home:
                //切换到主页面
                rgBtn.check(R.id.rb_home);
                break;
            case R.id.rb_cart:
                //切换到购物车
                rgBtn.check(R.id.rb_cart);
                break;
        }
    }

}
