package com.jit.taobaounion.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.jit.taobaounion.base.BaseApplication;
import com.jit.taobaounion.model.domain.IBaseInfo;
import com.jit.taobaounion.presenter.ITicketPresenter;
import com.jit.taobaounion.ui.activity.TicketActivity;

public class TicketUtil {
    public static void toTicketPage(Context context,IBaseInfo baseInfo){
        String title = baseInfo.getTitle();
        //详情的URL
        //String url = item.getClick_url();
        String url = baseInfo.getUrl();
        if (TextUtils.isEmpty(url)){
            url = baseInfo.getUrl();
        }
        String cover = baseInfo.getCover();
        //拿到tickerPresenter去加载数据
        ITicketPresenter ticketPresenter = PresenterManager.getInstance().getTicketPresenter();
        ticketPresenter.getTicket(title,url,cover);
        context.startActivity(new Intent(context, TicketActivity.class));
    }
}
