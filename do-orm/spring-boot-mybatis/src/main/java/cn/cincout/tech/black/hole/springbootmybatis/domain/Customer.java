package cn.cincout.tech.black.hole.springbootmybatis.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private Integer id;

    private String name;

    private String phonenumber;
}