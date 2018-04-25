package com.rtukpe.fixtrs.ui.login;

import com.rtukpe.fixtrs.di.annotations.PerActivity;
import com.rtukpe.fixtrs.ui.base.MvpPresenter;

/**
 * Created by rtukpe on 21/03/2018.
 */

@PerActivity
public interface LoginMvpContract<V extends LoginMvpView> extends MvpPresenter<V> {

}
