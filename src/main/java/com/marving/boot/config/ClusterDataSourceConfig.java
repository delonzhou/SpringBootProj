package com.marving.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author chenssy
 * @date 2017/6/3
 * @since v1.0.0
 */
@Configuration
@MapperScan(basePackages = "com.marving.boot.dao.cluster",
        sqlSessionFactoryRef   ="clusterSqlSessionFactory")
public class ClusterDataSourceConfig {

    @Value("${datasource.cluster.mapperLocations}")
    private String mapperLocations;

    @Value("${datasource.cluster.url}")
    private String url;

    @Value("${datasource.cluster.userName}")
    private String userName;

    @Value("${datasource.cluster.password}")
    private String password;

    @Value("${datasource.cluster.driverClass}")
    private String driverClass;

    // 数据源
    @Bean("clusterDataSource")
    public DataSource clusterDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);

        return dataSource;
    }

    // 事务
    @Bean("clusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager(){
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean("clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        return bean.getObject();
    }
}
