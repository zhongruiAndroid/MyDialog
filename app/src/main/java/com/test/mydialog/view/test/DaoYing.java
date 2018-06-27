package com.test.mydialog.view.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.test.mydialog.R;

/**
 * Created by Administrator on 2018/6/27.
 */

public class DaoYing {
    private void getNewBitmap(ImageView iv,Context context) {
        Matrix matrix=new Matrix();
        matrix.preScale(1,-1);
        Bitmap oldBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
        Bitmap newBitmap = Bitmap.createBitmap(oldBitmap, 0, oldBitmap.getHeight() / 2, oldBitmap.getWidth(), oldBitmap.getHeight()/2, matrix, false);
//        iv.setImageBitmap(newBitmap);

        int gapWidth=5;
        Bitmap bitmap = Bitmap.createBitmap(oldBitmap.getWidth(), gapWidth + oldBitmap.getHeight() + oldBitmap.getHeight() / 2, Bitmap.Config.ARGB_8888);


        Canvas canvas=new Canvas(bitmap);
        canvas.drawBitmap(oldBitmap,0,0,new Paint());
//        int count = canvas.saveLayer(0,0,0,0, null,Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(newBitmap,0,oldBitmap.getHeight()+gapWidth,new Paint());
        int color1= ContextCompat.getColor(context, R.color.transparent_half);
        int color2= ContextCompat.getColor(context, R.color.black);
        LinearGradient linearGradient=new LinearGradient(0,oldBitmap.getHeight()+gapWidth,0,bitmap.getHeight(),0x53000000,0xED000000, Shader.TileMode.MIRROR);

        Paint paint = new Paint();
        paint.setShader(linearGradient);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//        canvas.drawBitmap(newBitmap,0,oldBitmap.getHeight()+gapWidth,paint);
        canvas.drawRect(0,oldBitmap.getHeight()+gapWidth,bitmap.getWidth(),bitmap.getHeight(),paint);
//        paint.setXfermode(null);
//        canvas.restoreToCount(count);
        iv.setImageBitmap(bitmap);
    }

    public static Bitmap zheDie(ImageView iv,Context context){
        int num=8;
        double zhanBi=0.8;
        Matrix[] matrix=new Matrix[num];
        for (int i = 0; i < num; i++) {
            matrix[i]=new Matrix();
        }

        Bitmap oldBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
        int oldW = oldBitmap.getWidth();
        int oldH = oldBitmap.getHeight();
        int newW=(int)(oldW*zhanBi);
        Bitmap bitmap = Bitmap.createBitmap((int)(oldW*zhanBi),oldH , Bitmap.Config.ARGB_8888);

        int beforeEveryW=oldW/num;
        int nowEveryW=newW/num;

        int scaleHeight= (int) Math.sqrt(beforeEveryW*beforeEveryW-nowEveryW*nowEveryW)/2;
//        scaleHeight=43;
        Log.i("====",scaleHeight+"==="+(int) Math.sqrt(beforeEveryW*beforeEveryW-nowEveryW*nowEveryW));

        float[]src=new float[num];
        float[]dst=new float[num];
        for (int i = 0; i < num; i++) {
            src[0]=i*beforeEveryW;
            src[1]=0;

            src[2]=src[0]+beforeEveryW;
            src[3]=0;

            src[4]=src[2];
            src[5]=oldH;

            src[6]=src[0];
            src[7]=oldH;
            Log.i("====","==="+src[2]+"--"+src[4]);

            if(i%2==0){
                dst[0]=i*nowEveryW;
                dst[1]=0;

                dst[2]=dst[0]+nowEveryW;
                dst[3]=scaleHeight;

                dst[4]=dst[2];
                dst[5]=oldH-scaleHeight;

                dst[6]=dst[0];
                dst[7]=oldH;
            }else{
                dst[0]=i*nowEveryW;
                dst[1]=scaleHeight;

                dst[2]=dst[0]+nowEveryW;
                dst[3]=0;

                dst[4]=dst[2];
                dst[5]=oldH;

                dst[6]=dst[0];
                dst[7]=oldH-scaleHeight;
            }

            Log.i("====","==="+dst[2]+"--"+dst[4]);

            matrix[i].setPolyToPoly(src,0,dst,0,4);
        }



        Canvas canvas=new Canvas(bitmap);
        for (int i = 0; i < num; i++) {
            canvas.save();
            canvas.concat(matrix[i]);
            canvas.clipRect(beforeEveryW*i,0,beforeEveryW*i+beforeEveryW,oldH);
            canvas.drawBitmap(oldBitmap,0,0,null);
//            canvas.translate(beforeEveryW * i, 0);
            canvas.restore();
        }


        return bitmap;
    }

}
