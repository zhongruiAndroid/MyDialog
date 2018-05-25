package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

/**
 * Created by Administrator on 2018/5/25.
 */

public class XMView extends View {
    public boolean isDebug=true;
    public void Log(String log) {
        if(BuildConfig.DEBUG&&isDebug){
            Log.i("ClockView===", log);
        }
    }
    public XMView(Context context) {
        super(context);
        init(null);
    }

    public XMView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public XMView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    Paint mPaint;
    int borderWidth;
    int mRadius=300;
    private void init(AttributeSet attrs) {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(borderWidth);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue_00));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        Path path=new Path();
//        path.arcTo();
        canvas.drawPath(path,mPaint);
    }
}
