package cn.cincout.spring.cloud.consumerwithfeign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-5
 * @sine 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User implements Serializable {

    private Integer id;
    private String userName;
    private String email;

}
