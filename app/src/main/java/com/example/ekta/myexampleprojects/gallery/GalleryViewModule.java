package com.example.ekta.myexampleprojects.gallery;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ekta on 6/1/17.
 */

@Module
public class GalleryViewModule {
    GalleryContract.View mView;

    public GalleryViewModule(GalleryContract.View view) {
        mView = view;
    }

    @Provides
    public GalleryContract.View getView() {
        return mView;
    }
}
