package com.jit.taobaounion.utils;

public class UrlUtils {

    public static String createHomePagerUrl(int materialId, int page){
        return "discovery/" + materialId +"/" + page ;
    }
}
