package cn.cincout.tech.springmybatisjmsjta.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by zhaoyu on 17-10-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Data
@AllArgsConstructor
@Builder
public class Main {
    private Integer id;
    private String name;
    private Integer count;
}
