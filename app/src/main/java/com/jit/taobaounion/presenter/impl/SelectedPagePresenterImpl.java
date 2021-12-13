package com.jit.taobaounion.presenter.impl;

import android.util.Log;

import com.jit.taobaounion.model.Api;
import com.jit.taobaounion.model.domain.SelectedContent;
import com.jit.taobaounion.model.domain.SelectedPageCategory;
import com.jit.taobaounion.presenter.ISelectedPagePresenter;
import com.jit.taobaounion.utils.LogUtils;
import com.jit.taobaounion.utils.RetrofitManager;
import com.jit.taobaounion.utils.UrlUtils;
import com.jit.taobaounion.view.ISelectedPageCallback;

import org.jetbrains.annotations.NotNull;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SelectedPagePresenterImpl implements ISelectedPagePresenter {

    private final Api mApi;

    public SelectedPagePresenterImpl(){
        //拿到retrofit
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    private ISelectedPageCallback mViewCallback = null;

    @Override
    public void getCategories() {
        if (mViewCallback != null) {
            mViewCallback.onLoading();
        }
        Call<SelectedPageCategory> task = mApi.getSelectedPageCategories();
        task.enqueue(new Callback<SelectedPageCategory>() {
            @Override
            public void onResponse(Call<SelectedPageCategory> call, Response<SelectedPageCategory> response) {
                int code = response.code();
                LogUtils.e(SelectedPagePresenterImpl.this,"code----->" + code);
                if (code == HttpURLConnection.HTTP_OK){
                    SelectedPageCategory result = response.body();
                    LogUtils.e(SelectedPagePresenterImpl.this,"result----->" + result.toString());
                    //通知ui更新
                    if (mViewCallback != null) {
                        mViewCallback.onCategoriesLoaded(result);
                    }
                }else{
                    onLoadedError();
                }
            }

            @Override
            public void onFailure(Call<SelectedPageCategory> call, Throwable t) {
                LogUtils.e(SelectedPagePresenterImpl.this,t.toString());
                onLoadedError();
            }
        });
    }

    private void onLoadedError() {
        if (mViewCallback != null) {
            mViewCallback.onError();
        }
    }

    @Override
    public void getContentByCategory(@NotNull SelectedPageCategory.DataBean item) {
        int categoryId = item.getFavorites_id();
        String targetUrl = UrlUtils.getSelectedPageContentUrl(categoryId);
        Call<SelectedContent> task = mApi.getSelectedContent(targetUrl);
        task.enqueue(new Callback<SelectedContent>() {
            @Override
            public void onResponse(Call<SelectedContent> call, Response<SelectedContent> response) {
                int resultCode = response.code();
                LogUtils.e(SelectedPagePresenterImpl.this,"resultCode----->" + resultCode);
                if (resultCode == HttpURLConnection.HTTP_OK){
                    SelectedContent result = response.body();
                    LogUtils.e(SelectedPagePresenterImpl.this,"getContentCategory result----->" + result.toString());
                    //通知ui更新
                    if (mViewCallback != null) {
                        mViewCallback.onContentLoaded(result);
                    }
                }else{
                    onLoadedError();
                }
            }

            @Override
            public void onFailure(Call<SelectedContent> call, Throwable t) {
                onLoadedError();
            }
        });
    }

    @Override
    public void reloadContent() {
        this.getCategories();
    }

    @Override
    public void registerViewCallback(ISelectedPageCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ISelectedPageCallback callback) {
        this.mViewCallback = null;
    }
}
