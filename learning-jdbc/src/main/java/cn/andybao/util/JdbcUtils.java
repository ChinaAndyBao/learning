package cn.andybao.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Slf4j
public class JdbcUtils {
    // com.mysql.cj.jdbc.Driver com.mysql.jdbc.Driver
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://192.168.11.200:3306/va_demo?useUnicode=true&characterEncoding=utf8";
    public static final String USER = "wjl";
    public static final String PASSWORD = "&kK^v#e6HhBx";

    // SQL
    public static final String QUERY_ALL = "select * from bsc_dict_info_test";
    public static final String ADD = "insert into bsc_dict_info_test values(NULL,'aaaaatest', '担保人/被担保人与上市公司关联关系', '999', '无关联关系', '0', 190, ?),(NULL,'bbbbbtest', '担保人/被担保人与上市公司关联关系', '999', '无关联关系', '0', 190, ?);";
    public static final Integer NUMBER = 1000;

    public static HikariDataSource hs;
    public static DruidDataSource ds;

    static {
        try {
            InputStream hikariIn = JdbcUtils.class.getClassLoader().getResourceAsStream("hikari.properties");
            Properties hikariProperties = new Properties();
            hikariProperties.load(hikariIn);
            HikariConfig config = new HikariConfig(hikariProperties);
            hs = new HikariDataSource(config);
        } catch (Exception e) {
            log.error("Hikari数据源 初始化 异常 hs:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    static {
        Properties druidProperties = new Properties();
        try {
            InputStream druidIn = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            druidProperties.load(druidIn);
            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(druidProperties);
        } catch (Exception e) {
            log.error("Druid数据源 初始化 异常 ds:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 传统 无 prepareStatement 简单查询
     */
    public static void traditionNoPreparedStatementSelect(String sql) {
        try (Connection conn = JdbcUtils.getConnectionUtil(true);
             Statement statement = conn.createStatement();
             // 无准备
             ResultSet rs = statement.executeQuery(sql);
        ) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                StringBuilder builder = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    builder.append(rs.getString(i)).append("\t");
                }
                System.out.println(builder);
            }
        } catch (SQLException e) {
            log.error("传统 无 prepareStatement 简单查询 异常 message:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 传统 有 prepareStatement 简单查询
     */
    public static void traditionPreparedStatementSelect(String sql) {
        try (Connection conn = JdbcUtils.getConnectionUtil(true);
             // 有准备
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery();
        ) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                StringBuilder builder = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    builder.append(rs.getString(i)).append("\t");
                }
                System.out.println(builder);
            }

        } catch (SQLException e) {
            log.error("传统 有 prepareStatement 简单查询 异常 message:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 传统 无 prepareStatement 多次 单行插入
     */
    public static void traditionNoPreparedStatementMultiInsert(String sql, Integer number) {
        try (Connection conn = JdbcUtils.getConnectionUtil(true);
             Statement statement = conn.createStatement();
        ) {
            for (int i = 0; i < number; i++) {
                String target = sql.replaceAll("\\?", "'" + i + "'");
                // 无准备
                statement.executeUpdate(target);
            }
        } catch (SQLException e) {
            log.error("传统 无 prepareStatement 多次 单行插入 异常 message:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 传统 有 prepareStatement 多次 单行插入
     */
    public static void traditionPreparedStatementMultiInsert(String sql, Integer number) {
        try (Connection conn = JdbcUtils.getConnectionUtil(true);
             // 有准备
             PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            for (int i = 0; i < number; i++) {
                statement.setString(1, "'" + i + "'");
                statement.setString(2, "'" + i + "'");
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("传统 有 prepareStatement 多次 单行插入 异常 message:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 封装获取连接对象的方法
     */
    public static Connection getConnectionUtil(boolean autoCommit) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            log.error("获取连接对象 异常 :{}", e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 事务提交
     */
    public static void commitUtil(Connection conn) {
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                log.error("事务提交 异常 :{}", e.getMessage());
            }
        }
    }

    /**
     * 事务回滚
     */
    public static void rollbackUtil(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                log.error("事务回滚 异常 :{}", e.getMessage());
            }
        }
    }

    /**
     * 关闭资源
     */
    public static void closeUtil(ResultSet rs, Statement stm, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("事务回滚 异常 rs:{}", e.getMessage());
            }
        }
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException e) {
                log.error("事务回滚 异常 stm:{}", e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("事务回滚 异常 conn:{}", e.getMessage());
            }
        }
    }
}