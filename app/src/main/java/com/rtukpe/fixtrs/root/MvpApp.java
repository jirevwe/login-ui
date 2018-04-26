package com.rtukpe.fixtrs.root;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.firebase.FirebaseApp;
import com.rtukpe.fixtrs.R;
import com.rtukpe.fixtrs.di.component.ApplicationComponent;
import com.rtukpe.fixtrs.di.component.DaggerApplicationComponent;
import com.rtukpe.fixtrs.di.module.ApplicationModule;
import com.rtukpe.fixtrs.utils.others.AppLogger;
import com.twitter.sdk.android.core.Twitter;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MvpApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);

        //App logger
        AppLogger.init();
        FirebaseApp.initializeApp(this);
        Twitter.initialize(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/RobotoCondensed-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
