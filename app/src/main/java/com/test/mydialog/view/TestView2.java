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

import com.test.mydialog.AndroidUtils;
import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

/**
 * Created by Administrator on 2018/5/24.
 */

public class TestView2 extends View {
    public boolean isDebug=true;
    private double v;
    private double v2;

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
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initC();
    }

    private void initC() {
        double sqrt = Math.sqrt(480);
        v = AndroidUtils.chengFa(sqrt, 0.3937008);
        v2 = AndroidUtils.chengFa(480, 0.3937008);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        c(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);


        a(canvas);



    }

    private void c(Canvas canvas) {
        canvas.drawLine(10,10,(float) (10+v),10,mPaint);
        canvas.drawLine(30,30,30,(float) (30+v2),mPaint);
        canvas.drawLine(50,30,50,(float) (30+AndroidUtils.chengFa(160*3, 0.3937008)),mPaint);
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
