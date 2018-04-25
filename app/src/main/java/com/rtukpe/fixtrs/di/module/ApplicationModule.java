package com.rtukpe.fixtrs.di.module;

import android.app.Application;
import android.content.Context;

import com.rtukpe.fixtrs.data.manager.AppDataManager;
import com.rtukpe.fixtrs.data.manager.DataManager;
import com.rtukpe.fixtrs.di.annotations.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rtukpe on 13/03/2018.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }
}
