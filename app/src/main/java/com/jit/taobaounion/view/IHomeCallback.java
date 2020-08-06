package com.jit.taobaounion.view;

import com.jit.taobaounion.base.IBaseCallback;
import com.jit.taobaounion.model.domain.Categories;

public interface IHomeCallback extends IBaseCallback {
    void onCategoriesLoaded(Categories categories);

}
