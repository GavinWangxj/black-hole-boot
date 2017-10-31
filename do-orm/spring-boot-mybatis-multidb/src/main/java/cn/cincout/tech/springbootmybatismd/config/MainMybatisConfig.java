package cn.cincout.tech.springbootmybatismd.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by zhaoyu on 17-10-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
@MapperScan(
        basePackages = {"cn.cincout.tech.springbootmybatismd.inf.mapper.main"},
        sqlSessionFactoryRef = "mainSqlSessionFactory"
)
public class MainMybatisConfig {

    @Bean
    public SqlSessionFactoryBean mainSqlSessionFactory(@Qualifier("mainDataSource") DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //ClassPathResource classPathResource = new ClassPathResource("mybatis-config.xml");
        //sqlSessionFactoryBean.setConfigLocation(classPathResource);

        /*ClassPathResource mapperResources = new ClassPathResource("inf/mapper/CustomerMapper.xml");
        sqlSessionFactoryBean.setMapperLocations(
            new Resource[] {mapperResources}
        );
*/
        //sqlSessionFactoryBean.setTypeAliasesPackage("cn.cincout.tech.black.hole.springmybatis.domain");
        return sqlSessionFactoryBean;
    }

    @Bean(name = "mainTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("mainDataSource") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager =
                new DataSourceTransactionManager(dataSource);
        dataSourceTransactionManager.setDefaultTimeout(10000);
        return dataSourceTransactionManager;
    }
}
