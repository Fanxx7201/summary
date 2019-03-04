### 1. java异常

* 异常处理机制主要回答了三个问题

> what: 异常类型回答了什么被抛出
>
> where: 异常堆栈跟踪回答了在哪抛出
>
> why: 异常信息回答了为什么被抛出

### 2.Error和Exception的区别

* Java异常体系



* 从概念角度解析Java的异常处理机制

> Error: 程序无法处理的系统错误, 编译器不做检查
>
> Exception: 程序可以处理的异常, 捕获后可能恢复
>
> 总结: 前者是程序无法处理的错误, 后者是可以处理的异常



* RuntimeException: 不可预知的, 程序应当自行避免
* 非RuntimeException: 可预知的, 从编译器校验的异常



* 从责任角度看:

1. Error 属于JVM需要负担的责任;
2. RuntimeException是程序应该负担的责任;
3. CheckedException可检查异常是java编译器应该负担的责任.

### 3.常见Error以及Exception

RuntimeException

1.NullPointerException - 空指针异常

2.ClassCastException - 类型强制转换异常

3.IllegalArgumentException - 传递非法参数异常

4.IndexOutOfBoundsException - 下标越界异常

非RuntimeException

1.ClassNotFoundException - 找不到指定class的异常

2.IOException - IO操作异常

Error

1.NoClassDefFountError - 找不到class定义的异常

成因:

> 类依赖的class或者jar不存在
>
> 类文件存在, 但是存在不同的域中
>
> 大小写的问题, javac编译的时候是无视大小写的, 很有可能编译出来的class文件就与想要的不一样

2.StackOverFlowError - 深递归导致栈被耗尽而抛出的异常

3.OutOfMemoryError - 内存溢出异常

### 4. JAVA的异常处理机制

* 抛出异常: 创建异常对象, 交由运行时系统处理
* 捕获异常: 寻找合适的异常处理器处理异常, 否则终止运行

### 5. JAVA异常的处理原则

* 具体明确: 抛出的异常应能通过异常类名和message准确说明异常的类型和产生异常的原因
* 提早抛出: 应尽可能早的发现并抛出异常, 便于精确定位问题
* 延迟捕获: 异常的捕获和处理应尽可能延迟, 让掌握更多信息的作用域来处理异常(抛给上层的业务类来处理)

### 6.高效主流的异常处理框架

* 在用户看来, 应用系统发生的所有异常都是应用系统内部的异常

> 设计一个通用的继承自RuntimeException的异常来统一处理
>
> 其余异常都统一转译为上述异常AppException
>
> 在catch之后, 抛出上述异常的子类, 并提供足以定位的信息
>
> 由前端接收AppException做统一处理

### 7.try-catch的性能

* JAVA异常处理消耗性能的地方

> try-catch块影响jvm优化
>
> 异常对象实例需要保存栈快照等信息, 开销较大

