package com.rtukpe.fixtrs.data.manager;

import android.content.Context;

import com.rtukpe.fixtrs.data.model.Competition;
import com.rtukpe.fixtrs.data.model.FixturesResponse;
import com.rtukpe.fixtrs.data.repository.mock.MockHelper;
import com.rtukpe.fixtrs.data.repository.remote.helpers.RemoteServiceHelper;
import com.rtukpe.fixtrs.di.annotations.ApplicationContext;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by rtukpe on 14/03/2018.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private Context mContext;
    private RemoteServiceHelper mRemoteServiceHelper;
    private MockHelper mMockHelper;

    @Inject
    AppDataManager(@ApplicationContext Context mContext,
                   RemoteServiceHelper mRemoteServiceHelper,
                   MockHelper mockHelper) {
        this.mContext = mContext;
        this.mRemoteServiceHelper = mRemoteServiceHelper;
        this.mMockHelper = mockHelper;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public Observable<ArrayList<Competition>> getCompetitions() {
        return mRemoteServiceHelper.getCompetitions();
    }

    @Override
    public Observable<FixturesResponse> getFixtures(String timeFrame) {
        return mRemoteServiceHelper.getFixtures(timeFrame);
    }

    @Override
    public Observable<FixturesResponse> getMockFixtures() {
        return mMockHelper.getMockFixtures();
    }
}
