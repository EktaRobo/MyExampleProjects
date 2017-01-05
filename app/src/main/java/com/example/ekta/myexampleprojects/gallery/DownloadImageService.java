package com.example.ekta.myexampleprojects.gallery;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.ekta.myexampleprojects.R;
import com.example.ekta.myexampleprojects.application.AppWideApplication;
import com.example.ekta.myexampleprojects.constant.Constants;
import com.example.ekta.myexampleprojects.data.DownloadedImage;
import com.example.ekta.myexampleprojects.manager.NotificationListenerManager;

/**
 * Created by ekta on 14/12/16.
 */

public class DownloadImageService extends Service implements GalleryContract.View {
    private static final String TAG = DownloadImageService.class.getSimpleName();
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private GalleryContract.Presenter mPresenter;

    public DownloadImageService() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Save service started");
        if (mPresenter == null) {
            setPresenter(new GalleryPresenter(this, ((AppWideApplication) getApplicationContext()
            ).getImageRepository()));
        }
        mPresenter.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void notifyActivity(DownloadedImage downloadedImage) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BITMAP, downloadedImage.getBitmap());
        bundle.putInt(Constants.INDEX, downloadedImage.getIndex());
        NotificationListenerManager.getInstance().notifyObservers(Constants.NotificationEnum
                .NOTIFY_BITMAP_DOWNLOAD, bundle);
    }

    @Override
    public void showProgressNotification(int value) {
        final int id = 1;
        if (value < Constants.END_POINTS.size() - 1) {
            mNotifyManager = (NotificationManager) getSystemService(Context
                    .NOTIFICATION_SERVICE);

            mBuilder = new NotificationCompat.Builder(DownloadImageService.this);
            mBuilder.setContentTitle(getString(R.string.picture_download))
                    .setContentText(getString(R.string.download_in_progress))
                    .setSmallIcon(android.R.drawable.ic_notification_clear_all);
            mBuilder.setProgress(10, value, false);
            // Displays the progress bar for the first time.
            mNotifyManager.notify(id, mBuilder.build());
        } else {
            // When the loop is finished, updates the notification
            mBuilder.setContentText(getString(R.string.download_complete))
                    // Removes the progress bar
                    .setProgress(0, 0, false);
            mNotifyManager.notify(id, mBuilder.build());
        }
    }

    @Override
    public void stopService() {
        this.stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "in onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Save service finished");
        super.onDestroy();
    }

    @Override
    public void setPresenter(GalleryContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
