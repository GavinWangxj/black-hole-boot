package cn.cincout.tech.springbootmybatismd.service;

import cn.cincout.tech.springbootmybatismd.domain.Main;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhaoyu on 17-10-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Service
@Transactional(transactionManager = "mainTransactionManager")
public class MainServiceImpl implements MainService {
    @Override
    public int insert(Main main) {
        return 0;
    }
}
