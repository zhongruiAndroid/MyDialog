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

public class TestView extends View {
    public boolean isDebug=true;
    public void Log(String log) {
        if(BuildConfig.DEBUG&&isDebug){
            Log.i("ClockView===", log);
        }
    }
    public TestView(Context context) {
        super(context);
        init();
    }
    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    Paint mPaint;
    int strokeWidth=150;
    int radiusTop=130;
    int radiusLeft=180;
    int offset=6;
    int angle=90;
    int flag=2;
    int jianGe=10;
    private void init() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        Log("===onDraw");
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.save();
       /* RectF rectF=new RectF(-radiusLeft-strokeWidth/2,-radiusTop-strokeWidth/2,radiusLeft+strokeWidth/2,radiusTop+strokeWidth/2);
//        canvas.drawOval(rectF,mPaint);
        canvas.drawArc(rectF, 0,360, false, mPaint);*/

/*
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.green));
        rectF=new RectF(-radiusLeft-strokeWidth/2,-radiusTop-strokeWidth/2,radiusLeft+strokeWidth/2,radiusTop+strokeWidth/2);
        canvas.drawArc(rectF, 45+flag,angle, false, mPaint);


        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.orange));
        rectF=new RectF(-radiusLeft-strokeWidth/2,-radiusTop-strokeWidth/2,radiusLeft+strokeWidth/2,radiusTop+strokeWidth/2);
        canvas.drawArc(rectF, 135+flag,angle, false, mPaint);


        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.active_dianying1));
        rectF=new RectF(-radiusLeft-strokeWidth/2,-radiusTop-strokeWidth/2,radiusLeft+strokeWidth/2,radiusTop+strokeWidth/2);
        canvas.drawArc(rectF, 225+flag,angle, false, mPaint);*/

        int sqrt = (int) (jianGe/Math.sqrt(2));

        Path path=new Path();
        RectF rectF=new RectF(-radiusLeft-strokeWidth/2,-radiusTop-strokeWidth/2,radiusLeft+strokeWidth/2,radiusTop+strokeWidth/2);
        path.arcTo(rectF,-45,90,true);
        canvas.drawPath(path,mPaint);

        Log.i("==","===" + sqrt);
        canvas.translate(-sqrt,+sqrt);
        path=new Path();
        rectF=new RectF(-radiusLeft-strokeWidth/2,-radiusTop-strokeWidth/2,radiusLeft+strokeWidth/2,radiusTop+strokeWidth/2);
        path.arcTo(rectF,45,90,true);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        canvas.drawPath(path,mPaint);


        canvas.translate(-sqrt,-sqrt);
        path=new Path();
        path.arcTo(rectF,135,90,true);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.green));
        canvas.drawPath(path,mPaint);


        canvas.translate(sqrt,-sqrt);
        path=new Path();
        path.arcTo(rectF,225,90,true);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        canvas.drawPath(path,mPaint);

        canvas.restore();

        canvas.translate(-sqrt,0);

        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.green));
//        int flag=sqrt;
        int flag=jianGe/2;
        rectF=new RectF(-radiusLeft+flag,-radiusTop+flag,radiusLeft-flag,radiusTop-flag);
        canvas.drawArc(rectF, 0,360, false, mPaint);




       /* Path path=new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        path.lineTo(100,100);
        RectF rectF1=new RectF(130,130,300,300);
        path.arcTo(rectF1,-90,90,false);
        path.close();
        canvas.drawPath(path,mPaint);
*/
    }

}
