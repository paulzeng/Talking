package com.thomas.talking.data;

import com.drugdu.bean.BannerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class DataControl {
    public static List<BannerBean> getBannerData(){
        List<BannerBean> banners = new ArrayList<>();
        BannerBean bean1 = new BannerBean("https://a-ssl.duitang.com/uploads/item/201604/12/20160412094534_4VFKi.jpeg","","");
        banners.add(bean1);
        BannerBean bean2 = new BannerBean("https://a-ssl.duitang.com/uploads/blog/201403/20/20140320135625_y4eQm.thumb.0_800.jpeg","","");
        banners.add(bean2);
        BannerBean bean3 = new BannerBean("https://a-ssl.duitang.com/uploads/blog/201403/20/20140320140226_AXriE.thumb.0_800.jpeg","","");
        banners.add(bean3);
        return banners;
    }
}
