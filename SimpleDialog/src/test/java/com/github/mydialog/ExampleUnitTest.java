package com.github.mydialog;

import org.junit.Test;

import static org.junit.Assert.*;

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
    public void asf() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        long time=(       5000l) ;
        System.out.println(getTimeFormatStr(currentTimeMillis-time));
    }

    public String getTimeFormatStr(long loginTime) {
        long currentTimeMillis = System.currentTimeMillis();
        long l =  currentTimeMillis-loginTime;
        if (l <= 0) {
            return "上次登录0秒前";
        }
        int result;
        long year = 365 * 24 * 60 * 60 * 1000l;
        if (l >= year) {
            result = (int) (l / year);
            return "上次登录" + result + "年前";
        }
        long month = 30 * 24 * 60 * 60 * 1000l;
        if (l >= month) {
            result = (int) (l / month);
            return "上次登录" + result + "月前";
        }
        long day = 24 * 60 * 60 * 1000;
        if (l >= day) {
            result = (int) (l / day);
            return "上次登录" + result + "天前";
        }
        long hour = 60 * 60 * 1000;
        if (l >= hour) {
            result = (int) (l / hour);
            return "上次登录" + result + "小时前";
        }
        long second = 60 * 1000;
        if (l >= second) {
            result = (int) (l / second);
            return "上次登录" + result + "分钟前";
        }
        long minute = 1000;
        if (l >= minute) {
            result = (int) (l / minute);
            return "上次登录" + result + "秒前";
        }
        return "";
    }
}