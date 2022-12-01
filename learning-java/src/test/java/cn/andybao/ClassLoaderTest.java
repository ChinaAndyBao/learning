package cn.andybao;

import cn.bao.andy.china.dao.Student;
import org.junit.jupiter.api.Test;

/**
 * @author AndyBao
 * @description
 * @create_at 2022/11/24 16:12
 * @since
 */
class ClassLoaderTest {
    /**
     * 类的加载
     * <pre>
     * 当程序在运行后，在jvm执行某个类时，如果该类是第一次被执行 第一次使用某个类的时候，
     * 会将该类的.class文件件读取到内存，
     * 基于.class文件创建一个Class对象（方法区）并将此类的所有信息存储到一个Class对象中
     * 说明：
     *  Class对象是指java.lang.Class类的对象，此类由Java类库提供，专门用于存储类型的信息
     *  Class对象中存储的是.class文件中的内容：构造方法、成员变量、成员方法
     *  Class对象中存储的构造方法：构造器对象 Constructor对象
     *  Class对象中存储的成员变量：字段对象 Field对象
     *  Class对象中存储的成员方法：方法对象 Method对象
     *
     * 类的加载机制 在以下情况下会加载类：
     *      1.创建一个类的对象（第1次）
     *      2.调用类的静态方法，静态变量（第1次）
     *      3.使用一个类的子类时（第1次）
     *      4.通过反射进行加载类
     *      5.java命令执行某一个类（第1次）运行java程序
     *
     * .class文件中都包含
     *      构造方法
     *      成员变量
     *      成员方法
     * </pre>
     * <p>
     * 类加载器
     * <pre>
     * 什么是类加载器？
     *  类加载器是Java运行时环境的一部分，负责加载字节码文件
     *
     * 类加载器的作用：
     *  负责将磁盘上的某个class文件读取到内存并生成Class的对象
     *
     * 类加载器的分类：
     *  Java中有三种类加载器，它们分别用于加载不同种类的class：
     *      启动类加载器(Bootstrap ClassLoader)：用于加载系统类库<JAVA_HOME>\bin目录下的class，例如：rt.jar。
     *      扩展类加载器(Extension ClassLoader)：用于加载扩展类库<JAVA_HOME>\lib\ext目录下的class。
     *      应用程序类加载器(Application ClassLoader)：用于加载我们自定义类的加载器。
     * 获取类加载器的方式
     *
     * {@link Class#getClassLoader()} 返回该类的类加载器
     * 有些实现可能使用 null 来表示引导类加载器(启动类加载器)
     * 如果该类由引导类加载器(启动类加载器)加载，则此方法在这类实现中将返回 null
     * </pre>
     */
    @Test
    void getClassLoader() throws ClassNotFoundException {
        System.out.println("Hello world!");
        // 编译： javac HelloWorld.java 生成 HelloWorld.class 字节码文件
        // 运行： Java HelloWorld
        // 使用 java 命令执行 HelloWorld 类（默认调用main方法）
        // 底层： HelloWorld.main(null);

        Class<Object> objectClass1 = Object.class;
        Object object = new Object();
        Class<?> objectClass2 = object.getClass();
        Class<?> objectClass3 = Class.forName("java.lang.Object");


        // 获取 String 类的类加载器
        // 先获取 String 对应的Class对象
        Class<Student> studentClass = Student.class;
        // 再通过该 class 对象获取类加载器
        ClassLoader studentClassClassLoader = studentClass.getClassLoader();
        // stringClassClassLoader = null
        System.out.println("studentClassClassLoader = " + studentClassClassLoader);

        // 获取自定义类的类加载器
        // 先获取 Student 对应的Class对象
        Class<String> stringClass = String.class;
        // 再通过该 class 对象获取类加载器
        ClassLoader stringClassClassLoader = stringClass.getClassLoader();
        // studentClassClassLoader = sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("stringClassClassLoader = " + stringClassClassLoader);


    }
}