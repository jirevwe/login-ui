package com.rtukpe.fixtrs.data.repository.remote.helpers.base;

import com.rtukpe.fixtrs.utils.others.AppUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rtukpe on 14/03/2018.
 */

public class BaseHelper {
    private OkHttpClient.Builder okHttpCBuilder = new OkHttpClient.Builder();

    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(AppUtils.gson));

    private static String getBaseUrl() {
        return "http://api.football-data.org/";
    }

    protected <S> S createService(Class<S> serviceClass) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpCBuilder.networkInterceptors().add(httpLoggingInterceptor);
        okHttpCBuilder.retryOnConnectionFailure(true);
        OkHttpClient client = okHttpCBuilder.build();

        builder.client(client);
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}
