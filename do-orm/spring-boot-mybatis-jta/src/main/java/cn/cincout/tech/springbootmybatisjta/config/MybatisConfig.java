package cn.cincout.tech.springbootmybatisjta.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * Created by zhaoyu on 17-10-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
//@Configuration
public class MybatisConfig {

    @Bean
    public UserTransaction userTransaction() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager atomikosTransactionManager() throws SystemException {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setTransactionTimeout(10000);
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    // the name should be transactionManager
    @Bean(name = "transactionManager")
    public JtaTransactionManager transactionManager(@Qualifier("userTransaction") UserTransaction userTransaction,
                                                    @Qualifier("atomikosTransactionManager") TransactionManager atomikosTransactionManager) {
        return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
    }
}
