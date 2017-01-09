package com.example.ekta.myexampleprojects.application;

import android.app.Application;
import android.util.Log;

import com.example.ekta.myexampleprojects.ContextModule;
import com.example.ekta.myexampleprojects.data.DaggerImageRepositoryComponent;
import com.example.ekta.myexampleprojects.data.ImageRepositoryComponent;

/**
 * Created by ekta on 5/1/17.
 */

public class App extends Application {

    private static final String TAG = App.class.getSimpleName();
    private static ImageRepositoryComponent imageRepositoryComponent;

    public static ImageRepositoryComponent getImageRepositoryComponent() {
        return imageRepositoryComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: launched application class");
        imageRepositoryComponent = DaggerImageRepositoryComponent.builder().contextModule
                (new ContextModule(this
                        .getApplicationContext())).build();
    }


}
