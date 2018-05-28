package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.test.mydialog.AndroidUtils;
import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2018/5/25.
 */

public class XMView3 extends View {
    public boolean isDebug=true;
    private float dangQianAngle;
    private double meiDuanAngle;

    public void Log(String log) {
        if(BuildConfig.DEBUG&&isDebug){
            Log.i("ClockView===", log);
        }
    }
    public XMView3(Context context) {
        super(context);
        init(null);
    }

    public XMView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public XMView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    private long timeInterval=50;
    Paint mPaint;
    int borderWidth=38;
    int mRadius=300;
    int keDuNum=200;
    private void init(AttributeSet attrs) {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(borderWidth);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white_half3));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(borderWidth);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white_half3));
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.xm));
        canvas.translate(getWidth()/2,getHeight()/2);
        Calendar calendar=Calendar.getInstance();
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);

        dangQianAngle = (float) ((float) AndroidUtils.chuFa((millisecond+second*1000) * 360, 1000*60));
        canvas.save();
        canvas.rotate(dangQianAngle);
        double zhouChang = 2 * mRadius * 3.14;
        double meiDuanLength = AndroidUtils.chuFa(zhouChang, 400);
        meiDuanAngle = AndroidUtils.chuFa(360, 400);
        RectF rectF=new RectF(-mRadius,-mRadius,mRadius,mRadius);
        int color = ContextCompat.getColor(getContext(), R.color.white);
        int color2= ContextCompat.getColor(getContext(), R.color.white_half3);
        int temp=255;
        int colorClock=Color.parseColor("#ffffff");
//        drawKeDu(canvas);

        for (int i = 0; i < 70; i++) {
            double v = 100 - AndroidUtils.chuFa(i * 100, 70,3);
            temp= (int) (255*v/100);
            String str = Integer.toHexString(temp);
            if(str.length()<2){
                str="0"+str;
            }
            mPaint.setColor(Color.parseColor("#"+str+"ffffff"));
            colorClock=Color.parseColor("#"+str+"ffffff");

            canvas.drawArc(rectF, dangQianAngle, (float) meiDuanAngle,false,mPaint);
            canvas.rotate((float) (meiDuanAngle *2));
        }
        canvas.restore();
        postInvalidateDelayed(timeInterval -System.currentTimeMillis()%1000);
    }

    private void drawKeDu(Canvas canvas) {
        RectF rectF=new RectF(-mRadius,-mRadius,mRadius,mRadius);
        int color = ContextCompat.getColor(getContext(), R.color.white);
        int color2= ContextCompat.getColor(getContext(), R.color.white_half3);
        int temp=255;
        for (int i = 0; i < 400; i++) {
            int colorClock;
                double v = 100 - AndroidUtils.chuFa(69 * 100, 70,3);
                temp= (int) (255*v/100);
                String str = Integer.toHexString(temp);
                if(str.length()<2){
                    str="0"+str;
                }
                mPaint.setColor(Color.parseColor("#"+str+"ffffff"));
                colorClock=Color.parseColor("#"+str+"ffffff");
                mPaint.setColor(colorClock);

                mPaint.setColor(colorClock);
            canvas.drawArc(rectF,0, (float) meiDuanAngle,false,mPaint);
            canvas.rotate((float) (meiDuanAngle*2));
        }
    }
}
