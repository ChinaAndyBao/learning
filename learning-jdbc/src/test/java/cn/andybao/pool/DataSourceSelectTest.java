package cn.andybao.pool;

import cn.andybao.util.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import static cn.andybao.util.JdbcUtils.NUMBER;
import static cn.andybao.util.JdbcUtils.QUERY_ALL;

class DataSourceSelectTest {
    /**
     * 传统 无 prepareStatement 简单查询
     */
    @Test
    void traditionNoPreparedStatementSelectTest() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < NUMBER; i++) {
            JdbcUtils.traditionNoPreparedStatementSelect(QUERY_ALL);
        }
        // 数据库中2条记录数据, 查询1万次 耗时：15798ms~16701ms, 查询一万次时，实际关闭的速度太慢如果短时间内再启动查询，就各种异常,性能极差。
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 传统 有 prepareStatement 简单查询
     */
    @Test
    void traditionPreparedStatementSelectTest() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < NUMBER; i++) {
            JdbcUtils.traditionPreparedStatementSelect(QUERY_ALL);
        }
        // 查询1万次 耗时：16092ms~16444ms, 查询一万次时，实际关闭的速度太慢如果短时间内再启动查询，就各种异常,性能极差。
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    void druidSelectTest() throws SQLException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < NUMBER; i++) {
            Connection conn = JdbcUtils.ds.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(QUERY_ALL);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < columnCount; j++) {
                    builder.append(rs.getString(j + 1)).append("\t");
                }
                System.out.println(builder);
            }
            conn.close();
        }
        // 查询1万次耗时: 2080~2186ms
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    void hikariCPSelectTest() throws SQLException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < NUMBER; i++) {
            Connection conn = JdbcUtils.hs.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(QUERY_ALL);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < columnCount; j++) {
                    builder.append(rs.getString(j + 1)).append("\t");
                }
                System.out.println(builder);
            }
            conn.close();
        }
        // 查询1万次耗时: 1892~2101ms
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

}