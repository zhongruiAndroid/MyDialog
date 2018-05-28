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

public class XMView4 extends View {
    public boolean isDebug=true;
    private float dangQianAngle;
    private double meiDuanAngle;
    private double zhouChang;

    public void Log(String log) {
        if(BuildConfig.DEBUG&&isDebug){
            Log.i("ClockView===", log);
        }
    }
    public XMView4(Context context) {
        super(context);
        init(null);
    }

    public XMView4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public XMView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    private long timeInterval=50;
    Paint mPaint;
    float borderWidth;//弧长
    float keDuLength=38;
    int mRadius=280;
    int keDuNum=200;
    int pointLength=4;
    float offsetAngle;
    float keDuAngle;
    float offsetLength;
    private void init(AttributeSet attrs) {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(keDuLength);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white_half3));
        zhouChang = 2 * mRadius * Math.PI;
        Log("==="+Math.PI);
        borderWidth= (float)AndroidUtils.chuFa(zhouChang,keDuNum*2,pointLength);
        keDuAngle=(float) AndroidUtils.chuFa(360,keDuNum*2,pointLength);
        offsetAngle= (float) AndroidUtils.chuFa(keDuAngle,3,pointLength);//每个刻度需要绘制3次完成(用于着色)
        offsetLength=(float) AndroidUtils.chuFa(keDuLength,3,pointLength);
        Log("==周长="+zhouChang+"==单次旋转角度="+offsetAngle+"==刻度宽度="+keDuLength+"==单次旋转弧长="+offsetLength);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(keDuLength);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white_half));
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.xm));
        canvas.translate(getWidth()/2,getHeight()/2);
        Calendar calendar=Calendar.getInstance();
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);

        dangQianAngle = (float) ((float) AndroidUtils.chuFa((millisecond+second*1000) * 360, 1000*60));
       /* canvas.save();
        canvas.rotate(dangQianAngle);*/
        double meiDuanLength = AndroidUtils.chuFa(zhouChang, 400);
        meiDuanAngle = AndroidUtils.chuFa(360, keDuNum*2);
        RectF rectF=new RectF(-mRadius,-mRadius,mRadius,mRadius);
        int color = ContextCompat.getColor(getContext(), R.color.white);
        int color2= ContextCompat.getColor(getContext(), R.color.white_half3);
        int temp=255;
        int colorClock=Color.parseColor("#ffffff");
//        drawKeDu(canvas);

        for (int i = 0; i < keDuNum*2; i++) {
            if(i==keDuNum*2-2) {//最后一个刻度分三次绘制

                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
                canvas.drawArc(rectF,0,offsetAngle,false,mPaint);
                canvas.rotate(offsetAngle);


                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
                canvas.drawArc(rectF,0,offsetAngle,false,mPaint);
                canvas.rotate(offsetAngle);


                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white_half));
                canvas.drawArc(rectF,0,offsetAngle,false,mPaint);
                canvas.rotate(offsetAngle);
            }else if(i%2==0){
                canvas.drawArc(rectF,0,keDuAngle,false,mPaint);
                canvas.rotate(keDuAngle);
            }else{
                canvas.rotate(keDuAngle);
            }
        }
//        postInvalidateDelayed(timeInterval -System.currentTimeMillis()%1000);
    }

    private void drawKeDu(Canvas canvas) {
        RectF rectF=new RectF(-mRadius,-mRadius,mRadius,mRadius);
        int color = ContextCompat.getColor(getContext(), R.color.white);
        int color2= ContextCompat.getColor(getContext(), R.color.white_half3);
        int temp=255;
        for (int i = 0; i < 400; i++) {
            int colorClock;
                double v = 100 - AndroidUtils.chuFa(69 * 100, 70,pointLength);
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

