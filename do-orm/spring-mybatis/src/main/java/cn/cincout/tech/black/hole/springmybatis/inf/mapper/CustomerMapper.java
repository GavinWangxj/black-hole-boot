package cn.cincout.tech.black.hole.springmybatis.inf.mapper;

import cn.cincout.tech.black.hole.springmybatis.domain.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-21
 * @sine 1.8
 */
public interface CustomerMapper {
    Customer saveCustomer(Customer customer);
    Customer findById(int customerId);
}
