package com.thomas.talking.bean;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

public class MenuBean {
    private String title;
    private int icon;
    private String icon_url;
    public MenuBean(String title,int icon){
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
