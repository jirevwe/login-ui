package com.rtukpe.fixtrs.di.component;


import android.app.Application;
import android.content.Context;

import com.rtukpe.fixtrs.data.manager.DataManager;
import com.rtukpe.fixtrs.di.annotations.ApplicationContext;
import com.rtukpe.fixtrs.di.module.ApplicationModule;
import com.rtukpe.fixtrs.root.MvpApp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rtukpe on 13/03/2018.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
