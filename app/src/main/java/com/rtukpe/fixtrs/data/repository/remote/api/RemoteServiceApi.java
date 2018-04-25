package com.rtukpe.fixtrs.data.repository.remote.api;

import com.rtukpe.fixtrs.data.model.Competition;
import com.rtukpe.fixtrs.data.model.FixturesResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by rtukpe on 22/03/2018.
 */

public interface RemoteServiceApi {

    @Headers({"X-Auth-Token: 7801836b9d924a48b2970153fe220b49", "X-Response-Control:minified"})
    @GET("/v1/competitions")
    Observable<ArrayList<Competition>> getCompetitions();

    @Headers({"X-Auth-Token: 7801836b9d924a48b2970153fe220b49", "X-Response-Control:minified"})
    @GET("/v1/fixtures")
    Observable<FixturesResponse> getFixtures(@Query("timeFrame") String timeFrame);
}
