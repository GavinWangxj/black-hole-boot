package cn.cincout.tech.springdatajpa.inf.repository.ex;

import cn.cincout.tech.springdatajpa.domain.ex.ExceptionMessage;
import cn.cincout.tech.springdatajpa.domain.ex.ExceptionMeta;
import cn.cincout.tech.springdatajpa.domain.ex.ExceptionRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 17-9-28.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExceptionRecordRepositoryTest {

    @Autowired
    private ExceptionMetaRepository exceptionMetaRepository;

    @Autowired
    private ExceptionRecordRepository exceptionRecordRepository;


    @Test
    @Rollback(false)
    public void testSaveExceptionMeta() {
        ExceptionMeta exceptionMeta = ExceptionMeta.builder()
                .exceptionType("runtimeException")
                .applicationName("tx-web")
                .methodName("apistat")
                .build();

        ExceptionRecord exceptionRecord = ExceptionRecord.builder()
                .exceptionMessage(new ExceptionMessage("error message"))
                .exceptionMeta(exceptionMeta)
                .count(1)
                .startTime(System.currentTimeMillis())
                .timestamp(System.currentTimeMillis())
                .txIds("txid")
                .build();

        exceptionRecordRepository.save(exceptionRecord);
    }
}