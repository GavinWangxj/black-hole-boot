package cn.cincout.tech.springbootmybatisjta.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by zhaoyu on 17-10-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
@MapperScan(
        basePackages = {"cn.cincout.tech.springbootmybatisjta.inf.mapper.main"},
        sqlSessionFactoryRef = "mainSqlSessionFactory"
)
public class MainMybatisConfig {

    @Autowired
    private MainDbProperties mainDbProperties;

    @Bean
    public DataSource mainDataSource() {
        MysqlXADataSource xaDataSource = new MysqlXADataSource();
        xaDataSource.setUrl(mainDbProperties.getUrl());
        xaDataSource.setUser(mainDbProperties.getUsername());
        xaDataSource.setPassword(mainDbProperties.getPassword());

        xaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        atomikosDataSourceBean.setUniqueResourceName("mybatis-jta1");
        return atomikosDataSourceBean;
    }

    @Bean
    public SqlSessionFactoryBean mainSqlSessionFactory(@Qualifier("mainDataSource") DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
       /* ClassPathResource classPathResource = new ClassPathResource("mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(classPathResource);

        ClassPathResource mapperResources = new ClassPathResource("inf/mapper/CustomerMapper.xml");
        sqlSessionFactoryBean.setMapperLocations(
            new Resource[] {mapperResources}
        );

        sqlSessionFactoryBean.setTypeAliasesPackage("cn.cincout.tech.black.hole.springmybatis.domain");*/
        return sqlSessionFactoryBean;
    }

    @Bean("mainSqlSessionTemplate")
    public SqlSessionTemplate mainSqlSessionTemplate(@Qualifier("mainSqlSessionFactory")
                                                                 SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
