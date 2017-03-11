package com.thomas.talking.data;

import com.drugdu.bean.BannerBean;
import com.thomas.talking.R;
import com.thomas.talking.bean.MenuBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class DataControl {
    public static final List<String> datas = Arrays.asList("You know some birds are not meant to be caged, their feathers are just too bright. ",
            "I guess it comes down to a simple choice:get busy living or get busy dying. ",
            "Let me tell you something my friend: Hope is a dangerous thing. Hope can drive a man insane. ");

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

    public static List<MenuBean> getMenuData(){
        List<MenuBean> menus = new ArrayList<>();
        MenuBean bean1 = new MenuBean("Procurement\nservice", R.drawable.service);
        menus.add(bean1);
        MenuBean bean2 = new MenuBean("Product\nDirctory", R.drawable.product);
        menus.add(bean2);
        MenuBean bean3 = new MenuBean("Globel\nSuppliers", R.drawable.global);
        menus.add(bean3);
        MenuBean bean4 = new MenuBean("Agency\nService", R.drawable.agency);
        menus.add(bean4);
        return menus;
    }
}
