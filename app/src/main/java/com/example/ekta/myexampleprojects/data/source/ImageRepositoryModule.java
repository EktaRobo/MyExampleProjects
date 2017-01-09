package com.example.ekta.myexampleprojects.data.source;

import com.bumptech.glide.RequestManager;
import com.example.ekta.myexampleprojects.ApplicationScope;
import com.example.ekta.myexampleprojects.ContextModule;
import com.example.ekta.myexampleprojects.FileManagerModule;
import com.example.ekta.myexampleprojects.GlideModule;
import com.example.ekta.myexampleprojects.data.source.local.ImageLocalDataSource;
import com.example.ekta.myexampleprojects.data.source.remote.ImageRemoteDataSource;
import com.example.ekta.myexampleprojects.utils.FileUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ekta on 6/1/17.
 */

@ApplicationScope
@Module(includes = {ContextModule.class, FileManagerModule.class, GlideModule.class})
public class ImageRepositoryModule {

    @Provides
    @ApplicationScope
    @Remote
    public ImageDataSource provideImageRemoteRepositoryModule(FileUtils fileUtils, RequestManager
            requestManager) {
        return new ImageRemoteDataSource(fileUtils, requestManager);
    }

    @Provides
    @Local
    @ApplicationScope
    public ImageDataSource provideImageLocalRepositoryModule(FileUtils fileUtils) {
        return new ImageLocalDataSource(fileUtils);
    }

    @Provides
    @ApplicationScope
    public ImageRepository getImageRepository(@Remote ImageDataSource tasksRemoteDataSource, @Local
            ImageDataSource tasksLocalDataSource) {
        return new ImageRepository(tasksRemoteDataSource, tasksLocalDataSource);
    }


}
