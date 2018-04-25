package com.rtukpe.fixtrs.data.repository.mock;

import com.rtukpe.fixtrs.data.model.FixturesResponse;

import io.reactivex.Observable;

public interface MockerInterface {

    Observable<FixturesResponse> getMockFixtures();
}
