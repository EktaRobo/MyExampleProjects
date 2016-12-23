package com.example.ekta.recyclersearchview;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.ekta.recyclersearchview.manager.NotificationListenerManager;
import com.example.ekta.recyclersearchview.utils.FileUtils;

import java.util.concurrent.ExecutionException;

import static com.example.ekta.recyclersearchview.utils.FileUtils.StorageType.EXTERNAL_PRIVATE;

/**
 * Created by ekta on 14/12/16.
 */

public class DownloadImageService extends Service {
    private static final String TAG = DownloadImageService.class.getSimpleName();
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;

    public DownloadImageService() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Save service started");
        AsyncTask<Void, Integer, Void> asyncTask = new AsyncTask<Void, Integer, Void>() {
            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                final int id = 1;
                if (values[0] < Constants.END_POINTS.size() - 1) {
                    mNotifyManager = (NotificationManager) getSystemService(Context
                            .NOTIFICATION_SERVICE);

                    mBuilder = new NotificationCompat.Builder(DownloadImageService.this);
                    mBuilder.setContentTitle("Picture Download")
                            .setContentText("Download in progress")
                            .setSmallIcon(android.R.drawable.ic_notification_clear_all);
                    mBuilder.setProgress(10, values[0], false);
                    // Displays the progress bar for the first time.
                    mNotifyManager.notify(id, mBuilder.build());
                } else {
                    // When the loop is finished, updates the notification
                    mBuilder.setContentText("Download complete")
                            // Removes the progress bar
                            .setProgress(0, 0, false);
                    mNotifyManager.notify(id, mBuilder.build());
                }
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.INDEX, values[0]);
                NotificationListenerManager.getInstance().notifyObservers(Notification
                        .NOTIFY_BITMAP_DOWNLOAD, bundle);

            }


            @Override
            protected Void doInBackground(Void... voids) {
                Bitmap theBitmap;
                String imageName;

                try {
                    for (int index = 0; index < Constants.END_POINTS.size(); index++) {
                        String replace = Constants.END_POINTS.get(index).replace(".jpg", "");
                        imageName = Constants.END_POINTS.get(index);
                        String url = Constants.BASE_URL + imageName;
                        Log.e(TAG, "onHandleIntent: url: " + url);
                        theBitmap = Glide.
                                with(DownloadImageService.this).
                                load(url).
                                asBitmap().
                                into(100, 100). // Width and height
                                get();
                        FileUtils.saveImage(DownloadImageService.this, theBitmap, replace,
                                EXTERNAL_PRIVATE, FileUtils.ImageType.TYPE_JPEG);
                        publishProgress(index);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        asyncTask.execute();
        return super.onStartCommand(intent, flags, startId);
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

}
