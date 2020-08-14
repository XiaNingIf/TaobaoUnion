package com.jit.taobaounion.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class UrlUtils {

    @NotNull
    @Contract(pure = true)
    public static String createHomePagerUrl(int materialId, int page){
        return "discovery/" + materialId +"/" + page ;
    }

    @NotNull
    @Contract(pure = true)
    public static String getCoverPath(String pict_url, int size) {
        return "https:" + pict_url + "_" + size + "x" + size + ".jpg";
    }

    @NotNull
    @Contract(pure = true)
    public static String getCoverPath(@NotNull String pict_url) {
        if(pict_url.startsWith("http")||pict_url.startsWith("https")){
            return pict_url;
        }else{
            return "https:" + pict_url;
        }
    }

    @NotNull
    public static String getTicketUrl(@NotNull String url) {
        if(url.startsWith("http")||url.startsWith("https")){
            return url;
        }else{
            return "https:" + url;
        }
    }

    @NotNull
    @Contract(pure = true)
    public static String getSelectedPageContentUrl(int categoryId) {
        return "recommend/" + categoryId;
    }

    @NotNull
    @Contract(pure = true)
    public static String getOnSellPageUrl(int currentPage) {
        return "onSell/" + currentPage;
    }
}
