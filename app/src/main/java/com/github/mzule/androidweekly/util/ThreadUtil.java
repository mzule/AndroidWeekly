package com.github.mzule.androidweekly.util;

/**
 * Created by CaoDongping on 1/12/16.
 */
public class ThreadUtil {
    public static void sleepUntilNextDraw(long startTime) {
        int cost = (int) (System.currentTimeMillis() - startTime);
        if (cost < 16) {
            try {
                Thread.sleep(16 - cost);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
