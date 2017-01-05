package com.example.ekta.myexampleprojects.gallery;

import com.example.ekta.myexampleprojects.BasePresenter;
import com.example.ekta.myexampleprojects.BaseView;
import com.example.ekta.myexampleprojects.data.DownloadedImage;

/**
 * Created by ekta on 4/1/17.
 */

public interface GalleryContract {

    interface View extends BaseView<Presenter> {

        void notifyActivity(DownloadedImage downloadedImage);

        void showProgressNotification(int value);

        void stopService();
    }

    interface Presenter extends BasePresenter {


    }
}
