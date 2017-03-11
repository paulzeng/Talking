package com.thomas.talking.modules.main.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drugdu.ui.BaseFragment;
import com.drugdu.util.LogUtil;
import com.thomas.talking.R;


import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

public class InfomationFragment extends BaseFragment {
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        //填充各控件的数据
        LogUtil.e("TAG","lazyLoad");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.e("TAG", "InfomationFragment is onCreateView");
        isPrepared = true;
        View view = inflater.inflate(R.layout.layout_home_news, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
