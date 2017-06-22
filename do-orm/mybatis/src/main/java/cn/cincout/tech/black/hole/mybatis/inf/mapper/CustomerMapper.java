package cn.cincout.tech.black.hole.mybatis.inf.mapper;

import cn.cincout.tech.black.hole.mybatis.domain.Customer;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-21
 * @sine 1.8
 */
public interface CustomerMapper {
    Customer selectCustomer(int id);
}
