package com.example.ekta.myexampleprojects.data;

import com.example.ekta.myexampleprojects.ApplicationScope;
import com.example.ekta.myexampleprojects.data.source.ImageRepository;
import com.example.ekta.myexampleprojects.data.source.ImageRepositoryModule;
import com.example.ekta.myexampleprojects.gallery.GalleryActivity;

import dagger.Component;

/**
 * Created by ekta on 6/1/17.
 */

@ApplicationScope
@Component(modules = ImageRepositoryModule.class)
public interface ImageRepositoryComponent {

    ImageRepository provideImageRepository();

    void inject(GalleryActivity galleryActivity);
}
