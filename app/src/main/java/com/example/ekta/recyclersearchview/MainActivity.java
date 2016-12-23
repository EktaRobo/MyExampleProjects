package com.example.ekta.recyclersearchview;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ekta.recyclersearchview.adapter.RecyclerAdapter;
import com.example.ekta.recyclersearchview.manager.NotificationListenerManager;
import com.example.ekta.recyclersearchview.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NotificationListenerManager
        .Observer {
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private DownloadImageService mBoundService;
    private boolean mServiceBound;
    private RecyclerAdapter mAdapter;
    private ArrayList<Bitmap> mBitmaps = new ArrayList<>();
    private Intent mDownloadImageIntent;
    private static Handler sHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        NotificationListenerManager.getInstance().addObserver(Notification
                .NOTIFY_BITMAP_DOWNLOAD, this);
    }

    private void init() {
        // path to /data/data/yourapp/app_data/imageDir
//        Bitmap bitmap = loadImageFromStorage(directory.getPath());
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecyclerAdapter(mBitmaps);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
        mDownloadImageIntent = new Intent(MainActivity.this, DownloadImageService
                .class);

        startService(mDownloadImageIntent);

    }
    @Override
    public void update(Notification notificationName, Bundle data) {
        if (notificationName == Notification.NOTIFY_BITMAP_DOWNLOAD) {
            if (data != null) {
                int index = data.getInt(Constants.INDEX);
                String imageName = Constants.END_POINTS.get(index);
                if (imageName != null) {
                    File directory = FileUtils.getStorageDir(MainActivity.this, FileUtils
                            .StorageType.EXTERNAL_PRIVATE);
                    Bitmap bitmap = FileUtils.loadImageFromStorage(directory.getPath(), imageName);
                    mBitmaps.add(bitmap);
                    mAdapter.notifyItemInserted(index);
                    if (index == Constants.END_POINTS.size() - 1) {
                        mProgressBar.setVisibility(View.GONE);
                        stopService(mDownloadImageIntent);
                    }
                }
            }
        }
    }
}
