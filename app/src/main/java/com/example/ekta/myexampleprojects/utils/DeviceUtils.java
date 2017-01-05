package com.example.ekta.myexampleprojects.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by ekta on 23/12/16.
 */

public class DeviceUtils {

    /**
     * Returns device width
     *
     * @param context : context
     * @return : device width in int
     */
    public static int getDeviceWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * Returns device height
     * @param context : context
     * @return : device height in int
     */
    public static int getDeviceHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

}
