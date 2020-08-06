package com.jit.taobaounion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jit.taobaounion.R;
import com.jit.taobaounion.base.BaseFragment;

public class SelectedFragment extends BaseFragment {

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_selected;
    }

    @Override
    protected void initView(View rootView) {
        setUpstate(State.SUCCESS);
    }
}
