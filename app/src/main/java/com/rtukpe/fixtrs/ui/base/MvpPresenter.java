package com.rtukpe.fixtrs.ui.base;

/**
 * Created by rtukpe on 13/03/2018.
 */

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface MvpPresenter<V extends MvpView> {

    void onViewInitialized();

    void onAttach(V mvpView);

    void onDetach();

}
