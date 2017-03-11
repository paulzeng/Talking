package com.thomas.talking.modules.main.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.drugdu.adapter.BaseAdapterHelper;
import com.drugdu.adapter.QuickAdapter;
import com.drugdu.ui.BaseFragment;
import com.drugdu.util.LogUtil;
import com.drugdu.util.UIUtils;
import com.drugdu.widget.Carousel;
import com.drugdu.widget.Marquee.MarqueeFactory;
import com.drugdu.widget.Marquee.MarqueeView;
import com.drugdu.widget.ScrollGridView;
import com.flyco.tablayout.SlidingTabLayout;
import com.thomas.talking.R;
import com.thomas.talking.base.BaseLoaderFragment;
import com.thomas.talking.bean.MenuBean;
import com.thomas.talking.data.DataControl;
import com.thomas.talking.views.LoadingPage;
import com.thomas.talking.views.NoticeMF;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class HomeFragment extends BaseLoaderFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.home_banner)
    Carousel carousel;
    @Bind(R.id.gv_menu)
    ScrollGridView gvMenu;
    @Bind(R.id.marquee)
    MarqueeView marquee;
    @Bind(R.id.tabHost)
    SlidingTabLayout mTabHost;
    @Bind(R.id.pager)
    ViewPager pager;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private BaseFragment infomationFragment,realtimeFragment;
    private String[] mTitles = {"Infomation", "RealTime"};

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        LogUtil.e("TAG", "HomeFragment is onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public View onCreateSuccessView(LayoutInflater inflater, ViewGroup container) {
        isPrepared = true;
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        carousel.startup(DataControl.getBannerData());
        initMenu();
        initMarquee();
        initTabFragment();
        return view;
    }

    private void initMenu(){
        LogUtil.e("TAG","initMenu");
        QuickAdapter<MenuBean> adapter = new QuickAdapter<MenuBean>(UIUtils.getContext(), R.layout.item_gv_menu) {

            @Override
            protected void convert(BaseAdapterHelper helper, MenuBean menu) {
                helper.setText(R.id.tv_item_category, menu.getTitle());
                helper.setImageResourceID(R.id.iv_item_category, menu.getIcon());
            }
        };
        adapter.addAll(DataControl.getMenuData());
        gvMenu.setAdapter(adapter);
    }

    private void initMarquee(){
        LogUtil.e("TAG","initMarquee");
        MarqueeFactory<TextView, String> marqueeFactory1 = new NoticeMF(UIUtils.getContext());
        marquee.setMarqueeFactory(marqueeFactory1);
        marquee.startFlipping();
        marqueeFactory1.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(UIUtils.getContext(), holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory1.setData(DataControl.datas);
    }

    //初始化tab子页面
    private void initTabFragment() {
        infomationFragment = new InfomationFragment();
        mFragments.add(infomationFragment);
        realtimeFragment = new RealTimeFragment();
        mFragments.add(realtimeFragment);
        pager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        mTabHost.setViewPager(pager, mTitles);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        return LoadingPage.ResultState.STATE_SUCCESS;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }



    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        //填充各控件的数据
        LogUtil.e("TAG","lazyLoad");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        //FIX ME 调用下面方法，空指针，说明已经自动销毁
        //carousel.shutdown();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("TAG", "HomeFragment is onDestroy");
    }
}
