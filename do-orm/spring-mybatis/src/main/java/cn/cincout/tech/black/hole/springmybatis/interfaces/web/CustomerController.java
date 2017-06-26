package cn.cincout.tech.black.hole.springmybatis.interfaces.web;

import cn.cincout.tech.black.hole.springmybatis.domain.Customer;
import cn.cincout.tech.black.hole.springmybatis.inf.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-25
 * @sine 1.8
 */
@RestController
public class CustomerController {

    private final static Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Customer findById(@PathVariable("id") int id) {
        LOG.debug("customer id is {}", id);
        Customer customer = customerMapper.findById(id);
        LOG.debug("customer is {}.", customer.toString());
        return customerMapper.findById(id);
    }


}
