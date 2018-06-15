package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

/**
 * Created by Administrator on 2018/5/24.
 */

public class TestView2 extends View {
    public boolean isDebug=true;
    public void Log(String log) {
        if(BuildConfig.DEBUG&&isDebug){
            Log.i("ClockView===", log);
        }
    }
    public TestView2(Context context) {
        super(context);
        init();
    }
    public TestView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public TestView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    Paint mPaint;
    int strokeWidth=2;
    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.translate(getWidth()/2,getHeight()/2);


        a(canvas);



    }

    private void a(Canvas canvas) {

        Path path=new Path();
        RectF rectF=new RectF(-250,-250,250,250);
        RectF rectF2=new RectF(-200,-200,200,200);


        path.addArc(rectF,-45,90);
        path.arcTo(rectF2,45,-90);
        path.close();

        canvas.drawPath(path,mPaint);

    }
}
