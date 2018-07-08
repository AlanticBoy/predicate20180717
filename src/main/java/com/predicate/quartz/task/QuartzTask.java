package com.predicate.quartz.task;

import com.fusong.utils.ConnectionUtils;
import com.fusong.utils.RandomUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang.math.RandomUtils;
import org.testng.annotations.Test;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  12:42 2018/5/1
 * @ModefiedBy:
 */
public class QuartzTask {

    /*
        update(Connection conn, String sql, Object[] params)：被用来执行插入、更新或删除（DML）操作
          CloseQuietly:避免连接、声明或结果集为NULL的情况被关闭。
        */
    public void generateMess() throws PropertyVetoException, SQLException {
        QueryRunner runner = new QueryRunner(ConnectionUtils.getDataSource());
        String sql = "insert into temp(name) values(?)";
        String mess = RandomUtil.randomString(10);
        runner.update(sql, mess);
    }

    public void sayHello() {

    }


}
