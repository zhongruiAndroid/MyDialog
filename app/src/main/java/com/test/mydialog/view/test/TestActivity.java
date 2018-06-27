package com.test.mydialog.view.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.test.mydialog.BuildConfig;
import com.test.mydialog.R;

public class TestActivity extends AppCompatActivity {
    ImageView iv,iv2;
    public void Log(String log) {
        if(BuildConfig.DEBUG){
            Log.i(this.getClass().getSimpleName()+"===", log);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();


        iv= (ImageView) findViewById(R.id.iv);
        iv2= (ImageView) findViewById(R.id.iv2);
//        getNewBitmap();

        setPolyToPoly();
    }

    private void setPolyToPoly() {
        /*Matrix matrix=new Matrix();
        Bitmap oldBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        float[] floats = {0,0,
                oldBitmap.getWidth(),0,
                oldBitmap.getWidth(),oldBitmap.getHeight(),
                0,oldBitmap.getHeight()};
        float[] dst = {0,0,
                oldBitmap.getWidth(),0,
                oldBitmap.getWidth(),oldBitmap.getHeight()-100,
                0,oldBitmap.getHeight()};
        boolean b = matrix.setPolyToPoly(floats, 0, dst, 0, 4);
        Bitmap bitmap = Bitmap.createBitmap(oldBitmap.getWidth(), oldBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        canvas.drawBitmap(oldBitmap,matrix,new Paint(Paint.ANTI_ALIAS_FLAG));*/
//        iv2.setImageBitmap(bitmap);
        Bitmap bitmap1 = DaoYing.zheDie2(iv2, this);
//        iv2.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        iv2.setImageBitmap(bitmap1);
    }

    private void getNewBitmap() {
        Matrix matrix=new Matrix();
        matrix.preScale(1,-1);
        Bitmap oldBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        Bitmap newBitmap = Bitmap.createBitmap(oldBitmap, 0, oldBitmap.getHeight() / 2, oldBitmap.getWidth(), oldBitmap.getHeight()/2, matrix, false);
        iv.setImageBitmap(newBitmap);

        int gapWidth=5;
        Bitmap bitmap = Bitmap.createBitmap(oldBitmap.getWidth(), gapWidth + oldBitmap.getHeight() + oldBitmap.getHeight() / 2, Bitmap.Config.ARGB_8888);


        Canvas canvas=new Canvas(bitmap);
        canvas.drawBitmap(oldBitmap,0,0,new Paint());
//        int count = canvas.saveLayer(0,0,0,0, null,Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(newBitmap,0,oldBitmap.getHeight()+gapWidth,new Paint());
        int color1= ContextCompat.getColor(this, R.color.transparent_half);
        int color2= ContextCompat.getColor(this, R.color.black);
        LinearGradient linearGradient=new LinearGradient(0,oldBitmap.getHeight()+gapWidth,0,bitmap.getHeight(),0x53000000,0xED000000,Shader.TileMode.MIRROR);

        Paint paint = new Paint();
        paint.setShader(linearGradient);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//        canvas.drawBitmap(newBitmap,0,oldBitmap.getHeight()+gapWidth,paint);
        canvas.drawRect(0,oldBitmap.getHeight()+gapWidth,bitmap.getWidth(),bitmap.getHeight(),paint);
//        paint.setXfermode(null);
//        canvas.restoreToCount(count);
        iv2.setImageBitmap(bitmap);
    }

    private void init() {


// 获取屏幕密度（方法3）
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        float density  = dm.density;      // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        float densityDPI = dm.densityDpi;
//        Log("==="+density+"====="+densityDPI);
    }
}
