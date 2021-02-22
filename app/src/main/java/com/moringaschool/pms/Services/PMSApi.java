package com.moringaschool.pms.Services;

import com.moringaschool.pms.model.ApiReturn;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PMSApi {
    @GET("articles")
    Call<List<ApiReturn>> getArticles(

    );
}
