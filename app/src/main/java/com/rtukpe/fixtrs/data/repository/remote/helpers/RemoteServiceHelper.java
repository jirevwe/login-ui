package com.rtukpe.fixtrs.data.repository.remote.helpers;

import com.rtukpe.fixtrs.data.model.Competition;
import com.rtukpe.fixtrs.data.model.FixturesResponse;
import com.rtukpe.fixtrs.data.repository.remote.api.RemoteServiceApi;
import com.rtukpe.fixtrs.data.repository.remote.helpers.base.BaseHelper;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by rtukpe on 22/03/2018.
 */

public class RemoteServiceHelper extends BaseHelper implements RemoteServiceApi {

    private final RemoteServiceApi mRemoteServiceApi;

    @Inject
    RemoteServiceHelper() {
        this.mRemoteServiceApi = createService(RemoteServiceApi.class);
    }

    @Override
    public Observable<ArrayList<Competition>> getCompetitions() {
        return mRemoteServiceApi.getCompetitions();
    }

    @Override
    public Observable<FixturesResponse> getFixtures(String timeFrame) {
        return mRemoteServiceApi.getFixtures(timeFrame);
    }
}
