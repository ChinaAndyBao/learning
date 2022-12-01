package cn.andybao.pool;

import cn.andybao.util.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static cn.andybao.util.JdbcUtils.ADD;
import static cn.andybao.util.JdbcUtils.NUMBER;

class DataSourceInsertTest {
    @Test
    void replaceAll() {
        // 入参是正则表达时，特殊字符需要进行转义
        String target = ADD.replaceAll("\\?", "3");
        System.out.println(target);
    }

    /**
     * 传统 无 prepareStatement 多次 单行插入
     */
    @Test
    void traditionNoPreparedStatementInsertTest() {
        long start = System.currentTimeMillis();
        JdbcUtils.traditionNoPreparedStatementMultiInsert(ADD, NUMBER);
        // 插入1000次耗时：耗时: 3907 ~ 4638ms
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 传统 有 prepareStatement 多次 单行插入
     */
    @Test
    void traditionPreparedStatementInsertTest() {
        long start = System.currentTimeMillis();
        JdbcUtils.traditionPreparedStatementMultiInsert(ADD, NUMBER);
        // 插入1000次耗时：耗时: 3822 ~ 4145ms
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    void duridInsertTest() throws SQLException {
        long start = System.currentTimeMillis();
        Connection conn = JdbcUtils.ds.getConnection();
        // 开启事务
        conn.setAutoCommit(false);
        PreparedStatement statement = conn.prepareStatement(ADD);
        for (int i = 0; i < NUMBER; i++) {
            statement.setString(1, String.valueOf(i));
            statement.setString(2, String.valueOf(i));
            // 执行SQL语句
            statement.executeUpdate();
        }
        // 提交事务
        conn.commit();
        conn.close();
        // 插入1000次耗时：耗时: 535 ~ 543ms
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    void hikariCPInsertTest() throws SQLException {
        long start = System.currentTimeMillis();
        Connection conn = JdbcUtils.hs.getConnection();
        // 开启事务
        conn.setAutoCommit(false);
        PreparedStatement statement = conn.prepareStatement(ADD);
        for (int i = 0; i < NUMBER; i++) {
            statement.setString(1, String.valueOf(i));
            statement.setString(2, String.valueOf(i));
            // 执行SQL语句
            statement.executeUpdate();
        }
        // 提交事务
        conn.commit();
        conn.close();
        // 插入1000次耗时：耗时: 449 ~ 479ms
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    void druidInsertBatchTest() throws SQLException {
        long start = System.currentTimeMillis();
        Connection conn = JdbcUtils.ds.getConnection();
        // 开启事务
        conn.setAutoCommit(false);
        Statement statement = conn.createStatement();
        for (int i = 0; i < NUMBER; i++) {
            statement.addBatch(ADD.replaceAll("\\?", "'" + i + "'"));
        }
        // 执行SQL语句
        statement.executeBatch();
        // 提交事务
        conn.commit();
        conn.close();
        // 批处理 插入1000次耗时：耗时: 629 ~ 861ms, 性能稍差于预编译的情况
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    void hikariCPInsertBatchTest() throws SQLException {
        long start = System.currentTimeMillis();
        Connection conn = JdbcUtils.hs.getConnection();
        // 开启事务
        conn.setAutoCommit(false);
        Statement statement = conn.createStatement();
        for (int i = 0; i < NUMBER; i++) {
            statement.addBatch(ADD.replaceAll("\\?", "'" + i + "'"));
        }
        // 执行SQL语句
        statement.executeBatch();
        // 提交事务
        conn.commit();
        conn.close();
        // 批处理 插入1000次耗时：耗时: 524 ~ 578ms, 性能稍差于预编译的情况
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

}