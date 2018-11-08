package com.example.hemmerzoe.city_dispora.Retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by hemmerzoe on 16/10/2017.
 */

public class ServiceGenerator {
    private static String BASE_URL = "http://192.168.1.100/api_paskot/server/" ;

    private ServiceGenerator(){}

    public static <S> S createService(Class<S> serviceClass) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);


        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverter(String.class, new ToStringConverter())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return builder.create(serviceClass);
    }
}
