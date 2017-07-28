package com.marving.boot.config;

import javax.sql.DataSource;

/**
 * Created by mercop on 2017/7/27.
 */

public class AbstractDataSourceConfig {

    protected DataSource getDataSource(String url, String username,String password,String driverClass){
        return null;
    }
}
