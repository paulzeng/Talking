package com.thomas.talking.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drugdu.ui.BaseFragment;
import com.drugdu.util.LogUtil;
import com.drugdu.util.UIUtils;
import com.thomas.talking.views.LoadingPage;


/**
 * Created by Thomas on 2017/3/10 0010.
 */

public abstract class BaseLoaderFragment extends BaseFragment {
    private LoadingPage mLoadingPage;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        mLoadingPage = new LoadingPage(UIUtils.getContext()) {

            @Override
            public View onCreateSuccessView() {
                return BaseLoaderFragment.this.onCreateSuccessView(inflater,container);
            }

            @Override
            public ResultState onLoad() {
                return BaseLoaderFragment.this.onLoad();
            }

        };
        return mLoadingPage;
    }

    // 由子类实现创建布局的方法
    public abstract View onCreateSuccessView(LayoutInflater inflater,ViewGroup container);

    // 由子类实现加载网络数据的逻辑, 该方法运行在子线程
    public abstract LoadingPage.ResultState onLoad();

    // 开始加载网络数据
    public void loadData() {
        if (mLoadingPage != null) {
            mLoadingPage.loadData();
        }else{
            LogUtil.e("TAG","mLoadingPage==null");
        }
    }

}
