package com.example.hemmerzoe.city_dispora.Retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


public class ServiceGenerator {
    public static final String BASE_URL = "http://data.pasuruankota.go.id/api_smartcity/api_andro_dispora/andro_dispora/" ;
    //public static final String BASE_URL = "http://192.168.42.170/api_paskot/server/" ;

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
