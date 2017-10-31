package cn.cincout.tech.black.hole.springbootmybatis.inf.mapper;

import cn.cincout.tech.black.hole.springbootmybatis.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 17-10-9.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void saveCustomer() throws Exception {
        Customer customer = create();
        int id = customerMapper.insert(customer);
        System.out.println(id);
        System.out.println(customer);
    }

    @Test
    public void insertSelective() {
        int id = customerMapper.insertSelective(create());
        System.out.println(id);
    }

    @Test
    public void insertBatch() {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customerList.add(Customer.builder().name("name" + i).phonenumber("1851192981" + i).build());
        }

        System.out.println(customerMapper.insertBatch(customerList));
        customerList.forEach(customer -> System.out.println(customer));
    }

    @Test
    public void findById() throws Exception {
        Customer customer = customerMapper.selectByPrimaryKey(1);
        System.out.println(customer);
    }

    private Customer create() {
        return Customer.builder()
                .name("zhangzhaoyux")
                .phonenumber("18511929812")
                .build();
    }

}