package com.thomas.talking.base;

import com.alibaba.android.arouter.launcher.ARouter;
import com.drugdu.APP;
import com.drugdu.util.LogUtil;
import com.netease.scan.QrScan;
import com.netease.scan.QrScanConfiguration;
import com.thomas.talking.R;

/**
 * Created by Administrator on 2017/3/10 0010.
 */

public class BaseApplication extends APP {
    @Override
    public void onCreate() {
        super.onCreate();
        initRouter();
        initQrscan();
    }

    private void initRouter(){
        if (LogUtil.isPrint) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private void initQrscan(){
        // 自定义配置
        QrScanConfiguration configuration = new QrScanConfiguration.Builder(this)
                .setTitleHeight(53)
                .setTitleText("来扫一扫")
                .setTitleTextSize(18)
                .setTitleTextColor(R.color.white)
                .setTipText("将二维码放入框内扫描~")
                .setTipTextSize(14)
                .setTipMarginTop(40)
                .setTipTextColor(R.color.white)
                .setMaskColor(R.color.scan_color)
                .setSlideIcon(R.drawable.capture_add_scanning)
                .setAngleColor(R.color.white)

                .setScanFrameRectRate((float) 0.8)
                .build();
        QrScan.getInstance().init(configuration);
    }
}
