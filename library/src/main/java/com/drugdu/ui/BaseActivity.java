package com.drugdu.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.drugdu.APP;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2017/3/7 0007.
 * Activity的基类
 */

public class BaseActivity extends SwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APP.allActivity.add(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
