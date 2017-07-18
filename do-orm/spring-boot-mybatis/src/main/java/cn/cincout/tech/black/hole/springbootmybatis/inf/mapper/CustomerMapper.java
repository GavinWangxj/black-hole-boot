package cn.cincout.tech.black.hole.springbootmybatis.inf.mapper;

import cn.cincout.tech.black.hole.springbootmybatis.domain.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-4-28
 * @sine 1.8
 */
@Mapper
public interface CustomerMapper {
    Customer saveCustomer(Customer customer);
    Customer findById(int customerId);
}
