package com.test.mydialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2018/5/25.
 */

public class XMView extends View {
    public boolean isDebug=true;
    private Matrix shaderMatrix;

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
    //小时文本画笔
    Paint textPaint;
    Rect textRect;
    //表盘圆弧画笔
    Paint yuanHuPaint;
    RectF yuanHuRectF;
    //刻度画笔
    Paint keDuPaint;
    //刻度颜色圆弧画笔
    Paint keDuYuanHuPaint;
    private SweepGradient shader;

    float biaoPanPadding =38;
    float keDuLength=40;
    float keDuWidth;
    float keDuJianGeAngle;
    float mRadius=320;
    float keDuNum=200;
    float biaoPanWidth =4;
    //外表盘4段弧长的间隔角度
    float biaoPanIntervalAngle =6;
    int biaoPanColor;
    int startColor;
    int endColor;


    private float zhouChang;

    float keDuAngle;

    float hourAngle;
    float minuteAngle;
    float secondAngle;

    //圆点画笔
    Paint pointPaint;
    float pointRadius=14;
    float pointWidth=11;

    //时针画笔
    Paint shiZhenPaint;
    Path shiZhenPath;
    Path fenZhenPath;
    //分针画笔
    Paint fenZhenPaint;
    float shiZhenLength=160;
    float fenZhenLength=210;
    int shiZhenColor;
    int fenZhenColor;
    float shiZhenMaxHeigth=10;
    float shiZhenMinHeigth=8;

    float fenZhenMaxHeigth=8;
    float fenZhenMinHeigth=5;

    int sanJiaoColor;
    Paint sanJiaoPaint;
    float sanJiaoJianGe=10;
    float sanJiaoLength=36;

    private void init(AttributeSet attrs) {

        zhouChang = (float) (2 * (mRadius- biaoPanPadding -keDuLength/2) * Math.PI);
        keDuWidth= zhouChang/(keDuNum*2);
        keDuJianGeAngle= 360/keDuNum;

        keDuAngle=360/keDuNum;

        biaoPanColor =ContextCompat.getColor(getContext(), R.color.white_half60);
        startColor=ContextCompat.getColor(getContext(), R.color.white);
        endColor=ContextCompat.getColor(getContext(), R.color.white_half60);

        shiZhenColor=ContextCompat.getColor(getContext(), R.color.white_half20);
        fenZhenColor=ContextCompat.getColor(getContext(), R.color.white);
        sanJiaoColor=ContextCompat.getColor(getContext(), R.color.white_half20);



        //小时文本画笔
        textRect=new Rect();
        textPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(dip2px(getContext(),12));
        textPaint.setColor(biaoPanColor);

        //表盘圆弧画笔
        yuanHuRectF =new RectF(-mRadius,-mRadius,mRadius,mRadius);
        yuanHuPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        yuanHuPaint.setStrokeWidth(biaoPanWidth);
        yuanHuPaint.setStyle(Paint.Style.STROKE);
        yuanHuPaint.setColor(biaoPanColor);

        //刻度画笔
        keDuPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
//        keDuPaint.setStyle(Paint.Style.STROKE);
        keDuPaint.setStrokeWidth(keDuWidth);
        keDuPaint.setColor(ContextCompat.getColor(getContext(), R.color.xm));

        //刻度圆弧画笔
        keDuYuanHuPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        keDuYuanHuPaint.setStyle(Paint.Style.STROKE);
        keDuYuanHuPaint.setStrokeWidth(keDuLength);
        shader = new SweepGradient(0,0,new int[]{endColor,startColor}, new float[]{0.65f,1});
        shaderMatrix = new Matrix();



        shiZhenPath=new Path();
        shiZhenPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        shiZhenPaint.setColor(shiZhenColor);

        fenZhenPath=new Path();
        fenZhenPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        fenZhenPaint.setColor(fenZhenColor);

        pointPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setColor(ContextCompat.getColor(getContext(),R.color.white));
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setStrokeWidth(pointWidth);

        //三角画笔
        sanJiaoPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        sanJiaoPaint.setColor(sanJiaoColor);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.xm));
        canvas.translate(getWidth() / 2, getHeight() / 2);
        //获取时针分针秒针角度
        setTimeAngle();
        //绘制表盘弧线
        drawBiaoPan(canvas);
        //绘制表盘刻度文本
        drawBiaoPanText(canvas);
        //绘制表盘刻度
        drawBiaoPanKeDu(canvas);
        //绘制时针
        drawShiZhen(canvas);
        //绘制分针
        drawFenZhen(canvas);
        //绘制中间圆点
        drawPoint(canvas);
        //绘制三角形
        drawTriangle(canvas);
        canvas.restore();
        invalidate();
    }

    private void drawTriangle(Canvas canvas) {
        canvas.save();
        canvas.rotate(secondAngle-90,0,0);
        Path path=new Path();
        float sanJiaoWidth= (float) (Math.cos(Math.toRadians(30))*sanJiaoLength);
        path.moveTo(mRadius-biaoPanPadding-keDuLength-sanJiaoJianGe-sanJiaoWidth,sanJiaoLength/2);
        path.lineTo(mRadius-biaoPanPadding-keDuLength-sanJiaoJianGe,0);
        path.lineTo(mRadius-biaoPanPadding-keDuLength-sanJiaoJianGe-sanJiaoWidth,-sanJiaoLength/2);
        path.close();
        canvas.drawPath(path,sanJiaoPaint);
        canvas.restore();
    }

    private void drawPoint(Canvas canvas) {
        canvas.drawCircle(0,0,pointRadius,pointPaint);
    }

    private void drawFenZhen(Canvas canvas) {
        canvas.save();
        canvas.rotate(minuteAngle-90);
        if(fenZhenPath.isEmpty()){
            int scaleOffset=4;
            fenZhenPath.moveTo(pointRadius,fenZhenMaxHeigth/2);
            fenZhenPath.lineTo(fenZhenLength,fenZhenMinHeigth/2);
            fenZhenPath.quadTo(fenZhenLength+scaleOffset,0,fenZhenLength,-fenZhenMinHeigth/2);
            fenZhenPath.lineTo(pointRadius,-fenZhenMaxHeigth/2);
            fenZhenPath.close();
        }
        canvas.drawPath(fenZhenPath,fenZhenPaint);
        canvas.restore();
    }


    private void drawShiZhen(Canvas canvas) {
        canvas.save();
        canvas.rotate(hourAngle-90);
        if(shiZhenPath.isEmpty()){
            int scaleOffset=8;
            shiZhenPath.moveTo(pointRadius,shiZhenMaxHeigth/2);
            shiZhenPath.lineTo(shiZhenLength,shiZhenMinHeigth/2);
            shiZhenPath.quadTo(shiZhenLength+scaleOffset,0,shiZhenLength,-shiZhenMinHeigth/2);
            shiZhenPath.lineTo(pointRadius,-shiZhenMaxHeigth/2);
            shiZhenPath.close();
        }
        canvas.drawPath(shiZhenPath,shiZhenPaint);
        canvas.restore();

    }

    private void setTimeAngle() {
        Calendar calendar = Calendar.getInstance();
        float millisecond = calendar.get(Calendar.MILLISECOND);
        float second = calendar.get(Calendar.SECOND)+millisecond/1000;
        float minute=calendar.get(Calendar.MINUTE)+second/60;
        float hour=calendar.get(Calendar.HOUR)+minute/60;
        hourAngle=hour*360/12;
        minuteAngle=minute*360/60;
        secondAngle=second*360/60;
    }
    private void drawBiaoPanKeDu(Canvas canvas) {
        shaderMatrix.setRotate(secondAngle-90,0,0);
        shader.setLocalMatrix(shaderMatrix);
        keDuYuanHuPaint.setShader(shader);

        RectF rectF=new RectF(-mRadius+ biaoPanPadding +keDuLength/2,-mRadius+ biaoPanPadding +keDuLength/2,
                mRadius- biaoPanPadding -keDuLength/2,mRadius- biaoPanPadding -keDuLength/2);
        canvas.drawArc(rectF,0,360,false,keDuYuanHuPaint);

        canvas.save();
        for (int i = 0; i < 200; i++) {
            canvas.drawLine(mRadius- biaoPanPadding -keDuLength,0,mRadius- biaoPanPadding,0,keDuPaint);
            canvas.rotate(keDuAngle);
        }
        canvas.restore();
    }

    private void drawBiaoPanText(Canvas canvas) {
        String str="12";
        textPaint.getTextBounds(str,0,str.length(),textRect);

        float measureText = textPaint.measureText(str);
        canvas.drawText(str,-measureText/2,-mRadius+textRect.height()/2,textPaint);

        str="3";
        measureText = textPaint.measureText(str);
        canvas.drawText(str,mRadius-measureText/2,textRect.height()/2,textPaint);

        str="6";
        measureText = textPaint.measureText(str);
        canvas.drawText(str,-measureText/2,mRadius+textRect.height()/2,textPaint);

        str="9";
        measureText = textPaint.measureText(str);
        canvas.drawText(str,-mRadius-measureText/2,textRect.height()/2,textPaint);
    }

    private void drawBiaoPan(Canvas canvas) {
        for (int i = 0; i < 4; i++) {
            canvas.drawArc(yuanHuRectF, biaoPanIntervalAngle -90+90*i,90- biaoPanIntervalAngle *2,false,yuanHuPaint);
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

