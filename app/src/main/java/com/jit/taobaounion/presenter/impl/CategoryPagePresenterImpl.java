package com.jit.taobaounion.presenter.impl;

import android.util.Log;

import com.jit.taobaounion.model.Api;
import com.jit.taobaounion.model.domain.HomePagerContent;
import com.jit.taobaounion.presenter.ICategoryPagerPresenter;
import com.jit.taobaounion.utils.LogUtils;
import com.jit.taobaounion.utils.RetrofitManager;
import com.jit.taobaounion.utils.UrlUtils;
import com.jit.taobaounion.view.ICategoryPagerCallback;

import org.jetbrains.annotations.NotNull;

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
    private Integer mCurrentPage;


    @Override
    public void getContentByCategoryId(int categoryId) {
        for (ICategoryPagerCallback callback : callbacks) {
            if (callback.getCategoryID()==categoryId) {
                callback.onLoading();
            }
        }
        //根据分类id去加载内容

        Integer targetPage = pageInfo.get(categoryId);
        if (targetPage == null) {
            targetPage = DEFAULT_PAGE;
            pageInfo.put(categoryId,targetPage);
        }
        Call<HomePagerContent> task = createTask(categoryId, targetPage);
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

    private Call<HomePagerContent> createTask(int categoryId, Integer targetPage) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        String homePagerUrl = UrlUtils.createHomePagerUrl(categoryId, targetPage);
        LogUtils.e(this,"home page url ---->" + homePagerUrl);
        return api.getHomePagerContent(homePagerUrl);
    }

    private void handleNetworkError(int categoryId) {
        for (ICategoryPagerCallback callback : callbacks) {
            if (callback.getCategoryID()==categoryId) {
                callback.onError();
            }
        }
    }

    private void handleHomePageContentResult(@NotNull HomePagerContent pagerContent, int categoryId) {
        //通知UI层更新数据
        List<HomePagerContent.DataBean> data = pagerContent.getData();
        for (ICategoryPagerCallback callback : callbacks) {
            if (callback.getCategoryID()==categoryId) {
                if (pagerContent==null||pagerContent.getData().size()==0) {
                    callback.onEmpty();
                }else {
                    List<HomePagerContent.DataBean> looperData = data.subList(data.size() - 5, data.size());
                    callback.onLooperListLoaded(looperData);
                    callback.onContentLoaded(data);
                }
            }
        }
    }

    @Override
    public void loaderMore(int categoryId) {
        //加载更多的数据
        //拿到当前页码
        mCurrentPage = pageInfo.get(categoryId);
        if (mCurrentPage == null) {
            mCurrentPage =1;
        }
        //页码增加
        mCurrentPage++;
        //加载数据
        Call<HomePagerContent> task = createTask(categoryId, mCurrentPage);
        //处理数据结果
        task.enqueue(new Callback<HomePagerContent>() {
            @Override
            public void onResponse(Call<HomePagerContent> call, Response<HomePagerContent> response) {
                //结果
                int code = response.code();
                LogUtils.d(CategoryPagePresenterImpl.this,"code-----> " + code);
                if (code == HttpURLConnection.HTTP_OK){
                    HomePagerContent result = response.body();
                    LogUtils.e(CategoryPagePresenterImpl.this,"result---->"+result.toString());
                    handleLoaderResult(result,categoryId);
                } else {
                    handleLoaderMoreError(categoryId);
                }
            }

            @Override
            public void onFailure(Call<HomePagerContent> call, Throwable t) {
                //请求失败
                LogUtils.d(CategoryPagePresenterImpl.this,t.toString());
                handleLoaderMoreError(categoryId);
            }
        });
    }

    private void handleLoaderResult(HomePagerContent result, int categoryId) {
        for (ICategoryPagerCallback callback : callbacks) {
            if (callback.getCategoryID() == categoryId) {
                if (result==null||result.getData().size()==0) {
                    callback.onLoaderMoreEmpty();
                }else{
                    callback.onLoaderMoreLoaded(result.getData());
                }
            }
        }
    }

    private void handleLoaderMoreError(int categoryId) {
        mCurrentPage--;
        pageInfo.put(categoryId,mCurrentPage);
        for (ICategoryPagerCallback callback : callbacks) {
            if (callback.getCategoryID() == categoryId) {
                callback.onLoaderMoreError();
            }
        }
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
