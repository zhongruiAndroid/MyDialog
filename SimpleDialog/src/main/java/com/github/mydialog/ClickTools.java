package com.github.mydialog;

import android.view.View;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/8/10.
 */
class ClickTools {
    private static ClickTools clickTools;
    private Map<Long, ConcurrentHashMap> map;
    private static final int MIN_CLICK_DELAY_TIME = 900;

    private ClickTools() {
        map = new ConcurrentHashMap<>();
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
        ConcurrentHashMap<Integer, Long> concurrentHashMap = map.get(key);
        if (concurrentHashMap == null) {
            concurrentHashMap = new ConcurrentHashMap();
            concurrentHashMap.put(itemId, 0L);
            map.put(key, concurrentHashMap);
        }
        long currentTime = Calendar.getInstance().getTimeInMillis();
        Long preTime = concurrentHashMap.get(itemId);
        if (preTime == null) {
            preTime = new Long(0);
        }
        if (currentTime - preTime > time) {
            concurrentHashMap.put(itemId, currentTime);
            return false;
        }
        return true;
    }


    public void removeLastClickTime(Long key) {
        if (map != null) {
            ConcurrentHashMap concurrentHashMap = map.remove(key);
            if (concurrentHashMap != null) {
                concurrentHashMap.clear();
                concurrentHashMap = null;
            }
        }
    }

    public void clearLastClickTime(Long key) {
        if (map != null) {
            ConcurrentHashMap concurrentHashMap = map.get(key);
            if (concurrentHashMap != null) {
                concurrentHashMap.clear();
            }
        }
    }
}
