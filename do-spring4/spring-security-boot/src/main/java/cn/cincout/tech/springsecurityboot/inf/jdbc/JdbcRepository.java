package cn.cincout.tech.springsecurityboot.inf.jdbc;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-1-7
 * @sine 1.0
 */
public interface JdbcRepository<T, TD> {

    T save(T obj);
    void delete(long id);

    T findOne(Long id);

}
