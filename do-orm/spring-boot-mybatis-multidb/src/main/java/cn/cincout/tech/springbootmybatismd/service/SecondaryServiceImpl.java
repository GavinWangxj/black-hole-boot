package cn.cincout.tech.springbootmybatismd.service;

import cn.cincout.tech.springbootmybatismd.domain.Secondary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by zhaoyu on 17-10-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Service
@Transactional(transactionManager = "secondaryTransactionManager")
public class SecondaryServiceImpl implements SecondaryService {
    @Override
    public int insert(Secondary main) {
        return 0;
    }

    interface HelloService {
        void sayHello(String name);
    }

    @Resource
    private HelloService helloService;

    @PostConstruct
    public void init() {
        helloService.sayHello("zhangzhaoyu");
    }

    @Component
    static class HelloServiceImpl implements HelloService {
        @Override
        public void sayHello(String name) {
            System.out.println("hello " + name);
        }
    }
}
