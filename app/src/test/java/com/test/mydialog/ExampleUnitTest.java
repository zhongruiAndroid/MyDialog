package com.test.mydialog;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void additsdfion_isCorrect() throws Exception {
        System.out.println("=="+getVideoDurationStrFormat(0));
        System.out.println("=="+getVideoDurationStrFormat(9));
        System.out.println("=="+getVideoDurationStrFormat(10));
        System.out.println("=="+getVideoDurationStrFormat(59));
        System.out.println("=="+getVideoDurationStrFormat(60));
        System.out.println("=="+getVideoDurationStrFormat(63));
        System.out.println("=="+getVideoDurationStrFormat(3600-1));
        System.out.println("=="+getVideoDurationStrFormat(3600));
        System.out.println("=="+getVideoDurationStrFormat(3600+1));
        System.out.println("=="+getVideoDurationStrFormat(3600*60));
        System.out.println("=="+getVideoDurationStrFormat(3600*60-1));
    }
    public String getVideoDurationStrFormat(long videoDuration) {
        if(videoDuration<60){
            return "00:"+ timePrefix(videoDuration);
        }else if(videoDuration<3600){
            long min = videoDuration / 60;
            long second = videoDuration % 60;
            return timePrefix(min)+":"+ timePrefix(second);
        }else{
            long hour = videoDuration / 3600;
            long hourMod = videoDuration % 3600;
            long min = hourMod / 60;
            long second = hourMod % 60;

            return timePrefix(hour)+":"+ timePrefix(min)+":"+ timePrefix(second);
        }
    }

    private String timePrefix(long time){
        return time<10?"0"+time:time+"";
    }
    @Test
    public void asfd() throws Exception {
        String b="";
        String a=" ";
        System.out.println(a.toLowerCase()+"====");
        System.out.println(b.toLowerCase()+"====");
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
    @Test
    public void sdf() throws Exception {
        Map<String,String>map=a();
        if(map!=null){
            System.out.println(map.get("key"));
        }else{
            System.out.println("null");
        }

    }
    @Test
    public void sdddf() throws Exception {
        int a=300;
        bb(a);
        System.out.println(a);
    }
    public void bb(int scale){
        scale=2;
    }
    @Test
    public void sddf() throws Exception {
        MyTest test=new MyTest();
        test.i=100;
        aa(test);
        if(test==null){
            System.out.println("null");
        }else {
            System.out.println(test.i);
        }
    }
    public class MyTest{
        public int i=1;
    }
    public void aa(MyTest myTest){
        myTest.i=200;
        myTest=null;
    }

    public Map a(){
        Map<String,String> map=new HashMap<String,String>();
        try{
            map.put("key","try");
            return map;
        }catch (Exception e){
            map.put("key","try");
        }finally {
            map.put("key","finally");
            map=null;
        }
        return map;
    }
    @Test
    public void dsf() throws Exception {
        //new Scanner(System.in).nextInt()
        mysnakematrix my = new mysnakematrix(7);  //利用Scanner获取控制台输入
        my.snakeMatrix();
        my.print();
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }

    }

    public class mysnakematrix {
        private int n; //
        private int a[][]; // 声明一个矩阵
        private int value = 1;// 矩阵里数字的值

        public mysnakematrix(int i) {
            this.n = i;
            a = new int[n][n];
        }

        // 计算第m层左上角的数字
        private int getcorner(int m) {
            int corner = 1;
            int o = n - 1;
            for (int i = 0; i < m - 1; ++i) {
                corner += 4 * o;
                o = o - 2;
            }
            return corner;
        }

        // 生成矩阵的每一层的每一边的数
        // s表示4个方向，分别取值1,2,3,4，表示4个不同的方向。
        // o表示这条边的起始值。
        // x表示第m层每条边的数字个数
        private void side(int s, int o, int x, int m) {
            int i = 0;
            int j = 0;
            switch (s) {
                case 1:
                    i = m - 1;
                    j = m - 1;
                    for (int k = 0; k < x; ++k) {
                        a[i][j + k] = value;
                        ++value;
                    }

                    break;
                case 2:
                    i = m - 1;
                    j = m - 1 + x;
                    for (int k = 0; k < x; ++k) {
                        a[i + k][j] = value;
                        ++value;
                    }
                    break;
                case 3:
                    i = m - 1 + x;
                    j = m - 1 + x;
                    for (int k = 0; k < x; ++k) {
                        a[i][j - k] = value;
                        ++value;
                    }
                    break;
                case 4:
                    i = m - 1 + x;
                    j = m - 1;
                    for (int k = 0; k < x; ++k) {
                        a[i - k][j] = value;
                        ++value;
                    }
                    break;
            }
        }

        // 生成蛇形矩阵的第m层
        private void shell(int m)// m表示第m层
        {
            int x = n - 1 - (m - 1) * 2; // x表示第m层每条边的数字个数
            int o = getcorner(m);
            int o1 = o;
            int o2 = o1 + x;
            int o3 = o2 + x;
            int o4 = o3 + x;
            // System.out.println(o4);

            side(1, o, x, m);
            side(2, o, x, m);
            side(3, o, x, m);
            side(4, o, x, m);
        }

        // 生成蛇形矩阵
        public void snakeMatrix() {
            int m = (n + 1) / 2;// 计算一共有多少层
            for (int i = 1; i <= m; ++i) {

                shell(i);
            }
            if (n % 2 == 1) {
                a[n / 2][n / 2] = n * n;
            }

        }

        // 打印矩阵
        public void print() {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (a[i][j] < 10) {
                        System.out.print(a[i][j] + "  ");
                    } else {
                        System.out.print(a[i][j] + " ");
                    }

                }
                System.out.println();
            }
        }


    }

}