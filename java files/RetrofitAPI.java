package com.example.purplecloud;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitAPI {

    @GET
    Call<NewsResults> getAllNews(@Url String url);

    @GET
    Call<NewsResults> getTopHeadlines(@Url String url);

}
