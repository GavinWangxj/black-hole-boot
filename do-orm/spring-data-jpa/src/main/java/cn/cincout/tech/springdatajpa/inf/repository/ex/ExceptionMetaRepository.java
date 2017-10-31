package cn.cincout.tech.springdatajpa.inf.repository.ex;

import cn.cincout.tech.springdatajpa.domain.ex.ExceptionMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhaoyu on 17-9-27.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public interface ExceptionMetaRepository extends JpaRepository<ExceptionMeta, Long> {
    @Query(value = "SELECT exceptionMeta FROM ExceptionMeta exceptionMeta WHERE exceptionMeta.applicationName = ?1")
    List<ExceptionMeta> findByApplicationName(String applicationName);
}
