package cn.cincout.tech.springmybatisjmsjta.inf.mapper.main;

import cn.cincout.tech.springmybatisjmsjta.domain.Main;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * Created by zhaoyu on 17-10-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Mapper
public interface MainMapper {
    @Insert({
            "insert into Main (name, count)",
            "values (#{name,jdbcType=VARCHAR}, #{count,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Main main);

    @Select({
            "select",
            "id, name, count",
            "from Main",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType=JdbcType.VARCHAR)
    })
    Main selectByPrimaryKey(Integer id);
}
