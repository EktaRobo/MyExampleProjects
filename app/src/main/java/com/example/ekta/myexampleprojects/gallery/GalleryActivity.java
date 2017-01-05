package com.example.ekta.myexampleprojects.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ekta.myexampleprojects.R;
import com.example.ekta.myexampleprojects.utils.ActivityUtils;


public class GalleryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        GalleryFragment addEditTaskFragment =
                (GalleryFragment) getSupportFragmentManager().findFragmentById(R.id
                        .content_frame);


        if (addEditTaskFragment == null) {
            addEditTaskFragment = GalleryFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addEditTaskFragment, R.id.content_frame);
        }
        Intent downloadImageIntent = new Intent(this, DownloadImageService.class);
        startService(downloadImageIntent);
    }

}
