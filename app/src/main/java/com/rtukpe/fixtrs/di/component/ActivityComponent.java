package com.rtukpe.fixtrs.di.component;

import com.rtukpe.fixtrs.di.annotations.PerActivity;
import com.rtukpe.fixtrs.di.module.ActivityModule;
import com.rtukpe.fixtrs.ui.login.LoginActivity;

import dagger.Component;

/**
 * Created by rtukpe on 13/03/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    // Activities

    void inject(LoginActivity activity);

}
