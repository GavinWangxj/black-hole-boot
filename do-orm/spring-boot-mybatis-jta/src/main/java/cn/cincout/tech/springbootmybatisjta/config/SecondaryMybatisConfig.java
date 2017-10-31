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
        basePackages = {"cn.cincout.tech.springbootmybatisjta.inf.mapper.secondary"},
        sqlSessionFactoryRef = "secondarySqlSessionFactory"
)
public class SecondaryMybatisConfig {

    @Autowired
    private SecondaryDbProperties secondaryDbProperties;

    @Bean
    public DataSource secondaryDataSource() {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setUrl(secondaryDbProperties.getUrl());
        mysqlXADataSource.setUser(secondaryDbProperties.getUsername());
        mysqlXADataSource.setPassword(secondaryDbProperties.getPassword());

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("mybatis-jta2");
        return atomikosDataSourceBean;
    }

    @Bean
    public SqlSessionFactoryBean secondarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) {
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

    @Bean("secondarySqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(@Qualifier("secondarySqlSessionFactory")
                                                             SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
