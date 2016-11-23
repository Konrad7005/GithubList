package com.example.konrad.repositories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Konrad on 2016-11-22.
 */

public final class GithubApiFactory {

    private GithubApiFactory(){

    }

    public static GithubApi getApi () {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                return retrofit.create(GithubApi.class);

    }
}
