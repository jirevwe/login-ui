package com.rtukpe.fixtrs.di.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by rtukpe on 13/03/2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}

