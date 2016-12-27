package com.example.ekta.recyclersearchview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ekta.recyclersearchview.adapter.RecyclerAdapter;
import com.example.ekta.recyclersearchview.manager.NotificationListenerManager;
import com.example.ekta.recyclersearchview.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NotificationListenerManager
        .Observer {
    private RecyclerAdapter mAdapter;
    private ArrayList<Bitmap> mBitmaps = new ArrayList<>();
    private Intent mDownloadImageIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        NotificationListenerManager.getInstance().addObserver(Notification
                .NOTIFY_BITMAP_DOWNLOAD, this);
    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecyclerAdapter(mBitmaps);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(mAdapter);
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
                    mAdapter.notifyDataSetChanged();
                    if (index == Constants.END_POINTS.size() - 1) {
                        stopService(mDownloadImageIntent);
                    }
                }
            }
        }
    }
}
