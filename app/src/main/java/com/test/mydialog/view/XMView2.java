package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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

public class XMView2 extends View {
    public boolean isDebug=true;
    private float dangQianAngle;
    private double meiDuanAngle;

    public void Log(String log) {
        if(BuildConfig.DEBUG&&isDebug){
            Log.i("ClockView===", log);
        }
    }
    public XMView2(Context context) {
        super(context);
        init(null);
    }

    public XMView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public XMView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    private long timeInterval=50;
    Paint mPaint;
    int borderWidth=38;
    int keDuLength=38;
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

        double zhouChang = 2 * mRadius * 3.14;
        double meiDuanLength = AndroidUtils.chuFa(zhouChang, 400);
        borderWidth= (int) meiDuanLength;
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(borderWidth);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white_half3));
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.xm));
        canvas.translate(getWidth()/2,getHeight()/2);
        Calendar calendar=Calendar.getInstance();
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);

        double v = 100 - AndroidUtils.chuFa(69 * 100, 70,3);
        int temp= (int) (255*v/100);
        String str = Integer.toHexString(temp);
        if(str.length()<2){
            str="0"+str;
        }
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
//        mPaint.setColor(Color.parseColor("#"+str+"ffffff"));
        for (int i = 0; i < 200; i++) {
            canvas.drawLine(mRadius-keDuLength/2,0,mRadius+keDuLength/2,0,mPaint);
            canvas.rotate(360*1f/200);
        }
        drawKeDu(canvas);
    }

    private void drawKeDu(Canvas canvas) {

    }
}
