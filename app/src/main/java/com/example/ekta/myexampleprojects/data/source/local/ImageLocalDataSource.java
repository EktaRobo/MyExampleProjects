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

package com.example.ekta.myexampleprojects.data.source.local;

import android.graphics.Bitmap;

import com.example.ekta.myexampleprojects.constant.Constants;
import com.example.ekta.myexampleprojects.data.source.ImageDataSource;
import com.example.ekta.myexampleprojects.utils.FileUtils;

import java.io.File;


/**
 * Concrete implementation of a data source from file.
 */

public class ImageLocalDataSource implements ImageDataSource {

    private static ImageLocalDataSource sInstance;
    private FileUtils mFileUtilsInstance;

    public ImageLocalDataSource(FileUtils fileUtilInstance) {
        mFileUtilsInstance = fileUtilInstance;
    }

    @Override
    public void getImage(LoadImageCallback loadImageCallback, int index) {
        File directory = mFileUtilsInstance.getStorageDir(FileUtils
                .StorageType.EXTERNAL_PRIVATE);
        Bitmap bitmap;
        bitmap = mFileUtilsInstance.loadImageFromStorage(directory.getPath(), Constants.END_POINTS
                .get
                (index));
        if (bitmap == null) {
            loadImageCallback.onDataNotAvailable();
        } else {
            loadImageCallback.onImageLoaded(bitmap);
        }
    }
}
