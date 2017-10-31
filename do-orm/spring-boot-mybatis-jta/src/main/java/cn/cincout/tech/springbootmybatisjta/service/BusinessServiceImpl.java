package cn.cincout.tech.springbootmybatisjta.service;

import cn.cincout.tech.springbootmybatisjta.domain.Main;
import cn.cincout.tech.springbootmybatisjta.domain.Secondary;
import cn.cincout.tech.springbootmybatisjta.inf.mapper.main.MainMapper;
import cn.cincout.tech.springbootmybatisjta.inf.mapper.secondary.SecondaryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by zhaoyu on 17-10-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */

@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
    @Resource
    private MainMapper mainMapper;
    @Resource
    private SecondaryMapper secondaryMapper;

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void insert() throws SQLException {
        Main main = Main.builder().name("main").count(10).build();
        mainMapper.insert(main);
        System.out.println(main);

        Secondary secondary = Secondary.builder().name("secondary").count(10).build();
        secondaryMapper.insert(secondary);
        System.out.println(secondary);
        throw new SQLException("error");
    }


}
