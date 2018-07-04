package com.test.mydialog.view.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/7/4.
 */

public class RL extends RelativeLayout {
    public RL(Context context) {
        super(context);
    }

    public RL(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RL(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("====","2===ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("====","2===ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("====","2===ACTION_UP");

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("====","2===ACTION_CANCEL");

                break;
        }
//        return super.onTouchEvent(event);
        return true;
    }
}
