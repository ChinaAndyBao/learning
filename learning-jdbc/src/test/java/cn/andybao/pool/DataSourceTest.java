package cn.andybao.pool;

import cn.andybao.dao.User;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 数据库连接池(database connection pool)
 * <pre>
 * 当前的jdbc程序每次访问数据库都需要创建一个新的连接，访问完毕之后，还需要释放资源。
 * 那么在这样的一个过程中，连接的创建和销毁所消耗的资源是远远大于我们发送sql并执行的时间的。
 * 基于这样的情况，我们发现我们的jdbc程序将大量的资源浪费在了连接的创建和销毁上。
 *
 * 举例：就像在上海坐地铁，就一站2分钟的路程，往往在买地铁票的过程需要等待至少10分钟以上的时间。这样是不合理的。所以我们 需要对这样的结构进行优化。
 *
 * 思考上面的结构，大部分的时间浪费在了创建和销毁上。
 * 那么我们能不能实现将这些连接回收和利用呢？
 * 这样我们就不需要不停的创建和销毁了。只需要创建一次，放在指定的地方。
 * 当我们使用的时候，直接从里面拿就行了。用完放回原来的地方。
 * 不去销毁，当我再次使用的时候，去拿就行了。而这样的解决方案就是我们需要的。
 *
 * 优化后的结构如下：
 * 说明：首先创建一定数量的连接，然后放到指定的地方。
 * 当我们需要获取连接的时候，直接从指定的地方获取。
 * 用完了，我们再将连接放回去。这样就能将我们连接的回收利用。并且不用花费大量时间在创建和销毁连接上。
 *
 * 连接池好处
 * 连接池中保存了一些数据库连接，这些连接是 可以重复使用的。节省数据库的资源消耗。
 *
 * {@link DataSource}表示数据库连接池，
 * DataSource本身只是Sun公司提供的一个接口,没有具体的实现，它的实现由连接池的数据库厂商去实现。我们只需要学习这个工具如何使用即可。
 *
 * 常用的连接池实现组件有以下这些：
 *
 * 1. DBCP 连接池: DBCP(DataBase Connection Pool)数据库连接池，是Apache上的一个Java连接池项目。dbcp没有自动回收空闲连接的功能。
 * 2. C3P0 连接池: C3P0是一个开源的JDBC连接池，支持JDBC3规范和JDBC2的标准扩展。目前使用它的开源项目有Hibernate，Spring等。C3P0有自动回收空闲连接功能。
 * 3. Druid 连接池：阿里巴巴-德鲁伊 Druid是阿里巴巴开源平台上的一个项目，整个项目由数据库连接池、插件框架和SQL解析器组成。该项目主要是为了扩展JDBC的一些限制，可以让程序员实现一些特殊的需求。
 * 4. Hikari 连接池：Hikari是一款非常强大，高效，并且号称“史上最快连接池”。并且在springboot2.0之后，将默认的数据库连接池从 tomcat jdbc pool 改为了 hikari。不需要引入依赖，已经在SpringBoot中包含了。
 * </pre>
 */
class DataSourceTest {

    /**
     * C3P0 是一个开源的JDBC连接池，目前spring 和 hibernate框架对C3P0是支持的。
     * <pre>
     * 1)
     * <dependency>
     *     <groupId>com.mchange</groupId>
     *     <artifactId>c3p0</artifactId>
     * </dependency>
     *
     * 2)
     * 编写 c3p0-config.xml 配置文件，配置对应参数
     * C3P0常用的配置参数解释
     * 参数              说明
     * driverClass      数据库驱动类。例如：com.mysql.jdbc.Driver
     * jdbcUrl          连接数据库的url地址。例如：jdbc:mysql://192.168.11.200:3306/va_demo
     * user             连接数据库的用户名。例如：wjl
     * password         连接数据库的密码。例如：&kK^v#e6HhBx
     * initialPoolSize  刚创建好连接池的时候连接数量
     * maxPoolSize      连接池中最多可以放多少个连接
     *
     * {@link ComboPooledDataSource} 类表示C3P0的连接池对象，常用2种创建连接池的方式：
     * {@link ComboPooledDataSource#ComboPooledDataSource()}
     * 无参构造使用默认配置（使用xml中 default-config 标签中对应的参数）
     * {@link ComboPooledDataSource#ComboPooledDataSource(String configName)}
     * 有参构造使用命名配置（ configName：xml 中配置的名称，使用xml中 named-config 标签中对应的参数）
     * </pre>
     */
    @Test
    void c3p0() throws SQLException {
        // 3)创建数据源(连接池对象)对象，自动加载src根路径下c3p0-config.xml
        // ComboPooledDataSource dataSource = new ComboPooledDataSource();
        // 指定dev作为数据库开发环境
        ComboPooledDataSource dataSource = new ComboPooledDataSource("dev");
        // 获取连接对象
        Connection conn = dataSource.getConnection();
        // 4)创建发送sql的对象
        Statement stm = conn.createStatement();
        // 5)发送sql语句，获取ResultSet结果集
        String selectSql = "select * from user";
        ResultSet rs = stm.executeQuery(selectSql);
        // 6)解析结果集
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString("username");
            String password = rs.getString(3);
            User user = new User();
            user.setId(id);
            user.setPassword(password);
            user.setUserName(name);
            users.add(user);
        }
        System.out.println(users);
        // 7)close 归还连接对象到连接池
        rs.close();
        stm.close();
        conn.close();
    }

    /**
     * Druid是阿里巴巴开发的号称为监控而生的数据库连接池(可以监控访问数据库的性能)。
     * 在功能、性能、扩展性方面，都超过其他数据库连接池。
     * Druid已经在阿里巴巴部署了超过600个应用，经过一年多生产环境大规模部署的严苛考验。
     * 如：一年一度的双十一活动，每年春运的抢火车票。
     * <pre>
     * 1)
     * <dependency>
     *     <groupId>com.alibaba</groupId>
     *     <artifactId>druid</artifactId>
     * </dependency>
     *
     * 2)
     * 编写 druid.properties 配置文件，配置对应参数
     * Druid常用的配置参数
     * url              数据库连接字符串 jdbc:mysql://localhost:3306/数据库名
     * username         数据库的用户名
     * password         数据库的密码
     * driverClassName  驱动类名。根据url自动识别，这一项可配可不配，如果不配置druid会根据url自动识别数据库的类型，然后选择相应的数据库驱动名
     * initialSize      初始化时建立的物理连接的个数。初始化发生在显式调用init方法，或者第一次获取连接对象时
     * maxActive        连接池中最大连接数
     * maxWait          获取连接时最长等待时间，单位是毫秒。
     *
     * 获取数据源的方法：使用{@link com.alibaba.druid.pool.DruidDataSourceFactory}类中的静态方法
     * {@link DruidDataSourceFactory#createDataSource(Properties properties)}
     * 创建一个连接池，连接池的参数使用 properties 中的数据
     * </pre>
     */
    @Test
    void druid() throws Exception {
        // 加载配置信息
        // InputStream in = new FileInputStream("src/druid.properties");
        InputStream in = DataSourceTest.class.getClassLoader().getResourceAsStream("druid.properties");
        // 创建Properties配置对象
        Properties properties = new Properties();
        properties.load(in);
        // 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        // 获取连接对象
        Connection conn = dataSource.getConnection();
        // 3)创建发送sql的对象
        Statement stm = conn.createStatement();
        // 4)发送sql语句，获取ResultSet结果集
        String selectSql = "select * from user";
        ResultSet rs = stm.executeQuery(selectSql);
        // 5)解析结果集
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString("username");
            String password = rs.getString(3);
            User user = new User();
            user.setId(id);
            user.setPassword(password);
            user.setUserName(name);
            users.add(user);
        }
        System.out.println(users);
        //6)close 归还连接对象到连接池
        rs.close();
        stm.close();

        //归还资源
        conn.close();
    }

    /**
     * Hikari 是一款非常强大，高效，并且号称“史上最快连接池”。
     * 并且在springboot2.0之后，将默认的数据库连接池从 tomcat jdbc pool 改为了 hikari。
     * 不需要引入依赖，已经在SpringBoot中包含了。
     * <pre>
     * 1)
     * <dependency>
     *     <groupId>com.zaxxer</groupId>
     *     <artifactId>HikariCP</artifactId>
     * </dependency>
     *
     * 2)
     * 编写 hikari.properties 配置文件，配置对应参数
     * Hikari 常用的配置参数
     * jdbcUrl          数据库连接字符串 jdbc:mysql://localhost:3306/数据库名
     * username         数据库的用户名
     * password         数据库的密码
     * driverClassName  驱动类名。根据url自动识别，这一项可配可不配，如果不配置druid会根据url自动识别数据库的类型，然后选择相应的数据库驱动名
     * initialSize      初始化时建立的物理连接的个数。初始化发生在显式调用init方法，或者第一次获取连接对象时
     * maxPoolSize      连接池中最大连接数
     * minIdle          当minIdle<0或者minIdle>maxPoolSize,则minIdle被重置为maxPoolSize，该值默认为10，官方建议设置为一致，当做固定大小的连接池处理提高性能
     *
     * 获取数据源的方法：根据Properties配置对象构造{@link HikariConfig}Hikari配置对象
     * 再根据 Hikari配置对象构造{@link HikariDataSource} 创建一个连接池并启动池，连接池的参数使用 properties 中的数据
     * </pre>
     */
    @Test
    void hikari() throws Exception {
        // 加载配置信息
        // InputStream in = new FileInputStream("src/druid.properties");
        InputStream in = DataSourceTest.class.getClassLoader().getResourceAsStream("hikari.properties");
        // 创建Properties配置对象
        Properties properties = new Properties();
        properties.load(in);
        // 获取连接池对象
        HikariConfig config = new HikariConfig(properties);
        HikariDataSource hikariDataSource = new HikariDataSource(config);
        // 获取连接对象
        Connection conn = hikariDataSource.getConnection();
        // 3)创建发送sql的对象
        Statement stm = conn.createStatement();
        // 4)发送sql语句，获取ResultSet结果集
        String selectSql = "select * from user";
        ResultSet rs = stm.executeQuery(selectSql);
        // 5)解析结果集
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString("username");
            String password = rs.getString(3);
            User user = new User();
            user.setId(id);
            user.setPassword(password);
            user.setUserName(name);
            users.add(user);
        }
        System.out.println(users);
        //6)close 归还连接对象到连接池
        rs.close();
        stm.close();

        //归还资源
        conn.close();
    }
}
