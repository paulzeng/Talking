package com.thomas.talking.modules.category;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.drugdu.ui.BaseActivity;
import com.drugdu.util.UIUtils;
import com.drugdu.widget.TitleBar;
import com.jaeger.library.StatusBarUtil;
import com.thomas.talking.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
@Route(path = "/category/category_activity")
public class CategoryActivity extends BaseActivity {
    @Bind(R.id.title_bar)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, UIUtils.getColor(com.drugdu.R.color.main_color), 0);
        initView();
    }

    private void initView(){
        titleBar.setTitle(R.drawable.back, "", "Categorys", 0, "",null,null);
    }
}
