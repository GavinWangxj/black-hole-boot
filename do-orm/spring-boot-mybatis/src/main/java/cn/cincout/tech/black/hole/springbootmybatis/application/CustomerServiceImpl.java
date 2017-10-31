package cn.cincout.tech.black.hole.springbootmybatis.application;

import cn.cincout.tech.black.hole.springbootmybatis.domain.Customer;
import cn.cincout.tech.black.hole.springbootmybatis.inf.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 17-10-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void insert() throws SQLException {
        //customerMapper.insert(Customer.builder().name("namex").phonenumber("18511929331").build());
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                throw new SQLException("i = 5 throw error.");
            }
            customerMapper.insert(Customer.builder().name("name" + i).phonenumber("1851192981" + i).build());
        }
    }
}
