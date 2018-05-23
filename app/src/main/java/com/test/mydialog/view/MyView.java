package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.test.mydialog.AndroidUtils;
import com.test.mydialog.R;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/5/16.
 */

public class MyView extends View {

    public void Log(String log) {
        Log.i("===", log);
    }
    public MyView(Context context) {
        super(context);
        init();
        Log("===MyView1");
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        Log("===MyView2");
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        Log("===MyView3");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        Log("===MyView4");
    }

    Paint mPaint;
    int mRadius=500;
    int mPoint=22;//圆点半径
    int mPadding=20;
    int keDuWidth=3;//刻度宽度
    int keDuZhengWidth=6;//整点刻度宽度

    int keDuLength=25;//刻度长度
    int zhengDianLength=35;//整点刻度长度


    int shiZhenLength=mRadius-zhengDianLength*6-mPadding;//时针长度
    int fenZhenLength= mRadius-zhengDianLength*4-mPadding;//分针长度
    int miaoZhenLength=mRadius-mPadding-zhengDianLength;//秒针长度

    int shiZhenLengthD=zhengDianLength*3/2;//第二段时针长度
    int fenZhenLengthD= zhengDianLength*3/2;//第二段分针长度
    int miaoZhenLengthD=zhengDianLength*3/2;//第二段秒针长度


    int shiZhenWidth=26;//时针宽度
    int fenZhenWidth= 16;//分针宽度
    int miaoZhenWidth=10;//秒针宽度

    int timeFlag=1000;

    private void init() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.WHITE);
        canvas.save();
        canvas.translate(getWidth()/2,getHeight()/2);
        paintCircle(canvas);
        paintLine(canvas);
        drawShiZhen(canvas);
        drawFenZhen(canvas);
        drawMiaoZhen(canvas);
        drawYuanDian(canvas);
        canvas.restore();
        postInvalidateDelayed(timeFlag-System.currentTimeMillis()%1000);
        /*new Handler().postDelayed(new Runnable() {
            public void run() {
                invalidate();
            }
        }, 1 * 1000-System.currentTimeMillis()%1000);*/
//        drawYuanDian(canvas);
//        test(canvas);

    }

    private void drawMiaoZhen(Canvas canvas) {
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.red));
        canvas.save();
        Calendar calendar=Calendar.getInstance();
        float second = calendar.get(Calendar.SECOND);
        float millisecond = calendar.get(Calendar.MILLISECOND);
        Log.i(TAG+"===","3====="+second+"===="+millisecond);
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
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.gray_33));
        canvas.save();
        Calendar calendar=Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        Log.i(TAG+"===","2====="+minute);
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
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.gray_33));
        canvas.save();
        Calendar calendar=Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        Log.i(TAG+"===","1====="+hour);
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

    private void drawYuanDian(Canvas canvas) {
        canvas.save();

        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.red));
        canvas.drawCircle(0, 0, mPoint, mPaint);
        canvas.restore();
        mPaint.setColor(ContextCompat.getColor(getContext(),R.color.gray_33));
    }

    private void paintLine(Canvas canvas) {
        canvas.save();
        int kedu=0;
        for (int i = 0; i < 60; i++) {
            if(i%5==0){
                mPaint.setStrokeWidth(keDuZhengWidth);
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_33));
                canvas.drawLine(mRadius-zhengDianLength-mPadding,0,mRadius-mPadding,0,mPaint);
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
                mPaint.setTextSize(dip2px(getContext(),17));
                Rect rect = new Rect();
                mPaint.getTextBounds(keduStr,0,keduStr.length(),rect);
                int textHeight = rect.height(); //获得文字高度
                int textWidth = rect.width(); //获得文字宽度
                int textStartX=(zhengDianLength+mPadding)*2;
                canvas.save();
                canvas.translate(mRadius-textStartX+rect.width()/2,0);
                canvas.rotate(i*-6);
                float textY = mPaint.getFontMetrics().ascent + mPaint.getFontMetrics().leading;
//                mPaint.setColor(ContextCompat.getColor(getContext(),R.color.c_press));
//                canvas.drawRect(rect,mPaint);
//                mPaint.setColor(ContextCompat.getColor(getContext(),R.color.blue_00));
//                canvas.drawText(keduStr,0,0,mPaint);
                canvas.drawText(keduStr,-textWidth/2-keDuZhengWidth/2,textHeight/2,mPaint);
                Log("kedu="+kedu);
                canvas.restore();
            }else{
                mPaint.setStrokeWidth(3);
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_66));
                canvas.drawLine(mRadius-keDuLength-mPadding,0,mRadius-mPadding,0,mPaint);
            }
            canvas.rotate(6);
        }
        canvas.restore();
    }
    private void paintCircle(Canvas canvas) {
//        Log("==="+getWidth());
//        Log("==="+getHeight());
        canvas.drawCircle(0, 0, mRadius, mPaint);

    }
    private int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    private int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }
    private void test(Canvas canvas) {
         /*Paint paint = new Paint();

        paint.setAntiAlias( true );
        paint.setStrokeWidth( 100 );
        paint.setColor(Color.parseColor("#00ff00") );
//        paint.setStrokeCap( Paint.Cap.BUTT );       // 线帽，即画的线条两端是否带有圆角，butt，无圆角
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawLine( 100,100,400, 100, paint );

        paint.setColor(Color.parseColor("#ff0000") );
//        paint.setStrokeCap( Paint.Cap.ROUND );       // 线帽，即画的线条两端是否带有圆角，ROUND，圆角
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawLine( 100,300,400, 300, paint );

        paint.setColor(Color.parseColor("#0000ff") );
//        paint.setStrokeCap( Paint.Cap.SQUARE );       // 线帽，即画的线条两端是否带有圆角，SQUARE，矩形
        paint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawLine( 100,600,400, 600, paint );*/



        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);//描边
        mPaint.setStrokeWidth(60);
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆的
        //测试1
        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(300, 100);
        path.lineTo(100, 300);
        mPaint.setStrokeJoin(Paint.Join.MITER);
//      mPaint.setStrokeMiter(2);
        canvas.drawPath(path, mPaint);

        path.moveTo(100, 400);
        path.lineTo(300, 500);
        path.lineTo(100, 700);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
//      mPaint.setStrokeMiter(4);
        canvas.drawPath(path, mPaint);

        path.moveTo(100, 800);
        path.lineTo(300, 800);
        path.lineTo(100, 1100);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
//      mPaint.setStrokeMiter(6);
        canvas.drawPath(path, mPaint);





        /**
         * 画弧形
         * float left：表示弧形呈360度显示时，对应的最左边那个端点所对应的 x 坐标
         * float top：表示弧形呈360度显示时，对应的最上边那个端点所对应的 y 坐标
         * float right：表示弧形呈360度显示时，对应的最右边那个端点所对应的 x 坐标
         * float bottom：表示弧形呈360度显示时，对应的最下边那个端点所对应的 y 坐标
         * float startAngle：
         * 表示与水平方向呈多少角度开始绘制弧形，顺时针的角度记为正
         * float sweepAngle：
         * 表示绘制弧形时所对应的扇形角度，数值为正，则表示从顺时针方向开始绘制
         * boolean useCenter：
         * true表示扇形需要中间那一部分三角形，
         * false表示显示的是：从扇形中出去中间那个三角形之后的一小部分的弧形
         * Paint paint：表示我们所用的画笔
         */
        mPaint.setStrokeJoin(Paint.Join.MITER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(200,200,500,500,30,90,true,mPaint);
        }
    }


}