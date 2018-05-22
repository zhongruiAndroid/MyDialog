package com.test.mydialog.view;

import android.content.Context;
import android.content.res.TypedArray;
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
import android.view.ViewGroup;

import com.test.mydialog.AndroidUtils;
import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2018/5/22.
 */

public class ClockView extends View {
    public void Log(String log) {
        if(BuildConfig.DEBUG){
            Log.i("ClockView===", log);
        }
    }
    private final int def_keduLength=25;
    private final int def_keduWidth=3;

    private final int def_zhengkeduLength=35;
    private final int def_zhengkeduWidth=6;

    private final int def_clockRadius=0;
    private final int def_clockPointRadius=22;
    private final int def_clockPadding=20;

    private final int def_ZhenLengthD=50;

    private final int def_shiZhenWidth=26;
    private final int def_fenZhenWidth=16;
    private final int def_miaoZhenWidth=10;
    private final int def_textSize=17;


    public float keduLength;
    public float keduWidth;
    public int keduColor;
    public float zhengkeduLength;
    public float zhengkeduWidth;
    public int zhengkeduColor;
    public float clockRadius;
    public int clockColor;
    public float clockBorderWidth;
    public int clockBorderColor;
    public float clockPointRadius;
    public int clockPointColor;
    public float clockPadding;
    public float shiZhenLength;
    public float shiZhenLengthD;
    public float shiZhenWidth;
    public int shiZhenColor;
    public float fenZhenLength;
    public float fenZhenLengthD;
    public float fenZhenWidth;
    public int fenZhenColor;
    public float miaoZhenLength;
    public float miaoZhenLengthD;
    public float miaoZhenWidth;
    public int miaoZhenColor;
    public int clockTextSize;
    public int clockTextColor;
    public int clockTextDistance;
    private int timeInterval=1000;

    private Paint mPaint;

    public ClockView(Context context) {
        super(context);
        initAttr(null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        if(attrs==null){
            return;
        }
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ClockView);
        keduLength=   typedArray.getDimension(R.styleable.ClockView_keduLength,def_keduLength);
        keduWidth=   typedArray.getDimension(R.styleable.ClockView_keduWidth,def_keduWidth);
        /*if(keduWidth<=1){
            keduWidth=2;
        }*/
        keduColor=typedArray.getColor(R.styleable.ClockView_keduColor,getDef_keduColor());
        zhengkeduLength=typedArray.getDimension(R.styleable.ClockView_zhengkeduLength,def_zhengkeduLength);
        zhengkeduWidth=typedArray.getDimension(R.styleable.ClockView_zhengkeduWidth,def_zhengkeduWidth);
        /*if(zhengkeduWidth<=1){
            zhengkeduWidth=2;
        }*/
        zhengkeduColor=typedArray.getColor(R.styleable.ClockView_zhengkeduColor,getDef_zhengkeduColor());
        clockRadius=typedArray.getDimension(R.styleable.ClockView_clockRadius,def_clockRadius);
        clockColor=typedArray.getColor(R.styleable.ClockView_clockColor,getDef_clockColor());
        clockBorderWidth=typedArray.getDimension(R.styleable.ClockView_clockBorderWidth,0);
        clockBorderColor=typedArray.getColor(R.styleable.ClockView_clockBorderColor,getTransparentColor());
        clockPointRadius=typedArray.getDimension(R.styleable.ClockView_clockPointRadius,def_clockPointRadius);
        clockPointColor=typedArray.getColor(R.styleable.ClockView_clockPointColor,getDef_clockPointColor());
        clockPadding=typedArray.getDimension(R.styleable.ClockView_clockPadding,def_clockPadding);
        shiZhenLength=typedArray.getDimension(R.styleable.ClockView_shiZhenLength,0);
        shiZhenLengthD=typedArray.getDimension(R.styleable.ClockView_shiZhenLengthD,def_ZhenLengthD);
        shiZhenWidth=typedArray.getDimension(R.styleable.ClockView_shiZhenWidth,def_shiZhenWidth);
        shiZhenColor=typedArray.getColor(R.styleable.ClockView_shiZhenColor,getDef_shiZhenColor());
        fenZhenLength=typedArray.getDimension(R.styleable.ClockView_fenZhenLength,0);
        fenZhenLengthD=typedArray.getDimension(R.styleable.ClockView_fenZhenLengthD,def_ZhenLengthD);
        fenZhenWidth=typedArray.getDimension(R.styleable.ClockView_fenZhenWidth,def_fenZhenWidth);
        fenZhenColor=typedArray.getColor(R.styleable.ClockView_fenZhenColor,getDef_fenZhenColor());
        miaoZhenLength=typedArray.getDimension(R.styleable.ClockView_miaoZhenLength,0);
        miaoZhenLengthD=typedArray.getDimension(R.styleable.ClockView_miaoZhenLengthD,def_ZhenLengthD);
        miaoZhenWidth=typedArray.getDimension(R.styleable.ClockView_miaoZhenWidth,def_miaoZhenWidth);
        miaoZhenColor=typedArray.getColor(R.styleable.ClockView_miaoZhenColor,getDef_miaoZhenColor());
        timeInterval=typedArray.getColor(R.styleable.ClockView_timeInterval,1000);
        clockTextSize= (int) typedArray.getDimension(R.styleable.ClockView_clockTextSize,getDef_TextSize());
        clockTextColor= typedArray.getColor(R.styleable.ClockView_clockTextColor,getDef_zhengkeduColor());
        clockTextDistance= (int) typedArray.getDimension(R.styleable.ClockView_clockTextDistance,55);


        typedArray.recycle();

        initPaint();
    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        int mWidth =500;
        int mHeight = 500;
        // 当布局参数设置为wrap_content时，设置默认值
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight);
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth()-getPaddingLeft()-getPaddingRight();
        int height=getHeight()-getPaddingTop()-getPaddingBottom();
        if(clockRadius==0){
            clockRadius=width/2;
        }
        if(width>height){
            if(height/2<clockRadius+clockBorderWidth){
                clockRadius=height/2-clockBorderWidth;
            }
        }else{
            if(width/2<clockRadius+clockBorderWidth){
                clockRadius=width/2-clockBorderWidth;
            }
        }
        if(shiZhenLength==0){
            shiZhenLength=getDef_shiZhenLength();
        }
        if(fenZhenLength==0){
            fenZhenLength=getDef_fenZhenLength();
        }
        if(miaoZhenLength==0){
            miaoZhenLength=getDef_miaoZhenLength();
        }
        canvas.save();
        canvas.translate(width/2+getPaddingLeft(),height/2+getPaddingTop());
        if(clockBorderWidth>0){
            drawClockBorder(canvas);
        }
        paintCircle(canvas);
        paintLine(canvas);
        drawShiZhen(canvas);
        drawFenZhen(canvas);
        drawMiaoZhen(canvas);
        drawYuanDian(canvas);

        canvas.restore();


        postInvalidateDelayed(timeInterval-System.currentTimeMillis()%1000);
    }

    private void drawYuanDian(Canvas canvas) {
        canvas.save();

        mPaint.setColor(clockPointColor);
        canvas.drawCircle(0, 0, clockPointRadius, mPaint);
        canvas.restore();
    }

    private void drawMiaoZhen(Canvas canvas) {
        mPaint.setColor(miaoZhenColor);
        canvas.save();
        Calendar calendar=Calendar.getInstance();
        float second = calendar.get(Calendar.SECOND);
        float millisecond = calendar.get(Calendar.MILLISECOND);
        float v = (float) (AndroidUtils.chuFa(millisecond, 1000, 2) * 6);
        float miaoZhen=(second-15)*6+v;
        RectF r2 = new RectF();
        r2.left = 0;
        r2.right = miaoZhenLength;
        r2.top = -miaoZhenWidth/2;
        r2.bottom = miaoZhenWidth/2;
        canvas.rotate(miaoZhen);
        canvas.drawRoundRect(r2,miaoZhenWidth/2,miaoZhenWidth/2,mPaint);

        canvas.rotate(180);
        r2 = new RectF();
        r2.left = 0;
        r2.right = miaoZhenLengthD;
        r2.top = -miaoZhenWidth/2;
        r2.bottom = miaoZhenWidth/2;
        canvas.drawRoundRect(r2,miaoZhenWidth/2,miaoZhenWidth/2,mPaint);
        canvas.restore();
    }

    private void drawFenZhen(Canvas canvas) {
        mPaint.setColor(fenZhenColor);
        canvas.save();
        Calendar calendar=Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        double v =   (AndroidUtils.chuFa(second, 60) * 6);
        float fenZhen = (float) ((minute-15)*6+v);
        RectF r2 = new RectF();
        r2.left = 0;
        r2.right = fenZhenLength;
        r2.top = -fenZhenWidth/2;
        r2.bottom = fenZhenWidth/2;
        canvas.rotate(fenZhen);
        canvas.drawRoundRect(r2,fenZhenWidth/2,fenZhenWidth/2,mPaint);

        canvas.rotate(180);
        r2 = new RectF();
        r2.left = 0;
        r2.right = fenZhenLengthD;
        r2.top = -fenZhenWidth/2;
        r2.bottom = fenZhenWidth/2;
        canvas.drawRoundRect(r2,fenZhenWidth/2,fenZhenWidth/2,mPaint);
        canvas.restore();
    }

    private void drawShiZhen(Canvas canvas) {
        mPaint.setColor(shiZhenColor);
        canvas.save();
        Calendar calendar=Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        double v =   AndroidUtils.chengFa(AndroidUtils.chuFa(minute,60),30);
        float shizhen = (float) ((hour - 3) * 30+v);//每一个小时间隔30度,减3是因为从刻度3开始绘制，分针也是
        RectF r2 = new RectF();
        r2.left = 0;
        r2.right = shiZhenLength;
        r2.top = -shiZhenWidth/2;
        r2.bottom = shiZhenWidth/2;
        canvas.rotate(shizhen);
        canvas.drawRoundRect(r2,shiZhenWidth/2,shiZhenWidth/2,mPaint);

        canvas.rotate(180);
        r2 = new RectF();
        r2.left = 0;
        r2.right = shiZhenLengthD;
        r2.top = -shiZhenWidth/2;
        r2.bottom = shiZhenWidth/2;
        canvas.drawRoundRect(r2,shiZhenWidth/2,shiZhenWidth/2,mPaint);
        canvas.restore();
    }

    private void paintLine(Canvas canvas) {
        canvas.save();
        int kedu=0;
        for (int i = 0; i < 60; i++) {
            if(i%5==0){
                mPaint.setStrokeWidth(zhengkeduWidth);
                mPaint.setColor(zhengkeduColor);
                canvas.drawLine(clockRadius-zhengkeduLength-clockPadding,0,clockRadius-clockPadding,0,mPaint);
                if(i==0){
                    kedu=kedu+3;//从刻度3开始绘制
                }else{
                    if(kedu==12){
                        kedu=1;
                    }else{
                        kedu++;
                    }
                }
                String keduStr = kedu + "";
                mPaint.setTextSize(clockTextSize);
                Rect rect = new Rect();
                mPaint.getTextBounds(keduStr,0,keduStr.length(),rect);
                int textHeight = rect.height(); //获得文字高度
                int textWidth = rect.width(); //获得文字宽度
                float textStartX=zhengkeduLength+clockPadding+clockTextDistance;
                canvas.save();
                canvas.translate(clockRadius-textStartX+rect.width()/2,0);
                canvas.rotate(i*-6);
                float textY = mPaint.getFontMetrics().ascent + mPaint.getFontMetrics().leading;
                mPaint.setColor(clockTextColor);
                canvas.drawText(keduStr,-textWidth/2-zhengkeduWidth/2,textHeight/2,mPaint);
                canvas.restore();
            }else{
                mPaint.setStrokeWidth(keduWidth);
                mPaint.setColor(keduColor);
                canvas.drawLine(clockRadius-keduLength-clockPadding,0,clockRadius-clockPadding,0,mPaint);
            }
            canvas.rotate(6);
        }
        canvas.restore();
    }

    private void paintCircle(Canvas canvas) {

        mPaint.setColor(clockColor);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStrokeWidth(clockBorderWidth);
        canvas.drawCircle(0,0,clockRadius,mPaint);
    }

    private void drawClockBorder(Canvas canvas) {

        mPaint.setColor(clockBorderColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(clockBorderWidth);

        canvas.drawCircle(0,0,clockRadius+clockBorderWidth/2,mPaint);
    }

    private int getDef_keduColor(){
        return ContextCompat.getColor(getContext(),R.color.gray_66);
    }
    private int getDef_zhengkeduColor(){
        return ContextCompat.getColor(getContext(),R.color.gray_33);
    }

    private int getDef_clockColor(){
        return ContextCompat.getColor(getContext(),R.color.white);
    }
    private int getDef_clockPointColor(){
        return ContextCompat.getColor(getContext(),R.color.red);
    }
    private int getTransparentColor(){
        return ContextCompat.getColor(getContext(),R.color.transparent);
    }

    private float getDef_shiZhenLength(){
        return clockRadius-zhengkeduLength*6-clockPadding;//时针长度;
    }
    private int getDef_shiZhenColor(){
        return ContextCompat.getColor(getContext(),R.color.gray_33);
    }

    private float getDef_fenZhenLength(){
        return clockRadius-zhengkeduLength*4-clockPadding;//时针长度;
    }
    private int getDef_fenZhenColor(){
        return ContextCompat.getColor(getContext(),R.color.gray_33);
    }
    private float getDef_miaoZhenLength(){
        return clockRadius-zhengkeduLength-clockPadding;//时针长度;
    }
    private int getDef_miaoZhenColor(){
        return ContextCompat.getColor(getContext(),R.color.red);
    }
    private int getDef_TextSize(){
        return dip2px(getContext(),def_textSize);
    }
    private int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    private int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        if(timeInterval>1000){
            this.timeInterval = 1000;
        }else if(timeInterval<1){
            this.timeInterval = 1;
        }else{
            this.timeInterval = timeInterval;
        }
    }
}
