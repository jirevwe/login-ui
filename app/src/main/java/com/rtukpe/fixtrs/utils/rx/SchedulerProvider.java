package com.rtukpe.fixtrs.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by rtukpe on 13/03/2018.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
