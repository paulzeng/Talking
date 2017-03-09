package com.drugdu;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7 0007.
 * 初始化应用以及第三方lib初始化
 */

public class APP extends Application {
    private static Context context;
    private static int mainThreadId;
    private static Handler handler;
    public static List<Activity> allActivity;
    @Override
    public void onCreate() {
        super.onCreate();
        allActivity = new LinkedList<>();
        context = getApplicationContext();
        mainThreadId = android.os.Process.myTid();// 获取当前主线程id
        handler = new Handler();
    }

    public static Context getContext() {
        return context;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static boolean isLogin() {
        return false;
    }

    public static String getUserToken(){
        return null;
    }

    /**
     * 销毁所有activity，应用退出
     */
    public void exit() {
        for (Activity activity : allActivity) {
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
