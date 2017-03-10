package com.thomas.talking.modules.main.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drugdu.util.LogUtil;
import com.drugdu.widget.Carousel;
import com.thomas.talking.R;
import com.thomas.talking.base.BaseLoaderFragment;
import com.thomas.talking.data.DataControl;
import com.thomas.talking.views.LoadingPage;

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
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

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
        LogUtil.e("TAG","HomeFragment is onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public View onCreateSuccessView(LayoutInflater inflater,ViewGroup container) {
        isPrepared = true;
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        carousel.startup(DataControl.getBannerData());
        return view;
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
        LogUtil.e("TAG","HomeFragment is onDestroy");
    }
}
