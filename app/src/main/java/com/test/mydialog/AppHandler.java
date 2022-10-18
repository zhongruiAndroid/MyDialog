package com.test.mydialog;

import android.os.Handler;
import android.os.Looper;

public class AppHandler {
    private static Handler handler;
    public static synchronized Handler getHandler(){
        if(handler==null){
            handler=new Handler(Looper.getMainLooper());
        }
        return handler;
    }
}