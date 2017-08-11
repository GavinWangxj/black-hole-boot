package cn.cincout.tech.springsecurityboot.inf.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-1-7
 * @sine 1.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmailAddress implements Serializable {
    private int id;
    private String address;
}
