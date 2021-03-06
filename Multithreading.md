# 8: 进程与线程的区别？

回答: 进程是资源分配的最小单位, 线程是CPU调度的最小单位.
1. 线程并能看作是独立应用, 而进程可以看作是独立的应用
2. 进程有独立的地址空间, 相互不影响, 线程只是进程的不同执行路径
3. 线程没有独立的地址空间, 多进程的程序比多线程的程序健壮
4. 进程的切换比线程的切换开销大.

* 串行: 初期计算机串行执行任务, 需要长时间等待客户输入数据
* 批处理: 预先将用户指令集中成清单, 批量串行处理用户的指令, 依然无法并发执行
* 进程: 进程独占内存空间, 保存各自的运行状态, 互相不干扰可以互相切换, 为并发处理提供可能
* 线程: 共享进程的内存资源, 互相间切换更快速, 支持更细粒度的任务控制, 使进程内的子任务得以并发执行.

# 8.1 java进程和线程的关系？
* java对操作系统提供的功能进行封装，包括进程和线程。
* 运行一个程序会产生一个进程， 进程包括至少一个线程
* 每个进程对应一个JVM实例， 多个线程共享JVM里的堆
* JAVA采用单线程编程模型， 程序会自动创建主线程
* 主线程可以创建子线程，原则上要后于子线程完成执行（因为要进行一系列的关系操作安装）

### 8.2 线程的start和run方法 的区别？
* start() 是新创建一个新的子线程， 并启动（调用JVM的startThread方法）
* run()方法知识Thread的一个普通方法的调用.还是在主线程的层面.

### 8.3 Thread和Runnable是什么关系?
* Thread是一个类, Runnable是一个接口.
* Thread是实现了Runnable接口的类, 使得run支持多线程
* 因类的单一继承原则, 推荐多使用Runnable接口

### 9.如何实现处理线程的返回值
实现的方式3种
1. 主线程等待法
> 优点: 代码简单
> 缺点: 需要代码完成循环等待, 如果等待变量过多, 会导致代码异常臃肿. 循环多久也不确定.没办法更精准的控制

2. 使用Thread类的join()阻塞当前线程以等待子线程处理完毕.
> 优点: 代码简单
> 缺点: 粒度不够细

3.通过Callable接口实现: 通过FutureTask Or线程池获取.

>线程池的优势: 提交多个实现callable的方法的类, 让线程池并发去处理结果.方便统一管理




### 9.1 如何给run()方法传参?
* 构造函数传参
* 成员变量传参
* 回调函数传参


### 10.线程的状态?
* 新建:创建后尚未启动
* 运行(Runnable): running和ready
* 无限期等待(Waiting): 不会被分配CPU执行时间, 需要显式被唤醒
> 没有设置Timeout参数的Object.wait() 方法   
> 没有设置Timeout参数的Thread.join()方法   
> LockSupport.park()方法  
* 限期等待(TimeWaiting):一定时间后系统自动唤醒
> Thread.sleep()方法
> 设置了Timeout参数的Object.wait()方法
> 设置了Timeout参数的Thread.join方法
> LockSupport.parkNanos()方法
> LockSupport.partUntil()方法
* 阻塞(Blocked): 等待获取排他锁
* 结束(Terminated): 已终止线程的状态, 线程已经结束执行.

### 11.sleep和wait的区别?
* 基本的差别
> sleep是Thread类的方法, wait是Object类中定义的方法  
> sleep可以在任何地方使用. wait只能在synchronized方法或者是synchronized块中使用  
* 最主要的本质区别
> Thread.sleep只会让出CPU, 不会导致锁行为的改变
> Object.wait不仅让出CPU, 还会释放已经占有的同步资源锁.


### 12.notify 和 notifyAll的区别?(可以用来唤醒无限期等待中的线程.wait())
* notifyAll 会让所有处于等待池中的线程全部进入到锁池去竞争获取锁的机会.
* notyfy 只会随机选取一个处于等待池中的线程进入锁池去竞争获取锁的机会.

### 13.yield
* 概念:当调用Thread.yield()函数时, 会给线程调度器一个当前线程愿意让出CPU使用的暗示,
但是, 线程调度器可能会忽略这个暗示.(决定权在线程调度器)
* yield对锁没有影响. 并不会让当前线程让出已经占用的锁.

### 14.如何中断线程?
* 已经被抛弃的方法: 
> stop() :由一个线程去停止另外一个线程, 暴力, 不安全  
> suspend() 和resume()方法  
* 目前使用的方法: 
> 调用interrupt(), 通知线程应该中断了,类似yield, 也是一种暗示.
> 1.如果线程处于被阻塞状态, 线程会立即退出被阻塞状态, 抛出InterruptedException异常
> 2.如果线程出去正常活动状态, 那么会将该线程的中断标志设置为true.被设置中断的线程将继续正常运行,不受影响.


*** 图片: 线程状态的总结

### 15. JAVA内存模型JMM
JAVA内存模型(Java Memory Model)本身是一种抽象的概念, 并不真实存在, 它描述的是一组规则或者规范,
通过这组规范定义了程序中各个变量(包括实例字段, 静态字段和构成数组对象的元素)的访问方式.

*** 图片: JMM
* JMM中的主内存
> 存储java实例对象
> 包括成员变量, 类信息, 常量, 静态变量等等
> 属于数据共享的区域, 多线程并发操作时会引发线程安全问题

* JMM中的工作内存
> 存储当前方法的所有本地变量信息, 本地变量对其他线程不可见
> 字节码行号指示器, Native方法信息
> 属于线程私有数据区域, 不存在线程安全问题

* JMM与java内存区域划分是不同的概念层次
> JMM 描述的是一组规则, 围绕原子性, 有序性, 可见性展开
> 相似点: 存在共享区域和私有区域

* 主内存与工作内存的数据存储类型以及操作方式归纳
> 方法里的基本数据类型本地变量将直接存储在工作内存的栈帧结构中
> 引用类型的本地变量: 引用存储在工作内存中, 实例存储在主内存中
> 成员变量, static变量, 类信息均会被存储在主内存中
> 主内存共享的方式是线程各拷贝一份数据到工作内存, 操作完成后刷新回主内存

### 15.1 JMM如何解决可见性问题?

*** 图片: 可见性问题
* 把数据从内存加载到缓存, 寄存器. 运算结束后写回主内存.

### 15.2 指令重排序需要满足的条件?
* 单线程环境下不能改变程序运行的结果
* 存在数据依赖关系的不允许重排序
总之: 无法通过happens-before原则推导出来的, 才能进行指令的重排序

### 15.3 什么是happens-before原则?
* 是判断数据是否存在竞争, 线程是否安全的重要依据. 依靠这个原则可以解决两个线程是否存在冲突的重要依据.
* A操作的结果需要对B操作的结果可见, 则A和B之间存在着happens-before关系
> i = 1; //线程A执行   
> j = i; //线程B执行

### 15.4 happens-before的八大原则
1. 程序次序规则: 一个线程内, 按照代码顺序, 书写前面的操作先行发生于书写在后面的操作
2. 锁定规则: 一个unLock操作先行发生于后面对同一个锁的lock操作
3. volatile变量规则: 对一个变量的写操作先行发生于后面对这个变量的读操作
4. 传递规则: 如果操作A先行发生于操作B, 而操作B又先行发生于操作C, 则可以得出操作A先行发生于操作C
5. 线程启动规则: Thread对象的start()方法先行发生于此线程的每一个动作
6. 线程中断规则: 对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生
7. 线程终结规则: 线程中所有的操作都先行发生于线程的终止检测, 我们可以通过Thread.join()方法结束, 
Thread.isAlive()的返回值手段检测到线程已经终止执行
8. 对象终结规则: 一个对象的初始化完成先行发生于他的finalize()方法的开始

### 15.5 happens-before的概念
如果两个操作不满足上述任意一个happens-before规则, 那么这两个操作就没有顺序的保障, JVM可以对这两个
操作进行重排序;
如果操作A happens-before 操作B, 那么操作A在内存上所做的操作对操作B都是可见的.

### 15.6 volatile :JVM提供的轻量级同步机制
* 保证被volatile修饰的共享变量对所有线程总是立即可见的, 但是不能保证线程安全性.
* 禁止指令重排序优化
```java
//synchronized可以保证线程安全和内存可见, 所以这里是不需要volatile的
public class VolatileVisibility{  
    //volatile的任何改变,   
    public static volatile int value = 0;   
    //多条线程如果同时调用increase方法,线程是不安全的. 为了保证线程安全, 需要用syn关键字
    public static void increase(){  
        value++;  
    }
}
```

```java
//这里shutdown是一个原子操作.
public class VolatileSafe{
    volatile boolean shutdown;
    
    public void close(){
        shutdown = true;
    }
    
    public void doWork(){
        while (!shutdown){
            System.out.println("safe....");
        }
    }
}
```

### 15.7 volatile为何能实现立即可见?
* 当写一个volatile变量时, JMM会把该线程对应的工作内存中的共享变量值刷新到主内存中;
* 当读取一个volatile变量时, JMM会把该线程对应的工作内存置为无效.

### 15.8 volatile如何禁止重排优化?
* 内存屏障(Memory Barrier)
1. 保证特性操作的执行顺序
2. 保证某些变量的内存可见性
通过插入内存屏障指令禁止在内存屏障前后的指令执行重排序优化.
强制刷出各种CPU的缓存数据, 因此任何CPU上的线程都能读取到这些数据的最新版本.

### 16 volatile和synchronized的区别?
重点!


### 17. CAS(Compare and Swap)
* 一种高效实现线程安全性的方法
> 支持原子更新操作, 适用于计数器, 序列发生器等场景
> 属于乐观锁机制, 号称lock-free
> CAS操作失败时由开发者决定是继续尝试, 还是执行别的操作

* CAS多数情况下对开发者来说是透明的
> J.U.C的atomic包提供了常用的原子性数据类型以及引用、
数组等相关原子类型和更新操作工具, 是很多线程安全程序的首选   
> Unsafe类虽提供CAS服务, 但因能够操纵任意内存地址读写而有隐患  
> java9以后, 可以使用Variable Handle API来替代Unsafe

* 缺点
> 若循环时间长, 则开销很大
> 只能保证一个共享变量的原子操作
> ABA问题 解决: AtomicStampedReference(自己了解一下)

### 18.java线程池
*** 图片: 线程池分类

### 18.1 Fork/Join框架
> 把大任务分割成若干个小任务并行执行, 最终汇总每个小任务结果后得到大任务结果的框架.
> Work-Stealing算法: 某个线程从其他队列里窃取任务来执行.

### 18.2 为什么要使用线程池?
> 降低资源的消耗
> 提高线程的可管理性

### 18.3 Executor框架
* 是根据一组执行策略调用, 调度、执行和控制异步任务的框架. 目的是提供一种将任务提交与任务如何
运行分离开的机制.
*** 图片: Executor框架
![Image text](https://github.com/Fanxx7201/summary/blob/master/img/Executor%E6%A1%86%E6%9E%B6.png)

### 18.4 JUC的三个Executor接口?
* Executor: 运行新任务的简单接口, 将任务提交和任务执行细节解耦.
> 唯一的方法: execute
* ExecutorService: 具备管理执行器和任务生命周期的方法, 提交任务机制更完善

* ScheduledExecutorService: 支持Future和定期执行任务.

### 18.5 ThreadPoolExecutor的构造函数
* corePoolSize: 核心线程数量  
* maximumPoolSize: 线程不够用时能够创建的最大线程数  
* workQueue: 任务等等队列  
* keepAliveTime: 抢占的顺序不一定, 看运气  
* threadFactory: 创建新线程, Executors.defaultThreadTactory(); 这个
新创建的线程会有相同的优先级, 并且是非守护线程, 也设置了线程的名称.  
* handler: 线程池的饱和策略
> AbortPolicy: 直接抛出异常, 这是默认策略  
> CallerRunsPolicy: 用调用者所在的线程来执行任务  
> DiscardOldestPolicy: 丢弃队列中靠最前的任务, 并执行当前任务  
> DiscardPolicy: 直接丢弃任务  
> 实现RejectedExecutionHandler接口的自定义handler  

### 18.6 线程池的状态
![Image text](https://github.com/Fanxx7201/summary/blob/master/img/%E7%BA%BF%E7%A8%8B%E7%8A%B6%E6%80%81.png)
* RUNNING: 能接受新提交的任务, 并且也能处理阻塞队列中的任务
* SHUTDOWN: 不再接受新提交的任务, 但可以处理存量任务
* STOP: 不再接受新提交的任务, 也不处理存量任务
* TIDYING: 所有的任务都已终止
* TERMINATED: terminated()方法执行完后进去该状态

### 18.7线程池的大小如何选定?
> CPU密集型: 线程数 = 按照核数或者核数 + 1 设定  
> I/O密集型: 线程数 = CPU核数 * (1 + 平均等待时间 / 平均工作时间)  

