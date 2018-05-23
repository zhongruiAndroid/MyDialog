package com.test.mydialog.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.test.mydialog.AndroidUtils;
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
    //圆环半径
    private int ringRadius;
    //圆环宽度
    private int ringWidth;
    //圆环颜色
    private int ringColor;
    //圆环进度颜色
    private int ringProgressColor;
    //圆环进度过度颜色
    private int ringProgressSecondColor;
    //开始角度
    private int startAngle=120;
    //是否顺时针
    private boolean isClockwise=true;
    //当前进度
    private int progress=10;
    //总进度
    private int maxProgress=1000;
    //不绘制的度数
    private int disableAngle=60;
    //圆环进度是否为圆角
    private boolean isRound=true;
    //是否设置动画
    private boolean useAnimation =true;
    //动画执行时间
    private int duration =1000;

    //进度百分比
    private double progressPercent;
    //进度百分比数值是否是小数
    private boolean isDecimal=true;
    //小数点后几位
    private int decimalPointLength=1;
    //是否显示百分比
    private boolean isShowPercentText=true;
    private int textColor;
    private int textSize;

    //显示百分比还是具体数量
    private int percentOrText;

    private void initData() {
        neiYuanColor=ContextCompat.getColor(getContext(),R.color.transparent);
        ringRadius =-1;
        ringWidth=30;
        ringColor=ContextCompat.getColor(getContext(),R.color.gray_99);
        ringProgressColor=ContextCompat.getColor(getContext(),R.color.green1);
        ringProgressSecondColor=ContextCompat.getColor(getContext(),R.color.blue_00);
        textSize=dip2px(getContext(),17);
        textColor=ContextCompat.getColor(getContext(),R.color.green1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int mWidth =200;
        int mHeight = 200;
        if(getLayoutParams().width== ViewGroup.LayoutParams.WRAP_CONTENT&&getLayoutParams().height== ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(mWidth,mHeight);
        }else if(getLayoutParams().width== ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(mWidth,heightSize);
        }else if(getLayoutParams().height== ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(widthSize,mHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int WH=Math.min(getWidth()-getPaddingLeft()-getPaddingRight(),getHeight()-getPaddingTop()-getPaddingBottom());
        if(ringRadius<0){
            ringRadius=(WH-ringWidth)/2;
        }
        centerX = getWidth()/2;
        centerY = getHeight()/2;
        //绘制圆环
//        drawRing(canvas);
        drawRing2(canvas);
        //绘制内圆
        drawNeiYuan(canvas);
        //绘制进度圆环
        drawProgressRing(canvas);
        //绘制进度百分比
        if(isShowPercentText){
            drawProgressText(canvas);
        }
    }
    public int getEffectiveDegree(){
        return 360-disableAngle;
    }
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        setProgress(progress,useAnimation);
    }
    public void setProgress(int progress,boolean useAnimation) {
        int beforeProgress=this.progress;
        if(progress>maxProgress){
            this.progress=maxProgress;
        }else if(progress<0){
            this.progress=0;
        }else{
            this.progress = progress;
        }
        if(useAnimation){
            ValueAnimator valueAnimator =ValueAnimator.ofInt(beforeProgress,progress);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    CircleProgress.this.progress = (int) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.setDuration(duration);
            valueAnimator.start();
        }else{
            invalidate();
        }
    }

    private void drawProgressText(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        progressPercent  = AndroidUtils.chuFa(progress * 100, maxProgress, decimalPointLength);
        String percentStr=progressPercent+"%";
        if(!isDecimal){
            percentStr=((int)progressPercent)+"%";
        }
        Rect rect=new Rect();
        mPaint.setTextSize(textSize);
        mPaint.setColor(textColor);
        mPaint.getTextBounds(percentStr,0,percentStr.length(),rect);
        float baseLineHeight = Math.abs(mPaint.getFontMetrics().ascent);
        canvas.drawText(percentStr+"",centerX-rect.width()/2,centerY+baseLineHeight/2,mPaint);

    }

    private void drawNeiYuan(Canvas canvas) {

        mPaint.setColor(neiYuanColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX,centerY,ringRadius-ringWidth/2,mPaint);
    }

    private void drawProgressRing(Canvas canvas) {
        mPaint.setColor(ringProgressColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ringWidth);
        mPaint.setShader(null);

        RectF rectF=new RectF(centerX-ringRadius,centerY-ringRadius,centerX+ringRadius,centerY+ringRadius);
//        mPaint.setShadowLayer(1000,1000,1000,ContextCompat.getColor(getContext(),R.color.blue_00));
        LinearGradient linearGradient = new LinearGradient(0,0,
                getMeasuredWidth(),getMeasuredHeight(),
                ringProgressColor,ringProgressSecondColor,
                Shader.TileMode.MIRROR);
        mPaint.setShader(linearGradient);

        if(isRound){
            mPaint.setStrokeCap(Paint.Cap.ROUND);
        }
        float angle = (float) AndroidUtils.chuFa(progress*getEffectiveDegree(),maxProgress,2);
        if(!isClockwise){
            angle=-1*angle;
        }
        canvas.drawArc(rectF,startAngle,angle,false,mPaint);
    }

    private void drawRing2(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ringWidth);
        mPaint.setColor(ringColor);
        RectF rectF=new RectF(centerX-ringRadius,centerY-ringRadius,centerX+ringRadius,centerY+ringRadius);
        if(isRound){
            mPaint.setStrokeCap(Paint.Cap.ROUND);
        }
        float angle = getEffectiveDegree();

        if(!isClockwise){
            angle=-1*angle;
        }
        canvas.drawArc(rectF,startAngle,angle,false,mPaint);
    }
    private void drawRing(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ringWidth);
        mPaint.setColor(ringColor);
        canvas.drawCircle(centerX,centerY, ringRadius,mPaint);
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
