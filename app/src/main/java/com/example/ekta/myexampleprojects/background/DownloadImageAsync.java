package com.example.ekta.myexampleprojects.background;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.ekta.myexampleprojects.constant.Constants;
import com.example.ekta.myexampleprojects.data.DownloadedImage;
import com.example.ekta.myexampleprojects.data.source.ImageDataSource;

/**
 * Created by ekta on 28/12/16.
 */

public class DownloadImageAsync extends AsyncTask<ImageDataSource, DownloadedImage, Void> {
    private static final String TAG = DownloadImageAsync.class.getSimpleName();

    @Override
    protected Void doInBackground(ImageDataSource... taskRepository) {

        for (int index = 0; index < Constants.END_POINTS.size(); index++) {
            final int finalIndex = index;
            taskRepository[0].getImage(new ImageDataSource.LoadImageCallback() {
                @Override
                public void onImageLoaded(Bitmap bitmap) {
                    DownloadedImage downloadedImage = new DownloadedImage(bitmap, finalIndex);
                    publishProgress(downloadedImage);
                }

                @Override
                public void onDataNotAvailable() {

                }
            }, index);
        }

        return null;
    }


    /*private void downloadImageFromUrlAndSaveInFile(int index, Context context) throws
            InterruptedException, ExecutionException {
        String imageName;
        Bitmap theBitmap;
        String replace = Constants.END_POINTS.get(index).replace(".jpg", "");
        imageName = Constants.END_POINTS.get(index);
        String url = Constants.BASE_URL + imageName;
        Log.e(TAG, "onHandleIntent: url: " + url);
        theBitmap = Glide.
                with(context).
                load(url).
                asBitmap().
                into(100, 100). // Width and height
                get();
        FileUtils.saveImage(theBitmap, replace,
                EXTERNAL_PRIVATE, FileUtils.ImageType.TYPE_JPEG);
    }*/
}
