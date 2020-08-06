package com.jit.taobaounion.presenter.impl;

import com.jit.taobaounion.model.Api;
import com.jit.taobaounion.model.domain.HomePagerContent;
import com.jit.taobaounion.presenter.ICategoryPagerPresenter;
import com.jit.taobaounion.utils.LogUtils;
import com.jit.taobaounion.utils.RetrofitManager;
import com.jit.taobaounion.utils.UrlUtils;
import com.jit.taobaounion.view.ICategoryPagerCallback;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Url;

public class CategoryPagePresenterImpl implements ICategoryPagerPresenter {

    private Map<Integer,Integer> pageInfo = new HashMap<>();

    public static final int DEFAULT_PAGE = 1;

    private CategoryPagePresenterImpl(){}

    private static ICategoryPagerPresenter sInstance = null;

    public static ICategoryPagerPresenter getInstance(){
        if (sInstance == null) {
            sInstance = new CategoryPagePresenterImpl();
        }
        return sInstance;
    }

    @Override
    public void getContentByCategoryId(int categoryId) {
        for (ICategoryPagerCallback callback : callbacks) {
            callback.onLoading(categoryId);
        }
        //根据分类id去加载内容
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Integer targetPage = pageInfo.get(categoryId);
        if (targetPage == null) {
            targetPage = DEFAULT_PAGE;
            pageInfo.put(categoryId,targetPage);
        }
        String homePagerUrl = UrlUtils.createHomePagerUrl(categoryId, targetPage);
        LogUtils.e(this,"home page url ---->" + homePagerUrl);
        Call<HomePagerContent> task = api.getHomePagerContent(homePagerUrl);
        task.enqueue(new Callback<HomePagerContent>() {
            @Override
            public void onResponse(Call<HomePagerContent> call, Response<HomePagerContent> response) {
                int code = response.code();
                LogUtils.e(CategoryPagePresenterImpl.this,"code---->"+code);
                if (code == HttpURLConnection.HTTP_OK){
                    HomePagerContent pagerContent = response.body();
                    LogUtils.e(CategoryPagePresenterImpl.this,"pageContent---->"+pagerContent.toString());
                    //把数据给到UI更新
                    handleHomePageContentResult(pagerContent,categoryId);
                }else{
                    handleNetworkError(categoryId);
                }
            }

            @Override
            public void onFailure(Call<HomePagerContent> call, Throwable t) {
                LogUtils.e(CategoryPagePresenterImpl.this,"onFailure---->"+t.toString());
            }
        });
    }

    private void handleNetworkError(int categoryId) {
        for (ICategoryPagerCallback callback : callbacks) {
            callback.onError(categoryId);
        }
    }

    private void handleHomePageContentResult(HomePagerContent pagerContent, int categoryId) {
        //通知UI层更新数据
        for (ICategoryPagerCallback callback : callbacks) {
            if (pagerContent==null||pagerContent.getData().size()==0) {
                callback.onEmpty(categoryId);
            }else {
                callback.onContentLoaded(pagerContent.getData(),categoryId);
            }
        }
    }

    @Override
    public void loaderMore(int categoryId) {

    }

    @Override
    public void reload(int categoryId) {

    }

    private List<ICategoryPagerCallback> callbacks = new ArrayList<>();

    @Override
    public void registerViewCallback(ICategoryPagerCallback callback) {
        if (!callbacks.contains(callback)) {
            callbacks.add(callback);
        }
    }

    @Override
    public void unregisterViewCallback(ICategoryPagerCallback callback) {
        callbacks.remove(callback);
    }
}
