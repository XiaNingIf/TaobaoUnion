package com.jit.taobaounion.ui.fragment;

import android.view.View;

import com.jit.taobaounion.R;
import com.jit.taobaounion.base.BaseFragment;

public class SearchFragment extends BaseFragment {
    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView(View rootView) {
        setUpstate(State.SUCCESS);
    }
}
