package cn.cincout.tech.springbootmybatismd.in.mapper;

import cn.cincout.tech.springbootmybatismd.domain.Main;
import cn.cincout.tech.springbootmybatismd.domain.Secondary;
import cn.cincout.tech.springbootmybatismd.inf.mapper.main.MainMapper;
import cn.cincout.tech.springbootmybatismd.inf.mapper.secondary.SecondaryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.sql.SQLException;

/**
 * Created by zhaoyu on 17-10-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {
    @Resource
    private MainMapper mainMapper;
    @Resource
    private SecondaryMapper secondaryMapper;

    @Test
    public void testInsert() throws SQLException {
        Main main = Main.builder().name("main").count(103).build();
        mainMapper.insert(main);
        System.out.println(main);
        Secondary secondary = Secondary.builder().name("secondary").count(102).build();
        secondaryMapper.insert(secondary);
        System.out.println(secondary);
        throw new SQLException("exception");
    }

    @Test
    public void testSelect() {
        Main main = mainMapper.selectByPrimaryKey(1);
        System.out.println(main);

        Secondary secondary = secondaryMapper.selectByPrimaryKey(1);
        System.out.println(secondary);
    }
}