package com.jit.taobaounion.model;

import com.jit.taobaounion.model.domain.Categories;
import com.jit.taobaounion.model.domain.HomePagerContent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {
    @GET("discovery/categories")
    Call<Categories> getCategories();

    @GET
    Call<HomePagerContent> getHomePagerContent(@Url String url);
}
