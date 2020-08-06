package com.jit.taobaounion.base;

import com.jit.taobaounion.view.IHomeCallback;

public interface IBasePresenter<T> {
    void registerViewCallback(T callback);

    void unregisterViewCallback(T callback);
}
