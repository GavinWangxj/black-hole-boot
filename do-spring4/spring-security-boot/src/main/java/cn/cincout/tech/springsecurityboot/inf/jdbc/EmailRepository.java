package cn.cincout.tech.springsecurityboot.inf.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-1-7
 * @sine 1.0
 */
@Repository
public class EmailRepository implements JdbcRepository<EmailAddress, Integer> {

    @Autowired
    private DataSource dataSource;

    @Override
    public EmailAddress save(EmailAddress obj) {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        String insertSql = " insert into t_email(name) values(?) ";

        PreparedStatement pstmt = null;
        try {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(insertSql);
            pstmt.setString(1, obj.getAddress());
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public EmailAddress findOne(Long id) {
        return null;
    }
}
