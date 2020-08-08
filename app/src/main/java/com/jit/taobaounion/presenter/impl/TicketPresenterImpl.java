package com.jit.taobaounion.presenter.impl;

import com.jit.taobaounion.model.Api;
import com.jit.taobaounion.model.domain.TicketParams;
import com.jit.taobaounion.model.domain.TicketResult;
import com.jit.taobaounion.presenter.ITicketPresenter;
import com.jit.taobaounion.utils.LogUtils;
import com.jit.taobaounion.utils.RetrofitManager;
import com.jit.taobaounion.utils.UrlUtils;
import com.jit.taobaounion.view.ITicketPagerCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TicketPresenterImpl implements ITicketPresenter {

    private ITicketPagerCallback mViewCallback = null;
    private String mCover = null;
    private TicketResult mTicketResult;

    enum LoadState{
        LOADING,SUCCESS,ERROR,NONE
    }

    private LoadState mCurrentState = LoadState.NONE;

    @Override
    public void getTicket(String title, String url, String cover) {
        //去获取淘口令
        onTicketLoading();
        this.mCover = cover;
        String targetUrl = UrlUtils.getTicketUrl(url);
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        TicketParams ticketParams = new TicketParams(targetUrl,title);
        Call<TicketResult> task = api.getTicket(ticketParams);
        task.enqueue(new Callback<TicketResult>() {
            @Override
            public void onResponse(Call<TicketResult> call, Response<TicketResult> response) {
                int code = response.code();
                LogUtils.d(TicketPresenterImpl.this,"code----->" + code);
                //请求成功
                if (code == HttpURLConnection.HTTP_OK){
                    mTicketResult = response.body();
                    LogUtils.d(TicketPresenterImpl.this,"result---->" + mTicketResult.toString());
                    //通知UI
                    onTicketLoadedSuccess();
                }else{
                    //请求失败
                    onLoadedTicketError();
                }
            }

            @Override
            public void onFailure(Call<TicketResult> call, Throwable t) {
                //请求失败
                LogUtils.e(TicketPresenterImpl.this,t.toString());
                onLoadedTicketError();
            }
        });
    }

    private void onTicketLoadedSuccess() {
        if (mViewCallback != null) {
            mViewCallback.onTicketLoaded(mCover, mTicketResult);
        }else{
            mCurrentState = LoadState.SUCCESS;
        }
    }

    private void onLoadedTicketError() {
        if (mViewCallback != null) {
            mViewCallback.onError();
        }else{
            mCurrentState = LoadState.ERROR;
        }
    }

    @Override
    public void registerViewCallback(ITicketPagerCallback callback) {
        if (mCurrentState != LoadState.NONE){
            //说明状态已经改变
            //通知更新UI
            if(mCurrentState == LoadState.SUCCESS){
                onTicketLoadedSuccess();
            }else if(mCurrentState == LoadState.ERROR){
                onLoadedTicketError();
            }else if (mCurrentState == LoadState.LOADING){
                onTicketLoading();
            }
        }
        this.mViewCallback = callback;
    }

    private void onTicketLoading() {
        if (mViewCallback != null) {
            mViewCallback.onLoading();
        }else{
            mCurrentState = LoadState.LOADING;
        }
    }

    @Override
    public void unregisterViewCallback(ITicketPagerCallback callback) {
        this.mViewCallback  = null;
    }
}
