package cn.cincout.black.hole.doredis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-3
 * @sine 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "name", "email"})
public class User implements Serializable {

    private int id;
    private String name;
    private String email;

}
