package com.jit.taobaounion.ui.fragment;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jit.taobaounion.R;
import com.jit.taobaounion.base.BaseFragment;
import com.jit.taobaounion.model.domain.Histories;
import com.jit.taobaounion.model.domain.IBaseInfo;
import com.jit.taobaounion.model.domain.SearchRecommend;
import com.jit.taobaounion.model.domain.SearchResult;
import com.jit.taobaounion.presenter.ISearchPresenter;
import com.jit.taobaounion.ui.adapter.LinearItemContentAdapter;
import com.jit.taobaounion.ui.custom.TextFlowLayout;
import com.jit.taobaounion.utils.LogUtils;
import com.jit.taobaounion.utils.PresenterManager;
import com.jit.taobaounion.utils.SizeUtils;
import com.jit.taobaounion.utils.TicketUtil;
import com.jit.taobaounion.utils.ToastUtil;
import com.jit.taobaounion.view.ISearchPageCallback;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchFragment extends BaseFragment implements ISearchPageCallback {

    @BindView(R.id.search_history_view)
    TextFlowLayout mHistoriesView;

    @BindView(R.id.search_recommend_view)
    TextFlowLayout mRecommendView;

    @BindView(R.id.search_history_container)
    LinearLayout mHistoriesContainer;

    @BindView(R.id.search_recommend_container)
    LinearLayout mRecommendContainer;

    @BindView(R.id.search_history_delete)
    ImageView mHistoryDelete;

    @BindView(R.id.search_result_list)
    RecyclerView mSearchList;

    @BindView(R.id.search_result_container)
    TwinklingRefreshLayout mRefreshContainer;

    @BindView(R.id.search_btn)
    TextView mSearchBtn;

    @BindView(R.id.search_clean_btn)
    ImageView mCleanBtn;

    @BindView(R.id.search_input_box)
    EditText mSearchInputBox;

    private ISearchPresenter mSearchPresenter;
    private LinearItemContentAdapter mSearchResultAdapter;

    @Override
    protected void initPresenter() {
        mSearchPresenter = PresenterManager.getInstance().getSearchPresenter();
        mSearchPresenter.registerViewCallback(this);
        //获取搜索推荐词
        mSearchPresenter.getRecommendWords();
        mSearchPresenter.getHistories();
    }

    @Override
    protected void onRetryClick() {
        //重新加载内容
        if (mSearchPresenter != null) {
            mSearchPresenter.research();
        }
    }

    @Override
    protected void release() {
        super.release();
        mSearchPresenter.unregisterViewCallback(this);
    }

    @Override
    protected View loadRootView(@NotNull LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_search_layout,container,false);
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initListener() {
        mSearchInputBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE && mSearchPresenter != null){
                    //判断拿到的内容时候付为空
                    String keyword = v.getText().toString().trim();
                    if (TextUtils.isEmpty(keyword)){
                        return false;
                    }else{
                        //发起搜索
                        mSearchPresenter.doSearch(keyword);
                    }
                }
                return false;
            }
        });

        mHistoryDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除历史
                mSearchPresenter.deleteHistories();
            }
        });

        mRefreshContainer.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                //去加载更多内容
                if (mSearchPresenter != null) {
                    mSearchPresenter.loaderMore();
                }
            }
        });

        mSearchResultAdapter.setOnListItemClickListener(new LinearItemContentAdapter.OnListItemClickListener() {
            @Override
            public void onItemClick(IBaseInfo item) {
                //搜索列表的内容被点击了
                TicketUtil.toTicketPage(getContext(),item);
            }
        });
    }

    @Override
    protected void initView(View rootView) {
        mSearchList.setLayoutManager(new LinearLayoutManager(getContext()));
        mSearchResultAdapter = new LinearItemContentAdapter();
        mSearchList.setAdapter(mSearchResultAdapter);
        mRefreshContainer.setEnableLoadmore(true);
        mRefreshContainer.setEnableRefresh(false);
        mRefreshContainer.setEnableOverScroll(true);
    }

    @Override
    public void onHistoriesLoaded(Histories histories) {
        LogUtils.d(this,"histories---->" + histories);
        if (histories==null||histories.getHistories().size() == 0){
            mHistoriesContainer.setVisibility(View.GONE);
        }else{
            mHistoriesContainer.setVisibility(View.VISIBLE);
            mHistoriesView.setTextList(histories.getHistories());
        }
    }

    @Override
    public void onHistoriesDeleted() {
        //更新历史记录
        if (mSearchPresenter != null){
            mSearchPresenter.getHistories();
        }
    }

    @Override
    public void onSearchSuccess(@NotNull SearchResult result) {
        setUpstate(State.SUCCESS);
        //隐藏掉历史记录和推荐
        mRecommendContainer.setVisibility(View.GONE);
        mHistoriesContainer.setVisibility(View.GONE);
        //显示搜索界面
        mRefreshContainer.setVisibility(View.VISIBLE);
        try{
            mSearchResultAdapter.setData(result.getData().getTbk_dg_material_optional_response().getResult_list().getMap_data());
        }catch (Exception e){
            e.printStackTrace();
            //切换到搜索内容为空
            setUpstate(State.EMPTY);
        }
        mSearchList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = SizeUtils.dip2px(getContext(),1.5f);
                outRect.bottom = SizeUtils.dip2px(getContext(),1.5f);
            }
        });
    }

    @Override
    public void onMoreLoaded(@NotNull SearchResult result) {
        mRefreshContainer.finishLoadmore();
        //加载更多的结果
        //拿到结果，添加到适配器的尾部
        List<SearchResult.DataBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean> moreData = result.getData().getTbk_dg_material_optional_response().getResult_list().getMap_data();
        mSearchResultAdapter.addData(moreData);
        ToastUtil.showToast("加载到了" + moreData.size() + "条信息");
    }

    @Override
    public void onMoreLoaderError() {
        mRefreshContainer.finishLoadmore();
        ToastUtil.showToast("网络异常请稍后重试...");
    }

    @Override
    public void onMoreLoaderEmpty() {
        mRefreshContainer.finishLoadmore();
        ToastUtil.showToast("没有更多数据...");
    }

    @Override
    public void onRecommendWordsLoaded(@NotNull List<SearchRecommend.DataBean> recommendWords) {
        //LogUtils.d(this,"recommendWords size----->" + recommendWords.size());
        List<String> recommendKeywords = new ArrayList<>();
        for (SearchRecommend.DataBean recommendWord : recommendWords) {
            recommendKeywords.add(recommendWord.getKeyword());
        }
        if (recommendWords==null || recommendWords.size()==0){
            mRecommendContainer.setVisibility(View.GONE);
        }else{
            mRecommendContainer.setVisibility(View.VISIBLE);
            mRecommendView.setTextList(recommendKeywords);
        }
    }

    @Override
    public void onError() {
        setUpstate(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpstate(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpstate(State.EMPTY);
    }
}
