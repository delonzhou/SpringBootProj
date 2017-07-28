package com.marving.boot.config;

import com.marving.boot.common.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by mercop on 2017/7/27.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.marving.boot.dao.saas",
        sqlSessionFactoryRef   ="mTenantSqlSessionFactory")
public class MutiTenantDataSourceConfig {

    @Value("${datasource.saas.mapperLocations}")
    private String mapperLocations;

    @Value("${datasource.saas.url}")
    private String url;

    @Value("${datasource.saas.userName}")
    private String userName;

    @Value("${datasource.saas.password}")
    private String password;

    @Value("${datasource.saas.driverClass}")
    private String driverClass;


    private static String prefixOfDataBase= "cloudbu_";
    private static HashSet<String> dbSet = new HashSet<>();

    private static String DEFAULT_DB = "cloudbu_m";

    private Class<? extends DataSource> datasourceType = com.alibaba.druid.pool.DruidDataSource.class;

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(datasourceType).build();

    }

    @Bean("clusterDataSource")
    @ConfigurationProperties(prefix = "datasource.cluster")
    public DataSource clusterDataSource() {
        return DataSourceBuilder.create().type(datasourceType).build();
    }

    @Bean("saasDataSource")
    @ConfigurationProperties(prefix = "datasource.saas")
    public DataSource saasDataSource() {
        return DataSourceBuilder.create().type(datasourceType).build();
    }

    @Bean(name = "dynamicDataSource")

    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                               @Qualifier("clusterDataSource") DataSource clusterDataSource,
                                               @Qualifier("saasDataSource") DataSource saasDataSource){
        Map<Object,Object> map = new HashMap<>();

        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        map.put("cloudbu_m",masterDataSource);
        map.put("cloudbu_1000",saasDataSource);

        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);

        return dynamicDataSource;
    }



    @Bean("mTenantTransactionManager")
    public DataSourceTransactionManager mTenantTransactionManager(AbstractRoutingDataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }


    @Bean("mTenantSqlSessionFactory")
    public SqlSessionFactory mTenantSqlSessionFactory(AbstractRoutingDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        return bean.getObject();
    }

    /*@Bean
    public SqlSessionFactory sqlSessionFactorys(AbstractRoutingDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);

        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }

    @Bean
    DataSourceTransactionManager dataSourceTransactionManager(AbstractRoutingDataSource dynamicDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dynamicDataSource);
        return dataSourceTransactionManager;
    }*/
}
