package cn.cincout.tech.springmulticache.domain;

import lombok.*;

import java.io.Serializable;

/**
 * Created by zhaoyu on 17-10-27.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String name;
}
