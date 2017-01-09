package com.example.ekta.myexampleprojects.gallery;

import com.example.ekta.myexampleprojects.data.ImageRepositoryComponent;

import dagger.Component;

/**
 * Created by ekta on 6/1/17.
 */

@GalleryScope
@Component(dependencies = ImageRepositoryComponent.class, modules = GalleryPresenterModule.class)
public interface GalleryComponent {
    void inject(DownloadImageService downloadImageService);
}
