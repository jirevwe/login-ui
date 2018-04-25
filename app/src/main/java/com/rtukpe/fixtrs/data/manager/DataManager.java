package com.rtukpe.fixtrs.data.manager;


import com.rtukpe.fixtrs.data.repository.local.interfaces.LocalDataInterface;
import com.rtukpe.fixtrs.data.repository.mock.MockerInterface;
import com.rtukpe.fixtrs.data.repository.remote.api.RemoteServiceApi;

/**
 * Created by rtukpe on 14/03/2018.
 */

public interface DataManager extends RemoteServiceApi, LocalDataInterface, MockerInterface {

}
