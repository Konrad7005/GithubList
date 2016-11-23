package com.example.konrad.repositories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Konrad on 2016-11-22.
 */

public interface GithubApi {
    @GET("users/{user}/repos")
    Call<List<GithubRepository>> listRepositories(@Path("user") String user);

}
