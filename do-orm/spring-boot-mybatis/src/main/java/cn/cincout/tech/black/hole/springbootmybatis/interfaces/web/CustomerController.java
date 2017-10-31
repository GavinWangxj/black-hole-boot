package cn.cincout.tech.black.hole.springbootmybatis.interfaces.web;

import cn.cincout.tech.black.hole.springbootmybatis.domain.Customer;
import cn.cincout.tech.black.hole.springbootmybatis.inf.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-30
 * @sine 1.8
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping(value = "/customer")
    public String save(@RequestBody Customer customer) {
        customerMapper.insert(customer);
        return "success";
    }

    @GetMapping(value = "/customer/{id}")
    public Customer find(@PathVariable("id") int id) {
        return customerMapper.selectByPrimaryKey(id);
    }

}
