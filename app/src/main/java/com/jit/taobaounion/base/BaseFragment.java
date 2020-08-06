package com.jit.taobaounion.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jit.taobaounion.R;
import com.jit.taobaounion.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kotlin.reflect.KVariance;

public abstract class BaseFragment extends Fragment {

    private State currentState = State.NONE;
    private View mSuccessView;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;

    public enum State{
        NONE,LOADING,SUCCESS,ERROR,EMPTY
    }

    private Unbinder mBind;
    private FrameLayout mBaseContainer;

    @OnClick(R.id.network_error_tips)
    public void retry(){
        LogUtils.e(this,"on retry....");
        onRetryClick();
    }

    /**
     * 如果子类Fragment需要的时候进行覆盖操作
     */
    protected void onRetryClick(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater,container);
        mBaseContainer = rootView.findViewById(R.id.base_container);
        loadStatesView(inflater,container);
        mBind = ButterKnife.bind(this, rootView);
        initView(rootView);
        initPresenter();
        loadData();
        return rootView;
    }

    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_layout,container,false);
    }

    /**
     * 加载各种状态的View
     *
     * @param inflater
     * @param container
     */
    protected void loadStatesView(LayoutInflater inflater, ViewGroup container){
        //成功的View
        mSuccessView = loadSuccessView(inflater, container);
        mBaseContainer.addView(mSuccessView);
        //Loading的view
        mLoadingView = loadLoadingView(inflater, container);
        mBaseContainer.addView(mLoadingView);
        //错误页面
        mErrorView = loadErrorView(inflater, container);
        mBaseContainer.addView(mErrorView);
        //空页面
        mEmptyView = loadEmptyView(inflater, container);
        mBaseContainer.addView(mEmptyView);
        setUpstate(State.NONE);
    }

    protected View loadErrorView(LayoutInflater inflater, ViewGroup container){
        return inflater.inflate(R.layout.fragment_error,container,false);
    }

    protected View loadEmptyView(LayoutInflater inflater, ViewGroup container){
        return inflater.inflate(R.layout.fragment_empty,container,false);
    }

    /**
     * 子类通过这个方法更改界面状态
     * @param state
     */
    public void setUpstate(State state){
        this.currentState = state;
        mSuccessView.setVisibility(currentState==State.SUCCESS?View.VISIBLE:View.GONE);
        mLoadingView.setVisibility(currentState==State.LOADING?View.VISIBLE:View.GONE);
        mErrorView.setVisibility(currentState == State.ERROR?View.VISIBLE:View.GONE);
        mEmptyView.setVisibility(currentState == State.EMPTY?View.VISIBLE:View.GONE);
    }

    private View loadLoadingView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loading,container,false);
    }

    protected void initView(View rootView){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    protected void release(){
        //释放资源
    }

    protected void initPresenter() {
        //创建Presenter
    }

    protected void loadData() {
        //加载数据
    }

    protected View loadSuccessView(LayoutInflater inflater, ViewGroup container){
        int resId = getRootViewResId();
        return inflater.inflate(resId,container,false);
    }

    protected abstract int getRootViewResId();


}
