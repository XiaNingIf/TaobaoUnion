package com.jit.taobaounion.presenter.impl;

import com.jit.taobaounion.model.Api;
import com.jit.taobaounion.model.domain.Histories;
import com.jit.taobaounion.model.domain.SearchRecommend;
import com.jit.taobaounion.model.domain.SearchResult;
import com.jit.taobaounion.presenter.ISearchPresenter;
import com.jit.taobaounion.utils.JsonCacheUtil;
import com.jit.taobaounion.utils.RetrofitManager;
import com.jit.taobaounion.view.ISearchPageCallback;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchPresenterImpl implements ISearchPresenter {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_HISTORIES_SIZE = 10;
    private final Api mApi;
    private ISearchPageCallback mSearchViewCallback = null;
    private String mCurrentKeyword = null;
    private int mHistoriesMaxSize = DEFAULT_HISTORIES_SIZE;
    private final JsonCacheUtil mJsonCacheUtil;


    public SearchPresenterImpl(){
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
        mJsonCacheUtil = JsonCacheUtil.getInstance();
    }


    /**
     * 当前页
     */
    private int mCurrentPage = DEFAULT_PAGE;

    @Override
    public void getHistories() {
        Histories histories = mJsonCacheUtil.getValue(KEY_HISTORIES, Histories.class);
        if (mSearchViewCallback != null){
            mSearchViewCallback.onHistoriesLoaded(histories);
        }
    }

    @Override
    public void deleteHistories() {
        mJsonCacheUtil.delCache(KEY_HISTORIES);
        if (mSearchViewCallback != null) {
            mSearchViewCallback.onHistoriesDeleted();
        }
    }

    public final String  KEY_HISTORIES = "key_histories";

    private void saveHistory(String history){
        Histories histories = mJsonCacheUtil.getValue(KEY_HISTORIES, Histories.class);
        //如果说已经有了，就干掉，然后再添加
        List<String> historiesList = null;
        if (histories != null && histories.getHistories() != null) {
            historiesList = histories.getHistories();
            if (historiesList.contains(history)) {
                historiesList.remove(history);
            }
        }
        //去重完成
        //处理空的情况
        if (historiesList == null) {
            historiesList = new ArrayList<>();
        }
        if (histories == null) {
            histories = new Histories();
        }
        histories.setHistories(historiesList);
        //对个数进行限制
        if (historiesList.size()>mHistoriesMaxSize) {
            historiesList = historiesList.subList(0,mHistoriesMaxSize);
        }
        //添加记录
        historiesList.add(history);
        //保存记录
        mJsonCacheUtil.saveCache(KEY_HISTORIES,histories);
    }

    @Override
    public void doSearch(String keyword) {
        if (mCurrentKeyword == null || !mCurrentKeyword.equals(keyword)) {
            this.saveHistory(keyword);
            this.mCurrentKeyword = keyword;
        }
        if (mSearchViewCallback != null) {
            mSearchViewCallback.onLoading();
        }
        Call<SearchResult> task = mApi.doSearch(mCurrentPage, keyword);
        task.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK){
                    SearchResult result = response.body();
                    handleSearchResult(result);
                }else{
                    onError();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                onError();
            }
        });
    }

    private void onError() {
        if (mSearchViewCallback != null) {
            mSearchViewCallback.onError();
        }
    }

    private void handleSearchResult(SearchResult result) {
        if (mSearchViewCallback != null) {
            if (isResultEmpty(result)) {
                //数据为空
                mSearchViewCallback.onEmpty();
            } else {
                mSearchViewCallback.onSearchSuccess(result);
            }
        }
    }

    private boolean isResultEmpty(SearchResult result){
        try{
            return result==null||result.getData().getTbk_dg_material_optional_response().getResult_list().getMap_data().size()==0;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void research() {
        if (mCurrentKeyword == null) {
            if (mSearchViewCallback != null) {
                mSearchViewCallback.onEmpty();
            }
        }else{
            this.doSearch(mCurrentKeyword);
        }
    }

    @Override
    public void loaderMore() {
        mCurrentPage++;
        //进行搜索
        if (mCurrentKeyword == null){
            if (mSearchViewCallback != null) {
                mSearchViewCallback.onEmpty();
            }
        }else{
            //做搜索
            doSearchMore();
        }
    }

    private void doSearchMore() {
        Call<SearchResult> task = mApi.doSearch(mCurrentPage, mCurrentKeyword);
        task.enqueue(new Callback<SearchResult>() {
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK){
                    SearchResult result = response.body();
                    handleMoreSearchResult(result);
                }else{
                    onLoaderMoreError();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                onLoaderMoreError();
            }
        });
    }

    /**
     * 处理加载更多的结果
     * @param result
     */
    private void handleMoreSearchResult(SearchResult result) {
        if (mSearchViewCallback != null) {
            if (isResultEmpty(result)) {
                //数据为空
                mSearchViewCallback.onMoreLoaderEmpty();
            } else {
                mSearchViewCallback.onMoreLoaded(result);
            }
        }
    }

    /**
     * 加载更多失败
     */
    private void onLoaderMoreError() {
        mCurrentPage--;
        if (mSearchViewCallback != null) {
            mSearchViewCallback.onMoreLoaderError();
        }
    }

    @Override
    public void getRecommendWords() {
        Call<SearchRecommend> task = mApi.getRecommendWords();
        task.enqueue(new Callback<SearchRecommend>() {
            @Override
            public void onResponse(Call<SearchRecommend> call, Response<SearchRecommend> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK){
                    SearchRecommend result = response.body();
                    if (mSearchViewCallback != null) {
                        mSearchViewCallback.onRecommendWordsLoaded(result.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchRecommend> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void registerViewCallback(ISearchPageCallback callback) {
        this.mSearchViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ISearchPageCallback callback) {
        this.mSearchViewCallback = null;
    }
}
