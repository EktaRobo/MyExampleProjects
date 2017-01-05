package com.example.ekta.myexampleprojects.application;

import android.app.Application;
import android.util.Log;

import com.example.ekta.myexampleprojects.Injection;
import com.example.ekta.myexampleprojects.data.source.ImageRepository;

/**
 * Created by ekta on 5/1/17.
 */

public class AppWideApplication extends Application {

    private static final String TAG = AppWideApplication.class.getSimpleName();
    private ImageRepository mImageRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: launched application class");
        mImageRepository = Injection.provideTasksRepository(getApplicationContext());
    }

    public ImageRepository getImageRepository() {
        return mImageRepository;
    }
}
