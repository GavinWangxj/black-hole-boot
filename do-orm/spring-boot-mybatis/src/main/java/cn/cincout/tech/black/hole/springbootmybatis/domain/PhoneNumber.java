package cn.cincout.tech.black.hole.springbootmybatis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-1
 * @sine 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"", ""})
public class PhoneNumber implements Serializable {
    private String number;
}
