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

package com.example.ekta.myexampleprojects.data.source.remote;

import android.graphics.Bitmap;
import android.util.Log;

import com.bumptech.glide.RequestManager;
import com.example.ekta.myexampleprojects.constant.Constants;
import com.example.ekta.myexampleprojects.data.source.ImageDataSource;
import com.example.ekta.myexampleprojects.utils.FileUtils;

import java.util.concurrent.ExecutionException;

import static com.example.ekta.myexampleprojects.utils.FileUtils.StorageType.EXTERNAL_PRIVATE;

/**
 * Implementation of the data source that adds a latency simulating network.
 */
public class ImageRemoteDataSource implements ImageDataSource {

    private static final String TAG = ImageRemoteDataSource.class.getSimpleName();
    private static ImageRemoteDataSource sInstance;
    private FileUtils sFileUtilInstance;
    private RequestManager mGlideRequestManager;

    // Prevent direct instantiation.
    private ImageRemoteDataSource(FileUtils fileUtilInstance, RequestManager glideRequestManager) {
        sFileUtilInstance = fileUtilInstance;
        mGlideRequestManager = glideRequestManager;
    }

    public static ImageRemoteDataSource getInstance(FileUtils fileUtilInstance, RequestManager
            glideRequestManager) {
        if (sInstance == null) {
            sInstance = new ImageRemoteDataSource(fileUtilInstance, glideRequestManager);
        }
        return sInstance;
    }

    @Override
    public void getImage(LoadImageCallback loadImageCallback, int index) {
        String imageName;
        Bitmap theBitmap = null;
        String replace = Constants.END_POINTS.get(index).replace(".jpg", "");
        imageName = Constants.END_POINTS.get(index);
        String url = Constants.BASE_URL + imageName;
        Log.e(TAG, "onHandleIntent: url: " + url);
        try {
            theBitmap = mGlideRequestManager.
                    load(url).
                    asBitmap().
                    into(100, 100). // Width and height
                    get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (theBitmap == null) {
            loadImageCallback.onDataNotAvailable();
        } else {
            sFileUtilInstance.saveImage(theBitmap, replace,
                    EXTERNAL_PRIVATE, FileUtils.ImageType.TYPE_JPEG);
            loadImageCallback.onImageLoaded(theBitmap);
        }
    }
}
