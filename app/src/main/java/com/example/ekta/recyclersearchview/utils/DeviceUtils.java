package com.example.ekta.recyclersearchview.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by ekta on 23/12/16.
 */

public class DeviceUtils {

    public static int getDeviceWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getDeviceHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

}
