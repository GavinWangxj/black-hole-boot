package cn.cincout.tech.springbatch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by zhaoyu on 17-8-28.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person implements Serializable {
    private String lastName;
    private String firstName;
}
