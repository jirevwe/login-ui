package com.rtukpe.fixtrs.ui.login;

import com.rtukpe.fixtrs.data.manager.DataManager;
import com.rtukpe.fixtrs.ui.base.BasePresenter;
import com.rtukpe.fixtrs.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by rtukpe on 21/03/2018.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpContract<V> {

    @Inject
    public LoginPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewInitialized() {
        super.onViewInitialized();
    }
}
