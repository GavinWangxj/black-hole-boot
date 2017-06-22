package cn.cincout.tech.black.hole.springmybatis.inf.mapper;

import cn.cincout.tech.black.hole.springmybatis.domain.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-21
 * @sine 1.8
 */
@Mapper
public interface CustomerMapper {

    @Select("select * from Customer where name = #{name}")
    Customer findByName(@Param("name") String name);

    @Insert("INSERT INTO Customer(name, phoneNumber) VALUES(#{name}, #{phoneNumber})")
    int insert(@Param("name") String name, @Param("phoneNumber") String phoneNumber);



    @Results(
            id = "customerResultMap",
            value = {
                    @Result(property = "name", column = "name", javaType = java.lang.String.class)
            }
    )
    @Select("SELECT * FROM Customer")
    List<Customer> findAll();

}
