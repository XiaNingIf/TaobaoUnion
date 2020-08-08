package com.jit.taobaounion.presenter;

import com.jit.taobaounion.base.IBasePresenter;
import com.jit.taobaounion.view.ITicketPagerCallback;

public interface ITicketPresenter extends IBasePresenter<ITicketPagerCallback> {

    /**
     * 获取淘口令
     * @param title
     * @param url
     * @param cover
     */
    void getTicket(String title, String url,String cover);

}
