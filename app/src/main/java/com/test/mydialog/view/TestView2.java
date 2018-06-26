package com.test.mydialog.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.test.mydialog.AndroidUtils;
import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

/**
 * Created by Administrator on 2018/5/24.
 */

public class TestView2 extends View {
    public boolean isDebug=true;
    private double v;
    private double v2;

    private Region newRegion;
    private Region leftRegion;
    private Region topRegion;
    private Region rightRegion;
    private Region bottomRegion;
    private int x;
    private int y;
    int touchFlag=-1;

    public void Log(String log) {
        if(BuildConfig.DEBUG&&isDebug){
            Log.i("ClockView===", log);
        }
    }
    public TestView2(Context context) {
        super(context);
        init();
    }
    public TestView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public TestView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    Paint mPaint;
    Paint pointPaint;
    int strokeWidth=2;
    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));

        pointPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setDither(true);
        pointPaint.setStrokeWidth(13);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.home_green));

    }



    private Path path;
    private Path leftPath;
    private Path topPath;
    private Path rightPath;
    private Path bottomPath;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        RectF bRectF=new RectF(10,10,600,500);
        RectF sRectF=new RectF(130,130,480,380);
        RectF cRectF=new RectF(150,150,460,360);
        int bAngle=84;
        int sAngle=-82;
        Region region=new Region (10,10,600,500);
        newRegion = new Region();
        leftRegion = new Region();
        topRegion = new Region();
        rightRegion = new Region();
        bottomRegion = new Region();

        path = new Path();
//        path.addCircle(300,300,300, Path.Direction.CW);
        path.addArc(cRectF,0,360);
        newRegion.setPath(path,region);

        leftPath = new Path();
        leftPath.addArc(bRectF,-40,bAngle);
        leftPath.arcTo(sRectF,bAngle-40,sAngle);
        leftPath.close();
        leftRegion.setPath(leftPath,region);

        topPath = new Path();
        topPath.addArc(bRectF,-130,bAngle);
        topPath.arcTo(sRectF,bAngle-130,sAngle);
        topPath.close();
        topRegion.setPath(topPath,region);



        rightPath = new Path();
        rightPath.addArc(bRectF,-220,bAngle);
        rightPath.arcTo(sRectF,bAngle-220,sAngle);
        rightPath.close();
        rightRegion.setPath(rightPath,region);


        bottomPath = new Path();
        bottomPath.addArc(bRectF,50,bAngle);
        bottomPath.arcTo(sRectF,bAngle+50,sAngle);
        bottomPath.close();
        bottomRegion.setPath(bottomPath,region);
        initC();
        initBit();
    }

    private void initC() {
        double sqrt = Math.sqrt(480);
        v = AndroidUtils.chengFa(sqrt, 0.3937008);
        v2 = AndroidUtils.chengFa(480, 0.3937008);

        Matrix matrix=new Matrix();
        Log("==="+matrix.toString());
        Log("==="+matrix.toShortString());
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        c(canvas);


//        a(canvas);
       /* b(canvas);


        if(x>0){
            pointPaint.setStrokeWidth(9);
            pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.top_color));
            canvas.drawPoint(x,y,pointPaint);

            pointPaint.setStrokeWidth(3);
            pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.home_green));
        }*/
//        canvas.drawColor(Color.RED);

      /*  RectF rect = new RectF(10, 10, 290, 290);
        int saveLayer = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

        Xfermode xfermode=new Xfermode();
        Xfermode xfermode2 = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);


        canvas.drawBitmap(getA(),0,0,mPaint);
        mPaint.setXfermode(xfermode2);
        canvas.drawBitmap(getB(),10,10,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
        Path a=new Path();*/


        canvas.translate(getWidth()/2,getHeight()/2);

        //////////////////////////1111111/////////////////////////////////
      /*  path1.reset();
        path1.addCircle(0,0,200, Path.Direction.CW);
        PathMeasure measure=new PathMeasure(path1,true);

        measure.getPosTan(length*measure.getLength(),pos,tan);
        Matrix mat=new Matrix();
        measure.getMatrix(length*measure.getLength(),mat,PathMeasure.POSITION_MATRIX_FLAG|PathMeasure.TANGENT_MATRIX_FLAG);
        matrix.reset();
        float angle=(float) (Math.atan2(tan[1],tan[0])*180/Math.PI);
        matrix.postRotate(angle,bitmap.getWidth()/2,bitmap.getHeight()/2);
        matrix.postTranslate(pos[0]-bitmap.getWidth()/2,pos[1]-bitmap.getHeight()/2);
        length=length+0.005f;
        if(length>1){
            length=0;
        }
        canvas.drawBitmap(bitmap,matrix,mPaint);
        canvas.drawPath(path1,mPaint);
        canvas.drawPoint(3,3,pointPaint);
        invalidate();*/
        //////////////////////////22222222/////////////////////////////////
        path1.reset();
        path1.addCircle(0,0,200, Path.Direction.CW);
        PathMeasure measure=new PathMeasure(path1,true);
        Matrix mat=new Matrix();
        measure.getMatrix(length*measure.getLength(),mat,PathMeasure.POSITION_MATRIX_FLAG|PathMeasure.TANGENT_MATRIX_FLAG);
        mat.preTranslate(-mBitmap.getWidth() / 2,-mBitmap.getHeight() / 2);
        length=length+0.005f;
        if(length>1){
            length=0;
        }
        canvas.drawBitmap(bitmap,mat,mPaint);
        canvas.drawPath(path1,mPaint);
        canvas.drawPoint(3,3,pointPaint);
        invalidate();

       /* canvas.translate(getWidth() / 2, getHeight() / 2);      // 平移坐标系

        Path path = new Path();                                 // 创建 Path

        path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形

        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure

        currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue > 1) {
            currentValue = 0;
        }

        measure.getPosTan(measure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势

        Log.i("===","==="+pos[0]+"==="+pos[1]);
        mMatrix.reset();                                                        // 重置Matrix
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度

        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合

        canvas.drawPath(path, mPaint);                                   // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);                     // 绘制箭头*/

//        invalidate();                                                           // 重绘页面
    }
    Bitmap mBitmap;
    Bitmap bitmap;
    Matrix matrix;
    Matrix mMatrix;
    private Path path1;
    public void initBit(){
        path1 = new Path();
        matrix=new Matrix();
        mMatrix=new Matrix();
        mPaint.setStyle(Paint.Style.STROKE);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;       // 缩放图片
        bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.a,options);
        mBitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.a,options);
    }
    float currentValue=0;
    float length=0;
    float[]pos=new float[2];
    float[]tan=new float[2];
    private  Bitmap drawRectBm(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Bitmap bm = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
        Canvas cavas = new Canvas(bm);
        cavas.drawRect(new RectF(0,0,70,70),paint);
        return bm;
    }
    private  Bitmap drawCircleBm(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.home_text_color));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Bitmap bm = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
        Canvas cavas = new Canvas(bm);
        cavas.drawCircle(70,70,35,paint);
        return bm;
    }
    public Bitmap getA(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Bitmap bm = Bitmap.createBitmap(180,180, Bitmap.Config.ARGB_8888);
        Canvas cavas = new Canvas(bm);
        cavas.drawRect(new RectF(10, 10, 190, 190),paint);
        return bm;
    }
    public Bitmap getB(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.home_text_color));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Bitmap bm = Bitmap.createBitmap(380,380, Bitmap.Config.ARGB_8888);
        Canvas cavas = new Canvas(bm);
        cavas.drawCircle(170,170,90,paint);
        return bm;
    }

    private void b(Canvas canvas) {

        canvas.drawPath(path,pointPaint);
        canvas.drawPath(leftPath,pointPaint);
        canvas.drawPath(topPath,pointPaint);
        canvas.drawPath(rightPath,pointPaint);
        canvas.drawPath(bottomPath,pointPaint);
        switch (touchFlag){
            case 0:
                pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_d7));
                canvas.drawPath(path,pointPaint);
            break;
            case 1:
                pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_d7));
                canvas.drawPath(leftPath,pointPaint);

            break;
            case 2:

                pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_d7));
                canvas.drawPath(topPath,pointPaint);
            break;
            case 3:

                pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_d7));
                canvas.drawPath(rightPath,pointPaint);
            break;
            case 4:

                pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray_d7));
                canvas.drawPath(bottomPath,pointPaint);
            break;
        }

        pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.home_green));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                touchFlag=-1;
                Log(event.getRawX()+"=="+event.getRawY());
                x = (int) event.getX();
                y = (int) event.getY();
                if (newRegion.contains(x, y)) {
                    Toast.makeText(getContext(), "点击center"+ x +"=="+ y, Toast.LENGTH_SHORT).show();
                }
                if (leftRegion.contains(x, y)) {
                    Toast.makeText(getContext(), "点击left"+ x +"=="+ y, Toast.LENGTH_SHORT).show();
                }
                if (topRegion.contains(x, y)) {
                    Toast.makeText(getContext(), "点击top"+ x +"=="+ y, Toast.LENGTH_SHORT).show();
                }
                if (rightRegion.contains(x, y)) {
                    Toast.makeText(getContext(), "点击right"+ x +"=="+ y, Toast.LENGTH_SHORT).show();
                }
                if (bottomRegion.contains(x, y)) {
                    Toast.makeText(getContext(), "点击bottom"+ x +"=="+ y, Toast.LENGTH_SHORT).show();
                }
                invalidate();
            break;
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                if (newRegion.contains(x, y)) {
                    touchFlag=0;
                }
                if (leftRegion.contains(x, y)) {
                    touchFlag=1;
                }
                if (topRegion.contains(x, y)) {
                    touchFlag=2;
                }
                if (rightRegion.contains(x, y)) {
                    touchFlag=3;
                }
                if (bottomRegion.contains(x, y)) {
                    touchFlag=4;
                }
                invalidate();
                break;
        }
        return true;
//        return super.onTouchEvent(event);
    }

    private void c(Canvas canvas) {
        canvas.drawLine(10,10,(float) (10+v),10,mPaint);
        canvas.drawLine(30,30,30,(float) (30+v2),mPaint);
        canvas.drawLine(50,30,50,(float) (30+AndroidUtils.chengFa(160*3, 0.3937008)),mPaint);
    }

    private void a(Canvas canvas) {
        Path path=new Path();
        RectF rectF=new RectF(-250,-250,250,250);
        RectF rectF2=new RectF(-200,-200,200,200);


        path.addArc(rectF,-45,90);

        path.arcTo(rectF2,45,-90);
        path.close();

        canvas.drawPath(path,mPaint);





    }
}
