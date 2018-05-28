package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
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

public class XMView extends View {
    public boolean isDebug=true;
    private long timeInterval=500;
    private float dangQianAngle;
    private double meiDuanAngle;

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
    int borderWidth=38;
    int mRadius=300;
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
        int millisecond = calendar.get(Calendar.MILLISECOND);
        dangQianAngle = (float) AndroidUtils.chuFa(millisecond * 360, 1000);
        canvas.rotate(dangQianAngle);
        double zhouChang = 2 * mRadius * 3.14;
        double meiDuanLength = AndroidUtils.chuFa(zhouChang, 400);
        meiDuanAngle = AndroidUtils.chuFa(360, 400);
        RectF rectF=new RectF(-mRadius,-mRadius,mRadius,mRadius);
        int color = ContextCompat.getColor(getContext(), R.color.white);
        int color2= ContextCompat.getColor(getContext(), R.color.white_half3);
        Shader shader=new LinearGradient(0,0,
                getMeasuredWidth(),getMeasuredHeight(),
                color,color2, Shader.TileMode.MIRROR);
//        mPaint.setShader(shader);
        int temp=255;
        int colorClock=Color.parseColor("#ffffff");
        drawKeDu(canvas);
        for (int i = 0; i < 400; i++) {
            canvas.save();
            if(true){
                double v = 100 - AndroidUtils.chuFa(69 * 75, 70);
//                Log("13==="+v);
                temp= (int) (255*v/100);
//                Log("1==="+temp);
                String str = Integer.toHexString(temp);
                if(str.length()<2){
                    str="0"+str;
                }
//                Log("2==="+str);
                mPaint.setColor(Color.parseColor("#"+str+"ffffff"));
                colorClock=Color.parseColor("#"+str+"ffffff");
                mPaint.setColor(colorClock);
            }
            canvas.restore();
            if(i<70){
                double v = 100 - AndroidUtils.chuFa(i * 75, 70);
//                Log("13==="+v);
                temp= (int) (255*v/100);
//                Log("1==="+temp);
                String str = Integer.toHexString(temp);
                if(str.length()<2){
                    str="0"+str;
                }
//                Log("2==="+str);
                mPaint.setColor(Color.parseColor("#"+str+"ffffff"));
                colorClock=Color.parseColor("#"+str+"ffffff");
            }else{
                mPaint.setColor(colorClock);
//                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white_half3));
//                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.transparent));
            }
            canvas.drawArc(rectF, dangQianAngle, (float) meiDuanAngle,false,mPaint);
            canvas.rotate((float) (meiDuanAngle *2));
        }
        postInvalidateDelayed(timeInterval -System.currentTimeMillis()%1000);
    }

    private void drawKeDu(Canvas canvas) {
        RectF rectF=new RectF(-mRadius,-mRadius,mRadius,mRadius);
        int color = ContextCompat.getColor(getContext(), R.color.white);
        int color2= ContextCompat.getColor(getContext(), R.color.white_half3);
        int temp=255;
        for (int i = 0; i < 400; i++) {
            int colorClock;
                double v = 100 - AndroidUtils.chuFa(69 * 75, 70);
                temp= (int) (255*v/100);
                String str = Integer.toHexString(temp);
                if(str.length()<2){
                    str="0"+str;
                }
                mPaint.setColor(Color.parseColor("#"+str+"ffffff"));
                colorClock=Color.parseColor("#"+str+"ffffff");
                mPaint.setColor(colorClock);

                mPaint.setColor(colorClock);
            canvas.drawArc(rectF,dangQianAngle, (float) meiDuanAngle,false,mPaint);
            canvas.rotate((float) (meiDuanAngle*2));
        }
    }
}
