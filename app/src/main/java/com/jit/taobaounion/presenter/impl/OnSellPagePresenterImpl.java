package com.jit.taobaounion.presenter.impl;

import com.jit.taobaounion.model.Api;
import com.jit.taobaounion.model.domain.OnSellContent;
import com.jit.taobaounion.presenter.IOnSellPagePresenter;
import com.jit.taobaounion.utils.RetrofitManager;
import com.jit.taobaounion.utils.UrlUtils;
import com.jit.taobaounion.view.IOnSellPageCallback;

import org.jetbrains.annotations.NotNull;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OnSellPagePresenterImpl implements IOnSellPagePresenter {

    public static final  int DEFAULT_PAGE = 1;
    private  int mCurrentPage = DEFAULT_PAGE;
    private IOnSellPageCallback mOnSellPageCallback = null;
    private final Api mApi;

    public OnSellPagePresenterImpl(){
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    @Override
    public void getOnSellContent() {
        if (mIsLoading){
            return;
        }
        mIsLoading = true;
        //通知UI状态为加载中
        if (mOnSellPageCallback != null) {
            mOnSellPageCallback.onLoading();
        }
        //获取特惠内容
        
        String targetUrl = UrlUtils.getOnSellPageUrl(mCurrentPage);
        Call<OnSellContent> task = mApi.getOnSellPageContent(targetUrl);
        task.enqueue(new Callback<OnSellContent>() {
            @Override
            public void onResponse(Call<OnSellContent> call, Response<OnSellContent> response) {
                mIsLoading = false;
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK){
                    OnSellContent result = response.body();
                    onSuccess(result);
                }else{
                    onError();
                }
            }

            @Override
            public void onFailure(Call<OnSellContent> call, Throwable t) {
                onError();
            }
        });
    }

    private void onSuccess(OnSellContent result) {
        if (mOnSellPageCallback != null) {
            try{
                if (isEmpty(result)){
                    onEmpty();
                }else{
                    mOnSellPageCallback.onContentLoadedSuccess(result);
                }
            } catch (Exception e){
                e.printStackTrace();
                onEmpty();
            }
        }
    }

    private boolean isEmpty(@NotNull OnSellContent result){
        try{
            int size = result.getData().getTbk_dg_optimus_material_response().getResult_list().getMap_data().size();
            return size == 0;
        }catch (Exception e){
            return true;
        }
    }

    private void onEmpty(){
        if (mOnSellPageCallback != null) {
            mOnSellPageCallback.onEmpty();
        }
    }

    private void onError() {
        mIsLoading = false;
        if (mOnSellPageCallback != null) {
            mOnSellPageCallback.onError();
        }
    }

    /**
     * 当前状态
     */
    private boolean mIsLoading = false;

    @Override
    public void reload() {
        //重新加载
        this.getOnSellContent();
    }

    @Override
    public void loaderMore() {
        if (mIsLoading){
            return;
        }
        mIsLoading = true;
        //加载更多
        mCurrentPage++;
        //去加载更多内容
        String targetUrl = UrlUtils.getOnSellPageUrl(mCurrentPage);
        Call<OnSellContent> task = mApi.getOnSellPageContent(targetUrl);
        task.enqueue(new Callback<OnSellContent>() {
            @Override
            public void onResponse(Call<OnSellContent> call, Response<OnSellContent> response) {
                mIsLoading = false;
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK){
                    OnSellContent result = response.body();
                    onMoreLoaded(result);
                }else{
                    onMoreLoadedError();
                }
            }

            @Override
            public void onFailure(Call<OnSellContent> call, Throwable t) {
                onMoreLoadedError();
            }
        });
    }

    private void onMoreLoadedError() {
        mIsLoading = false;
        mCurrentPage--;
        mOnSellPageCallback.onMoreLoadedError();
    }

    private void onMoreLoaded(OnSellContent result) {
        if (mOnSellPageCallback != null) {
            if (isEmpty(result)) {
                mCurrentPage--;
                mOnSellPageCallback.onMoreLoadedEmpty();
            } else {
                mOnSellPageCallback.onMoreLoaded(result);
            }
        }
    }

    @Override
    public void registerViewCallback(IOnSellPageCallback callback) {
        this.mOnSellPageCallback = callback;
    }

    @Override
    public void unregisterViewCallback(IOnSellPageCallback callback) {
        this.mOnSellPageCallback = null;
    }
}
