package cn.cincout.tech.black.hole.springmybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-25
 * @sine 1.8
 */
@Configuration
public class MybatisConfig {

    @Bean
    public PoolProperties poolProperties() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
        poolProperties.setUrl("jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=UTF-8");
        poolProperties.setUsername("root");
        poolProperties.setPassword("root");
        return poolProperties;
    }

    @Bean
    public DataSource dataSource(@Autowired PoolConfiguration poolConfiguration) {
        org.apache.tomcat.jdbc.pool.DataSource dataSource =
                new org.apache.tomcat.jdbc.pool.DataSource(poolConfiguration);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(@Autowired DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        ClassPathResource classPathResource = new ClassPathResource("mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(classPathResource);

        /*ClassPathResource mapperResources = new ClassPathResource("inf/mapper/CustomerMapper.xml");
        sqlSessionFactoryBean.setMapperLocations(
            new Resource[] {mapperResources}
        );
*/
        //sqlSessionFactoryBean.setTypeAliasesPackage("cn.cincout.tech.black.hole.springmybatis.domain");

        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("cn.cincout.tech.black.hole.springmybatis.inf.mapper");
        return mapperScannerConfigurer;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Autowired DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactoryBean) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean);
        return sqlSessionTemplate;
    }
}
