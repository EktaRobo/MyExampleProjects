package com.example.ekta.myexampleprojects.data;

import android.graphics.Bitmap;

/**
 * Created by ekta on 5/1/17.
 */

public class DownloadedImage {
    private Bitmap mBitmap;
    private int mIndex;

    public DownloadedImage(Bitmap bitmap, int index) {
        mBitmap = bitmap;
        mIndex = index;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public int getIndex() {
        return mIndex;
    }

}
