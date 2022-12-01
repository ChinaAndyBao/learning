package cn.andybao.jdbc;

import cn.andybao.util.JdbcUtils;
import com.mysql.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

/**
 * JDBC(Java DataBase Connectivity)
 * <pre>
 * JDBC的由来
 * sun公司定义了一套操纵数据库的接口规范(JDBC),具体的实现由不同的数据库服务厂商;
 * 一般程序访问一些硬件资源都需要安装驱动,比如我们使用声卡\网卡 就需要安装声卡驱动和网卡驱动,
 * 如果我们使用U盘,那么就需要安装U盘相关的驱动;
 * 同样的道理,如果我们使用java访问数据库,也需要安装相关的驱动包;
 * 数据库运行在不同的操作系统下,比如windows,linux,unix,macos等等,同时数据库又有不同的厂家提供,
 * 比如mysql,oracle,sqlserver等,
 * 如果对于程序员来说,针对不同的数据库在不同的操作系统下,安装不同的驱动软件,那么成本会很高;
 * 所以,sun公司制定了一套规则,这套规则就是用来java连接数据库的,
 * 然后不同的厂商自己实现定义个规则即可,对于程序员只了解规则核心内容,即可对不同的数据库进行操作,这套规则叫做JDBC;
 *
 * JDBC的好处
 * 1. 开发者只需会调用JDBC接口中的方法即可，使用简单；
 * 2. 底层实现交给具体的**数据库厂商**来完成，与开发者解耦；
 * 3. 对于不同的数据库，开发者使用同一套Java代码，进行少量的修改就可以访问其他JDBC支持的数据库（移植性好）；
 *
 * JDBC会用到的包
 * 1. java.sql：JDBC访问数据库的基础包，在JavaSE中的包。如：{@link Connection}
 * 2. javax.sql： JDBC访问数据库的扩展包(x表示extension,扩展)
 * 3. 数据库的驱动，各大数据库厂商来实现。如：MySQL的驱动：{@link Driver}
 *
 * JDBC四个核心对象
 * 这几个类都是在java.sql包中(1类3接口)
 * 1. {@link DriverManager}(类):  数据库驱动管理类。这个类的作用：1）注册驱动; 2)创建java代码和数据库之间的连接，即获取Connection接口;
 * 2. {@link Connection}(接口): 是一个接口, 建立数据库连接的一个接口。作用：建立数据库和java代码之间的连接。表示与数据库创建的连接
 * 3. {@link Statement}(接口)、{@link PreparedStatement}(接口) (解决安全隐患问题，比如sql注入的问题)： 数据库操作，向数据库发送sql语句。执行SQL语句的对象
 * 4. {@link ResultSet}(接口): 结果集或一张虚拟表。 Statement 发送sql语句，得到的结果 封装在 ResultSet 中。
 *
 * JDBC访问数据库的步骤
 * 1. 由{@link DriverManager}注册驱动程序；
 * 2. 创建和数据库的连接对象{@link Connection}；
 * 3. 由客户端发送SQL语句给服务器执行，SQL语句封装成{@link Statement}对象；
 * 4. 查询到的结果集封装成{@link ResultSet}对象；
 * 5. 在客户端可以从{@link ResultSet}中取出数据，处理结果集；
 * 6. 释放资源，关闭连接对象；
 * </pre>
 */
class JDBCTest {
    /* JDBC注册驱动 */

    /**
     * JDBC显式注册驱动
     * 对于mysql不建议使用{@link DriverManager}的显式注册方式,会造成驱动2次注册,造成内存浪费;
     */
    @Test
    void explicitlyRegistered() throws SQLException {
        // 1)显式注册驱动 com.mysql.jdbc.Driver
        DriverManager.registerDriver(new Driver());
        // 2)获取注册的驱动
        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        // 3)遍历
        while (drivers.hasMoreElements()) {
            System.out.println(drivers.nextElement().toString());
        }
    }

    /**
     * JDBC隐式注册驱动
     */
    @Test
    void implicitRegistered() throws ClassNotFoundException {
        // 1)隐式注册
        Class.forName("com.mysql.jdbc.Driver");
        // 2)获取注册的驱动
        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        // 3)遍历
        while (drivers.hasMoreElements()) {
            System.out.println(drivers.nextElement().toString());
        }

    }

    /**
     * SPI(Service Provider Interface)机制自动加载
     * ServiceLoader是SPI的是一种实现，SPI用于一些服务提供给第三方实现或者扩展，可以增强框架的扩展或者替换一些组件。 通过jar包来实现扩展功能的热插拔。
     * <pre>
     * java6之后引入了SPI机制,jdbc利用SPI机制会自动加载实现了Driver的接口实现类;
     * 所以导入驱动包后无需配置,直接使用即可;
     *
     * 在ServiceLoader.load时，根据传入的接口类，遍历META-INF/services目录下的以该类命名的文件中的所有类，并实例化返回。{@link java.util.ServiceLoader}是spi机制的一个实现
     * {@link java.util.ServiceLoader#PREFIX}是 META-INF/services，
     * {@link java.util.ServiceLoader.LazyIterator#service}service.getName 是 接口Driver的类名称，拼接出文件路径
     * 拼出的全路径 fullName 是：META-INF/services/Driver，然后去本地找到该路径下的这个文件，然后把这个文件加载进来，
     * 这个文件的内容是：com.mysql.cj.jdbc.Driver，
     * 这个类就是mysql厂商提供的驱动里面的Driver实现类，然后对其进行初始化加载，进行实例化，调这个类的static静态块，执行注册驱动。
     * </pre>
     */
    @Test
    void automaticRegistered() {
        // 1)SPI机制自动加载获取注册的驱动
        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        // 2)遍历
        while (drivers.hasMoreElements()) {
            System.out.println(drivers.nextElement().toString());
        }
    }

    /* JDBC获取连接 */

    /**
     * {@link DriverManager}类中有如下方法获取数据库连接
     * <pre>
     * {@link DriverManager#getConnection(String url, String user, String password)}
     * 连接数据库的URL是sun公司与数据库厂商之间的一种协议
     * 如果数据出现乱码需要加上参数: ?useUnicode=true&characterEncoding=utf8，表示让数据库以UTF8编码来处理数据。
     * 协议:子协议:/IP:端口号 数据库库名称?参数=参数值&...&.....
     * jdbc:mysql://192.168.11.200:3306/va_demo?useUnicode=true&characterEncoding=utf8
     *
     * 数据库的连接对象{@link Connection}
     * </pre>
     */
    @Test
    void getConnection() throws SQLException {
        // 1)注册驱动
        // Class.forName("com.mysql.jdbc.Driver");
        // 2)获取连接对象
        String url = "jdbc:mysql://192.168.11.200:3306/va_demo?useUnicode=true&characterEncoding=utf8";
        String user = "wjl";
        String password = "&kK^v#e6HhBx";
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    /* JDBC实现对单表数据增、删、改 */

    /**
     * 在{@link Connection}接口中有如下方法获取到{@link Statement}对象
     * <pre>
     * {@link Connection#createStatement()} 创建一个 {@link Statement} 对象来将 SQL 语句发送到数据库
     * {@link Statement#executeUpdate(String sql)} 根据执行的DML（INSERT、UPDATE、DELETE）语句，返回受影响的行数
     * </pre>
     */
    @Test
    void executeUpdate() throws SQLException {
        // 1)注册驱动
        // Class.forName("com.mysql.jdbc.Driver");
        // 2)获取连接
        String url = "jdbc:mysql://192.168.11.200:3306/va_demo?useUnicode=true&characterEncoding=utf8";
        String user = "wjl";
        String password = "&kK^v#e6HhBx";
        Connection conn = DriverManager.getConnection(url, user, password);
        // 3)获取发送sql的对象Statement
        Statement stm = conn.createStatement();
        System.out.println(stm);
        // 测试插入
        // String sql = "insert into user values (null,'zhouba','8888')";
        // 测试更新
        // String sql="update user set username='tianqi',password='7777' where id=1";
        // 测试删除
        String sql = "delete from user where id >6";
        int count = stm.executeUpdate(sql);
        System.out.println(count);
        // 4)释放资源,关闭连接
        stm.close();
        conn.close();
    }

    /* JDBC实现对单表数据查询 */

    /**
     * 在{@link Connection}接口中有如下方法获取到{@link Statement}对象
     * <pre>
     * {@link Connection#createStatement()} 创建一个 {@link Statement} 对象来将 SQL 语句发送到数据库
     * {@link Statement#executeQuery(String sql)} 根据查询语句返回结果集,只能执行SELECT语句
     * {@link ResultSet}(接口): 结果集
     * ResultSet 类似游标:
     * 1. ResultSet 内部有一个指针,刚开始记录开始位置
     * 2. 调用 {@link ResultSet#next()}方法, ResultSet内部指针会移动到下一行数据
     * 3. 我们可以通过 ResultSet 得到一行数据 getXxx 得到某列数据
     * </pre>
     */
    @Test
    void executeQuery() throws SQLException {
        // 1)注册驱动
        // Class.forName("com.mysql.jdbc.Driver");
        // 2)获取连接
        String url = "jdbc:mysql://192.168.11.200:3306/va_demo?useUnicode=true&characterEncoding=utf8";
        String user = "wjl";
        String password = "&kK^v#e6HhBx";
        Connection conn = DriverManager.getConnection(url, user, password);
        // 3)获取发送sql的对象Statement
        Statement stm = conn.createStatement();
        System.out.println(stm);
        // 4)发送select查询语句
        String sql = "select * from user";
        ResultSet rs = stm.executeQuery(sql);
        // 5)解析rs结果集,获取数据
        while (rs.next()) {
            int id = rs.getInt(1);
            String username = rs.getString("username");
            String pwd = rs.getString("password");
            System.out.println(id + "----" + username + "-----" + pwd);
        }
        // 6)释放资源
        rs.close();
        stm.close();
        conn.close();
    }

    /* JDBC事务 */

    /**
     * 在{@link Connection}接口中事务
     * <pre>
     * {@link Connection#setAutoCommit(boolean autoCommit)} true: 自动提交 flase: 手动提交
     * {@link Connection#commit()} 提交事务
     * {@link Connection#rollback()} 回滚事务
     *
     * 以a给b转账 100 业务为例；
     * </pre>
     */
    @Test
    void transfer() {
        Connection conn = JdbcUtils.getConnectionUtil(false);
        Statement stm = null;
        try {
            stm = conn.createStatement();
            // a扣款100
            String subMoney = "update account set money=money-100 where name='a'";
            stm.executeUpdate(subMoney);
            // b入账100
            String addMoney = "update account set money=money+100 where name='b'";
            stm.executeUpdate(addMoney);
            // 事务提交
            JdbcUtils.commitUtil(conn);
        } catch (SQLException e) {
            // 回滚事务
            JdbcUtils.rollbackUtil(conn);
            System.out.println(e.getMessage());
        } finally {
            // 释放资源
            JdbcUtils.closeUtil(null, stm, conn);
        }
    }

    /**
     * SQL注入
     * <pre>
     * 由于没有对用户输入进行充分检查，而SQL又是拼接而成，在用户输入参数时，在参数中添加一些SQL 关键字，达到改变SQL运行结果的目的，也可以完成恶意攻击。
     * {@link PreparedStatement}(接口) 是Statement的子接口，可以防止sql注入问题。
     * 可以通过{@link Connection} 接口中的 {@link Connection#prepareStatement(String sql)}方法获得PreparedStatement的对象。
     * </pre>
     */
    @Test
    void injectionLogin() throws SQLException {
        // 1)获取连接对象
        Connection conn = JdbcUtils.getConnectionUtil(true);
        // 2)获取发送sql语句的对象
        Statement stm = conn.createStatement();
        // 3)发送sql查询用户是否存在
        // select * from user where username='xx or 1=1 -- ' and password='66666';
        // String userName="tianqi'  -- '";
        String userName = "xx' or 1=1  -- '";
        String password = "66666";
        String loginSql = "select * from user where username='" + userName + "' and password='" + password + "'";
        ResultSet rs = stm.executeQuery(loginSql);
        if (rs.next()) {
            System.out.println(rs.getString("username") + rs.getString("password"));
        } else {
            System.out.println("您输入的用户名或者密码错误!");
        }
        JdbcUtils.closeUtil(rs, stm, conn);
    }

    /**
     * {@link PreparedStatement} 防止sql注入
     * <pre>
     * 步骤一：PreparedStatement  pstmt =  conn.prepareStatement(sql); -----需要你事先传递sql模板。如果sql需要参数，使用？进行占位。
     * 步骤二：设置参数（执行sql之前）：pstmt.setXXX(int index, 要放入的值) -----根据不同类型的数据进行方法的选择。第一个参数index表示的是？出现的位置。**从1开始计数，有几个问号，就需要传递几个参数**。
     * 方法的参数说明：
     * 第一个参数：int index ;表示的是问号出现的位置。 问号是从1开始计数
     * 第二个参数：给问号的位置传入的值。
     * 步骤三、执行，不需要在传递sql了。
     * pstmt.executeQuery();---执行select
     * pstmt.executeUpdate();---执行insert，delete，update
     * </pre>
     */
    @Test
    void prepareStatement() throws SQLException {
        // 1)获取连接对象
        Connection conn = JdbcUtils.getConnectionUtil(true);
        // 定义登录的sql模板 参数索引位从1开始
        String loginSql = "select * from user where username=? and password=?";
        // 2)获取发送sql语句的预编译对象
        PreparedStatement pstm = conn.prepareStatement(loginSql);
        // 设置参数
        // pstm.setString(1,"tianqi");
        // 防止了sql注入
        pstm.setString(1, "tianqi' or 1=1  -- '");
        pstm.setString(2, "66666");
        // 发送参数,执行sql
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            System.out.println(rs.getString("username") + rs.getString("password"));
        } else {
            System.out.println("您输入的用户名或者密码错误!");
        }
        JdbcUtils.closeUtil(rs, pstm, conn);
    }
}