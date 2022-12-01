package cn.andybao;

import cn.bao.andy.china.dao.Student;

import java.util.Arrays;

/**
 * @author AndyBao
 * @description
 */
public class HelloWorld {
    /**
     * 类加载器
     * <pre>
     * 什么是类加载器？
     *  加载器是Java运行时环境的一部分，负责加载字节码文件
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
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Arrays.toString(args));
        System.out.println("Hello world!");
        // 编译： javac HelloWorld.java
        // 生成： HelloWorld.class  字节码文件
        // 运行： Java HelloWorld
        // 使用java命令执行HelloWorld类（默认调用main方法）
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