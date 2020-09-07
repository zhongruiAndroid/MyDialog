package com.github.mydialog;

import android.view.View;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/10.
 */
class ClickTools {
    private static ClickTools clickTools;
    private Map<Long, HashMap> map;
    private static final int MIN_CLICK_DELAY_TIME = 900;

    private ClickTools() {
        map = new HashMap<>();
    }

    public static ClickTools get() {
        if (clickTools == null) {
            synchronized (ClickTools.class) {
                if (clickTools == null) {
                    clickTools = new ClickTools();
                }
            }
        }
        return clickTools;
    }

    public boolean isFastClick(Long key, View view) {
        return isFastClick(key, view, MIN_CLICK_DELAY_TIME);
    }

    public boolean isFastClick(Long key, View view, long time) {
        return isFastClickById(key, view.getId(), time);
    }

    public boolean isFastClickById(Long key, int itemId) {
        return isFastClickById(key, itemId, MIN_CLICK_DELAY_TIME);
    }

    public boolean isFastClickById(Long key, int itemId, long time) {
        HashMap<Integer, Long> hashMap = map.get(key);
        if (hashMap == null) {
            hashMap = new HashMap();
            hashMap.put(itemId, 0L);
            map.put(key, hashMap);
        }
        long currentTime = Calendar.getInstance().getTimeInMillis();
        Long preTime = hashMap.get(itemId);
        if (preTime == null) {
            preTime = new Long(0);
        }
        if (currentTime - preTime > time) {
            hashMap.put(itemId, currentTime);
            return false;
        }
        return true;
    }


    public void removeLastClickTime(Long key) {
        if (map != null) {
            HashMap hashMap = map.remove(key);
            if (hashMap != null) {
                hashMap.clear();
                hashMap = null;
            }
        }
    }

    public void clearLastClickTime(Long key) {
        if (map != null) {
            HashMap hashMap = map.get(key);
            if (hashMap != null) {
                hashMap.clear();
            }
        }
    }
}
