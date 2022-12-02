package cn.andybao;

import cn.andybao.dao.UserDO;
import cn.andybao.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Mybatis框架
 * <pre>
 * 框架概述
 *
 * 1)什么是框架
 * 程序开发中的框架往往是对常见功能的封装，通常与具体业务无关，也可以认为是软件的半成品。
 * 程序框架理解为基础或者机械标准件(例如螺丝螺母标准的机械部件)。
 * 假如你要造一辆马车，在没有框架的情况下，你需要自己去伐木，去把木头做成木板，木棍，然后组成轮子，门，等部件，然后组装起来。
 * 但如果你用了框架，就相当于你有现成的轮子，门等部件，你只需要组装一下就可以了。
 * 一个框架是一组可复用的设计构件。
 * 框架是一个半成品，软件是成品。我们在它的基础上开发出成品(软件)。
 *
 * 2)框架解决的问题
 *  1.解决了技术通用的问题
 *      在JavaEE体系中，有着各种各样的技术。
 *      不同的软件企业，根据自身的业务需求选择不同的技术，容易造成应用依赖技术，增加了项目开发实现的复杂性和技术风险性。
 *      而框架技术就可以解决上述问题。
 *  2.提升了开发效率
 *      企业项目中使用框架，只需要专注实现业务需求。
 *      使用框架的方便性，提升了开发效率。
 *  3.提升了系统稳定性
 *      一个成熟的框架，经过了在众多企业项目中的验证使用，稳定性有保障。
 * </pre>
 * <p>
 * Mybatis框架介绍
 *
 * <pre>
 * mybatis是Apache软件基金会下的一个开源项目，前身是iBatis框架。
 * 2010年这个项目由apache 软件基金会迁移到google code下，改名为mybatis。
 * 2013年11月又迁移到了github(GitHub 是一个面向开源及私有 软件项目的托管平台)。
 * MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射(多表)。
 * MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。
 * 它对 jdbc 的操作数据库的过程进行封装，使开发者只需要关注 SQL 本身，
 * 而不需要花费精力去处理例如注册驱动、创建 connection、创建 statement、手动设置参数、结果集检索等 jdbc 繁杂的过程代码。
 * MyBatis 可以使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。
 *
 * 1)mybatis的优点
 *  1. 简单易学：mybatis本身就很小且简单。没有任何第三方依赖，最简单安装只要两个jar文件+配置几个SQL映射文件即可。
 *  2. 使用灵活：Mybatis不会对应用程序或者数据库的现有设计强加任何影响。SQL语句写在XML里，便于统一管理和优化。
 *  3. 解除SQL与程序代码的耦合：通过提供DAO层，将业务逻辑和数据访问逻辑分离，使系统的设计更清晰，更易维护，更易进行单元测试。SQL语句和代码的分离，提高了可维护性。
 *
 * 2)mybatis的不足
 *  1. 编写SQL语句时工作量很大，尤其是字段多、关联表多时，更是如此。
 *  2. SQL语句依赖于数据库，导致数据库移植性差，不能更换数据库。
 *  3. Mybatis框架还是比较简陋(**半自动化框架**)，功能尚有缺失，虽然简化了数据绑定代码，但是整个底层数据库查询实际还是要自己写的，工作量也比较大，而且不太容易适应快速数据库修改。
 *
 * 3)官方网站及框架包下载
 *  官网地址<a href="http://www.mybatis.org/mybatis-3/">http://www.mybatis.org/mybatis-3</a>
 *
 * 4)mybatis框架整体架构
 * <img width="584" height="716" src="image/Mybatis执行过程.png" alt="">
 *
 * MyBatis的 ORM (Object Relational Mapping 对象关系映射)方式
 * <img width="1093" height="633" src="image/ORM映射.png" alt="">
 *  Mybatis有两种映射方式:
 *      1.通过XML映射;
 *      2.通过注解;
 *
 * 小结:
 * Mybatis 解决了三层(web,service,dao)中 dao 层的问题
 * Mybatis 框架是 JDBC 的封装
 *
 * Mybatis存在2种开发方式:
 *  1)旧版本 Mybatis 执行的方式;
 *  2)动态代理实现操纵数据库;
 * </pre>
 */
class MybatisTest {
    /*
    Mybatis存在2种开发方式:
        1)旧版本 Mybatis 执行的方式;
        2)动态代理实现操纵数据库;
     */

    /**
     * Mybatis老式开发
     * <pre>
     * 使用Mybatis框架,从User表中根据用户id查询用户信息
     * 1)
     * <dependency>
     *     <groupId>mysql</groupId>
     *     <artifactId>mysql-connector-java</artifactId>
     * </dependency>
     * <dependency>
     *     <groupId>org.mybatis</groupId>
     *     <artifactId>mybatis</artifactId>
     * </dependency>
     *
     * 2)创建实体类UserDO
     * 3)创建核心配置文件:mybatis-config.xml
     * 4)创建映射文件UserMapper.xml
     *
     * 旧式开发加载流程：
     * <img width="1413" height="1009" src="image/旧式开发加载流程.png" alt="">
     *
     * 使用mybatis老式的开发模式弊端：
     *  1.要使用指定的sql必须使用命名空间.id进行字符串拼接,维护性比较差;
     *  2.并且这个sql用多少次,就拼接多少次,维护性比较差;
     *
     * Mybatis框架的核心类
     * {@link SqlSessionFactoryBuilder} 会话工厂构建类 主要用来构建会话工厂的
     * {@link SqlSessionFactory} :会话工厂类 主要用来生产会话对象的;
     * {@link SqlSession}:会话对象,底层是对Connection连接对象的封装;
     * </pre>
     */
    @Test
    void selectOne() {
        // 5)加载核心配置文件,构建会话工厂
        String resource = "mybatis-config.xml";
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 2.获取会话对象
        SqlSession sqlSession = sessionFactory.openSession();
        // 3.调用 sqlSession API完成查询
        UserDO user = (UserDO) sqlSession.selectOne("cn.vision.demo.dao.UserMapper.findUserById", 1);
        System.out.println(user);
        // 4.关闭资源
        sqlSession.close();
    }

    /**
     * 基于动态代理实现查询
     * <pre>
     * 核心步骤:
     *  1)接口与xml映射文件绑定;
     *      接口名称与xml映射文件命名空间要一致;
     *      接口方法与xml中crud标签ID要一致;
     *  2)通过SqlSession获取代理对象;
     *      {@link SqlSession#getMapper(Class)} (接口);
     * </pre>
     */
    @Test
    void getMapper() {
        //1.加载核心配置文件,构建会话工厂
        String resource = "mybatis-config.xml";
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
        //2.获取会话对象
        SqlSession sqlSession = sessionFactory.openSession();
        //3.获取接口的代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用接口方法,进行处理
        UserDO user = mapper.findUserById(1);
        System.out.println(user);
        //5.释放资源
        sqlSession.close();
    }

    /**
     * mybatis核心配置
     * <pre>
     * mybatis-config.xml，是MyBatis的全局配置文件，包含全局配置信息，如数据库连接参数、插件等。整个框架中只需要一个即可。
     *
     *
     * <pre>
     */
}