package com.example.ekta.myexampleprojects;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ekta on 6/1/17.
 */

@ApplicationScope
@Module
public class GlideModule {

    @Provides
    RequestManager getRequestManager(Context context) {
        return Glide.with(context);
    }
}
