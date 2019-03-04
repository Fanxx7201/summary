## JVM

### 1.谈谈你读JAVA的理解?
* 平台无关性
* GC
* 语言特性
* 面向对象
* 类库
* 异常处理

### 2.CompileOnce, RunAnywhere如何实现?
回答: 源代码.java文件, 通过javac的编译, 生成字节码文件, 保存到.class文件中去. 
字节码和.class文件是跨平台的基础.  
有了.class文件, JVM(虚拟机)才能加载对应的类, JAVA提供了各种不同平台的虚拟机, 可以实现到处执行.
 ![Image text](https://github.com/Fanxx7201/summary/blob/master/img/jvm.png)

* class文件: javac命令对代码进行编译. .class文件保存的是java文件翻译成的二进制字节码.
java类中的属性、方法、常量信息等等都会被保存在.class文件当中.
会添加一个公有 常量属性(.class), 这个属性记录了类的相关信息及类型信息, 是class的一个实例. 

* javap: java自带的反汇编器. 可以看到java的字节码. (命令: javap -help, 打开帮助文档. -c是对代码进行反汇编)
> 什么是反汇编? 
> 编译: java文件通过javac编译, 生成字节码文件, 保存到.class文件中去.   
> 反编译: 将.class文件反编译成为java文件   
> 命令: javap -c 类的全限定类名  
> 不指定构造函数时, 编译器会为我们默认生成一个不带参的构造函数.  

### 2.1 为什么JVM不直接将源码解析成机器码去执行?
* 直接执行的话, 每次执行都需要进行各种检查, 而且这些检查不能被保留, 这样做了很多重复的事情.
* 兼容性的需求: 可以将别的语言解析成字节码, 能增加平台的兼容、拓展能力.

### 3.JVM如何加载.class文件?


### 3.1 理解java虚拟机?
* 虚拟机是抽象化的计算机. JVM有处理器, 堆栈, 寄存器等等...虚拟机屏蔽了底层操作系统的不同, 
减少基于原生语言开发的复杂性.

### 3.2JVM架构?
![Image text](https://github.com/Fanxx7201/summary/blob/master/img/JVM%E6%9E%B6%E6%9E%84.png)
* Class loader: 加载我们刚编译好的class文件到内存中
* Execution Engine: 解析class文件中的字节码, 提交到操作系统中执行
* Native Interface: 融合不同的开发语言的原生库为java所用.
> 源码中的private static native接口, 指的就是Native Interface
* Runtime Data Area: JVM内存空间结构模型

### 4.谈谈反射?
回答: java反射机制, 是在运行状态中, 对于任意一个类, 都能够知道这个类的所有属性和方法; 对于任意一个对象, 都能够调用它的任意方法和属性.
这种动态获取信息以及动态调用对象方法的功能, 称为java的反射机制.

### 4.1写一个反射的例子?
-- javabasic.reflect

### 5.类从编译到执行的过程
* 编译器将Robot.java源文件编译为Robot.class字节码文件
* ClassLoader将字节码转换为JVM中的Class<Robot>对象
* JVM利用Class<Robot>对象实例化为Robot对象

### 5.1 谈谈ClassLoader
回答: ClassLoader在java中有非常重要的作用, 它主要在Class装载的加载阶段, 主要作用是从系统外部获得Class二进制数据流.
它是JAVA的核心组件, 所有的Class都是由ClassLoader进行加载的, ClassLoader负责将Class文件中的二进制数据流装载到系统,
然后交给JAVA虚拟机进行连接、初始化等操作.
> 最重要的方法: loadClass(String name)

### 5.2 ClassLoader的种类
* BootStrapClassLoader: C++编写, 加载核心库java.*
* ExtClassLoader: JAVA编写, 加载扩展库javax.* (用到才会做加载)
* AppClassLoader: JAVA编写, 加载程序所在目录
* 自定义ClassLoader: JAVA编写, 定制化加载

### 6.谈谈类加载器的双亲委派机制?
* 不同的ClassLoader加载类的方式和路径不同.加载类的时候, 肯定会根据自己加载的区域各司其职. 所以会存在一个机制, 让他们之前相互
协作, 形成一个整体. 这就是双亲委派机制.
![Image text](https://github.com/Fanxx7201/summary/blob/master/img/classloader.png)

### 6.1为什么要使用双亲委派机制去加载类?
* 避免多份同样字节码的加载

### 7.类的加载方式?
* 隐式加载: new 
* 显式加载: loadClass, forName等. 获取到对象之后, 要通过newInstance()方法获取对象的实例.

### 7.1 loadClass和forName的区别?
回答:
* forName得到的class是已经初始化完成的
* loadClass得到的class是还没有链接的

> 代码: loadDiff.java  
> 举例说明: 如果我们要创建数据库的链接, 就不能用loadClass, 而是要用forName. 
因为mysql驱动, 是有一段静态代码的, 要通过
    forName才能获取.

### 7.2 类的装载过程?
* 加载
* 链接
* 初始化

![Image text](https://github.com/Fanxx7201/summary/blob/master/img/%E7%B1%BB%E7%9A%84%E8%A3%85%E8%BD%BD%E8%BF%87%E7%A8%8B.png)

---

### 8.你了解java的内存模型吗?
* 内存简介: 程序执行的过程之中, 需要不断将内存的逻辑地址和物理地址进行映射, 
找到相关的指令以及数据去执行.
* 程序计数器: 
    > 当前线程所执行的字节码行号指示器.   
    > 字节码解释器工作时, 是通过改变计数器的值来选取下一条需要执行的字节码指令, 包括分支、循环、跳转异常处理, 线程恢复等基础功能都需要依赖计数器来完成.
    > 和线程是一对一的关系, 即"线程私有".  
    > 对java方法计数, 如果是Native方法, 计数器的值为Undefined  
    > 不会发生内存邪路泄露(原因是只记录行号)  
    
* JAVA虚拟机栈(Stack)
    > JAVA方法执行的内存模型  
    > 包含多个栈帧(栈帧: 方法运行期间的基础数据结构, 存储: 局部变量表, 操作栈, 动态连接, 返回地址)  
    

![Image text](https://github.com/Fanxx7201/summary/blob/master/img/JVM%E5%86%85%E5%AD%98%E6%A8%A1%E5%9E%8B.png)

### 8.1 局部变量表和操作数栈的区别?
* 局部变量表: 包含方法执行过程中的所有变量
* 操作数栈: 入栈, 出栈, 复制, 交换, 产生消费变量

### 8.2 递归为什么会引发java.lang.StackOverFlowError异常?
* 代码: Fibonacci.java
回答: 线程执行方法, 会随之创建一个栈帧, 将建立的栈帧压入到虚拟机栈中, 方法运行完毕, 栈帧出栈, 因此可知, 
java运行的栈帧必定在java栈的顶部, 递归函数不断去调用自身, 每次调用, 
①创建一个栈帧, ②保存当前方法的栈帧状态, 将他放在虚拟机栈中.
③栈帧上下文切换的时候, 会切换到最新的方法栈中.  
由于我们每个虚拟机栈帧的深度是有限的, 如果栈帧数超过了虚拟栈的深度, 就会报这个错误.

* 解决思路: 限制递归的次数, 或者是使用循环的方法去替换递归.

### 8.3 虚拟机栈过多会引发java.lang.OutOfMemoryError异常?
虚拟机栈动态扩展时, 无法申请足够多的内存, 就会抛这个异常

### 8.4 元空间(MetaSpace) 和永久代(PermGen)的区别?
回答:
* 元空间使用本地内存, 而永久代使用的是jvm内存.
> 最直观的显示是, java.lang.OutOfMemoryError: PermGen space 不复存在. 
本地内存剩余多少, metaSpace就可以
有多大
* MetaSpace没有字符串常量池, jdk7的时候已经被移动到了堆中. 

### 8.5 MetaSpace相比PermGen的优势?
* 字符串常量池存在永久代中,容易出现性能问题和内存溢出.
* 类和方法的信息大小难以确定, 给永久代的大小指定带来困难(太小永久代溢出, 太大老年代溢出)
* 永久代会为GC带来不必要的复杂度
* 方便HotSpot与其他JVM如Jrockit的集成

### 8.6 JAVA堆(线程共享区域)
* 对象实例的分配区域
* GC管理的主要区域

### 常考题1: JVM三大性能调优参数-Xms -Xmx -Xss的含义?
* 命令使用:java -Xms128m -Xmx128m -Xss256k -jar xxxx.jar
* -Xss : 规定每个线程虚拟机栈(堆栈)大小, 影响并发线程数的大小. 256k足够了
* -Xms : 堆的初始值.线程刚创建出来的时候, java堆的大小.(一旦对象容量, 超过了初始的堆大小, 就会自动扩容. 扩大到-Xmx)
* -Xmx : 堆能达到的最大值(-Xms和-Xmx设置成一样的.heap不够用, 扩容时, 会发生抖动, 影响程序的稳定.)

### 常考题2: JAVA内存模型中堆和栈的区别? -内存分配策略
* 静态存储: 编译时确定每个数据目标在运行时的存储空间需求, 
编译时确定固定的内存空间(代码中不允许有可变数据结构存在, 
不允许嵌套或者是递归的情况出现, 会导致无法计算准确的存储空间)
* 栈式存储: 数据区需求在编译时未知, 运行时模块入口前确定, 先进后出原则进行分配
* 堆式存储: 编译时或者运行时模块入口都无法确定, 动态分配.(可变长度串和对象实例, 
有大片的可利用块或者空闲块组成, 内存可以按任意顺序分配和释放)

* 堆和栈的联系: 引用对象、数组时, 栈里定义变量保存堆中目标的首地址.
![Image text](https://github.com/Fanxx7201/summary/blob/master/img/%E5%A0%86%E5%92%8C%E6%A0%88%E7%9A%84%E8%81%94%E7%B3%BB.png)

### 常考题2: 堆和栈的区别?
* 管理方式: 栈自动释放, 堆需要GC
* 空间大小: 栈比堆小
* 碎片相关: 栈产生的碎片远小于堆
* 分配方式: 栈支持静态和动态分配, 而堆仅支持动态分配
* 效率: 栈的效率比堆高(栈的灵活程度不够, 而堆是动态分配, 双向列表. 操作堆比较复杂.)

### 常考题3: 不同JDK版本之间的intern()方法的区别-JDK6 VS JDK6+
String s = new String("a");
s.intern();
* JDK6: 当调用intern方法时, 如果该字符串常量池先前已经创建出该字符串对象, 则返回池中的该字符串的引用.
否则, 将此字符串对象添加到字符串常量池中, 并且返回该字符串对象的引用.
* JDK6+ : 当调用intern方法时, 如果字符串常量池先前已经创建出该字符串对象, 则返回池中的该字符串的引用.
否则, 如果该字符串对象已经存在于java堆中, 则将堆中对此对象的引用添加到字符串常量池中, 并且返回该引用;如果堆中不存在,则在池中创建该字符串并
返回其引用.






