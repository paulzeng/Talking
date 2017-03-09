package com.drugdu.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.drugdu.mvp.BasePresenterImpl;

/**
 * 封装MvpFragment的基类
 * Created by ZWT on 2016/9/9.
 */
public abstract class BaseMvpFragment<V, T extends BasePresenterImpl> extends Fragment {
    public T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != presenter) {
            presenter.attach((V) this);
        }

    }

    @Override
    public void onDestroy() {
        if (null != presenter) {
            presenter.dettach();
        }
        super.onDestroy();
    }

    // 实例化presenter
    public abstract T initPresenter();


}

