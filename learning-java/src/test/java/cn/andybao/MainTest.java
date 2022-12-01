package cn.andybao;

/**
 * @author AndyBao
 * @description
 * @create_at 2022/11/24 20:35
 * @since
 */
public class MainTest {
    /**
     * 1、main方法的调用者是java虚拟机。
     * 2、因为调用者是java虚拟机，java虚拟机不在该类中也不在该类所在的包中更不是该类的子类，所以main函数的权限只能设置为最大的public，便于java虚拟机的调用。
     * 3、java虚拟机在执行main方法时不必创建对象，所以方法必须是static，也就是在类加载进内存时该方法就可以使用。
     * 4、该方法接收String类型的数组参数，该数据中保存执行java命令时传递给所运行的类的参数。
     */
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("上海")) {
                System.out.println("上海是个美丽的地方");
            } else {
                System.out.println("args;" + args[i]);
            }
        }
    }

}
