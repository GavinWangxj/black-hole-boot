package cn.cincout.black.hole.dohbase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by zhaoyu on 17-7-31.
 *
 * @author zhaoyu
 * @date 17-7-31
 * @sine 1.8
 */
@Data
@AllArgsConstructor
public class User {

    private String name;
    private String email;
    private String password;

}
