package cn.cincout.tech.springsecurityboot.application.service;

import java.util.List;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-30
 * @sine 1.0
 */
public interface AbstractEntityService<T, TD> {
    T save(T entity);
    T update(T entity);
    void delete(TD id);

    T findById(TD id);
    List<T> findAll();

}
