package cn.cincout.tech.springbootmybatismd.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by zhaoyu on 17-10-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "mainDataSource")
    @Qualifier("mainDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.main")
    @Primary
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }


}
