package com.jit.taobaounion.view;

import com.jit.taobaounion.base.IBaseCallback;
import com.jit.taobaounion.model.domain.TicketResult;

public interface ITicketPagerCallback extends IBaseCallback {

    /**
     * 淘口令加载
     *
     * @param cover
     * @param result
     */
    void onTicketLoaded(String cover, TicketResult result);

}
