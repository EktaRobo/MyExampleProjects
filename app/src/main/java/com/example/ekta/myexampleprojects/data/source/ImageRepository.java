/*
 * Copyright 2016, The Android Open Source Project
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

package com.example.ekta.myexampleprojects.data.source;

import android.graphics.Bitmap;

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 * <p>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
public class ImageRepository implements ImageDataSource {


    private static ImageRepository sInstance;
    private ImageDataSource mTasksRemoteDataSource;
    private ImageDataSource mTasksLocalDataSource;


    public ImageRepository(ImageDataSource tasksRemoteDataSource, ImageDataSource
            tasksLocalDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;

        mTasksLocalDataSource = tasksLocalDataSource;
    }

    /**
     * Used to force {@link #ImageRepository(ImageDataSource, ImageDataSource)} to create a new
     * instance
     * next time it's called.
     */
    public static void destroyInstance() {
        sInstance = null;
    }

    @Override
    public void getImage(final LoadImageCallback loadImageCallback, final int index) {
        mTasksLocalDataSource.getImage(new LoadImageCallback() {
            @Override
            public void onImageLoaded(Bitmap bitmap) {
                loadImageCallback.onImageLoaded(bitmap);
            }

            @Override
            public void onDataNotAvailable() {
                mTasksRemoteDataSource.getImage(loadImageCallback, index);
            }
        }, index);
    }
}
