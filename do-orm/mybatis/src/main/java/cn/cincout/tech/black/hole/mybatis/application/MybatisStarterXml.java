package cn.cincout.tech.black.hole.mybatis.application;

import cn.cincout.tech.black.hole.mybatis.domain.Customer;
import cn.cincout.tech.black.hole.mybatis.inf.mapper.CustomerMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-21
 * @sine 1.8
 */
public class MybatisStarterXml {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        SqlSessionFactory sessionFactory = null;
        SqlSession sqlSession = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sessionFactory.openSession();
            Customer customer = sqlSession.selectOne("cn.cincout.tech.black.hole.mybatis.inf.mapper.CustomerMapper.selectCustomer", 1);
            System.out.println(customer);

            CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
            System.out.println(customerMapper.selectCustomer(1));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
