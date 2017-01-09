package com.example.ekta.myexampleprojects;

import android.content.Context;

import com.example.ekta.myexampleprojects.utils.FileUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ekta on 6/1/17.
 */

@ApplicationScope
@Module
public class FileManagerModule {

    @Provides
    FileUtils getFileUtils(Context context) {
        return new FileUtils(context);
    }
}
