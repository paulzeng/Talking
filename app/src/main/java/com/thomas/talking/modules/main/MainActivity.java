package com.thomas.talking.modules.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.drugdu.ui.BaseActivity;
import com.drugdu.ui.BaseFragment;
import com.drugdu.util.LogUtil;
import com.drugdu.util.UIUtils;
import com.drugdu.widget.ControlScrollViewPager;
import com.drugdu.widget.TitleBar;
import com.jaeger.library.StatusBarUtil;
import com.netease.scan.IScanModuleCallBack;
import com.netease.scan.QrScan;
import com.netease.scan.ui.CaptureActivity;
import com.thomas.talking.R;
import com.thomas.talking.modules.main.ui.CartFragment;
import com.thomas.talking.modules.main.ui.HomeFragment;
import com.thomas.talking.modules.main.ui.MessageFragment;
import com.thomas.talking.modules.main.ui.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{

    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Bind(R.id.contentPager)
    ControlScrollViewPager mViewPager;
    @Bind(R.id.tab_home)
    TextView tabHome;
    @Bind(R.id.tab_cart)
    TextView tabCart;
    @Bind(R.id.tab_message)
    TextView tabMessage;
    @Bind(R.id.tab_my)
    TextView tabMy;
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    // content的Fragment
    private List<Fragment> fragments;
    private BaseFragment homeFragment,cartFragment,messageFragment,myFragment;
    private FragmentPagerAdapter pagerAdapter;

    // 当前fragment的index
    private int index;
    private int currentTabIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, UIUtils.getColor(com.drugdu.R.color.main_color), 0);
        initView();
    }

    private void setHomeTitle(){
        titleBar.setSearchTitle(R.drawable.scan,R.drawable.menu,new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                scan();
            }
        },new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/category/category_activity")
                        .navigation();
            }
        });
    }
    private void initView(){
        tabHome.setSelected(true);
        //titleBar.setTitle(R.drawable.scan, "", "Home", R.drawable.menu, "",null,null);
        setHomeTitle();
        //FIX ME 这里需要进行优化
        fragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        cartFragment = new CartFragment();
        messageFragment = new MessageFragment();
        myFragment = new MyFragment();
        fragments.add(homeFragment);
        fragments.add(cartFragment);
        fragments.add(messageFragment);
        fragments.add(myFragment);
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        mViewPager.setNoScroll(true);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
    }

    /**
     * 进入扫码页面，获取返回结果之后回调
     */
    private void scan(){
        QrScan.getInstance().launchScan(MainActivity.this, new IScanModuleCallBack() {
            @Override
            public void OnReceiveDecodeResult(final Context context, String result) {
                LogUtil.e("TAG","Scan Result == "+ result);
                QrScan.getInstance().finishScan((CaptureActivity)context);
            }
        });
    }

    @OnClick({R.id.tab_home, R.id.tab_cart, R.id.tab_message, R.id.tab_my})
    public void tabItemClick(View view) {
        tabHome.setSelected(false);
        tabCart.setSelected(false);
        tabMessage.setSelected(false);
        tabMy.setSelected(false);
        titleBar.setLeftImageResource(0);
        titleBar.removeAllActions();
        switch (view.getId()) {
            case R.id.tab_home:
                index = 0;
                //titleBar.setTitle(R.drawable.scan, "", "Home", R.drawable.menu, "",null,null);
                setHomeTitle();
                tabHome.setSelected(true);
                break;
            case R.id.tab_cart:
                index = 1;
                titleBar.setTitle("Cart");
                tabCart.setSelected(true);
                break;
            case R.id.tab_message:
                index = 2;
                titleBar.setTitle("Message");
                tabMessage.setSelected(true);
                break;
            case R.id.tab_my:
                index = 3;
                tabMy.setSelected(true);
                titleBar.setTitle("My Ddu");
                break;
        }
        if (currentTabIndex != index) {
            mViewPager.setCurrentItem(index);
        }
        currentTabIndex = index;
    }


}
