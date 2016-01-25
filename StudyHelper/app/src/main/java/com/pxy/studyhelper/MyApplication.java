package com.pxy.studyhelper;

import android.app.Application;

import org.xutils.x;

import cn.bmob.v3.Bmob;

/**
 * User: Pxy(15602269883@163.com)
 * Date: 2016-01-24
 * Time: 21:16
 * FIXME
 */
public class MyApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化xUtil
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志

        //初始化Bmob
        Bmob.initialize(this,"b6df61fc2e46b2ba781525d42e8b318a");

    }
}
