package cn.cincout.tech.springbootmybatismd.inf.mapper.secondary;

import cn.cincout.tech.springbootmybatismd.domain.Secondary;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * Created by zhaoyu on 17-10-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Mapper
public interface SecondaryMapper {
    @Insert({
            "insert into Secondary (name, count)",
            "values (#{name,jdbcType=VARCHAR}, #{count,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Secondary main);

    @Select({
            "select",
            "id, name, count",
            "from Secondary",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType=JdbcType.VARCHAR)
    })
    Secondary selectByPrimaryKey(Integer id);
}
