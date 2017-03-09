package com.drugdu.ui;

/**
 * Created by Administrator on 2017/3/7 0007.
 * 使用MVP构建的activity基类
 */

import android.os.Bundle;

import com.drugdu.mvp.BasePresenterImpl;

/**
 * 封装使用mvp框架的activity的基类
 * Created by ZWT on 2016/9/9.
 */
public abstract class BaseMvpActivity<V, T extends BasePresenterImpl> extends BaseActivity {

    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }

    // 实例化presenter
    public abstract T initPresenter();

}
