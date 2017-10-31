package cn.cincout.black.hole.dohbase.application;

import cn.cincout.black.hole.dohbase.domain.User;
import cn.cincout.black.hole.dohbase.inf.HBaseSupport;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by zhaoyu on 17-7-31.
 *
 * @author zhaoyu
 * @date 17-7-31
 * @sine 1.8
 */
@Service
public class UserServiceImpl extends HBaseSupport implements UserService {

    public UserServiceImpl() {
    }

    public UserServiceImpl(String zookeeperQuorum) {
        super(zookeeperQuorum);
    }

    @Override
    public void save(User user) {
        try (Table table = getConnection().getTable(TableName.valueOf("users"))) {

            Put put = new Put(Bytes.toBytes(user.getName()));
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(user.getName()));
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("email"), Bytes.toBytes(user.getEmail()));
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("password"), Bytes.toBytes(user.getPassword()));

            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public void delete(String name) {

    }
}
