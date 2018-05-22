package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

/**
 * Created by Administrator on 2018/5/22.
 */

public class CircleProgress extends View {
    public boolean isDebug=true;
    private int centerX;
    private int centerY;

    public void Log(String log) {
        if(BuildConfig.DEBUG&&isDebug){
            Log.i("ClockView===", log);
        }
    }
    private Paint mPaint;

    public CircleProgress(Context context) {
        super(context);
        initAttr(null);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }


        initData();
        initPaint();
    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }


    //内圆颜色
    private int neiYuanColor;
    //内圆半径
    private int neiYuanRadius;
    //圆环宽度
    private int ringWidth;
    //圆环颜色
    private int ringColor;
    //圆环进度颜色
    private int ringProgressColor;

    private void initData() {
        neiYuanColor=ContextCompat.getColor(getContext(),R.color.white);
        neiYuanRadius=300;
        ringWidth=30;
        ringColor=ContextCompat.getColor(getContext(),R.color.gray_99);
        ringProgressColor=ContextCompat.getColor(getContext(),R.color.green1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth()/2;
        centerY = getHeight()/2;
        drawCircle(canvas);

        drawRing(canvas);
    }

    private void drawRing(Canvas canvas) {

        mPaint.setColor(ringColor);

        canvas.drawCircle(centerX,centerY,neiYuanRadius,mPaint);
    }

    private void drawCircle(Canvas canvas) {
        mPaint.setColor(neiYuanColor);

        canvas.drawCircle(centerX,centerY,neiYuanRadius,mPaint);
    }
}
