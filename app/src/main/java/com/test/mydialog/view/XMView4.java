package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
 * Created by Administrator on 2018/5/25.
 */

public class XMView4 extends View {
    public boolean isDebug=true;

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
    float keDuLength;
    float keDuJianGe=keDuLength;
    float keDuWidth=38;
    float keDuJianGeAngle;
    int mRadius=320;
    int keDuNum=200;
    int pointLength=4;
    int bianPanWidth=4;
    int bianPanIntervalAngle=6;
    int bianPanColor;


    private float dangQianAngle;
    private float meiDuanAngle;
    private float zhouChang;

    float offsetAngle;
    float keDuAngle;
    float offsetLength;
    private void init(AttributeSet attrs) {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(keDuLength);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white_half20));
        zhouChang = (float) (2 * (mRadius-keDuJianGe-keDuLength/2) * Math.PI);
        keDuWidth= (float) AndroidUtils.chuFa(zhouChang,keDuNum*2,3);
        keDuJianGeAngle= (float) AndroidUtils.chuFa(360,keDuNum,3);
        keDuAngle=(float) AndroidUtils.chuFa(360,keDuNum,pointLength);
        offsetAngle= (float) AndroidUtils.chuFa(keDuAngle,3,pointLength);//每个刻度需要绘制3次完成(用于着色)
        offsetLength=(float) AndroidUtils.chuFa(keDuLength,3,pointLength);
        bianPanColor=ContextCompat.getColor(getContext(), R.color.white_half70);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.xm));
        canvas.translate(getWidth()/2,getHeight()/2);

        //绘制表盘弧线
        drawBiaoPan(canvas);
        //绘制表盘刻度文本
        drawBiaoPanText(canvas);
        //绘制表盘刻度
        drawBiaoPanKeDu(canvas);

        canvas.restore();

    }

    private void drawBiaoPanKeDu(Canvas canvas) {
        mPaint.reset();
        mPaint.setStrokeWidth(keDuWidth);
        mPaint.setAntiAlias(true);
        mPaint.setColor(bianPanColor);

        canvas.save();
        for (int i = 0; i < 200; i++) {
            canvas.drawLine(mRadius-keDuJianGe-keDuLength,0,mRadius-keDuLength,0,mPaint);
            canvas.rotate(keDuJianGeAngle);
        }
        canvas.restore();
    }

    private void drawBiaoPanText(Canvas canvas) {
        String str="12";
        Rect rect=new Rect();
        mPaint.reset();

        mPaint.setAntiAlias(true);
        mPaint.setTextSize(dip2px(getContext(),12));
        mPaint.setColor(bianPanColor);
        mPaint.getTextBounds(str,0,str.length(),rect);

        float measureText = mPaint.measureText(str);
        canvas.drawText(str,-measureText/2,-mRadius+rect.height()/2,mPaint);

        str="3";
        measureText = mPaint.measureText(str);
        canvas.drawText(str,mRadius-measureText/2,rect.height()/2,mPaint);

        str="6";
        measureText = mPaint.measureText(str);
        canvas.drawText(str,-measureText/2,mRadius+rect.height()/2,mPaint);

        str="9";
        measureText = mPaint.measureText(str);
        canvas.drawText(str,-mRadius-measureText/2,rect.height()/2,mPaint);
    }

    private void drawBiaoPan(Canvas canvas) {
        mPaint.setStrokeWidth(bianPanWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(bianPanColor);
        mPaint.setAntiAlias(true);
        RectF rectF=new RectF(-mRadius,-mRadius,mRadius,mRadius);
        for (int i = 0; i < 4; i++) {
            canvas.drawArc(rectF,bianPanIntervalAngle-90+90*i,90-bianPanIntervalAngle*2,false,mPaint);
        }
    }

    private void drawKeDu(Canvas canvas) {
        RectF rectF=new RectF(-mRadius,-mRadius,mRadius,mRadius);
        int color = ContextCompat.getColor(getContext(), R.color.white);
        int color2= bianPanColor;
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
    private int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    private int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

}

