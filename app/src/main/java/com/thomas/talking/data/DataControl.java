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
        BannerBean bean1 = new BannerBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489064397892&di=156d75204e3743396b34603e6a11d153&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0153035770d5e20000012e7e9b3cbf.jpg%40900w_1l_2o_100sh.jpg","","");
        banners.add(bean1);
        BannerBean bean2 = new BannerBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489064398052&di=bb810aed0332a7cb7ef0d065c09fc946&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F17%2F90%2F90%2F55a900020385c_1024.jpg","","");
        banners.add(bean2);
        BannerBean bean3 = new BannerBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489064398050&di=6dd8143bcee85b05ba5ae7cc5c282022&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F00%2F69%2F40%2Fad1ec9813fa678f4ade69ab4fbaccbce.jpg","","");
        banners.add(bean3);
        return banners;
    }
}
