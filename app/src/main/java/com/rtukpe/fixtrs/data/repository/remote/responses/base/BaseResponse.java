package com.rtukpe.fixtrs.data.repository.remote.responses.base;

import com.rtukpe.fixtrs.utils.others.AppUtils;

/**
 * Created by rtukpe on 14/03/2018.
 */

public class BaseResponse<T> {
    private String status;
    private T data;
    private Object message;
    private Object meta;

    @Override
    public String toString() {
        return AppUtils.gson.toJson(this);
    }
}