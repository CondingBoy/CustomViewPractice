package com.wang.customviewpractice;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by wangdachui on 2017/5/19.
 */

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "5460913954", false);
    }
}
