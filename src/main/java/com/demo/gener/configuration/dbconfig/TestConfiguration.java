package com.demo.gener.configuration.dbconfig;

import com.demo.gener.configuration.DataBaseConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author mr.yang.demo
 * @date - 2018/09/19
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = {"com.demo.gener.mapper.test"}, sqlSessionFactoryRef = "testSqlSessionFactory")
public class TestConfiguration {
    /**
     * 配置数据源
     */
    @Bean(name = "testDataSource", initMethod = "init", destroyMethod = "close")
    @Qualifier("testDataSource")
    @ConfigurationProperties(prefix = "dataSource.test")
    public DataSource gettestDataSource(){
        return DataBaseConfiguration.createDefaultDruidDataSource();
    }

    /**
     * 配置SqlSessionFactory
     */
    @Bean(name = "testSqlSessionFactory")
    @Qualifier("testSqlSessionFactory")
    public SqlSessionFactory gettestSqlSessionFactory(){
        String configLocation="classpath:/conf/mybatis/configuration.xml";
        String mapperLocation="classpath:/conf/mybatis/test/*.xml";
        SqlSessionFactory sqlSessionFactory=DataBaseConfiguration.createDefaultSqlSessionFactory(gettestDataSource(),configLocation,mapperLocation);
        return sqlSessionFactory;
    }

    /**
     * 事物管理器配置
     */
    @Bean(name="testTransactionManager")
    @Qualifier("testTransactionManager")
    public PlatformTransactionManager testTransactionManager() {
         return new DataSourceTransactionManager(gettestDataSource());
    }
}
