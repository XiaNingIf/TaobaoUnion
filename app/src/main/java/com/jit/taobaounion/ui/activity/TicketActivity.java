package com.jit.taobaounion.ui.activity;

import android.media.Image;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jit.taobaounion.R;
import com.jit.taobaounion.base.BaseActivity;
import com.jit.taobaounion.model.domain.TicketResult;
import com.jit.taobaounion.presenter.ITicketPresenter;
import com.jit.taobaounion.ui.custom.LoadingView;
import com.jit.taobaounion.utils.PresenterManager;
import com.jit.taobaounion.utils.UrlUtils;
import com.jit.taobaounion.view.ITicketPagerCallback;

import butterknife.BindView;

public class TicketActivity extends BaseActivity implements ITicketPagerCallback {

    private ITicketPresenter mTicketPresenter;

    @BindView(R.id.ticket_cover)
    ImageView mCover;

    @BindView(R.id.ticket_code)
    EditText mTicketCode;

    @BindView(R.id.ticket_copy_or_open_btn)
    TextView mOpenOrCopyBtn;

    @BindView(R.id.ticket_back_press)
    ImageView backPress;

    @BindView(R.id.ticket_cover_loading)
    LoadingView mLoadingView;

    @BindView(R.id.ticket_load_retry)
    TextView retryLoadText;

    @Override
    protected void initPresenter() {
        mTicketPresenter = PresenterManager.getInstance().getTicketPresenter();
        mTicketPresenter.registerViewCallback(this);
    }

    @Override
    protected void release() {
        if (mTicketPresenter != null) {
            mTicketPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_ticket;
    }

    @Override
    public void onTicketLoaded(String cover, TicketResult result) {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
        }
        if (retryLoadText != null) {
            retryLoadText.setVisibility(View.GONE);
        }
        if (mCover != null && !TextUtils.isEmpty(cover)){
        //ViewGroup.LayoutParams layoutParams = mCover.getLayoutParams();
        //int width = layoutParams.width;
        //int height = layoutParams.height;
        //int targetSize = 400;
        //if ((width>height?width:height)/2%10!=0){
        //    targetSize = 400;
        //}else{
        //    targetSize = (width>height?width:height)/2;
        //}
            String coverPath = UrlUtils.getCoverPath(cover);
            Glide.with(this).load(coverPath).into(mCover);
        }
        if (result != null && result.getData().getTbk_tpwd_create_response() != null){
            mTicketCode.setText(result.getData().getTbk_tpwd_create_response().getData().getModel());
        }
    }

    @Override
    public void onError() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
        }
        if (retryLoadText != null) {
            retryLoadText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoading() {
        if (retryLoadText != null) {
            retryLoadText.setVisibility(View.GONE);
        }
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onEmpty() {

    }
}
