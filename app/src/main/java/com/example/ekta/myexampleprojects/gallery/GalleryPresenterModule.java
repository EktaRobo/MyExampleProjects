package com.example.ekta.myexampleprojects.gallery;

import com.example.ekta.myexampleprojects.data.source.ImageRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ekta on 6/1/17.
 */

@Module(includes = GalleryViewModule.class)
public class GalleryPresenterModule {

    @Provides
    @GalleryScope
    public GalleryContract.Presenter getGalleryPresenter(GalleryContract.View view, ImageRepository
            imageRepository) {
        return new GalleryPresenter(view, imageRepository);
    }
}
