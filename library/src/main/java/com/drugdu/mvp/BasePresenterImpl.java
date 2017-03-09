package com.drugdu.mvp;

/**
 * User: Thomas_Zeng(zengwentao@icloud.com)
 * Date: 2016-06-01
 * Time: 11:03
 * FIXME
 */
public class BasePresenterImpl<T> implements BasePresenter{
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }
}  