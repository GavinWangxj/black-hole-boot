package cn.cincout.tech.springdatajpa.inf.repository.ex;

import cn.cincout.tech.springdatajpa.domain.ex.ExceptionRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhaoyu on 17-7-25.
 *
 * @author zhaoyu
 * @date 17-7-25
 * @sine 1.8
 */
public interface ExceptionRecordRepository extends JpaRepository<ExceptionRecord, Long> {
    @Query(value = "SELECT exceptionRecord FROM ExceptionRecord exceptionRecord WHERE exceptionRecord.exceptionMeta.applicationName = ?1")
    List<ExceptionRecord> findLastExceptionRecord(String applicationName, Pageable pageable);

    @Query(
            value = "SELECT timestamp FROM t_exception_record  ORDER BY timestamp ASC LIMIT 1",
            nativeQuery = true
    )
    String findFirstExceptionReportTimestamp();
}
