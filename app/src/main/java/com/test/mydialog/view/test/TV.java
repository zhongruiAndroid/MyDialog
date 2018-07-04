package com.test.mydialog.view.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/7/4.
 */

public class TV extends TextView {
    public TV(Context context) {
        super(context);
    }

    public TV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TV(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("====","1===ACTION_DOWN");
            break;
            case MotionEvent.ACTION_MOVE:
                Log.i("====","1===ACTION_MOVE");
            break;
            case MotionEvent.ACTION_UP:
                Log.i("====","1===ACTION_UP");

            break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("====","1===ACTION_CANCEL");

            break;
        }
//        return super.onTouchEvent(event);
        return true;
    }
}
