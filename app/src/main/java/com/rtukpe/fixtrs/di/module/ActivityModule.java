package com.rtukpe.fixtrs.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.rtukpe.fixtrs.di.annotations.ActivityContext;
import com.rtukpe.fixtrs.di.annotations.PerActivity;
import com.rtukpe.fixtrs.ui.login.LoginMvpContract;
import com.rtukpe.fixtrs.ui.login.LoginMvpView;
import com.rtukpe.fixtrs.ui.login.LoginPresenter;
import com.rtukpe.fixtrs.utils.rx.AppSchedulerProvider;
import com.rtukpe.fixtrs.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by rtukpe on 13/03/2018.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    LoginMvpContract<LoginMvpView> provideDashboardPresenter(LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }
}
