package com.jit.taobaounion.model;

import com.jit.taobaounion.model.domain.Categories;
import com.jit.taobaounion.model.domain.HomePagerContent;
import com.jit.taobaounion.model.domain.OnSellContent;
import com.jit.taobaounion.model.domain.SearchRecommend;
import com.jit.taobaounion.model.domain.SearchResult;
import com.jit.taobaounion.model.domain.SelectedContent;
import com.jit.taobaounion.model.domain.SelectedPageCategory;
import com.jit.taobaounion.model.domain.TicketParams;
import com.jit.taobaounion.model.domain.TicketResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {
    @GET("discovery/categories")
    Call<Categories> getCategories();

    @GET
    Call<HomePagerContent> getHomePagerContent(@Url String url);

    @POST("tpwd")
    Call<TicketResult> getTicket(@Body TicketParams ticketParams);

    @GET("recommend/categories")
    Call<SelectedPageCategory> getSelectedPageCategories();

    @GET
    Call<SelectedContent> getSelectedContent(@Url String url);

    @GET
    Call<OnSellContent> getOnSellPageContent(@Url String url);

    @GET("search/recommend")
    Call<SearchRecommend> getRecommendWords();

    @GET("search")
    Call<SearchResult> doSearch(@Query("page") int page,@Query("keyword") String keyword);

}
