package com.example.ekta.myexampleprojects;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. We use this to pass in the Context dependency to the
 * {@link
 * com.example.ekta.myexampleprojects}.
 */

@ApplicationScope
@Module
public final class ContextModule {

    private final Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}