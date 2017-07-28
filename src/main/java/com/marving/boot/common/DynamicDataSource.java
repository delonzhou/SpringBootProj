package com.marving.boot.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashSet;

/**
 * Created by mercop on 2017/7/27.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static String prefixOfDataBase= "cloudbu_";
    private static HashSet<String> dbSet = new HashSet<>();

    private static String DEFAULT_DB = "cloudbu_m";

    private static void init(){
        dbSet.add("cloudbu_m");
        dbSet.add("cloudbu_s");
        dbSet.add("cloudbu_1000");
    }

    {
        init();
    }

    public static HashSet<String> getDbSet() {
        return dbSet;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dbUrl = prefixOfDataBase + ThreadHolder.getTenant();
        if(dbSet.isEmpty()){
            init();
        }
        if(dbSet.contains(dbUrl))
            return dbUrl;
        else
            return DEFAULT_DB;
    }
}