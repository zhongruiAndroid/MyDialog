package com.test.mydialog;


import android.app.Application;

import com.github.retrofitutil.NetWorkManager;


/**
 * Created by administartor on 2017/8/8.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //正式
        String baseUrl="http://121.40.186.118:10088/";
        if(true&&BuildConfig.DEBUG){
            //测试
            baseUrl="http://121.40.186.118:10089/";
        }
        baseUrl="http://121.40.186.118:10089/";
        NetWorkManager.getInstance(getApplicationContext(),baseUrl,BuildConfig.DEBUG).complete();
    }
}
