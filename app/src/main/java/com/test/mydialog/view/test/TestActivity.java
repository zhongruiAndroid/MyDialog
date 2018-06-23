package com.test.mydialog.view.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;

import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

public class TestActivity extends AppCompatActivity {

    public void Log(String log) {
        if(BuildConfig.DEBUG){
            Log.i(this.getClass().getSimpleName()+"===", log);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }

    private void init() {


// 获取屏幕密度（方法3）
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        float density  = dm.density;      // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        float densityDPI = dm.densityDpi;
//        Log("==="+density+"====="+densityDPI);
    }
}
