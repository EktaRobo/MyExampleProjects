/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ekta.myexampleprojects;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.ekta.myexampleprojects.data.source.ImageDataSource;
import com.example.ekta.myexampleprojects.data.source.ImageRepository;
import com.example.ekta.myexampleprojects.data.source.local.ImageLocalDataSource;
import com.example.ekta.myexampleprojects.data.source.remote.ImageRemoteDataSource;
import com.example.ekta.myexampleprojects.utils.FileUtils;

/**
 * Enables injection of mock implementations for
 * {@link ImageDataSource} at compile time. This is useful for testing, since it allows us to use
 * a fake sFileUtilInstance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {


    public static ImageRepository provideTasksRepository(Context context) {
        return ImageRepository.getInstance(ImageRemoteDataSource.getInstance(getFileUtils
                        (context), getGlideRequestManager(context)),
                ImageLocalDataSource.getInstance(getFileUtils(context)));
    }

    private static RequestManager getGlideRequestManager(Context context) {
        return Glide.with(context);
    }

    private static FileUtils getFileUtils(Context context) {
        return FileUtils.getInstance(context);
    }
}
