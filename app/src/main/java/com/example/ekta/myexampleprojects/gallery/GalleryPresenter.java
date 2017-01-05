package com.example.ekta.myexampleprojects.gallery;

import android.graphics.Bitmap;

import com.example.ekta.myexampleprojects.background.DownloadImageAsync;
import com.example.ekta.myexampleprojects.data.DownloadedImage;
import com.example.ekta.myexampleprojects.data.source.ImageDataSource;
import com.example.ekta.myexampleprojects.data.source.ImageRepository;

/**
 * Created by ekta on 4/1/17.
 */

public class GalleryPresenter implements GalleryContract.Presenter, ImageDataSource
        .LoadImageCallback {

    private final GalleryContract.View mAddTaskView;
    private ImageRepository mImageRepository;

    public GalleryPresenter(GalleryContract.View addTaskView, ImageRepository imageRepository) {
        mAddTaskView = addTaskView;
        mImageRepository = imageRepository;
    }

    @Override
    public void start() {
        DownloadImageAsync asyncTask = new DownloadImageAsync() {
            @Override
            protected void onProgressUpdate(DownloadedImage... downloadedImages) {
                super.onProgressUpdate(downloadedImages);
                mAddTaskView.showProgressNotification(downloadedImages[0].getIndex());
                mAddTaskView.notifyActivity(downloadedImages[0]);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mAddTaskView.stopService();
            }
        };

        asyncTask.execute(mImageRepository);

    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {

    }

    @Override
    public void onDataNotAvailable() {

    }

}
