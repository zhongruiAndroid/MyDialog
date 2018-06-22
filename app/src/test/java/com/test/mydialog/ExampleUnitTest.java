package com.test.mydialog;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void a2sdf() throws Exception {
        String atr="1,2,3,4,5,6";
        String[] split = atr.split(",");
        List<String> stringList = Arrays.asList(split);

        ArrayList<String>list=new ArrayList<>();
        list.addAll(stringList);

        System.out.println("删除前:"+list.size());
        boolean contains = list.contains("1");
        if(contains){
            int index = list.indexOf("1");
            list.remove(index);
        }
        System.out.println("删除后:"+list.size());
    }
    @Test
    public void asdf() throws Exception {

        double oldPrice=1;
        double oldNum=1;

        AndroidUtils.chuFa(oldPrice,oldNum);
    }
    @Test
    public void asdfd() throws Exception {

        for (int i = 0; i <= 100; i++) {
            float temp = 255 * i * 1.0f / 100f;
            int alpha = Math.round(temp);
            System.out.println(alpha+"==");
            String hexStr = Integer.toHexString(alpha);
            if (hexStr.length() < 2)
                hexStr = "0" + hexStr;
            System.out.println(i + "%, " + hexStr.toUpperCase());

        }
    }
    @Test
    public void asddf() throws Exception {
        double v = Math.cos(Math.toRadians(30)) * 118;
        System.out.println(v);
        System.out.println(Math.sin(Math.toRadians(30)) * 118);
    }
    @Test
    public void asdddf() throws Exception {
        for (int i = 0; i < 20; i++) {
            System.out.println(Math.random());
        }
    }

    @Test
    public void asf() throws Exception {
        double sqrt = Math.sqrt(480);
        System.out.println(sqrt);
        double v = AndroidUtils.chengFa(sqrt, 0.3937008);
        System.out.println(v);
        double v2 = AndroidUtils.chengFa(480, 0.3937008);
        System.out.println(v2);
    }

}