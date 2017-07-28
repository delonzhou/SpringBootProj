package com.marving.boot.common;

/**
 * Created by mercop on 2017/7/27.
 */

public class ThreadHolder {

    private static ThreadLocal<String> localTid = new ThreadLocal<>();

    public static void putTenant(String tid){
        localTid.set(tid);
    }

    public static String getTenant(){
        if(localTid.get() == null)
            return null;
        else
            return localTid.get().toString();
    }
}
