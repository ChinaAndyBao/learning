# Java基础

## 内存分配

内存是计算机中的重要原件，临时存储区域，作用是运行程序。
我们编写的程序是存放在硬盘中的，在硬盘中的程序是不会运行的。
必须放进内存中才能运行，运行完毕后会清空内存。
Java虚拟机要运行程序，必须要对内存进行空间的分配和管理。

### java中的内存分配

目前我们只需要记住两个内存，分别是：栈内存和堆内存

| 区域名称   | 作用                                                       |
| ---------- | ---------------------------------------------------------- |
| 寄存器     | 给CPU使用，和我们开发无关。                                |
| 本地方法栈 | JVM在使用操作系统功能的时候使用，和我们开发无关。          |
| 方法区     | 存储可以运行的class文件。                                  |
| 堆内存     | 存储对象或者数组，new来创建的，都存储在堆内存。            |
| 方法栈     | 方法运行时使用的内存，比如main方法运行，进入方法栈中执行。 |

## Java中的数据类型

Java是一个强类型语言，Java中的数据必须明确数据类型。在Java中的数据类型包括基本数据类型和引用数据类型
两种。

### 基本数据类型

| 数据类型 | 关键字 | 内存占用(字节) | 取值范围 |
| -------- | :----- | -------- | :------- |
| 整数类型 | byte    |     1     |  -128~127        |
|          | short       |  2        |-32768~32767|
|          |   int(默认)     |     4     |-2的31次方到2的31次方-1|
|          |   long     |    8      |    -2的63次方到2的63次方-1      |
| 浮点类型 |  float      |    4      |    负数：-3.402823E+到-1.401298E-45正数： 1.401298E-45到 3.402823E+38    |
|          |   double(默认)   |    8      |  负数：-1.797693E+308到-4.9000000E-324 正数：4.9000000E-324 到1.797693E+308  |
|    字符类型      |    char    |    2      |    0-65535      |
|    布尔类型      |    boolean    |   1       |    true，false      |

#### 类型转换

在Java中，一些数据类型之间是可以相互转换的。分为两种情况：自动类型转换和强制类型转换。

##### 自动类型转换：

```java
把一个表示数据范围小的数值或者变量赋值给另一个表示数据范围大的变量。这种转换方式是自动的，直接书写即可。
        例如：
        double num=10; // 将int类型的10直接赋值给double类型 
        System.out.println(num); // 输出10.0
```

##### 强制类型转换：

```java
把一个表示数据范围大的数值或者变量赋值给另一个表示数据范围小的变量。强制类型转换格式：目标数据类型 变量名=(目标数据类型)值或者变量;
        例如：
        double num1=5.5;
        int num2=(int)num1; // 将double类型的num1强制转换为int类型 
        System.out.println(num2); // 输出5（小数位直接舍弃）
```

##### 说明：

```java
1.char类型的数据转换为int类型是按照码表中对应的int值进行计算的。比如在ASCII码表中，'a'对应97。
        int a='a';System.out.println(a); // 将输出97

        2.整数默认是int类型，byte、short和char类型数据参与运算均会自动转换为int类型。
        byte b1=10;
        byte b2=20;
        byte b3=b1+b2;
// 第三行代码会报错，b1和b2会自动转换为int类型，计算结果为int，int赋值给byte需要强制类型转换。 
// 修改为: 
        int num=b1+b2;
// 或者： 
        byte b3=(byte)(b1+b2);

        3.boolean类型不能与其他基本数据类型相互转换。
```

### 引用数据类型

## 运算符

### 自增自减运算符

```
++和-- 既可以放在变量的后边，也可以放在变量的前边。
单独使用的时候， ++和-- 无论是放在变量的前边还是后边，结果是一样的。
参与操作的时候，如果放在变量的后边，先拿变量参与操作，后拿变量做++或者--。
参与操作的时候，如果放在变量的前边，先拿变量做++或者--，后拿变量参与操作。
```

### 逻辑运算符

| 符号 | 作用     | 说明                                         |
| ---- | -------- | -------------------------------------------- |
| &    | 逻辑与   | a&b，a和b都是true，结果为true，否则为false   |
| \|   | 逻辑或   | a\|b，a和b都是false，结果为false，否则为true |
| ^    | 逻辑异或 | a^b，a和b结果不同为true，相同为false         |
| !    | 逻辑非   | !a，结果和a的结果正好相反                    |

### 短路逻辑运算符

| 符号 | 作用   | 说明                         |
| ---- | ------ | ---------------------------- |
| &&   | 短路与 | 作用和&相同，但是有短路效果  |
| \|\| | 短路或 | 作用和\|相同，但是有短路效果 |

在逻辑与运算中，只要有一个表达式的值为false，那么结果就可以判定为false了，没有必要将所有表达式的值都计算出来，短路与操作就有这样的效果，可以提高效率。同理在逻辑或运算中，一旦发现值为true，右边的表达式将不再参与运算。

逻辑与 &， 无论左边真假，右边都要执行。
短路与 &&，如果左边为真，右边执行；如果左边为假，右边不执行。
逻辑或 |， 无论左边真假，右边都要执行。
短路或 ||， 如果左边为假，右边执行；如果左边为真，右边不执行。

### 三元运算符

```java
三元运算符语法格式：
        关系表达式?表达式1:表达式2;
        解释：问号前面的位置是判断的条件，判断结果为boolean型，为true时调用表达式1，为false时调用表达式2。其逻辑为：如果条件表达式成立或者满足则执行表达式1，否则执行第二个。

        举例：
        int a=10;
        int b=20;
        int c=a>b?a:b; // 判断 a>b 是否为真，如果为真取a的值，如果为假，取b的值
```

## 流程控制语句

顺序结构
分支结构(if, switch)
循环结构(for, while, do…while)

### 分支结构(if, switch)

#### 分支结构之if语句

```java
if语句格式1
        格式：
        if(关系表达式){
        语句体;
        }

        if语句格式2
        格式：
        if(关系表达式){
        语句体1;
        }else{
        语句体2;
        }

        if语句格式3
        格式：
        if(关系表达式1){
        语句体1;
        }else if(关系表达式2){
        语句体2;
        }…else{
        语句体n+1;
        }
```

#### 分支结构之switch句

```java
格式:
        switch(表达式){
        case 1:
        语句体1;
        break;
        case 2:
        语句体2;
        break;
        ...
default:
        语句体n+1;
        break;
        }

        执行流程：
        首先计算出表达式的值
        其次，和case依次比较，一旦有对应的值，就会执行相应的语句，在执行的过程中，遇到break就会结束。
        最后，如果所有的case都和表达式的值不匹配，就会执行default语句体部分，然后程序结束掉。
```

### 循环结构(for, while, do…while)

循环语句可以在满足循环条件的情况下，反复执行某一段代码，这段被重复执行的代码被称为循环体语句，当反复
执行这个循环体时，需要在合适的时候把循环判断条件修改为false，从而结束循环，否则循环将一直执行下去，形成死循环。

#### 循环结构之for循环

```java
for循环格式：
        for(初始化语句;条件判断语句;条件控制语句){
        循环体语句;
        }

        格式解释：
        初始化语句： 用于表示循环开启时的起始状态，简单说就是循环开始的时候什么样
        条件判断语句：用于表示循环反复执行的条件，简单说就是判断循环是否能一直执行下去
        循环体语句： 用于表示循环反复执行的内容，简单说就是循环反复执行的事情
        条件控制语句：用于表示循环执行中每次变化的内容，简单说就是控制循环是否能执行下去

        执行流程：
        ①执行初始化语句
        ②执行条件判断语句，看其结果是true还是false
        如果是false，循环结束
        如果是true，继续执行
        ③执行循环体语句
        ④执行条件控制语句
        ⑤回到②继续

        示例代码：

public class ForTest01 {
  public static void main(String[] args) {
    //需求：输出数据1-5 
    for (int i = 1; i <= 5; i++) {
      System.out.println(i);
    }
    System.out.println("--------");
    //需求：输出数据5-1 
    for (int i = 5; i >= 1; i--) {
      System.out.println(i);
    }
  }
} 
```

#### 循环结构之while循环

```java
格式：
        初始化语句;
        while(条件判断语句){
        循环体语句;
        条件控制语句;
        }

        while循环执行流程：
        ①执行初始化语句
        ②执行条件判断语句，看其结果是true还是false
        如果是false，循环结束
        如果是true，继续执行
        ③执行循环体语句
        ④执行条件控制语句
        ⑤回到②继续

        示例代码：

public class WhileDemo {
  public static void main(String[] args) {
    //需求：在控制台输出5次"HelloWorld" 
    //for循环实现 
    for (int i = 1; i <= 5; i++) {
      System.out.println("HelloWorld");
    }
    System.out.println("--------");
    //while循环实现 
    int j = 1;
    while (j <= 5) {
      System.out.println("HelloWorld");
      j++;
    }
  }
}
```

#### 循环结构之do...while循环结构

```java
格式：
        初始化语句;
        do{
        循环体语句;
        条件控制语句;
        }while(条件判断语句);

        执行流程：
        ① 执行初始化语句
        ② 执行循环体语句
        ③ 执行条件控制语句
        ④ 执行条件判断语句，看其结果是true还是false
        如果是false，循环结束
        如果是true，继续执行
        ⑤ 回到②继续

        示例代码：

public class DoWhileDemo {
  public static void main(String[] args) {
    //需求：在控制台输出5次"HelloWorld" 
    //for循环实现 
    for (int i = 1; i <= 5; i++) {
      System.out.println("HelloWorld");
    }
    System.out.println("--------");
    //do...while循环实现 
    int j = 1;
    do {
      System.out.println("HelloWorld");
      j++;
    } while (j <= 5);
  }
}
```

#### 三种循环的区别

```java
for循环和while循环先判断条件是否成立，然后决定是否执行循环体（先判断后执行）
        do...while循环先执行一次循环体，然后判断条件是否成立，是否继续执行循环体（先执行后判断）
```

#### for循环和while的区别

```java
条件控制语句所控制的自增变量，因为归属for循环的语法结构中，在for循环结束后，就不能再次被访问到了
        条件控制语句所控制的自增变量，对于while循环来说不归属其语法结构中，在while循环结束后，该变量还可以继续使用
```

#### 死循环（无限循环）的三种格式

```java
1. for(;;){
        }
        2.while(true){
        }
        3. do{
        }while(true);
```

#### 跳转控制语句

```java
跳转控制语句（break）
        跳出循环，结束循环
        跳转控制语句（continue）
        跳过本次循环，继续下次循环
        注意： continue只能在循环中进行使用！
```

#### 循环嵌套

```java
循环嵌套概述：在循环中，继续定义循环
        示例代码：
public static void main(String[]args){
        //外循环控制小时的范围，内循环控制分钟的范围 
        for(int hour=0;hour< 24;hour++){
        for(int minute=0;minute< 60;minute++){
        System.out.println(hour+"时"+minute+"分");
        }System.out.println("--------");
        }
        }

        理解：
        请反复理解这句话（整个内循环，就是外循环的一个循环体，内部循环体没有执行完毕，外循环是不会继续向下执行的）
        结论：
        外循环执行一次，内循环执行一圈
```

## 数组

数组就是存储数据长度固定的容器，存储多个数据的数据类型要一致。

### 数组定义格式

```java
格式：
        第一种：
        数据类型[]数组名
        示例：
        int[]arr;
        double[]arr;
        char[]arr;

        第二种：
        数据类型 数组名[]
        示例：
        int arr[];
        double arr[];
        char arr[];
```

### 数组静态初始化

在创建数组时，直接将元素确定

```java
完整版格式：
        数据类型[]数组名=new 数据类型[]{元素1,元素2,...};

        简化版格式：
        数据类型[]数组名={元素1,元素2,...};

        格式：

public class ArrayDemo {
  public static void main(String[] args) {
    //定义数组 
    int[] arr = {1, 2, 3};
    //输出数组名 
    System.out.println(arr);
    //输出数组中的元素 
    System.out.println(arr[0]);
    System.out.println(arr[1]);
```

### 数组动态初始化

数组动态初始化就是只给定数组的长度，由系统给出默认初始化值

```java
格式：
        数据类型[]数组名=new 数据类型[数组长度];
        int[]arr=new int[3];

        格式详解：
        等号左边：
        int:数组的数据类型
        []:代表这是一个数组
        arr:代表数组的名称
        等号右边：
        new:为数组开辟内存空间
        int:数组的数据类型
        []:代表这是一个数组
        5:代表数组的长度
```

#### 数组元素访问

每一个存储到数组的元素，都会自动的拥有一个编号，从0开始。
这个自动编号称为数组索引(index)，可以通过数组的索引访问到数组中的元素。

```java
格式：
        数组名[索引];

        示例：

public class ArrayDemo {
  public static void main(String[] args) {
    int[] arr = new int[3];
    //输出数组名 
    System.out.println(arr); //[I@880ec60 
    //输出数组中的元素 
    System.out.println(arr[0]);
    System.out.println(arr[1]);
    System.out.println(arr[2]);
  }
}
```

### 数组操作的两个常见问题

#### 索引越界异常(ArrayIndexOutOfBoundsException)

```java
出现原因

public class ArrayDemo {
  public static void main(String[] args) {
    int[] arr = new int[3];
    System.out.println(arr[3]);
  }
}
数组长度为3，索引范围是0~2，但是我们却访问了一个3的索引。
        程序运行后，将会抛出ArrayIndexOutOfBoundsException 数组越界异常。
        在开发中，数组的越界异常是不能出现的，一旦出现了，就必须要修改我们编写的代码。
        解决方案
        将错误的索引修改为正确的索引范围即可！
```

#### 空指针异常(NullPointerException)

```java
出现原因

public class ArrayDemo {
  public static void main(String[] args) {
    int[] arr = new int[3];

    //把null赋值给数组
    arr = null;
    System.out.println(arr[0]);
  }
}
arr=null 这行代码，
        意味着变量arr将不会在保存数组的内存地址，也就不允许再操作数组了，
        因此运行的时候会抛出 NullPointerException 空指针异常。
        在开发中，数组的越界异常是不能出现的，一旦出现了，就必须要修改我们编写的代码。
        解决方案
        给数组一个真正的堆内存空间引用即可！
```

## 变量

### 成员变量和局部变量的区别

```java
类中位置不同：
        成员变量（类中方法外）
        局部变量（方法内部或方法声明上）
        内存中位置不同：
        成员变量（堆内存）
        局部变量（栈内存）
        生命周期不同：
        成员变量（随着对象的存在而存在，随着对象的消失而消失）
        局部变量（随着方法的调用而存在，醉着方法的调用完毕而消失）
        初始化值不同：
        成员变量（有默认初始化值）
        局部变量（没有默认初始化值，必须先定义，赋值才能使用）
```

## 方法

方法（method）是将具有独立功能的代码块组织成为一个整体，使其具有特殊功能的代码集
注意：
方法必须先创建才可以使用，该过程成为方法定义
方法创建后并不是直接可以运行的，需要手动使用后，才执行，该过程成为方法调用

### 方法的定义和调用

#### 无参数方法定义和调用

```java
格式：
public static void 方法名(){
        // 方法体; 
        }

        范例：
public static void method(){
        // 方法体; 
        }

        调用格式：
        方法名();

        范例：
        method();

        注意：
        方法必须先定义，后调用，否则程序将报错

        总结：
        每个方法在被调用执行的时候，都会进入栈内存，并且拥有自己独立的内存空间，方法内部代码调用
        完毕之后，会从栈内存中弹栈消失。
```

#### 带参数方法定义和调用

```java
格式：
        参数：由数据类型和变量名组成-数据类型 变量名
        参数范例：int a
public static void 方法名(参数1){
        方法体;
        }
public static void 方法名(参数1,参数2,参数3...){
        方法体;
        }

        范例：
public static void isEvenNumber(int number){
        ...
        }
public static void getMax(int num1,int num2){
        ...
        }

        注意：
        方法定义时，参数中的数据类型与变量名都不能缺少，缺少任意一个程序将报错
        方法定义时，多个参数之间使用逗号( ，)分隔

        调用格式：
        方法名(参数)；
        方法名(参数1,参数2);

        范例：
        isEvenNumber(10);
        getMax(10,20);
        方法调用时，参数的数量与类型必须与方法定义中的设置相匹配，否则程序将报错
```

##### 形参和实参

```java
1.形参：方法定义中的参数,等同于变量定义格式，
        例如：
        int number
        2.实参：方法调用中的参数,等同于使用变量或常量，
        例如：
        10number
```

#### 带返回值方法的定义和调用

```java
定义格式：
public static 数据类型 方法名(参数){
        return 数据;
        }
        范例：
public static boolean isEvenNumber(int number){
        return true;
        }
public static int getMax(int a,int b){
        return 100;
        }
        注意：
        方法定义时return后面的返回值与方法定义上的数据类型要匹配，否则程序将报错

        调用格式：
        方法名(参数);
        数据类型 变量名=方法名(参数);

        范例：
        isEvenNumber(5);
        boolean flag=isEvenNumber(5);
        注意：
        方法的返回值通常会使用变量接收，否则该返回值将无意义 
```

### 方法的注意事项

#### 方法的注意事项

```java
方法不能嵌套定义
        示例代码：

public class MethodDemo {
  public static void main(String[] args) {
  }

  public static void methodOne() {
    public static void methodTwo () {
      // 这里会引发编译错误!!! 
    }
  }
}

void表示无返回值，可以省略return，也可以单独的书写return，后面不加数据
        示例代码：

public class MethodDemo {
  public static void main(String[] args) {
  }

  public static void methodTwo() {
    //return 100; 编译错误，因为没有具体返回值类型
    return;
    //System.out.println(100); return语句后面不能跟数据或代码 
  }
}
```

#### 方法的通用格式

```java
格式：
public static 返回值类型 方法名(参数){
        方法体;
        return 数据;
        }

        解释：
public static 修饰符，目前先记住这个格式
        返回值类型 方法操作完毕之后返回的数据的数据类型
        如果方法操作完毕，没有数据返回，这里写void，而且方法体中一般不写return

        方法名 调用方法时候使用的标识
        参数 由数据类型和变量名组成，多个参数之间用逗号隔开
        方法体 完成功能的代码块
        return 如果方法操作完毕，有数据返回，用于把数据返回给调用者
```

#### 定义方法时，要做到两个明确

```java
明确返回值类型：主要是明确方法操作完毕之后是否有数据返回，如果没有，写void；如果有，写对应的数据类型
        明确参数：主要是明确参数的类型和数量

        调用方法时的注意：
        void类型的方法，直接调用即可
        非void类型的方法，推荐用变量接收调用
```

### 方法重载

同类 同名 不同参

```java
方法重载指同一个类中定义的多个方法之间的关系，满足下列条件的多个方法相互构成重载
        多个方法在同一个类中
        多个方法具有相同的方法名
        多个方法的参数不相同，类型不同或者数量不同

        注意：
        重载仅对应方法的定义，与方法的调用无关，调用方式参照标准格式
        重载仅针对同一个类中方法的名称与参数进行识别，与返回值无关，换句话说不能通过返回值来判定两个方法是否相互构成重载

        正确范例：

public class MethodDemo {
  public static void fn(int a) {
    //方法体 
  }

  public static int fn(double a) {
    //方法体 
  }
}

public class MethodDemo {
  public static float fn(int a) {
    //方法体 
  }

  public static int fn(int a, int b) {
    //方法体 
  }
}
```

### 方法的参数传递

#### 方法参数传递基本类型

```java
测试代码：

public class ArgsDemo01 {
  public static void main(String[] args) {
    int number = 100;
    System.out.println("调用change方法前：" + number);
    change(number);
    System.out.println("调用change方法后：" + number);
  }

  public static void change(int number) {
    number = 200;
  }
}

结论：
        基本数据类型的参数，形式参数的改变，不影响实际参数

        结论依据：
        每个方法在栈内存中，都会有独立的栈空间，方法运行结束后就会弹栈消失
```

#### 方法参数传递引用类型

```java
测试代码：

public class ArgsDemo02 {
  public static void main(String[] args) {
    int[] arr = {10, 20, 30};
    System.out.println("调用change方法前：" + arr[1]);
    change(arr);
    System.out.println("调用change方法后：" + arr[1]);
  }

  public static void change(int[] arr) {
    arr[1] = 200;
  }
}

结论：
        对于引用类型的参数，形式参数的改变，影响实际参数的值

        结论依据：
        引用数据类型的传参，传入的是地址值，内存中会造成两个引用指向同一个内存的效果，所以即使方法
        弹栈，堆内存中的数据也已经是改变后的结果
```

## 类和对象

### 类

```java
类
        类的理解
        类是对现实生活中一类具有共同属性和行为的事物的抽象
        类是对象的数据类型，类是具有相同属性和行为的一组对象的集合
        简单理解：类就是对现实事物的一种描述
        类的组成
        属性：指事物的特征，在类中通过成员变量来体现（类中方法外的变量）
        例如：手机事物（品牌，价格，尺寸）
        行为：指事物能执行的操作，在类中通过成员方法来体现（和前面的方法相比去掉static关键字即可）
        例如：手机事物（打电话，发短信）
```

#### 类的加载

```java
当程序在运行后，第一次使用某个类的时候，
        会将此类的class文件读取到内存，
        并将此类的所有信息存储到一个Class对象中
        说明：
        Class对象是指java.lang.Class类的对象，此类由Java类库提供，专门用于存储类型的信息

        类的加载机制
        在以下情况下会加载类：
        1.创建一个类的对象（第1次）
        2.调用类的静态方法，静态变量 （第1次）
        3.使用一个类的子类时 （第1次）
        4.通过反射进行加载类
        5.java命令执行某一个类  （第1次）运行java程序

        .class文件中都包含
        构造方法
        成员变量
        成员方法

        在jvm执行某个类时，如果该类是第一次被执行：
        先把该类的.class文件读取到内存中
        基于.class文件创建一个Class对象（方法区）
        Class对象中存储的是.class文件中的内容：构造方法、成员变量、成员方法
        Class对象中存储的构造方法：构造器对象 Constructor对象
        Class对象中存储的成员变量：字段对象 Field对象
        Class对象中存储的成员方法：方法对象 Method对象
```

#### 类加载器

```java
类加载器是Java运行时环境的一部分，负责加载字节码文件
        作用：
        负责将磁盘上的某个class文件读取到内存并生成Class的对象

        Java中有三种类加载器，它们分别用于加载不同种类的class：
        启动类加载器(Bootstrap ClassLoader)：
        用于加载系统类库<JAVA_HOME>\bin目录下的class，例如：rt.jar。
        扩展类加载器(Extension ClassLoader)：
        用于加载扩展类库<JAVA_HOME>\lib\ext目录下的class。
        应用程序类加载器(Application ClassLoader)：
        用于加载我们自定义类的加载器。

        来自Class类型获取类加载器的方法：
public ClassLoader getClassLoader()  //返回该类的类加载器
        //有些实现可能使用null来表示引导类加载器(启动类加载器)
        //如果该类由引导类加载器(启动类加载器)加载，则此方法在这类实现中将返回 null
//获取String类的类加载器
        ClassLoader loader2=String.class.getClassLoader();

```

#### 双亲委派机制

```java
在使用一个类时，类并不会由应用程序类加载器来加载，而是由"双亲:启动类加载器、扩展类加载器"先来进行加载，当"双亲"无法加载时，才由应用程序类加载器来完成.class的加载及Class对象的创建

        工作机制：
        某个"类加载器"收到类加载的请求，它首先不会尝试自己去加载这个类，而是把请求交给父级类加载器
        因此，所有的类加载的请求最终都会传送到顶层的"启动类加载器"中
        如果"父级类加载器"无法加载这个类，然后子级类加载器再去加载
        "双亲委派模型"中，除了顶层的启动类加载器外，其余的类加载器都应当有自己的"父级类加载器"

        好处是：
        Java的类随着它的类加载器一起具备了一种带有优先级的层次关系。
        例如：`java.lang.Object`。
        它存放在rt.jar中。
        无论哪一个类加载器要加载这个类，最终都是委派给处于顶端的"启动类加载器"进行加载。
        因此java.lang.Object类在程序的各种类加载器环境中都是同一个类。
        相反，如果没有"双亲委派机制"，
        如果用户自己编写了一个java.lang.Object，
        那么当我们编写其它类时，这种隐式的继承使用的将会是用户自己编写的java.lang.Object类，
        那将变得一片混乱。


```

#### 抽象类

```
概述:
	没有方法体的方法称为抽象方法。Java语法规定，包含抽象方法的类就是抽象类。
	抽象类：	包含抽象方法的类。	通常为父类
	抽象方法： 	没有方法体的方法。	由子类重写

作用:	为了不让父类实例化

存在的意义:
抽象类存在的意义是为了被子类继承，否则抽象类将毫无意义，
**抽象类体现的是模板思想**，
模板是通用的东西抽象类中已经是具体的实现（抽象类中可以有成员变量和实现方法），
而模板中不能决定的东西定义成抽象方法，
让使用模板（继承抽象类的类）的类去重写抽象方法实现需求，
这是典型的模板思想。

抽象类构造方法是为了保障子类对象实例化成功的准备
```

### 对象

客观存在的事物皆为对象 ，所以我们也常常说万物皆对象

```java
创建对象的格式：
        类名 对象名=new 类名();
        调用成员的格式：
        对象名.成员变量
        对象名.成员方法();

        示例代码：
        创建对象格式：
        类名 对象名=new 类名();
        范例：Phone p=new Phone();
        使用对象：
        1：使用成员变量
        格式：对象名.变量名
        范例：p.brand
        2：使用成员方法
        格式：对象名.方法名()
        范例：p.call()

public class PhoneDemo {
  public static void main(String[] args) {
    //创建对象
    Phone p = new Phone();
    //使用成员变量 
    System.out.println(p.brand);
    System.out.println(p.price);
    p.brand = "小米";
    p.price = 2999;
    System.out.println(p.brand);
    System.out.println(p.price);
    //使用成员方法
    p.call();
    p.sendMessage();
  }
}
```

#### 匿名对象

没有对象名的对象

```java
有名对象：
        有对象名保存地址的对象
        Student stu=new Student();
        Scanner sc=new Scanner(System.in);

        匿名对象：
        没有对象名的对象就是匿名对象
        new Student();
        new Scanner(System.in);

        匿名对象怎么用
        匿名对象的使用特点：
        只能使用一次，对象名可以怎么用，匿名对象也可以怎么用。
        直接使用匿名对象调用方法
        直接当做一个参数传给方法
        直接当做返回值进行传递
```

### 类和对象的关系

```java
类：类是对现实生活中一类具有共同属性和行为的事物的抽象
        对象：是能够看得到摸的着的真实存在的实体
        简单理解：类是对事物的一种描述，对象则为具体存在的事物
```

## 面向对象

面向对象是一种思想，世间万物都可以看做一个对象
Java 是一个支持并发、基于类和面向对象的计算机编程语言。

```java
// 面向对象软件开发具有以下优点：
1.代码开发模块化，更易维护和修改。
        2.代码复用性强。
        3.增强代码的可靠性和灵活性。
        4.增加代码的可读性。

        // 面向对象的特征
        封装、继承、多态
```

### 封装

#### 概述&原则&好处

```java
// 封装概述：
    1.是面向对象三大特征之一（封装，继承，多态）
            2.是面向对象编程语言对客观世界的模拟，
            3.客观世界里成员变量都是隐藏在对象内部的，外界是无法直接操作的
// 封装原则：
            1.将类的某些信息隐藏在类内部，不允许外部程序直接访问，
            2.而是通过该类提供的方法来实现对隐藏信息的操作和访问 成员变量 private，
        3.提供对应的 getXxx()/setXxx()方法
// 封装好处：
        1.提高代码安全性，隐藏对象属性 保护对象内部状态，通过方法控制成员变量操作
        2.提高代码复用性，即可用性和可维护性 把代码用方法进行封装
```

#### 权限修饰符关键字

权限大小的特点：

public > protected > 包私有【缺省】> private

|                        | public | protected | （空的）包私有 | private |
| ---------------------- | ------ | --------- | -------------- | ------- |
| 同一类中               | √      | √         | √              | √       |
| 同一包中(子类与无关类) | √      | √         | √              |         |
| 不同包的子类           | √      | √         |                |         |
| 不同包中的无关类       | √      |           |                |         |

```java
public 公共的修饰符 权限最大 跨包
protected 受保护权限 跨包，，必须有继承
        package-private 包私有 默认权限 什么都不写 只能在同一包访问
private 私有权限 只能在本类访问
```

#### 构造器(Constructor)

[^生成对象。 初始化（对象、成员变量）]:

生成对象。 初始化（对象、成员变量）

##### 构造器的格式、特点、作用

```java
格式：
        修饰符 类名(形参){
        给成员变量初始化值
        }

        特点：
        1.没有返回值（即使void也不用写）
        2.方法名一定是和当前类的类名一样
        3.构造方法支持重载（同名不同参）

        作用：
        结合关键字new创建对象，也可以同时给成员变量赋值。
```

##### 构造方法的注意事项

```java
构造方法的创建：
        如果没有定义构造方法，系统将给出一个默认的无参数构造方法 如果定义了构造方法，系统将不再提供默认的构造方法
        构造方法的重载：
        如果自定义了带参构造方法，还要使用无参数构造方法，就必须再写一个无参数构造方法
        推荐的使用方式：
        无论是否使用，都手工书写无参数构造方法
        重要功能：
        可以使用带参构造，为成员变量进行初始化
```

##### this关键字

this在构造器中就是指当前创建的对象，如果在实例方法中就是调用方法的对象

```java
this修饰的变量用于指代成员变量，其主要作用是（区分局部变量和成员变量的重名问题）
        方法的形参如果与成员变量同名，不带this修饰的变量指的是形参，而不是成员变量
        方法的形参没有与成员变量同名，不带this修饰的变量指的是成员变量
```

### 继承(extends)

#### 概述

子类继承父类，可以使用父类中的非私有成员

##### 思想

```
把共性的属性或者行为抽取到父类中。
定义子类继承父类。重复的部分不用定义，特有的需要定义。
```

##### 好处

提高代码的复用性

##### 格式

```java
class A {
}

class B {
}

A继承B

class A extends B {
}
```

#### 类的继承特点

```java
1.支持单继承，只有一个父类即亲爹
        2.支持多重继承，一个类可以有一个父类，父类还可以继续继承其他类即一个人有爷爷

class Child extends Father {
}

class Father extends GrandPa {
}
	对于Child类的对象可以使用GrandPa类中的非私有成员
            3.不支持多继承，一个子类不能同时继承多个父类，即一个人不能有多个亲爹
```

#### 成员的继承特点

##### 1.成员方法

```java
方法重写，满足条件
        1.方法返回值类型 方法名 方法参数列表中的参数类型 必须相同
        2.子类的权限修饰符大于等于父类的

        方法重写作用：
        可以修改父类的方法，例如Object中的equals toString hashCode等
```

###### 方法重写

当父类中方法继承到子类后，不满足子类需要的时候，子类就可以对这个方法进行覆盖，重写（Override）。

```java
声明不变，重新实现。

        方法的声明：
        修饰符 返回值类型 方法名(参数列表)

        逻辑的重新实现：
        方法体的逻辑重写

        修饰符 返回值类型 方法名(参数列表){
        方法体的逻辑重写
        }
```

##### 2.构造方法

```java
子类调用父类的构造方法格式：
        super(实参);

        隐式三步
        1.super();默认的调用父类的无参构造方法
        2.显示给非静态成员变量赋值
        3.执行本类的构造代码块
```

##### 3.成员变量

```java
父类定义成员变量，子类直接使用。

        子类调用父类的成员变量格式：
        super.父类成员变量名
```

### 多态

###     	

## 集合

### List集合

List集合：一次只能存储1个元素（单列集合）

特点：

- 有索引。 可以利用索引访问集合、可以利用索引遍历集合
- 允许存储重复元素
- 存取元素的顺序一致（存储时的顺序和获取集合中元素的顺序一致）

#### ArrayList集合

- 特点：底层使用数组结构

  - 优势：查询快、增删慢

- 特有方法：

  - ~~~java
    public boolean add(int index , Object obj)
    public Object remove(int index)
    public Object get(int index)
    public Object set(int index , Object obj)    
    ~~~

#### LinkedList集合

- 特点：底层使用双向链表结构。 有头有尾（每次都是从头部或尾部依次向后查询元素）

  - 优势：增删快、查询慢

- 特有方法：

  - ~~~java
    public void addFirst(Object obj)
    public void addLast(Object obj)
        
    public Object  removeFirst()
    public Object  removeLast()
        
    public Object  getFirst()
    public Object  getLast()   
    ~~~

#### 遍历：

```java
//普通的for循环

//迭代器：Iterator

//专用迭代器：ListIterator

//增强for循环
```

### Set集合

Set集合： 一次只能存储1个元素（单列集合）

Set集合的特点：

- 没有索引
- 不能存储重复元素
- 存取元素有顺序不保证一致

#### HashSet集合

- 特点：底层使用哈希表结构
  - 保证存储元素唯一的方式： 要存储的元素自身重写hashCode()、equals()方法

#### LinkedHashSet集合

- 特点：底层使用链表+哈希表结构
  - 存储元素的顺序和获取元素顺序一致（存取有序）

#### TreeSet集合

- 特点：底层使用红黑树

  - 存储的元素会自动排序

    - 排序机制：

      - 自然排序： 存储的元素自身要实现Comparable接口，并重写comparaTo方法

      - 比较器排序：在创建TreeSet集合对象时，传递Comparator对象进行排序

 ~~~java
TreeSet set=new TreeSet(new Comparator(){
//重写compare方法
public int compare(Object o1,Object o2){

        }
        });
 ~~~

#### 遍历：

```java
//迭代器：Iteratore

//增强for循环
```

### Map集合

Map集合：一次存储2个元素（Key元素、Value元素）

Map集合的特点：

- 存储的元素为key和value两个元素
- 存储的key元素不能重复、value元素可以重复
- 一个key元素对应一个value元素（一 一对应）
- 存取顺序不保证一致
- 没有索引
- 不能直接对Map集合进行遍历

#### HashMap集合

- 特点：底层使用哈希表结构
  - 保证存储元素唯一的方式： 要存储的元素自身重写hashCode()、equals()方法

#### LinkedHashMap集合

- 特点：底层使用链表+哈希表结构
  - 存储元素的顺序和获取元素顺序一致（存取有序）

#### TreeMap集合

- 特点：底层使用红黑树
  - 存储的元素会自动排序（以Key元素为主进行排序）
    - 排序机制：
      - 自然排序： 存储的元素自身要实现Comparable接口，并重写comparaTo方法
      - 比较器排序：在创建TreeSet集合对象时，传递Comparator对象进行排序

Map集合的常用方法：

~~~java
public V put(K key,V value) //添加、修改
public V remove(K key)
public V get(K key)
public boolean containsKey(K key)

//遍历使用
public Set<K>  ketSet()
public Set<Map.Entry<K, V>>entrySet()    
~~~

#### 遍历：

```java
//Map集合不能直接遍历。  
//方式1： 先所有所有的Key元素，遍历Key元素，找到对应的value元素

//方式2： 获取所有的Key-Value元素（Map.Entry对象）。 遍历所有的Entry对象，利用Entry对象分别获取Key、Value
```

###     

## Stream流

思想

Stream流处理数据，就是一种流水线作业

### Stream流对象的获取(转换成流)

Stream流是Java1.8才出现的。处理极大的简化了对于集合、数组等的操作，只要是可以转换成流，我们都可以借助流式处理。

#### Collection获取Stream对象

JDK8开始，Collection存在一个默认方法stream去获取流对象。

所有Collection的实现类都具有该方法，能够直接使用集合对象调用该方法。

```java
default Stream<E> stream()返回以此集合作为源的顺序 Stream

//List 集合
        List<String> list=new ArrayList<>();
        Stream<String> s1=list.stream();

//Set 集合
        Set<String> set=new HashSet<>();
        Stream<String> s2=set.stream();
```

#### Map获取Stream对象

Map中没有专属的获取流的默认方法。可以先将Map集合转换为Collection集合，再转换为Stream就可。

```java
//键值对
如果要使用Stream处理键值对对象数据，可以借助entrySet
        Set<Map.Entry<K, V>>entrySet()将Map集合中的键值对对象，放到Set集合中并返回

//键
        如果要使用Stream处理Map集合的键，可以借助keySet
        Set<K> keySet()将Map集合中的所有键放到一个Set集合中并返回

//值
        如果要使用Stream处理Map集合中的值，可以借助
        Collection<V> values()将所有的值放到一个Collection集合中
```

```java
 //获取键值对对象对应的流
Stream<Map.Entry<String, String>>s1=map.entrySet().stream();
//获取键对应的流
        Stream<String> s2=map.keySet().stream();
//获取值对应的流
        Stream<String> s3=map.values().stream();
```

#### 数组获取Stream对象

Stream接口本身存在一个静态方法：of

```java
static<T> Stream<T> of(T...values)可以指定该类型的数组或者单个的多个数据也可以转换为流
```

```java
//数组
String[]arr={"A","B"};
        Stream<String> s1=Stream.of(arr);
//单个存在的多个数据
        Stream<String> s2=Stream.of("A","B","C");
```

### Stream流中间操作(非终结方法)

非终结方法：也叫做**函数拼接方法**，返回值类型是Stream，支持链式调用。

流的中间操作方法：过滤，去重，排序，映射，拼接

| f                    | 方法                                                 | 作用                                       |
| -------------------- | :--------------------------------------------------- | ------------------------------------------ |
| Stream<T>            | filter (Predicate<? super T> predicate)              | 过滤  根据条件过滤不需要的数据             |
| Stream<T>            | limit (long n)                                       | 过滤  获取流中前n个数据，不足n个获取全部   |
| Stream<T>            | skip (long n)                                        | 过滤  跳过流中前n个数据，不足n个则没有数据 |
| Stream<T>            | distinct ()                                          | 去重（equals比较为true的元素会被去重）     |
| Stream<T>            | sorted ()                                            | 排序  将流中元素按照自然排序的规则排序     |
| Stream<T>            | sorted (Comparator<? super T> comparator)            | 排序  将流中元素按照自定义比较器规则排序   |
| <R>Stream<R>         | map(Function<? super T, ? extends R> mapper);        | 映射  将流中数据转换为另外一种想要的数据   |
| static <T> Stream<T> | concat(Stream<? extends T> a, Stream<? extends T> b) | 合并  将两个流合并为一个流                 |

```java

```

### Stream流终端操作数据收集

终结方法：返回值类型不再是Stream，不支持链式调用

以集合为例，一个流式处理的操作我们首先需要调用stream()
函数将其转换成流，然后再调用相应的中间操作达到我们需要对集合进行的操作，比如筛选、转换等，最后通过终端操作对前面的结果进行封装，返回我们需要的形式。

获取流后，经过一系列中间方法调用操作后，需将结果收集起来或做遍历

|            | 方法                                | 作用                             |
| ---------- | ----------------------------------- | -------------------------------- |
| long       | count()                             | 统计  流中数据的个数             |
| void       | forEach(Consumer<? super T> action) | 遍历  流中的数据                 |
| static <T> | collect(Collectors.toList())        | 收集  将流中数据收集到List集合中 |
| static <T> | collect(Collectors.toSet ())        | 收集  将流中数据收集到Set集合中  |
| Object[]   | toArray()                           | 收集  将流中数据收集到数组中     |

```java
//统计 流中数据的个数
long count=list.stream()
        .count();
        System.out.println("count = "+count);

//遍历 流中的数据
        list.stream()
        .forEach(new Consumer<String>(){
@Override
public void accept(String name){
        System.out.println(name);
        }
        });
```

## 反射

```java
反射技术就是把一个类进行了解剖，然后获取到 构造方法、成员变量、成员方法

        必备条件：
        Class对象
        原因：
        .class文件由类加载器读取并创建Class对象。
        Class对象中存储了.class文件中的内容：构造方法、成员变量、成员方法

        作用1
        使用反射技术，可以对类进行解剖，可以获取到类中的：构造方法、成员变量、成员方法
        构造方法： 可以创建对象
        成员方法： 可以调用执行
        成员变量： 赋值、取值
        作用2
        不用使用new关键字，就可以创建对象
        不用使用"对象名.方法"形式，就可以调用方法
        不用使用"对象名.属性"形式，就可以给属性赋值、取值
        通常在类中属性，都被修饰为private（私有的：外部不能访问）
        反射技术，可以做到对私有成员进行操作
        作用3
        程序在运行中,使用反射技术，可以在JVM运行状态下,动态的获取一个Student.

class

示例：
        1.创建一个字符串对象
        "cn.itcast.pojo.Student"stu=new cn.itcast.pojo.Student();
```

#### 反射操作Class对象

```java
Class就是用来描述正在运行的java类型
        `Class`类的实例表示Java中任何正在运行的类型，每一个类型都有与之对应的Class对象
        比如：
        类，接口，枚举，注解，数组，基本数据类型，void 都有与之对应的Class对象
        类名.

class
接口名.class
		int.class
		boolean.

class
array.

class

获取Class类对象的方式有3种:
        方式一：类型名.

class   //Student.class
方式二：对象.getClass()  //对象名.getClass()
        方式三：Class.forName(String className) //className是全路径类名 = 包名+类型名

        方式1：类型名.

class
应用场景： 当类名明确时，可以直接使用"类名.class"
        Class clz=String.

class

方式2：对象.getClass()
        应用场景：通常是应用方法中
public void method(Student stu){
        Class clz=stu.getClass();
        }

        方式3： Class.forName("类的全名称");//带有包名的类   Class类中静态方法forName
        应用场景： 通常使用在读取配置文件中的类型
        Class StudentClass=Class.forName("cn.icast.pojo.Student")

//当获取到Class对象了，就可以对类进行解剖了
        Class对象中存储的构造方法：构造器对象 Constructor对象
        Class对象中存储的成员变量：字段对象 Field对象
        Class对象中存储的成员方法：方法对象 Method对象
```

#### 反射操作构造方法

```java
目的：
        获得Constructor对象来创建类的对象
        大白话：不使用new关键字，通过Constructor来创建对象

        获取Constructor对象
        Constructor对象的获取和Class类中方法有关：

        1.获得类中的所有构造方法对象，只能获得public的
        Constructor[]getConstructors()

        2.获得类中的所有构造方法对象,可以是public、protected、(默认)、private修饰符的构造方法
        Constructor[]getDeclaredConstructors()

        3.根据参数类型获得对应的Constructor对象 获取public修饰的
        Constructor getConstructor(Class...parameterTypes)
			/*示例： Student       
			public Student(String name, int age)    public Student(int age) 
        		Class stuClass = Student.class;
        		//根据给定的参数类型，来获取匹配的构造器对象
        		Constructor c = stuClass.getConstructor( String.class , int.class );*/

        4.根据参数类型获得对应的Constructor对象,可以是public、protected、默认、private修饰符的构造方法
        Constructor getDeclaredConstructor(Class...parameterTypes)

        Constructor类常用方法
        1.根据指定的参数创建对象
        T newInstance(Object...initargs)
		/*
        Class stuClass = Student.class;
        //根据给定的参数类型，来获取匹配的构造器对象
        Constructor c = stuClass.getConstructor( String.class , int.class );
        //使用构造器对象，来实例化Student
        c.newInstance( "张三", 22 );//Student(String name, int age)
           
        //无参构造
        Constructor c = stuClass.getConstructor();
        Student stu = (Student) c.newInstance();*/


        void setAccessible(true)//应用场景：仅适用于访问有权限检查的成员
//设置"暴力反射" ——是否取消权限检查，true取消权限检查，false表示不取消
```

#### 反射操作成员方法

#### 反射操作成员变量

## API

### java.lang

#### String字符串(API)

String 类代表字符串，Java 程序中的所有字符串文字（例如“abc”）都被实现为此类的实例。
也就是说，Java 程序中所有的双引号字符串，都是 String 类的对象。String 类在 java.lang 包下，
所以使用的时候不需要导包！

```java
特点：
        字符串不可变，它们的值在创建后不能被更改
        虽然 String 的值是不可变的，但是它们可以被共享
        字符串效果上相当于字符数组(char[])，但是底层原理是字节数组(byte[])
```

##### 构造方法

| 方法名                      | 说明                                      |
| --------------------------- | ----------------------------------------- |
| public   String()           | 创建一个空白字符串对象，不含有任何内容    |
| public   String(char[] chs) | 根据字符数组的内容，来创建字符串对象      |
| public   String(byte[] bys) | 根据字节数组的内容，来创建字符串对象      |
| String s =   “abc”;         | 直接赋值的方式创建字符串对象，内容就是abc |

### java.util

#### Scanner数据输入(API)

```java
我们可以通过 Scanner 类来获取用户的输入。使用步骤如下：
        1、导包。Scanner 类在java.util包下，所以需要将该类导入。导包的语句需要定义在类的上面。
import java.util.Scanner;
2、创建Scanner对象。
        Scanner sc=new Scanner(System.in);// 创建Scanner对象，sc表示变量名，其他均不可变
        3、接收数据
        int i=sc.nextInt(); // 表示将键盘录入的值作为int数返回。

        示例：
import java.util.Scanner;

public class ScannerDemo {
  public static void main(String[] args) {
    //创建对象 
    Scanner sc = new Scanner(System.in);
    //接收数据 
    int x = sc.nextInt();
    //输出数据 
    System.out.println("x:" + x);
  }
}
```

#### Random生成随机数(API)

Random类似Scanner，也是Java提供好的API，内部提供了产生随机数的功能

```java
使用步骤：
        1.导入包
import java.util.Random;
2.创建对象
        Random r=new Random();
        3.产生随机数
        int num=r.nextInt(10);
        解释： 10代表的是一个范围，如果括号写10，产生的随机数就是0-9，括号写20，参数的随机数则是0-19

        示例代码：
import java.util.Random;

public class RandomDemo {
  public static void main(String[] args) {
    //创建对象 
    Random r = new Random();
    //用循环获取10个随机数 
    for (int i = 0; i < 10; i++) {
      //获取随机数 
      int number = r.nextInt(10);
      System.out.println("number:" + number);
    }//需求：获取一个1-100之间的随机数 
    int x = r.nextInt(100) + 1;
    System.out.println(x);
  }
}
```

#### Date存储日期时间(API)

- 存储日期时间的对象

- 使用：

  - ~~~java
    //构造方法
    Date date1 = new Date();//获取当前系统的日期时间
    Date date2 = new Date(毫秒值);//根据给定的毫秒值，创建日期对象
    
    //常用方法：
    long getTime() //获取毫秒值
    void setTime(long  time) //设置毫秒值   
    ~~~

##### DateFormat相互转换Date和String

- 作用：实现Date类型和String类型之间的相互转换（抽象类）

- 使用：

  - 构造方法

    - ~~~java
      //java.text.DateFormate（抽象类）
      DateFormate df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      ~~~

  - 常用方法

    - ~~~java
      //Date => String
      //public String  format(Date  date)
      String strDate = df.format( new Date() );
      
      //String => Date
      String strDate="2021-05-12 18:50:00";
      Date  date = df.parse( strDate );
      ~~~

##### Calendar代替Date已过时功能

- 日历类。用来代替Date类中已过时的功能

- 使用：

  - 构造方法

    - ~~~java
        //Calendar是抽象类
        Calendar c = Calendar.getInstance();
        ~~~

  - 常用方法

    - ~~~java
        //get   获取指定日历字段中的数据值
        int 变量  = c.get( 日历字段 )
        int year = c.get( Calendar.YEAR );
        
        
        //set   修改日历对象中的日历字段值
        set(日历字段 ， 修改的值)
        c.set( Calendar.MONTH, 5);//月份：从0到11
        
        
        //add   基于指定的日历字段，增加或减少数值
        add( 日历字段 ， 数值)
            
        c.add( Calendar.DATE , 1);//在当前的日期基础上，添加1天
        
        c.add( Calendar.YRAR, -1)//在当前日期基础上，减少1年
        ~~~

### java.io

#### File

java.io.File 文件和目录路径名的抽象表示形式。

用来描述计算机中的文件和文件夹，使用路径来表示。

##### 构造方法

```java
//通过将给定路径名字符串        转换为抽象路径名来创建一个新 File 实例。 
File(String pathname)

//根据 parent 路径名字符串和 child 路径名字符串  创建一个新 File 实例。  
        File(String parent,String child)

//根据 parent 抽象路径名和 child 路径名字符串    创建一个新 File 实例。 
        File(File parent,String child) 
```

##### 获取功能的方法

|        | 方法              | 作用                                                         |
| ------ | ----------------- | ------------------------------------------------------------ |
| String | getAbsolutePath() | 返回此File的绝对路径名字符串。                               |
| String | getPath()         | 将此File转换为路径名字符串。 【构造路径】                    |
| String | getName()         | 返回由此File表示的文件或目录的名称。 【如果有后缀，包含】    |
| long   | length()          | 返回由此File表示的文件的长度（文件的大小，单位是字节）。 不能获取目录的长度。 |

```java
//获取绝对路径
public String getAbsolutePath()

//获取构造路径
public String getPath()

//获取文件名
public String getName()

//获取文件的大小
public long length()
```

##### 判断功能方法

|         | 方法            | 作用                                 |
| ------- | --------------- | ------------------------------------ |
| boolean | exists()        | 此File表示的文件或目录是否实际存在。 |
| boolean | isDirectory()   | 此File表示的是否为目录。             |
| boolean | isFile()        | 此File表示的是否为文件。             |
|         | getParentFile() |                                      |

```java
//判断是否存在
public boolean exists()

//判断是否是目录
public boolean isDirectory()

//判断是否是文件
public boolean isFile()
```

##### 创建删除功能方法

|         | 方法            | 作用                                                     |
| ------- | --------------- | -------------------------------------------------------- |
| boolean | createNewFile() | 当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。 |
| boolean | mkdir()         | 创建由此File表示的目录。                                 |
| boolean | mkdirs()        | 创建由此File表示的目录，包括任何必需但不存在的父目录。   |
| boolean | delete()        | 删除由此File表示的文件或目录。                           |

```java
//创建指定路径的文件，要求文件路径是合法的，否则会报错
public boolean createNewFile()

//创建文件夹，只能创建单级文件，如果有父目录是空的不能创建
public boolean mkdir()

//创建文件夹，如果父目录不存在，可以将父目录页创建出来
public boolean mkdirs()

//删除文件夹（是空的）或者文件，删除不走回收站，谨慎删除
public boolean delete()
```

##### 目录遍历方法

|          | 方法        | 作用                                                     |
| -------- | ----------- | -------------------------------------------------------- |
| String[] | list()      | 返回一个String数组，表示该File目录中的所有子文件或目录。 |
| File[]   | listFiles() | 返回一个File数组，表示该File目录中的所有的子文件或目录。 |

```java
//获取所有子文件的名字
public String[]list()

//获取所有子文件的文件对象
public File[]listFiles()
```































